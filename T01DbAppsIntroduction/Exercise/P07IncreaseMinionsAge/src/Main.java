import java.sql.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String[] minionsId = scanner.nextLine().split("\\s+");

        Properties properties = new Properties();
        properties.setProperty("user", "guest_user");
        properties.setProperty("password", "DummyPassword");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        String sqlStatement = String.format("UPDATE `minions` " +
                        "SET `age` = `age` + 1, `name` = lower(`name`) " +
                        "WHERE `id` IN (%s);",
                Arrays.stream(minionsId)
                        .map(v -> "?")
                        .collect(Collectors.joining(",")));

        PreparedStatement statementUpdateMinion = connection.prepareStatement(
                sqlStatement);

        for (int i = 1; i <= minionsId.length; i++) {
            statementUpdateMinion.setString(i, minionsId[i - 1]);
        }

        statementUpdateMinion.executeUpdate();

        PreparedStatement statementGetMinions = connection.prepareStatement(
                "SELECT `name`, `age` FROM `minions`");

        ResultSet resultSetGetMinions = statementGetMinions.executeQuery();

        while (resultSetGetMinions.next()) {
            String minionName = resultSetGetMinions.getString("name");
            int minionAge = resultSetGetMinions.getInt("age");
            System.out.printf("%s %d%n", minionName, minionAge);
        }

        connection.close();
    }
}
