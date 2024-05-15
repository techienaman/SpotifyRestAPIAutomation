package spotify.oauth2.api;

import io.restassured.response.Response;
import spotify.oauth2.tests.pojo.Playlist;

import static io.restassured.RestAssured.*;

public class RestResource {


    public Response post(Playlist playlist,String parameters){
         Response res=given(specBuilder.getRequestSpecification()).body(playlist).when().post(parameters).then().extract().response();
         return res;
    }
}
