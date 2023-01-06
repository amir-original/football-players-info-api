import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rest.apidemo.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import static java.net.http.HttpResponse.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerResourceShould {

    private static final String BASE_URL = "http://localhost:8080/apidemo-1.0-SNAPSHOT/api/";
    private Gson gson;
    private HttpClient client;

    @BeforeEach
    void setUp() {
        gson = new Gson();
        client = HttpClient.newHttpClient();
    }

    @Test
    void send_get_request_to_get_players() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "players"))
                .header("content-type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        TypeToken<List<Player>> listTypeToken = new TypeToken<>(){};
        List<Player> players = gson.fromJson(response.body(), listTypeToken.getType());
        Optional<Player> levandoski = players.stream().filter(player -> player.getName().equals("levandoski")).findFirst();
        Player exp = getLevandoskiInfo();

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(levandoski.get()).isEqualTo(exp);
    }

    @Test
    void send_get_request_to_get_player_info() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "players/levandoski"))
                .header("content-type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());


        Player player = gson.fromJson(response.body(), Player.class);

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(player).isEqualTo(getLevandoskiInfo());
    }

    @Test
    void send_get_request_with_club_to_get_player_info() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "players/c/barcelona"))
                .header("content-type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        List<Player> players = gson.fromJson(response.body(), new TypeToken<List<Player>>(){}.getType());

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(players).contains(getLevandoskiInfo());
    }

    private static Player getLevandoskiInfo() {
        return new Player("levandoski", 34, "poland", "barcelona", "forward");
    }
}
