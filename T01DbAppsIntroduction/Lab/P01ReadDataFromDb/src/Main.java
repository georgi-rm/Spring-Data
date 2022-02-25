import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username (default - root): ");
        String input = scanner.nextLine().trim();
        String username = input.isEmpty() ? "root" : input;

        System.out.print("Enter password (default - empty): ");
        input = scanner.nextLine().trim();
        String password = input.isEmpty() ? "" : input;

        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);

        System.out.print("Search username: ");
        String userNameToSearch = scanner.nextLine().trim();

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/diablo", properties);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT u.`first_name`, u.`last_name`, count(*) AS 'played_games'\n" +
                        "FROM `users` AS u " +
                        "         JOIN " +
                        "     `users_games` AS ug ON u.`id` = ug.`user_id` " +
                        "WHERE u.`user_name` = ? " +
                        "GROUP BY u.`id`;");

        statement.setString(1, userNameToSearch);

        ResultSet resultSetPlayedGames = statement.executeQuery();

        if (resultSetPlayedGames.next()) {
            String firstName = resultSetPlayedGames.getString("first_name");
            String lastName = resultSetPlayedGames.getString("last_name");
            int playedGames = resultSetPlayedGames.getInt("played_games");
            System.out.printf("User: %s%n", userNameToSearch);
            System.out.printf("%s %s has played %d games", firstName, lastName, playedGames);
        } else {
            System.out.println("No such user exists");
        }

        connection.close();
    }
}
