
import javax.swing.JOptionPane;

public class ATMWithMySql {

	public static void main(String[] args) {
		
		CreateDataBaseAndTableAtm createNewDataBase = CreateDataBaseAndTableAtm.getInstance();
		String query = "CREATE DATABASE users_of_atm_dataBase";
		createNewDataBase.isCreatedAtmDataBase(query);
		createNewDataBase.isTableCreated();
		Transaction transaction = new Transaction();

		String answer = "";

		try {

			do {

				answer = JOptionPane.showInputDialog(null, "Do you have a created account ? YES / NO", "ATM",
						JOptionPane.QUESTION_MESSAGE);

				switch (answer.toLowerCase()) {

				case "no":
					JOptionPane.showMessageDialog(null, "You must create an account!", "ATM",
							JOptionPane.INFORMATION_MESSAGE);
					createNewDataBase.isUserCreated();
					
					// break;

				case "yes":

					JOptionPane.showMessageDialog(null, "Please logIn!", "ATM", JOptionPane.INFORMATION_MESSAGE);

					int account_number = Integer.parseInt(JOptionPane.showInputDialog(null,
							"Please enter your account number", "ATM", JOptionPane.QUESTION_MESSAGE));

					String pin = JOptionPane.showInputDialog(null, "Please enter your pin", "ATM",
							JOptionPane.QUESTION_MESSAGE);

					if (createNewDataBase.isSuccessfulLogIn(account_number, pin)) {

						int answerForTransfer = Integer.parseInt(JOptionPane.showInputDialog(null,
								"For payout press 1" + "\nFor payment press 2"
										+ "\nFor transfer to another account press3 " + "\nFor exit press 0",
								"ATM", JOptionPane.QUESTION_MESSAGE));

						do {

							switch (answerForTransfer) {

							case 1:
								double amountForPayout = Double.parseDouble(JOptionPane.showInputDialog(null,
										"Please enter your amount for payout", "ATM", JOptionPane.QUESTION_MESSAGE));

								transaction.amountPayout(account_number, pin, amountForPayout);

								answerForTransfer = Integer.parseInt(JOptionPane.showInputDialog(null,
										"For payout press 1" + "\nFor payment press 2"
												+ "\nFor transfer to another account press3 " + "\nFor exit press 0",
										"ATM", JOptionPane.QUESTION_MESSAGE));

							case 2:
								double amountForPayment = Double.parseDouble(JOptionPane.showInputDialog(null,
										"Please enter your amount for payment", "ATM", JOptionPane.QUESTION_MESSAGE));
								transaction.amountPayment(account_number, pin, amountForPayment);

								answerForTransfer = Integer.parseInt(JOptionPane.showInputDialog(null,
										"For payout press 1" + "\nFor payment press 2"
												+ "\nFor transfer to another account press3 " + "\nFor exit press 0",
										"ATM", JOptionPane.QUESTION_MESSAGE));
								break;

							case 3:
								double amountForTransfer = Double.parseDouble(JOptionPane.showInputDialog(null,
										"Please enter your amount for transfer", "ATM", JOptionPane.QUESTION_MESSAGE));
								int targetAccount_number = Integer.parseInt(JOptionPane.showInputDialog(null,
										"Please enter target account number", "ATM", JOptionPane.QUESTION_MESSAGE));

								transaction.amountTransfer(account_number, targetAccount_number, pin,
										amountForTransfer);

								answerForTransfer = Integer.parseInt(JOptionPane.showInputDialog(null,
										"For payout press 1" + "\nFor payment press 2"
												+ "\nFor transfer to another account press3 " + "\nFor exit press 0",
										"ATM", JOptionPane.QUESTION_MESSAGE));
								break;
							}
							
						} while (answerForTransfer != 0);

					} else {

						JOptionPane.showMessageDialog(null, "You left the app!", "ATM",
								JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				}
			} while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"));

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "You left the app!", "ATM", JOptionPane.INFORMATION_MESSAGE);

		}
		ConnectionToDatabase.getInstance().closeConnection();
	}
}
