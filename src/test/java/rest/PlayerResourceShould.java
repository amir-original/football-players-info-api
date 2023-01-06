package rest;

import com.google.gson.reflect.TypeToken;
import com.rest.apidemo.helper.HttpRequestHandler;
import com.rest.apidemo.model.Player;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerResourceShould {

    private static final String BASE_URL = "http://localhost:8080/apidemo-1.0-SNAPSHOT/api/";
    private static final int HTTP_200 = 200;
    private static final int HTTP_NOT_AUTHORIZED = 401;
    private HttpRequestHandler client;

    @BeforeEach
    void setUp() {
        client = new HttpRequestHandler();

    }

    @Test
    void send_get_request_to_get_players() {
        HttpResponse<String> response = client
                .target(BASE_URL)
                .path("players")
                .header("Authorization","Zm9vdGJhbGwtc2VydmljZQ==")
                .GET().build();
        List<Player> players = client.getResponse(response, new TypeToken<List<Player>>() {}.getType());
        Optional<Player> levandoski = players.stream()
                .filter(player -> player.getName().equals("levandoski")).findFirst();

        assertThat(response.statusCode()).isEqualTo(HTTP_200);
        assertThat(levandoski.get()).isEqualTo(getLevandoskiInfo());
    }

    @Test
    void send_get_request_to_get_player_info() {
        HttpResponse<String> response = client
                .target(BASE_URL)
                .path("players/levandoski")
                .mediaType(MediaType.APPLICATION_JSON)
                .header("Authorization","Zm9vdGJhbGwtc2VydmljZQ==")
                .GET()
                .build();


        Player player = client.getResponse(response, Player.class);

        assertThat(response.statusCode()).isEqualTo(HTTP_200);
        assertThat(player).isEqualTo(getLevandoskiInfo());
    }

    @Test
    void send_get_request_with_club_to_get_player_info() {
        HttpResponse<String> response = client
                .target(BASE_URL)
                .path("players/c/barcelona")
                .mediaType(MediaType.APPLICATION_JSON)
                .header("Authorization","Zm9vdGJhbGwtc2VydmljZQ==")
                .GET()
                .build();

        List<Player> players = client.getResponse(response, new TypeToken<List<Player>>() {}.getType());

        assertThat(response.statusCode()).isEqualTo(HTTP_200);
        assertThat(players).contains(getLevandoskiInfo());
    }

    @Test
    void send_post_request_for_add_player_info() {
        Player player = new Player("Messi", 35, "Argentina",
                        "Paris Saint-Germain", "forward");
        HttpResponse<String> response = client
                .target(BASE_URL)
                .path("players")
                .mediaType("application/json")
                .header("Authorization","Zm9vdGJhbGwtc2VydmljZQ==")
                .POST(player)
                .build();

        assertThat(response.statusCode()).isEqualTo(201);

    }

    @Test
    void send_put_request_for_update_player_info() {
        Player player = new Player("Kylian Mbappé", 24,
                "France", "Paris Saint-Germain", "forward");

        HttpResponse<String> response = client
                .target(BASE_URL)
                .path("players/3")
                .PUT(player)
                .mediaType(MediaType.APPLICATION_JSON)
                .header("Authorization","Zm9vdGJhbGwtc2VydmljZQ==")
                .build();

        assertThat(response.statusCode()).isEqualTo(HTTP_200);
    }

    @Test
    void send_delete_request_for_remove_player() {
        HttpResponse<String> response = client
                .target(BASE_URL)
                .path("players/7")
                .DELETE()
                .header("Authorization","Zm9vdGJhbGwtc2VydmljZQ==")
                .build();


        assertThat(response.statusCode()).isEqualTo(204);
    }

    @Test
    void get_NotAuthorized_code_when_dont_send_authorization_header() {
        HttpResponse<String> response = client
                .target(BASE_URL)
                .path("players")
                .GET()
                .build();


        assertThat(response.statusCode()).isEqualTo(HTTP_NOT_AUTHORIZED);
    }

    private static Player getLevandoskiInfo() {
        return new Player("levandoski", 34, "poland", "barcelona", "forward");
    }

}
