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

        PreparedStatement statement = connection.prepareStatement(
                "SELECT `name` FROM `villains` WHERE `id` = ?;");

        statement.setInt(1, idOfVillain);

        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            System.out.printf("No villain with ID %d exists in the database.", idOfVillain);
            return;
        }

        String villainName = resultSet.getString("name");

        statement = connection.prepareStatement(
                "SELECT " +
                        "    m.`name`, m.`age` " +
                        "FROM " +
                        "    `minions_villains` AS mv " +
                        "        JOIN " +
                        "    `minions` AS m ON mv.`minion_id` = m.`id` " +
                        "WHERE " +
                        "    mv.`villain_id` = ?;");

        statement.setInt(1, idOfVillain);
        resultSet = statement.executeQuery();

        System.out.printf("Villain: %s%n", villainName);

        int number = 1;
        while (resultSet.next()) {
            String minionName = resultSet.getString("name");
            int minionAge = resultSet.getInt("age");
            System.out.printf("%d. %s %s%n", number, minionName, minionAge);
            number++;
        }

        connection.close();
    }
}
