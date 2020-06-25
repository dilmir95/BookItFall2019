package com.automation.units;

import com.automation.pojos.Room;
import com.automation.utils.APIUtils;
import com.automation.utils.Environment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ApiUtilsTest {
    static {
        baseURI = Environment.BASE_URI;
    }
    @Test
    public void getTokenTest(){
        String token  = APIUtils.getToken("teacher");
        System.out.println(token);
        Assert.assertNotNull(token);
    }

    @Test
    public void getAllRooms(){
        Response response = given().accept(ContentType.JSON).auth().oauth2(APIUtils.getToken("teacher")).
                when().get("/api/rooms");

        List<Room> rooms = response.jsonPath().getList("",Room.class);

        System.out.println(rooms);





    }

    @Test
    public void getMe(){
        List<Map<String,String>> dataTable = new ArrayList<>();
        Map<String,String> login = new HashMap<>();
        login.put("email","lesly2020@email.com");
        login.put("password","1111");
        dataTable.add(login);

        Response response = given().auth().oauth2(APIUtils.getToken(dataTable.get(0).get("email"),dataTable.get(0).get("password"))).
                contentType(ContentType.JSON).
                when().get("/api/users/me").prettyPeek();

        int id = response.jsonPath().getInt("id");

        System.out.println(id);



    }



}
