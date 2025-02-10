package api_rest;

import config.CarController;
import dto.CarDTO;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static helper.RandomUtils.generatePhone;

public class ApiDeleteCarTests extends CarController implements BaseApi {

    @Test
    public void deleteCarPositiveTest(){
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
        login();
        Response response = addUserCar(car, tokenDto.getAccessToken());
        if(response.getStatusCode() == 200) {
            Response response1 = deleteCar(car.getSerialNumber());
            System.out.println("================"+response1.getStatusCode());
            Assert.assertEquals(response1.getStatusCode(), 200);
        }else
            Assert.fail("failed test");
    }
}
