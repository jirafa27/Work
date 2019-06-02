package part1.lesson13;


import java.sql.*;

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

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        stmt = con.createStatement();
        stmt.executeUpdate("TRUNCATE TABLE USER");
        stmt.executeUpdate("TRUNCATE TABLE ROLE");
        stmt.executeUpdate("TRUNCATE TABLE USER_ROLE");
        stmt.executeUpdate("INSERT INTO USER VALUES (1, 'Alex', '1994-12-24', 1, 'Moskow', '123@mail.ru', '')");
        con.setAutoCommit(false);
        stmt.addBatch("INSERT INTO USER VALUES (2, 'Ann', '1995-02-24', 2, 'Spb', '456@mail.ru', '')");
        stmt.addBatch("INSERT INTO USER VALUES (3, 'Dima', '1989-02-24', 3, 'Spb', '457@mail.ru', '')");
        stmt.executeBatch();
        con.commit();
        rs = stmt.executeQuery("SELECT * FROM USER WHERE `login_ID`=2 or `name`='Alex'");
        //rs = stmt.executeQuery("SELECT * FROM USER");
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
            con.rollback(savepointTwo);

        }
        rs = stmt.executeQuery("SELECT * FROM USER_ROLE");
        while (rs.next()) {
            int id = rs.getInt(1);
            int user_id = rs.getInt(2);
            int role_id = rs.getInt(3);
            System.out.printf("id: %d, user_id: %d, role_id: %d %n", id, user_id, role_id);
        }
        rs.close();
        con.close();
        stmt.close();


    }

}
