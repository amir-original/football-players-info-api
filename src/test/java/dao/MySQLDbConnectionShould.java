package dao;

import com.rest.apidemo.dao.MySQLDbConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.fail;

public class MySQLDbConnectionShould {

    private static final String CONNECTION_FAIL = "connection fail";

    @Test
    void connect_to_database_correctly() {

        try {
            MySQLDbConnection mysql = new MySQLDbConnection();
            Connection dbConnection = mysql.getDbConnection();
            if (dbConnection == null)
                throwFileMessage();
        } catch (RuntimeException e) {
            throwFileMessage();
        }


    }

    private static void throwFileMessage() {
        fail(CONNECTION_FAIL);
    }
}
