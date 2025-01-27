package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import static helper.RandomUtils.*;

public class RegistrationTests extends AppiumConfig {

    private String userNameRegUser = "bm8hreura5@mail.com";

    @BeforeMethod
    public void beforeTest(){
        new SplashScreen(driver);
        new SearchScreen(driver).goToRegistrationScreen();
    }

    @Test
    public void registrationPositiveTest(){
        UserDTO user = UserDTO.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(generateEmail(10))
                .password("Qwerty123!")
                .build();
        System.out.println("--> "+user.getUsername());
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegistrationForm(user);
        Assert.assertTrue(new SearchScreen(driver)
                .validateMessageSuccess("Registration success!"));
    }

    @Test
    public void registrationNegativeTest_duplicateUser(){
        UserDTO user = UserDTO.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(userNameRegUser)
                .password("Qwerty123!")
                .build();
        System.out.println("--> "+user.getUsername());
        RegistrationScreen registrationScreen = new RegistrationScreen(driver);
        registrationScreen.typeRegistrationForm(user);
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("User already exists"));
    }


}
