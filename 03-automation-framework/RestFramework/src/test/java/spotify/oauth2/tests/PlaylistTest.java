package spotify.oauth2.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class PlaylistTest {

    ResponseSpecification responseSpecification;
    String accessToken="BQCSCT-AfdFBKVVy0h_JNgRPmN6eJsrn1NOJFxmCQA5585--R52eyyuoOjXcRAR5jS9ITxpmH595Fzu98Jb41zd8TVcvR8hoHFyQPot0XKtcA3wtSz_ZwJ-RmXvSb2iRiqVRkm9Gtsivr4oN2rQFjAMF7DaN624glN6AnPpSU7NitPoJByYbqwagbDzDB-zvkS1JxrKJzS9huZWmsHSR3bz00nWPDsTj_-71fwU_Jc4mSHwDj9f7_wMM3X9z25Ns3eGXsin6w6v7mpxh";

    @BeforeMethod
            public void setup(){
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.spotify.com/").addHeader("Authorization","Bearer "+accessToken)
                        .setBasePath("/v1")
                .setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder();
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecification=responseSpecBuilder.build();

    }


    @Test
    public void test_createPlaylist(){
        String payload="{\n" +
                "    \"name\": \"Mayank third Playlist\",\n" +
                "    \"description\": \"Playlist created just for demo purpose\",\n" +
                "    \"public\": false\n" +
                "}";
        Response res=with().body(payload).when().post("/users/31dvh34wk26pkyib5buretxczowy/playlists").then().spec(responseSpecification).assertThat().statusCode(201).body("description",equalTo("Playlist created just for demo purpose"),
                        "public",equalTo(false)).
                extract().response();

        JsonPath jsonPath=new JsonPath(res.asString());
        assertThat(jsonPath.getString("name"),equalTo("Mayank third Playlist"));
    }


    @Test
    public void test_getAPlaylist(){
        given().when().get("/playlists/623r61fiWD8CkAkDrvnNVg").then().spec(responseSpecification).assertThat().statusCode(200).body("name",equalTo("Mayank FirstPlaylist"));
    }


    @Test
    public void updateAPlaylist(){
        String payload="{\n" +
                "    \"name\": \"Updated Playlist Name2\",\n" +
                "    \"description\": \"Updated playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given().body(payload).when().put("playlists/623r61fiWD8CkAkDrvnNVg").then().assertThat().statusCode(200);

    }


    @Test
    public void test_createPlaylistWithoutName(){
        String payload="{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"Playlist created just for demo purpose\",\n" +
                "    \"public\": false\n" +
                "}";

        given().body(payload).when().post("/users/31dvh34wk26pkyib5buretxczowy/playlists").then().assertThat().statusCode(400).
                body("error.status",equalTo(400),
                        "error.message",equalTo("Missing required field: name"));
    }

    @Test
    public void test_createPlaylistWithWrongToken(){
        String payload="{\n" +
                "    \"name\": \"Mayank FirstPlaylist\",\n" +
                "    \"description\": \"Playlist created just for demo purpose\",\n" +
                "    \"public\": false\n" +
                "}";
        given().
                baseUri("https://api.spotify.com/").
                basePath("/v1").
                header("Authorization","Bearer 123456").
        when().
                post("/users/31dvh34wk26pkyib5buretxczowy/playlists").then().assertThat().statusCode(401).body("error.message",equalTo("Invalid access token"));
    }
}
