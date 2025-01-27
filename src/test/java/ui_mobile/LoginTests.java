package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    LoginScreen loginScreen;

    @BeforeMethod
    public void beforeLogin(){
        new SplashScreen(driver);
        new SearchScreen(driver).goToLoginScreen();
    }

    @Test
    public void loginPositiveTest(){
        loginScreen = new LoginScreen(driver);
        loginScreen.login(
                UserDTO.builder()
                .username("0bagginsbob@mail.com")
                .password("Qwerty123!")
                .build()
        );
        Assert.assertTrue(new SearchScreen(driver)
                .validateMessageSuccess("Login success"));
    }

    @Test
    public void loginNegativeTest_emailWithSpaces(){
        loginScreen = new LoginScreen(driver);
        loginScreen.login(
                UserDTO.builder()
                .username(" 0bagginsbob@mail.com ")
                .password("Qwerty123!")
                .build()
        );
        Assert.assertTrue(new SearchScreen(driver)
                .validateMessageSuccess("Login success"));
    }

    @Test
    public void loginNegativeTest_emailWOAt(){
        loginScreen = new LoginScreen(driver);
        loginScreen.login(
                UserDTO.builder()
                .username("0bagginsbobmail.com")
                .password("Qwerty123!")
                .build()
        );
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("Login or Password incorrect"));
    }
}
