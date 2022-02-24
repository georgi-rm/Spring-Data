import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int minionId = Integer.parseInt(scanner.nextLine());

        Properties properties = new Properties();
        properties.setProperty("user", "guest_user");
        properties.setProperty("password", "DummyPassword");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        CallableStatement callableStatement = connection.prepareCall("CALL `usp_get_older`(?);");

        callableStatement.setInt(1, minionId);

        callableStatement.execute();

        PreparedStatement statementGetMinion = connection.prepareStatement(
                "SELECT `name`, `age` FROM `minions` WHERE `id` = ?");

        statementGetMinion.setInt(1, minionId);

        ResultSet resultSetGetMinions = statementGetMinion.executeQuery();

        if (resultSetGetMinions.next()) {
            String minionName = resultSetGetMinions.getString("name");
            int minionAge = resultSetGetMinions.getInt("age");
            System.out.printf("%s %d", minionName, minionAge);
        }

        connection.close();
    }
}
