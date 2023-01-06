import com.rest.apidemo.dao.MySQLDbConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class MySQLDbConnectionShould {

    @Test
    void connect_to_database_correctly() {

        try {
            MySQLDbConnection mysql = new MySQLDbConnection();
            Connection dbConnection = mysql.getDbConnection();
            if (dbConnection == null)
                Assertions.fail("connection fail");
        } catch (RuntimeException e) {
            Assertions.fail("connection fail");
        }


    }
}
