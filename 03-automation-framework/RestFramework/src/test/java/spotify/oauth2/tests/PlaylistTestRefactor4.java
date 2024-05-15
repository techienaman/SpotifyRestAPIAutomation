package spotify.oauth2.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import spotify.oauth2.tests.pojo.Error;
import spotify.oauth2.tests.pojo.Playlist;

import static io.restassured.RestAssured.*;
import static spotify.oauth2.api.applicationApi.Playlistapi.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static spotify.oauth2.api.specBuilder.getRequestSpecification;
import static spotify.oauth2.api.specBuilder.getResponseSpecification;

public class PlaylistTestRefactor4 {
    @Test
    public void test_createPlaylist(){
        Playlist requestPlaylist=new Playlist();
        requestPlaylist.setName("Mayank New Playlist1");
        requestPlaylist.setDescription("This is refactoring");
        requestPlaylist.setPublic(false);
        Response response=post(requestPlaylist);

        assertThat(response.statusCode(),equalTo(201));
        Playlist response1=response.as(Playlist.class);

        assertThat(response1.getName(),equalTo("Mayank New Playlist1"));
        assertThat(response1.getDescription(),equalTo("This is refactoring"));
        assertThat(response1.getPublic(),equalTo(false));
    }


    @Test
    public void test_getAPlaylist(){
        Playlist playlist=gets("623r61fiWD8CkAkDrvnNVg").as(Playlist.class);
        assertThat(playlist.getName(),equalTo("Mayank New Playlist"));
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
        Response res=post(createPlaylistWithoutName);
        assertThat(res.statusCode(),equalTo(400));

        Error errors=post(createPlaylistWithoutName).as(Error.class);
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
