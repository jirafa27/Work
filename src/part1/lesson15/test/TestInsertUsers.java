package part1.lesson15.test;

import org.junit.Test;
import part1.lesson15.JavaToMySQL;

import java.sql.SQLException;

public class TestInsertUsers {
    @Test
    public void testUsers() throws SQLException {
        JavaToMySQL.insertUsers();
    }
}
