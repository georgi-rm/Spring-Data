import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String[] minionParams = scanner.nextLine().split("\\s+");

        String minionName = minionParams[1];
        int minionAge = Integer.parseInt(minionParams[2]);
        String minionTown = minionParams[3];

        String nameOfVillain = scanner.nextLine().split("\\s+")[1];

        Properties properties = new Properties();
        properties.setProperty("user", "guest_user");
        properties.setProperty("password", "DummyPassword");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        int townId = getTownIdEnsured(minionTown, connection);
        int villainId = getVillainIdEnsured(nameOfVillain, connection);

        PreparedStatement statementInsertMinion = connection.prepareStatement(
                "INSERT INTO `minions` (`name`, `age`, `town_id` ) VALUES (?, ?, ?);");

        statementInsertMinion.setString(1, minionName);
        statementInsertMinion.setInt(2, minionAge);
        statementInsertMinion.setInt(3, townId);

        statementInsertMinion.executeUpdate();

        PreparedStatement statementGetMinionId = connection.prepareStatement(
                "SELECT `id` FROM `minions` ORDER BY `id` DESC LIMIT 1");

        ResultSet resultSetGetMinionId = statementGetMinionId.executeQuery();
        resultSetGetMinionId.next();
        int minionId = resultSetGetMinionId.getInt("id");

        PreparedStatement statementInsertMinionVillains = connection.prepareStatement(
                "INSERT INTO `minions` (`minion_id`, `villain_id` ) VALUES (?, ?);");

        statementInsertMinion.setInt(1, minionId);
        statementInsertMinion.setInt(1, villainId);

        statementInsertMinion.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.%n", minionName, nameOfVillain);

        connection.close();
    }

    private static int getTownIdEnsured(String minionTown, Connection connection) throws SQLException {
        PreparedStatement statementFindTown = connection.prepareStatement(
                "SELECT `id` FROM `towns` WHERE `name` = ?;");

        statementFindTown.setString(1, minionTown);

        ResultSet resultSetFindTown = statementFindTown.executeQuery();

        int townId;
        if (resultSetFindTown.next()) {
            return resultSetFindTown.getInt("id");
        }

        PreparedStatement statementInsertTown = connection.prepareStatement(
                "INSERT INTO `towns` (`name`) VALUES (?);");

        statementInsertTown.setString(1, minionTown);

        statementInsertTown.executeUpdate();

        resultSetFindTown = statementFindTown.executeQuery();
        resultSetFindTown.next();

        System.out.printf("Town %s was added to the database.%n", minionTown);
        return resultSetFindTown.getInt("id");
    }

    private static int getVillainIdEnsured(String nameOfVillain, Connection connection) throws SQLException {
        PreparedStatement statementFindTown = connection.prepareStatement(
                "SELECT `id` FROM `villains` WHERE `name` = ?;");

        statementFindTown.setString(1, nameOfVillain);

        ResultSet resultSetFindVillain = statementFindTown.executeQuery();

        if (resultSetFindVillain.next()) {
            return resultSetFindVillain.getInt("id");
        }

        PreparedStatement statementInsertTown = connection.prepareStatement(
                "INSERT INTO `villains` (`name`, `evilness_factor`) VALUES (?, 'evil');");

        statementInsertTown.setString(1, nameOfVillain);

        statementInsertTown.executeUpdate();

        resultSetFindVillain = statementFindTown.executeQuery();
        resultSetFindVillain.next();

        System.out.printf("Villain %s was added to the database.%n", nameOfVillain);
        return resultSetFindVillain.getInt("id");
    }
}
