import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Transaction {

	public double amountPayout(int accountNumberOfUser, String pin, double amountForPayout) {

		double amount = 0;

		Connection myConn = ConnectionToDatabase.getInstance().getConnection();
		try {

			Statement st = myConn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users_of_atm_dataBase.info WHERE account_number = '"
					+ accountNumberOfUser + "' AND pin='" + pin + "'");
			while (rs.next()) {

				amount = rs.getDouble("amount") - amountForPayout;

				JOptionPane.showMessageDialog(null, "Your account balance is " + amount, "ATM",
						JOptionPane.INFORMATION_MESSAGE);
			}
			st.executeUpdate("UPDATE info SET amount='" + amount + "' WHERE pin='" + pin + "' AND account_number='"
					+ accountNumberOfUser + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return amount;
	}

	public double amountPayment(int accountNumberOfUser, String pin, double amountForPayment) {

		double amount = 0;

		Connection myConn = ConnectionToDatabase.getInstance().getConnection();
		try {

			Statement st = myConn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users_of_atm_dataBase.info WHERE account_number = '"
					+ accountNumberOfUser + "' AND pin='" + pin + "'");
			while (rs.next()) {

				amount = rs.getDouble("amount") + amountForPayment;

				JOptionPane.showMessageDialog(null, "Your account balance is " + amount, "ATM",
						JOptionPane.INFORMATION_MESSAGE);
			}
			st.executeUpdate("UPDATE info SET amount='" + amount + "' WHERE pin='" + pin + "' AND account_number='"
					+ accountNumberOfUser + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return amount;
	}

	public double amountTransfer(int accountNumberOfSender, int targetAccountNumber, String pin,
			double amountForTransfer) {

		double amount = 0;
		double targetAmount = 0;

		Connection myConn = ConnectionToDatabase.getInstance().getConnection();
		try {

			Statement st = myConn.createStatement();
			Statement st2 = myConn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users_of_atm_dataBase.info WHERE account_number = '"
					+ accountNumberOfSender + "' AND pin='" + pin + "'");
			while (rs.next()) {

				amount = rs.getDouble("amount") - amountForTransfer;

				ResultSet rs2 = st2.executeQuery("SELECT * FROM users_of_atm_dataBase.info WHERE account_number = '"
						+ targetAccountNumber + "'");
				while (rs2.next()) {
					targetAmount = rs2.getDouble("amount") + amountForTransfer;
				}
				rs2.close();
			}
			rs.close();
			st.executeUpdate("UPDATE info SET amount='" + amount + "' WHERE pin='" + pin + "' AND account_number='"
					+ accountNumberOfSender + "'");
			st2.executeUpdate("UPDATE info SET amount='" + targetAmount + "' WHERE  account_number='"
					+ targetAccountNumber + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return amount;
	}
}
