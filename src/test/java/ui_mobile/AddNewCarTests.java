package ui_mobile;

import config.AppiumConfig;
import dto.CarDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static helper.RandomUtils.*;

public class AddNewCarTests extends AppiumConfig {

    SearchScreen searchScreen;
    LoginScreen loginScreen;
    MyCarsScreen myCarsScreen;

    @BeforeMethod
    public void login(){
        new SplashScreen(driver);
        searchScreen = new SearchScreen(driver);
        searchScreen.goToLoginScreen();
        loginScreen = new LoginScreen(driver);
        loginScreen.login(
                UserDTO.builder()
                        .username("0bagginsbob@mail.com")
                        .password("Qwerty123!")
                        .build()
        );
        searchScreen.goToMyCarsScreen();
    }

    @Test
    public void addNewCarPositiveTest(){
        CarDTO car = CarDTO.builder()
                .serialNumber("num-"+generatePhone(6))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("Hi")
                .fuel("Gas")
                .year("1975")
                .seats(4)
                .about("best of the best")
                .build();
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToAddNewCarScreen();
        new AddNewCarScreen(driver).addNewCar(car);
        Assert.assertTrue(myCarsScreen.validateMessageSuccess("Car was added!"));
    }

    @Test
    public void addNewCarPositiveTestValidateCarList(){
        CarDTO car = CarDTO.builder()
                .serialNumber("num-"+generatePhone(6))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("Hi")
                .fuel("Gas")
                .year("1975")
                .seats(4)
                .about("best of the best")
                .build();
        myCarsScreen = new MyCarsScreen(driver);
        //myCarsScreen.goToAddNewCarScreen();
        //new AddNewCarScreen(driver).addNewCar(car);
        myCarsScreen.scrollToLastElementAuto();
    }
}
