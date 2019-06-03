package part1.lesson13;


import java.sql.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class JavaToMySQL {
    private static final String url = "jdbc:mysql://localhost:3306/database" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "chudo1994";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    String query = "";
    private static void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        stmt = con.createStatement();
        log.info("Создание соедиения с базой данных");
    }
    private static void clearDB() throws SQLException {
        stmt.executeUpdate("TRUNCATE TABLE USER");
        stmt.executeUpdate("TRUNCATE TABLE ROLE");
        stmt.executeUpdate("TRUNCATE TABLE USER_ROLE");
        log.info("Удаление данных из базы");
    }
    private static void insertUsers() throws SQLException {
        stmt.executeUpdate("INSERT INTO USER VALUES (1, 'Alex', '1994-12-24', 1, 'Moskow', '123@mail.ru', '')");

    }
    private static void batchInsertUsers() throws SQLException {
        con.setAutoCommit(false);
        stmt.addBatch("INSERT INTO USER VALUES (2, 'Ann', '1995-02-24', 2, 'Spb', '456@mail.ru', '')");
        stmt.addBatch("INSERT INTO USER VALUES (3, 'Dima', '1989-02-24', 3, 'Spb', '457@mail.ru', '')");
        stmt.executeBatch();
        con.commit();
        log.info("Использование batch-процесса");
    }
    private static void printSelect() throws SQLException {
        rs = stmt.executeQuery("SELECT * FROM USER WHERE `login_ID`=2 or `name`='Alex'");
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Date date = rs.getDate(3);
            int login_ID = rs.getInt(4);
            String city = rs.getString(5);
            String email = rs.getString(6);
            String description = rs.getString(7);
            System.out.printf("id: %d, name: %s, birthday: %s, login_ID: %s, city: %s, email: %s, description: %s %n", id, name, date, login_ID, city, email, description);
        }
        log.info("Вывод параметризованной выборки");
    }
    private static void trySavepoints() throws SQLException {
        Savepoint savepointOne = null;
        Savepoint savepointTwo = null;
        try {
            con.setAutoCommit(false);
            stmt.executeUpdate("INSERT INTO ROLE VALUES (1, 'Administration', '')");
            savepointOne = con.setSavepoint("SavepointOne");
            stmt.executeUpdate("INSERT INTO USER_ROLE VALUES (1, 1, 1)");

            stmt.executeUpdate("INSERT INTO ROLE VALUES (2, 'Clients', '')");
            savepointTwo = con.setSavepoint("SavepointTwo");
            stmt.executeUpdate("INSERT INTO &&&&&&&&USER_ROLE VALUES (2, 2, 2)");
            con.commit();
        } catch (SQLException e) {
            log.error("Произошел откат к savepointTwo");
            con.rollback(savepointTwo);

        }
        rs = stmt.executeQuery("SELECT * FROM USER_ROLE");
        while (rs.next()) {
            int id = rs.getInt(1);
            int user_id = rs.getInt(2);
            int role_id = rs.getInt(3);
            System.out.printf("id: %d, user_id: %d, role_id: %d %n", id, user_id, role_id);
        }
    }
    private static void closing() throws SQLException {
        rs.close();
        con.close();
        stmt.close();
    }
    private static final Logger log = Logger.getLogger("reg");
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        PropertyConfigurator.configure("src/part1/lesson13/resources/log4j.properties");

        initConnection();
        clearDB();
        insertUsers();
        batchInsertUsers();
        printSelect();
        trySavepoints();
        closing();


    }

}
