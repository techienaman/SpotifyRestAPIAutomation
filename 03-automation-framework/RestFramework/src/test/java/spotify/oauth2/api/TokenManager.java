package spotify.oauth2.api;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class TokenManager {
    private static Instant expiry_Time;
    private  static int expires_in;
    static String token;

    public static String getToken(){
        try{
            if(token==null || Instant.now().isAfter(expiry_Time)){
                System.out.println("Creating new Access token");
                Response res=renewToken();
                expires_in=res.path("expires_in");
                expiry_Time=Instant.now().plusSeconds(expires_in);
                token= res.path("access_token");
            }
            else{
                System.out.println("Using the same access token");
                return token;
            }

        }
        catch (Exception e){
            throw new RuntimeException("Abort....");
        }

        return token;
    }



    public static Response renewToken(){
        HashMap<String,String> formParams=new HashMap<>();
        formParams.put("grant_type","refresh_token");
        formParams.put("refresh_token","AQDpwLYBZBwh9vEbSly4muSmBUBs89L2p-NWqwUFMuUNSkqPTKDxOCfXl6JmzTuTObCuQ475hAWPmfb3AAkIlNqscDF-8JFmlo8ZYlNSSep97UaLGMd9y_jBRn6JTqWSVLQ");
        formParams.put("client_id","9a894cb9ae164d22b168c1cf568ea813");
        formParams.put("client_secret","0e39222d23df414f876c525a10f6d02e");
        Response res=given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParams(formParams).
                when().post("/api/token").then().extract().response();
        return res;
    }
}