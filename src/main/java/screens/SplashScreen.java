package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.FindBy;

public class SplashScreen extends BaseScreen{
    public SplashScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/versionText")
    AndroidElement versionApp;

    @AndroidFindBy(id = "com.telran.ilcarro:id/versionText")
    AndroidElement versionAppAnd;

    public boolean validateVersion(String version){
        return textInElementPresent(versionApp, version, 5);
    }

}
