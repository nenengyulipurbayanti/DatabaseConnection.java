// PersonDAO.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    public static void createPerson(Person person) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO person (name, age) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, person.getName());
        statement.setInt(2, person.getAge());
        statement.executeUpdate();
    }

    public static List<Person> readPersons() throws SQLException {
        List<Person> persons = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM person";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            Person person = new Person(id, name, age);
            persons.add(person);
        }

        return persons;
    }
}