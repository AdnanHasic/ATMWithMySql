import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConnectionToDatabaseTest {
	
	ConnectionToDatabase myConn;
	
	@Before
	public void setUp() {
		
		myConn = ConnectionToDatabase.getInstance();
	
	}

	@After
	public void setUp2() {
		
		myConn.closeConnection();
	}
	
	@Test
	public void getInstanceTest_shouldReturnInstance_ifConnectionIsNull() {
		
		assertNotNull(myConn);
		
	}
	
	@Test
	public void getConnection_shouldReturnConnection_ifConnectionIsOpen() {
		
		boolean result = myConn.getConnection() != null;
		assertTrue(result);
	}
	
 
	
}
