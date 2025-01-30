package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyCarsScreen extends BaseScreen {
    public MyCarsScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/addCarBtn")
    AndroidElement btnAddCar;

    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/myCarsRv']//*[@resource-id='com.telran.ilcarro:id/myCarSerialTxt']")
    List<AndroidElement> carList;



    public void goToAddNewCarScreen(){
        clickWait(btnAddCar, 5);
    }

    public boolean validateMessageSuccess(String message){
        return textInElementPresent(popUpMessageSuccess, message, 5);
    }

    public void scrollToLastElementAuto(){
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();
        System.out.println(height + "X" + width);
        TouchAction<?> touchAction = new TouchAction<>(driver);

        System.out.println("--> "+ carList.get(carList.size()-1).getText());
        System.out.println("--> "+ carList.size());

//        touchAction.longPress(PointOption.point(5, height / 6 * 5))
//                .moveTo(PointOption.point(5, height / 6))
//                .release().perform();
    }
}
