import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class DBDemo
{
    public static void main(String [] args) {
        String jdbcURL = "jdbc:mysql://Localhost:3306/payroll_service?userSSL=false";
        String userName = "root";
        String password = "chinni@68";
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("cannot find the driver in the classpath!", e);
        }
        listDrivers();
        try {
            System.out.println("Connecting to database:" + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is successful!!!!!" + connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        private static void listDrivers() {
            Enumeration <Driver>driverlist = DriverManager.getDrivers();
            while (driverlist.hasMoreElements()){
                Driver driverClass = (Driver) driverlist.nextElement();
                System.out.println("   "+driverClass.getClass().getName());
            }
        }

    }

