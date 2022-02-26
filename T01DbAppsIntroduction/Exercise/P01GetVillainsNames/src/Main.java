import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "guest_user");
        properties.setProperty("password", "DummyPassword");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT v.name, count(DISTINCT mv.minion_id) AS 'count_of_minions' " +
                        "FROM `villains` AS v " +
                        "        JOIN " +
                        "    `minions_villains` AS mv ON v.`id` = mv.`villain_id` " +
                        "GROUP BY v.`id` " +
                        "HAVING `count_of_minions` > 15 " +
                        "ORDER BY `count_of_minions` DESC;");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String villainName = resultSet.getString("name");
            String minionsCount = resultSet.getString("count_of_minions");
            System.out.printf("%s %s%n", villainName, minionsCount);
        }

        connection.close();
    }
}
