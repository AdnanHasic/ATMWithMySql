import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionToDatabase {

	private static ConnectionToDatabase instance = null;

	private static final String USERNAME = "root";
	private static final String PASSWORD = "adnan";

	private static final String CONN_STRING = "jdbc:mysql://localhost:3306/users_of_atm_dataBase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private Connection connection = null;

	private ConnectionToDatabase() {

	}

	public static ConnectionToDatabase getInstance() {
		if (instance == null) {
			instance = new ConnectionToDatabase();
		}
		return instance;
	}

	private boolean openConnection() {
		try {
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	public Connection getConnection() {
		if (connection == null) {
			if (openConnection()) {
				System.out.println("Connection is open !");
				return connection;
			} else {
				return null;
			}
		}
		return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
			connection = null;
			System.out.println("Connection is closed !");
		} catch (Exception e) {
		}
	}
}
