package ui_mobile;

import config.AppiumConfig;
import config.CarController;
import dto.CarDTO;
import dto.CarsDto;
import dto.UserDTO;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import java.util.Arrays;

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
        myCarsScreen.goToAddNewCarScreen();
        new AddNewCarScreen(driver).addNewCar(car);
        Assert.assertEquals(myCarsScreen.scrollToLastElementAuto(), car.getSerialNumber());
    }

    @Test
    public void addNewCarPositiveTestValidateRestApi(){
        CarDTO car = CarDTO.builder()
                .serialNumber("num-"+generatePhone(10))
                .manufacture("Volvo")
                .model("100")
                .city("Haifa")
                .pricePerDay(1000.10)
                .carClass("C")
                .fuel("Gas")
                .year("1999")
                .seats(5)
                .about("best of the best car volvo")
                .build();
        myCarsScreen = new MyCarsScreen(driver);
        myCarsScreen.goToAddNewCarScreen();
        new AddNewCarScreen(driver).addNewCar(car);
        CarController carController = new CarController();
        carController.login();
        Response response = carController.getUserCars(carController.tokenDto.getAccessToken());
        CarDTO[] arrayCar = response.body().as(CarsDto.class).getCars();
        int index=0;
        for (int i=0; i<arrayCar.length;i++) {
            //System.out.println(carApi.toString());
            if(arrayCar[i].equals(car)){
                System.out.println("car api === car");
                index = i;
                break;
            }
        }
        Assert.assertEquals(car, arrayCar[index]);
    }
}
