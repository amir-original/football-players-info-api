import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rest.apidemo.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;
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

        List<Player> players = getResponse(request, new TypeToken<List<Player>>(){}.getType());
        Optional<Player> levandoski = players.stream()
                .filter(player -> player.getName().equals("levandoski")).findFirst();

        assertThat(getHttpResponse(request).statusCode()).isEqualTo(200);
        assertThat(levandoski.get()).isEqualTo(getLevandoskiInfo());
    }

    @Test
    void send_get_request_to_get_player_info() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "players/levandoski"))
                .header("content-type", "application/json")
                .GET()
                .build();


        Player player = getResponse(request,Player.class);

        assertThat(getHttpResponse(request).statusCode()).isEqualTo(200);
        assertThat(player).isEqualTo(getLevandoskiInfo());
    }

    @Test
    void send_get_request_with_club_to_get_player_info() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        HttpRequest request = requestBuilder
                .uri(new URI(BASE_URL + "players/c/barcelona"))
                .header("content-type", "application/json")
                .GET()
                .build();

        List<Player> players = getResponse(request,new TypeToken<List<Player>>(){}.getType());

        assertThat(getHttpResponse(request).statusCode()).isEqualTo(200);
        assertThat(players).contains(getLevandoskiInfo());
    }

    @Test
    void send_post_request_for_add_player_info() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest.Builder reqBuilder = HttpRequest.newBuilder();
        Player player = new Player("Messi",35,"Argentina"," Paris Saint-Germain","forward");
        String saka = gson.toJson(player);
        HttpRequest request = reqBuilder
                .uri(new URI(BASE_URL+"players"))
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(saka)).build();

        assertThat(getHttpResponse(request).statusCode()).isEqualTo(201);

    }

    private HttpResponse<String> getHttpResponse(HttpRequest request) throws IOException, InterruptedException {
        return client.send(request, BodyHandlers.ofString());
    }

    private static Player getLevandoskiInfo() {
        return new Player("levandoski", 34, "poland", "barcelona", "forward");
    }

    private <T> T getResponse(HttpRequest request, Type type) throws IOException, InterruptedException {
        return gson.fromJson(getHttpResponse(request).body(),type);
    }
}
