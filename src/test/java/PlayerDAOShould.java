import com.rest.apidemo.dao.PlayerDAOImpl;
import com.rest.apidemo.model.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerDAOShould {

    private PlayerDAOImpl dao;

    @BeforeEach
    void setUp() {
        dao = new PlayerDAOImpl();
    }

    @Test
    void get_players() {
        List<Player> players = dao.getPlayers();
        Assertions.assertThat(players).isNotNull();
    }

    @Test
    void get_player_with_name() {
        Player levandoski = dao.getPlayer("levandoski");
        Assertions.assertThat(levandoski).isNotNull();
        Assertions.assertThat(levandoski).isEqualTo(getLevandoskiInfo());
    }

    @Test
    void get_players_with_their_club() {
        List<Player> players = dao.getPlayers("barcelona");
        Assertions.assertThat(players).isNotNull();
        Assertions.assertThat(players).contains(getLevandoskiInfo());
    }

    private static Player getLevandoskiInfo() {
        return new Player("levandoski", 34, "poland", "barcelona", "forward");
    }
}
