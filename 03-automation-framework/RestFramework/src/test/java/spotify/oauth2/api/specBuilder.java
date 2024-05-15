package spotify.oauth2.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;

import static spotify.oauth2.api.TokenManager.getToken;

public class specBuilder {



    public static RequestSpecification getRequestSpecification(){
        //String accessToken="BQAlhuyn1NfVjJ9DMrmOcc2qB3LVffl6rxnrwynpggBbsiqXhmHrWqk-7hLvXO0w6U7u4gQE5b6ioHX3G8H9ssTYm4btVsSHyBc6Z7y6DZe6kw3wmIavl5rxPldHrhUtN_MY6zdHrh1nTC3hJFrrR4NXtPbNBjJkPygd_oSLEglldLNu4KEoXWLF65iEvvgTiPsjwiyLbi58N4FExo94YuRYHikoyGvI5o2jMZLAKfAYWHoOakMI5ACHlQ4EJ2wofxJB0oiMe14jQ1jh";
            RequestSpecBuilder requestSpecBuilder= new RequestSpecBuilder();
            requestSpecBuilder.setBaseUri("https://api.spotify.com/").addHeader("Authorization","Bearer "+getToken())
                    .setBasePath("/v1")
                    .setContentType(ContentType.JSON).
                    log(LogDetail.ALL);
            return  requestSpecBuilder.build();



        }
        public  static ResponseSpecification getResponseSpecification(){
                ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder();
                responseSpecBuilder.expectContentType(ContentType.JSON);
          return responseSpecBuilder.build();

        }


    }

