import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	String name;
	String surname;
	int accountNumber;
	double amount;
	User newUser;
	
	@Before
	
	public void setup() {
	
	name = "Homer";
	surname = "Simpson";
	accountNumber = 1234567890;
	amount = 500;
	newUser = new User(name, surname, accountNumber, amount);
	
	}
	
	

	@Test
	public void getNameTest_ShouldReturnNameOfUser_ifGivenName() {
		  
		String result = newUser.getName();
		String expected = "Homer";
		
		assertEquals(result, expected);
		
	}
	
	@Test
	public void getSurnameTest_ShouldReturnSurnameOfUser_ifGivenSurname() {
		
		String result = newUser.getSurname();
		String expected = "Simpson";
		
		assertEquals(result, expected);
		
	}
	
	@Test
	public void getAccountNumberTest_ShouldReturnAccountNumberOfUser_ifGivenAccountNumber() {
		
		int result = newUser.getAccountNumber();
		int expected = 1234567890;
		
		assertEquals(result, expected);
	}
	
	@Test
	public void getAmountTest_ShouldReturnAmountOfUser_ifGivenAmount() {
		
		double result = newUser.getAmount();
		double expected = 500;
		
		assertEquals(result, expected, 0.01);
	}
	
	

}
