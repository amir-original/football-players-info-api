import com.rest.apidemo.filter.Authenticator;
import com.rest.apidemo.model.Player;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpClient;
import java.util.List;

public class Register {

    @Test
    void name() throws UnsupportedEncodingException {
        String token = "football-service";
        String s = DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
        System.out.println(s);
    }
}
