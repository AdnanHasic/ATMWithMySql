import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CreateDataBaseAndTableAtmTest {

	CreateDataBaseAndTableAtm newCreateDB;
	String query = "CREATE DATABASE " + "database" + Integer.toString((int) (Math.random() * 10) + 1);

	@Before
	public void setUp() {

		newCreateDB = CreateDataBaseAndTableAtm.getInstance();

	}

	@Test
	public void getInstanceTest_shouldReturnInstance_ifInstanceIsNull() {

		assertNotNull(CreateDataBaseAndTableAtm.getInstance());

	}

	@Test
	public void isCreatedAtmDataBaseTest_shouldReturnTrueIsDataBaseIsCreated() {

		boolean results = newCreateDB.isCreatedAtmDataBase(query);

		assertTrue(results);

	}

	@Test
	public void isUserCreatedTest_shouldReturTrue_ifUserIsCreated() {

		boolean result = newCreateDB.isUserCreated();

		assertTrue(result);
	}

}
