
public class User {
	private String name;
	private String surname;
	private int accountNumber;
	private double amount;
	
	public User() {
		
	}

	public User(String name, String surname, int accountNumber, double amount) {
		this.name = name;
		this.surname = surname;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
	

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getAccountNumber() {
		return accountNumber;
	}
	
	

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	

	public double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return String.format("name = " + getName() + 
				            "\nsurname = " + getSurname() + 
				            "\naccount number = " + getAccountNumber() + 
				            "\namount = " + getAmount());
	}

	
	
	

}
