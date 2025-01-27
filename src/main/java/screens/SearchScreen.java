package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class SearchScreen extends BaseScreen{
    public SearchScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    AndroidElement btnMoreOptions;

    @FindBy(xpath = "//*[@text='Registration']")
    AndroidElement btnRegistration;
    @FindBy(xpath = "//*[@text='Login']")
    AndroidElement btnLogin;
    @FindBy(xpath = "//*[@text='My Cars']")
    AndroidElement btnMyCars;

    public void goToRegistrationScreen(){
        //btnMoreOptions.click();
        //btnRegistration.click();
        clickWait(btnMoreOptions,5);
        clickWait(btnRegistration, 5);
    }

    public void goToLoginScreen() {
        clickWait(btnMoreOptions, 5);
        clickWait(btnLogin, 5);
    }
    public void goToMyCarsScreen() {
        clickWait(btnMoreOptions, 5);
        clickWait(btnMyCars, 5);
    }

    public boolean validateMessageSuccess(String message){
        return textInElementPresent(popUpMessageSuccess, message, 5);
    }
}
