package api_rest;

import config.AuthenticationController;
import dto.ErrorMessageDto;
import dto.UserDTO;
import helper.BaseApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static helper.RandomUtils.*;

public class ApiRegistrationTests extends AuthenticationController implements BaseApi {

    @Test
    public void registrationPositiveTest(){
        UserDTO user = UserDTO.builder()
                .username(generateEmail(10))
                .password("Qwerty123!")
                .firstName(generateString(5))
                .lastName(generateString(10))
                .build();
        Response response = requestRegLogin(user, REGISTRATION);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void registrationNegativeTest(){
        UserDTO user = UserDTO.builder()
                .username(generateString(10))
                .password("Qwerty123!")
                .firstName(generateString(5))
                .lastName(generateString(10))
                .build();
        Response response = requestRegLogin(user, REGISTRATION);
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        System.out.println(errorMessageDto.toString());
        Assert.assertTrue(errorMessageDto.getError().equals("Bad Request"));
    }
}
