package spotify.oauth2.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static spotify.oauth2.api.specBuilder.*;
import spotify.oauth2.tests.pojo.Error;
import spotify.oauth2.tests.pojo.Playlist;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTestRefactor3 {
    @Test
    public void test_createPlaylist(){
        Playlist requestPlaylist=new Playlist();
        requestPlaylist.setName("Mayank New Playlist");
        requestPlaylist.setDescription("This is refactoring");
        requestPlaylist.setPublic(false);

        Playlist response=with().spec(getRequestSpecification()).body(requestPlaylist).when().post("/users/31dvh34wk26pkyib5buretxczowy/playlists").then().spec(getResponseSpecification()).assertThat().statusCode(201).
                extract().as(Playlist.class);
        assertThat(response.getName(),equalTo("Mayank New Playlist"));
        assertThat(response.getDescription(),equalTo("This is refactoring"));
        assertThat(response .getPublic(),equalTo(false));
    }


    @Test
    public void test_getAPlaylist(){
        Playlist res= given(getRequestSpecification()).when().get("/playlists/623r61fiWD8CkAkDrvnNVg").then().spec(getResponseSpecification()).assertThat().statusCode(200).extract().as(Playlist.class);
        assertThat(res.getName(),equalTo("Mayank New Playlist"));
    }


    @Test
    public void updateAPlaylist(){
        Playlist updatePlaylist=new Playlist();
        updatePlaylist.setName("Mayank New Playlist");
        updatePlaylist.setDescription("This is refactoring");
        updatePlaylist.setPublic(false);

        given(getRequestSpecification()).body(updatePlaylist).when().put("playlists/623r61fiWD8CkAkDrvnNVg").then().assertThat().statusCode(200);

    }


    @Test
    public void test_createPlaylistWithoutName(){
        Playlist createPlaylistWithoutName=new Playlist();
        createPlaylistWithoutName.setDescription("This is refactoring");
        createPlaylistWithoutName.setPublic(false);

        Error errors=given(getRequestSpecification()).body(createPlaylistWithoutName).when().post("/users/31dvh34wk26pkyib5buretxczowy/playlists").then().spec(getResponseSpecification()).assertThat().statusCode(400).extract().as(Error.class);
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
                post("/users/31dvh34wk26pkyib5buretxczowy/playlists").then().spec(getResponseSpecification()).assertThat().statusCode(401).extract().as(Error.class);

        assertThat(errors.getError().getStatus(),equalTo(401));
        assertThat(errors.getError().getMessage(),equalTo("Invalid access token"));
    }
}


