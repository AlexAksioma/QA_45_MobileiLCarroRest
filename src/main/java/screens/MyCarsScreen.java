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

    @FindBy(xpath = "//*[@resource-id='com.telran.ilcarro:id/LinearLayout']")
    AndroidElement firstCarList;

    @FindBy(id = "android:id/button1")
    AndroidElement buttonOk;

    public void goToAddNewCarScreen() {
        clickWait(btnAddCar, 5);
    }

    public boolean validateMessageSuccess(String message) {
        return textInElementPresent(popUpMessageSuccess, message, 5);
    }

    public String scrollToLastElementAuto() {
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();
        System.out.println(height + "X" + width);
        TouchAction<?> touchAction = new TouchAction<>(driver);
        System.out.println("--> " + carList.get(carList.size() - 1).getText());
        System.out.println("--> " + carList.size());
        String second = "";
        boolean flag = true;
        while (flag) {
            String first = carList.get(carList.size() - 1).getText();
            System.out.println("first -->" + first);
            touchAction.longPress(PointOption.point(5, height / 6 * 5))
                    .moveTo(PointOption.point(5, height / 6))
                    .release().perform();
            second = carList.get(carList.size() - 1).getText();
            System.out.println("second-->" + second);
            flag = !first.equals(second);
        }
        return second;
    }


    public void deleteFirstCar() {
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();
        System.out.println(height + "X" + width);
        int yElement = firstCarList.getLocation().getY();
        System.out.println(firstCarList.getLocation().getY());
        int heightElement = firstCarList.getSize().getHeight();
        System.out.println(firstCarList.getSize().getHeight());

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(width/10*9,yElement+heightElement/2))
                .moveTo(PointOption.point(width/10, yElement+heightElement/2))
                .release().perform();
        clickWait(buttonOk, 3);
    }
}
