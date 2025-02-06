package api_rest;

import config.AuthenticationController;
import dto.ErrorMessageDto;
import dto.UserDTO;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiLoginTests extends AuthenticationController implements BaseApi {

    @Test
    public void loginPositiveTest() {
        UserDTO user = UserDTO.builder()
                .username("0bagginsbob@mail.com")
                .password("Qwerty123!")
                .build();
        Response response = requestRegLogin(user, LOGIN);
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void loginNegativeTest_wrongEmail() {
        UserDTO user = UserDTO.builder()
                .username("0bagginsbob@mail")
                .password("Qwerty123!")
                .build();
        Response response = requestRegLogin(user, LOGIN);
        System.out.println(response.getStatusCode());
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        System.out.println(errorMessageDto.toString());
        Assert.assertTrue(errorMessageDto.getMessage().toString().equals("Login or Password incorrect"));
    }


}
