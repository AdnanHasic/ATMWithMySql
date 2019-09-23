import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CreateDataBaseAndTableAtm {

	private static CreateDataBaseAndTableAtm instance = null;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "adnan";
	private static final String CONN_STRING = "jdbc:mysql://localhost:3306/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String TABLEQUERY = "CREATE TABLE info("
			+ "userID INTEGER PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT," + "name VARCHAR(70) NOT NULL,"
			+ "surname VARCHAR(70) NOT NULL," + "account_number INTEGER NOT NULL UNIQUE," + "amount DOUBLE,"
			+ "pin VARCHAR(4) NOT NULL" + ");";

	private CreateDataBaseAndTableAtm() {

	}

	public static CreateDataBaseAndTableAtm getInstance() {

		if (instance == null) {

			instance = new CreateDataBaseAndTableAtm();
		}

		return instance;
	}

	public boolean isCreatedAtmDataBase(String query) {

		try {

			Connection myConn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			Statement statement = myConn.createStatement();
			statement.executeUpdate(query);
			System.out.println("Successful, data base is created !");
			myConn.close();
			return true;
		} catch (SQLException e) {

			System.out.println("Data base exist !");
		}

		return false;
	}

	public boolean isTableCreated() {

		try {
			Connection myConn = ConnectionToDatabase.getInstance().getConnection();
			Statement statement = myConn.createStatement();
			statement.executeUpdate(TABLEQUERY);

			return true;

		} catch (SQLException e) {

			System.out.println("Table exist !");
		} finally {

			ConnectionToDatabase.getInstance().closeConnection();
		}

		return false;
	}

	public boolean isUserCreated() {
		String name = JOptionPane.showInputDialog(null, "Your name :", "ATM", JOptionPane.QUESTION_MESSAGE);

		String surname = JOptionPane.showInputDialog(null, "Your surame:", "ATM", JOptionPane.QUESTION_MESSAGE);

		int account_number = (int) (Math.random() * 900000000) + 100000000;

		JOptionPane.showMessageDialog(null,
				"Please remember your account number.\nYour account number is " + account_number, "ATM",
				JOptionPane.INFORMATION_MESSAGE);

		double amount = Double
				.parseDouble(JOptionPane.showInputDialog(null, "Your deposit", "ATM", JOptionPane.QUESTION_MESSAGE));

		String pin = "";
		try {
			JOptionPane.showMessageDialog(null, "Your pin must be of 4 characters  ", "ATM",
					JOptionPane.INFORMATION_MESSAGE);

			while (pin.length() != 4) {
				pin = JOptionPane.showInputDialog(null, "Your pin :", "ATM", JOptionPane.QUESTION_MESSAGE);
			}

		} catch (Exception e) {
			System.out.println("Wrong pin format !");
		}

		String insertQuery = "INSERT INTO info VALUES (" + null + ",'" + name + "', '" + surname + "', '"
				+ account_number + "', '" + amount + "', '" + pin + "'" + ")";

		Connection myConn = ConnectionToDatabase.getInstance().getConnection();

		try {
			Statement st = myConn.createStatement();
			st.executeUpdate(insertQuery);
			System.out.println("Successful update.");
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			ConnectionToDatabase.getInstance().closeConnection();

		}

		return false;
	}

	public boolean isSuccessfulLogIn(int accountNumberOfUser, String pin) {

		int accountNumber = 0;
		String pinCode = "";

		Connection myConn = ConnectionToDatabase.getInstance().getConnection();
		try {
			Statement st = myConn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users_of_atm_dataBase.info WHERE account_number = '"
					+ accountNumberOfUser + "' AND pin='" + pin + "'");
			while (rs.next()) {

				String name = rs.getString("name");
				String surname = rs.getString("surname");
				accountNumber = rs.getInt("account_number");
				double amount = rs.getDouble("amount");
				pinCode = rs.getString("pin");
				User newUser = new User(name, surname, accountNumber, amount);

				JOptionPane.showMessageDialog(null, newUser.toString(), "ATM", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		if (pin.equals(pinCode) && accountNumber == accountNumberOfUser) {

			return true;
		} else {
			
			return false;
		}
	}

}
