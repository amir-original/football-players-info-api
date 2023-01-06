package rest;

import com.google.gson.reflect.TypeToken;
import com.rest.apidemo.helper.HttpRequestHandler;
import com.rest.apidemo.model.Player;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;
import java.util.List;

public class HttpRequestHandlerTest {
    @Test
    void test() {
        HttpRequestHandler requestHandler = new HttpRequestHandler();
        HttpResponse<String> response = requestHandler
                .target("http://localhost:8080/apidemo-1.0-SNAPSHOT/api/")
                .path("players")
                .GET()
                .build();
        TypeToken<List<Player>> typeToken = new TypeToken<>() {};
        List<Player> players = requestHandler.getResponse(response, typeToken.getType());
        players.forEach(System.out::println);
    }
}
