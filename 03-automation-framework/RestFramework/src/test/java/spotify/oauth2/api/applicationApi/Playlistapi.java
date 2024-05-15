package spotify.oauth2.api.applicationApi;

import io.restassured.response.Response;
import spotify.oauth2.tests.pojo.Playlist;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static spotify.oauth2.api.specBuilder.getRequestSpecification;

public class Playlistapi {


    public static  Response post(Playlist requestPlaylist){
       return with().spec(getRequestSpecification()).body(requestPlaylist).when().post("/users/31dvh34wk26pkyib5buretxczowy/playlists").then().
                extract().response();
    }

    public static Response gets(String playlist_id){
        return given(getRequestSpecification()).when().get("/playlists/"+playlist_id).then().extract().response();
    }


    public static void updatePlaylist(Playlist playlist,String playlist_id){
        given(getRequestSpecification()).body(playlist).when().put("playlists/"+playlist_id).then();

    }
}
