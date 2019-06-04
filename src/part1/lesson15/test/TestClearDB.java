package part1.lesson15.test;

import part1.lesson15.JavaToMySQL;

import java.sql.SQLException;

public class TestClearDB {

    public void testClearDB() throws SQLException {
        JavaToMySQL.clearDB();
    }
}
