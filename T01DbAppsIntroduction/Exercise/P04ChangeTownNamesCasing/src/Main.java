import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String nameOfTown = scanner.nextLine();

        Properties properties = new Properties();
        properties.setProperty("user", "guest_user");
        properties.setProperty("password", "DummyPassword");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement statementGetNonCapitalTownNames = connection.prepareStatement(
                "SELECT upper(`name`) AS 'upper_name' FROM `towns` " +
                        "WHERE `country` = ? AND BINARY `name` != upper(`name`);");

        statementGetNonCapitalTownNames.setString(1, nameOfTown);

        ResultSet resultSetGetNonCapitalTownNames = statementGetNonCapitalTownNames.executeQuery();

        List<String> changedTowns = new ArrayList<>();
        while (resultSetGetNonCapitalTownNames.next()) {
            changedTowns.add(resultSetGetNonCapitalTownNames.getString("upper_name"));

        }

        PreparedStatement statementUpdateNamesToCapital = connection.prepareStatement(
                "UPDATE `towns` " +
                        "SET `name` = upper(`name`) " +
                        "WHERE `country` = ? AND BINARY `name` != upper(`name`);");

        statementUpdateNamesToCapital.setString(1, nameOfTown);
        int numberOfAffectedRecords = statementUpdateNamesToCapital.executeUpdate();

        if (numberOfAffectedRecords == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.%n", numberOfAffectedRecords);
            System.out.printf("[%s]", String.join(", ", changedTowns));
        }

        connection.close();
    }
}
