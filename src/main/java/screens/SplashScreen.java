package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void goToSearchScreen(){
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.id("com.telran.ilcarro:id/findTitle")));
        }catch (TimeoutException e){
            e.printStackTrace();
            System.out.println("element findTitle not find");
        }
    }

}
