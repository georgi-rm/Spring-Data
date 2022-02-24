import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "guest_user");
        properties.setProperty("password", "DummyPassword");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement statementGetAllMinions = connection.prepareStatement(
                "SELECT `name` FROM `minions`;");

        List<String> allMinions = new ArrayList<>();
        ResultSet resultSetGetAllMinions = statementGetAllMinions.executeQuery();
        while (resultSetGetAllMinions.next()) {
            allMinions.add(resultSetGetAllMinions.getString("name"));
        }

        for (int i = 0; i < (allMinions.size() + 1) / 2; i++) {
            System.out.println(allMinions.get(i));
            if (i != allMinions.size() - i - 1) {
                System.out.println(allMinions.get(allMinions.size() - i - 1));
            }
        }

        connection.close();
    }
}
