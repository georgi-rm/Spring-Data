import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int idOfVillain = Integer.parseInt(scanner.nextLine());

        Properties properties = new Properties();
        properties.setProperty("user", "guest_user");
        properties.setProperty("password", "DummyPassword");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement statementGetVillain = connection.prepareStatement(
                "SELECT `name` FROM `villains` WHERE `id` = ?;");

        statementGetVillain.setInt(1, idOfVillain);

        ResultSet resultSetGetVillain = statementGetVillain.executeQuery();
        if (!resultSetGetVillain.next()) {
            System.out.println("No such villain was found");
            return;
        }

        String villainName = resultSetGetVillain.getString("name");

        connection.setAutoCommit(false);

        try {
            int numberOfReleasedMinions = releaseMinions(idOfVillain, connection);
            deleteVillain(idOfVillain, connection);
            connection.commit();
            System.out.printf("%s was deleted%n", villainName);
            System.out.printf("%d minions released", numberOfReleasedMinions);
        } catch (SQLException ex) {
            connection.rollback();
        }

        connection.close();
    }

    private static void deleteVillain(int idOfVillain, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM `villains` WHERE `id` = ?;");

        statement.setInt(1, idOfVillain);

        statement.executeUpdate();
    }

    private static int releaseMinions(int idOfVillain, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM `minions_villains` WHERE `villain_id` = ?;");

        statement.setInt(1, idOfVillain);

        return statement.executeUpdate();
    }
}
