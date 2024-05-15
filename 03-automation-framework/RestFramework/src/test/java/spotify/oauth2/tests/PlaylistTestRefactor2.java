package spotify.oauth2.tests;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import spotify.oauth2.tests.pojo.Error;
import spotify.oauth2.tests.pojo.Playlist;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTestRefactor2 {

    ResponseSpecification responseSpecification;
    String accessToken="BQAsAyXeUd655kyZylkBEjuzfAATL-YPZ0h00vWm9VbpENqKTtTZjwRHsYrQn9j-HyiC3q-RYM5uo6FUy6XPHDjE0F2-5pB9MUGymafNLgx82R_KhhluWRWpuKjmHQFHpLyFTbCwZxG0i_DzC9Kr3OcsFr9EJcsJE5FcZcOdsheQAPEnJoHhAZSqQeasr_mb0OD7-HZAb4RUkpKKp6qPyMFRVvBh2GgkoWqTvSCREPQXrchgfvxMYIOk0rpkYGZIHbQ24TwIhZEMJY0P";
    @BeforeMethod
    public void setup(){
//        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
//        requestSpecBuilder.setBaseUri("https://api.spotify.com/").addHeader("Authorization","Bearer "+accessToken)
//                .setBasePath("/v1")
//                .setContentType(ContentType.JSON).
//                log(LogDetail.ALL);
//        RestAssured.requestSpecification=requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder();
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecification=responseSpecBuilder.build();

    }


    @Test
    public void test_createPlaylist(){
        Playlist requestPlaylist=new Playlist();
        requestPlaylist.setName("Mayank New Playlist");
        requestPlaylist.setDescription("This is refactoring");
        requestPlaylist.setPublic(false);

        Playlist response=with().body(requestPlaylist).when().post("/users/31dvh34wk26pkyib5buretxczowy/playlists").then().spec(responseSpecification).assertThat().statusCode(201).
                extract().as(Playlist.class);
        assertThat(response.getName(),equalTo("Mayank New Playlist"));
        assertThat(response.getDescription(),equalTo("This is refactoring"));
        assertThat(response .getPublic(),equalTo(false));
    }


    @Test
    public void test_getAPlaylist(){
       Playlist res= given().when().get("/playlists/623r61fiWD8CkAkDrvnNVg").then().spec(responseSpecification).assertThat().statusCode(200).extract().as(Playlist.class);
       assertThat(res.getName(),equalTo("Updated Playlist Name2"));
    }


    @Test
    public void updateAPlaylist(){
        Playlist updatePlaylist=new Playlist();
        updatePlaylist.setName("Mayank New Playlist");
        updatePlaylist.setDescription("This is refactoring");
        updatePlaylist.setPublic(false);

        given().body(updatePlaylist).when().put("playlists/623r61fiWD8CkAkDrvnNVg").then().assertThat().statusCode(200);

    }


    @Test
    public void test_createPlaylistWithoutName(){
        Playlist createPlaylistWithoutName=new Playlist();
        createPlaylistWithoutName.setDescription("This is refactoring");
        createPlaylistWithoutName.setPublic(false);

        Error errors=given().body(createPlaylistWithoutName).when().post("/users/31dvh34wk26pkyib5buretxczowy/playlists").then().assertThat().statusCode(400).extract().as(Error.class);
        assertThat(errors.getError().getMessage(),equalTo("Missing required field: name"));
        assertThat(errors.getError().getStatus(),equalTo(400));
    }

    @Test
    public void test_createPlaylistWithWrongToken(){
        Playlist updatePlaylist=new Playlist();
        updatePlaylist.setName("Mayank New Playlist");
        updatePlaylist.setDescription("This is refactoring");
        updatePlaylist.setPublic(false);

        Error errors=given().
                baseUri("https://api.spotify.com/").
                basePath("/v1").
                header("Authorization","Bearer 123456").body(updatePlaylist).
                when().
                post("/users/31dvh34wk26pkyib5buretxczowy/playlists").then().assertThat().statusCode(401).extract().as(Error.class);

        assertThat(errors.getError().getStatus(),equalTo(401));
        assertThat(errors.getError().getMessage(),equalTo("Invalid access token"));
    }
}