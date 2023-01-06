import com.rest.apidemo.dao.PlayerDAOImpl;
import com.rest.apidemo.model.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class PlayerDAOShould {

    private PlayerDAOImpl dao;

    @BeforeEach
    void setUp() {
        dao = new PlayerDAOImpl();
    }

    @Test
    void get_players() {
        List<Player> players = dao.getPlayers();
        assertThat(players).isNotNull();
    }

    @Test
    void get_player_with_name() {
        Player levandoski = dao.getPlayer("levandoski");
        assertThat(levandoski).isNotNull();
        assertThat(levandoski).isEqualTo(getLevandoskiInfo());
    }

    @Test
    void get_players_with_their_club() {
        List<Player> players = dao.getPlayers("barcelona");
        assertThat(players).isNotNull();
        assertThat(players).contains(getLevandoskiInfo());
    }

    @Test
    void add_player() {
        Player saka = new Player("saka", 21, "england", "arsenal", "forward");
        assertThatNoException().isThrownBy(()-> dao.addPlayer(saka));
    }

    @Test
    void update_player() {
        Player player = new Player("Mbappé", 24, "France", "Paris Saint-Germain", "forward");
        assertThatNoException().isThrownBy(()-> dao.updatePlayer(3,player));
        Player mbappé = dao.getPlayer("Mbappé");
        assertThat(mbappé).isNotNull();
    }

    @Test
    void delete_player() {
        assertThatNoException().isThrownBy(()-> dao.deletePlayer(5));

        Player player = dao.getPlayer(5);

        assertThat(player).isNull();

    }

    private static Player getLevandoskiInfo() {
        return new Player("levandoski", 34, "poland", "barcelona", "forward");
    }
}
