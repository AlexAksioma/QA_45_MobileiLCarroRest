package ui_mobile;

import config.AppiumConfig;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.SearchScreen;
import screens.SplashScreen;

public class RegistrationTests extends AppiumConfig {

    @BeforeMethod
    public void beforeTest(){
        new SplashScreen(driver);
        new SearchScreen(driver).goToRegistrationScreen();
    }

    @Test
    public void registrationPositiveTest(){

    }
}
