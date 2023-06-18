import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import model.City;
import model.Employee;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "your_password";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (
                final Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = connection.prepareStatement("" +
                        "SELECT * FROM employee WHERE id = (?)")) {
            statement.setInt(1, 1);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = "Имя: " + resultSet.getString("last_name");
                String surname = "Фамилия " + resultSet.getString("last_name");
                String gender = "Пол: " + resultSet.getString(4);
                int age = resultSet.getInt(5);
                System.out.println(name);
                System.out.println(surname);
                System.out.println(gender);
                System.out.println("Возраст: " + age);

            }
        }
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        System.out.println(employeeDao.getAllEmployee());
        Employee employee = new Employee(1, "Василий", "Васин", "м", 32, new City(1, "Новосибирск"));
        employeeDao.add(employee);
        System.out.println(employeeDao.getAllEmployee());
        System.out.println();
        employee.setLast_name("Петров");
        employeeDao.updateEmployee(4,employee);
        System.out.println(employeeDao.getById(4));
        employeeDao.deleteEmployee(1);
        System.out.println();
        System.out.println(employeeDao.getAllEmployee());

    }
}