package api_rest;

import config.CarController;
import dto.TokenDto;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class ApiGetUserCarsTests extends CarController implements BaseApi {

    @Test
    public void getUserCarsPositiveTest(){
        login();
        Response response = getUserCars(tokenDto.getAccessToken());
        System.out.println("--> "+response.getStatusCode());
    }
}
