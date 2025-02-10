package config;

import dto.CarDTO;
import dto.TokenDto;
import dto.UserDTO;
import helper.BaseApi;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class CarController implements BaseApi {
    public TokenDto tokenDto;

    //@BeforeSuite
    RequestSpecification requestSpecBuilder;
    public void login() {
        UserDTO user = UserDTO.builder()
                .username("0bagginsbob@mail.com")
                .password("Qwerty123!")
                .build();
        AuthenticationController authenticationController = new AuthenticationController();
        Response response = authenticationController.requestRegLogin(user, LOGIN);
        if (response.getStatusCode() == 200) {
            tokenDto = response.getBody().as(TokenDto.class);
            requestSpecBuilder = new RequestSpecBuilder()
                    .addHeader("Authorization", tokenDto.getAccessToken())
                    .build();
        } else
            System.out.println("Something went wrong " + response.getStatusCode());

    }

    public Response getUserCars(String token){
        return given()
                //.header("Authorization", token)
                .spec(requestSpecBuilder)
                .when()
                .get(BASE_URL + GET_USER_CARS)
                .thenReturn();
    }

    public Response addUserCar(CarDTO car, String token){
        return given()
                .body(car)
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .post(BASE_URL + ADD_NEW_CAR)
                .thenReturn();
    }
    public Response deleteCar(String serialNumber){
        return given()
                .spec(requestSpecBuilder)
                .when()
                .delete(BASE_URL+ADD_NEW_CAR+"/"+serialNumber)
                .thenReturn();
    }
}
