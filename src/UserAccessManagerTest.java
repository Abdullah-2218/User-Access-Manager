import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserAccessManagerTest {
	
	private UserAccessManager manager;
	
	@BeforeEach 
	public void setUp() {
		manager = new UserAccessManager();
	}
	
	// TEst adding a user and verifying with correct password
	
	@Test
	public void testAddUser() throws Exception{
		
		String encryptedPassword = Utilities.encryptPassword("hello123");
		
		manager.addUser("abdullah",  encryptedPassword);
		
		assertTrue(manager.verifyAccess("abdullah",encryptedPassword ));
	}

	// test duplicate user name
	
	@Test
	public void testDuplicateUser() throws Exception{
		
		String encryptedPassword = Utilities.encryptPassword("hello123");
		
		      manager.addUser("abdullah", encryptedPassword);
		
		assertThrows(DuplicateUserException.class, ()->{
			
			manager.addUser("abdullah", encryptedPassword);
		});
	}
	
	// Test removing an existing user
	
	@Test
	public void testRemoveUser() throws Exception{
		
		String encryptedPassword = Utilities.encryptPassword("hello123");
		
		
		    manager.addUser("abdullah", encryptedPassword);
		
		manager.removeUser("abdullah");
		
		    assertThrows(UserNotFoundException.class, ()->{
			
			manager.verifyAccess("abdullah", "hello123");
		});
	}
		
		//Test removing a user that doesn't exist
		
		@Test
		public void testRemoveUserNotFound() {
			
			assertThrows(UserNotFoundException.class, ()->{
				   manager.removeUser("abdullah");
			});
		}
		
		
		// Test Correct password
		
		@Test
		public void testCorrectPassword() throws Exception{
			
			String encryptedPassword = Utilities.encryptPassword("hello123");
			       manager.addUser("abdullah", encryptedPassword);
			
			assertTrue (manager.verifyAccess("abdullah", encryptedPassword));
		}
		
		
		// Verify Incorrect Password
		public void testIncorrectPassword() throws Exception{
		
			String encryptedPassword = Utilities.encryptPassword("hello123");
			        manager.addUser("abdullah", encryptedPassword);
			
			assertThrows(PasswordIncorrectException.class, ()->{
				   manager.verifyAccess("abdullah", Utilities.encryptPassword("wrongPassword"));
			});
		}
		
		//Test that account locks after 3 incorrect attempts
		
		public void testAccountLock() throws Exception{
			
			String encryptedPassword = Utilities.encryptPassword("hello123");
			
			      manager.addUser("abdullah", encryptedPassword);
			
			//First Incorrect attempt
			      
			assertThrows(PasswordIncorrectException.class, ()->{
				    manager.verifyAccess("abdullah", "wrong1");
			});
			
			//Second Incorrect attempt
			assertThrows(PasswordIncorrectException.class, ()->{
				
				    manager.verifyAccess("abdullah", "wrong2");
			});
			
			//Third Incorrect attempt should lock the account
			
			assertThrows(PasswordIncorrectException.class, ()->{
				
				    manager.verifyAccess("abdullah", "wrong3");
			});
			
			//Even the correct password should now be rejected
			
			assertThrows(PasswordIncorrectException.class, ()->{
				
				    manager.verifyAccess("abdullah", "hello123");
			});
			
		}
		
		
		//Test empty username when adding
		@Test
		public void testAddEmptyUsername() {
			
			String  encryptedPassword = Utilities.encryptPassword("hello123");
			
			assertThrows(InvalidCommandException.class, ()->{
				
				   manager.addUser("", encryptedPassword);
			});		
		}

		
		//Test null username when adding
		@Test
		public void testAddNullUsername() {
			
			String  encryptedPassword = Utilities.encryptPassword("hello123");
			
			assertThrows(InvalidCommandException.class, ()->{
				
				   manager.addUser(null, encryptedPassword);
			});		
		}
		
		//Test empty password when adding
		@Test
		
	public void testAddEmptyPassword() {
			
			assertThrows(InvalidCommandException.class, ()->{
				
				    manager.addUser("abdullah", "");
			});		
		}
		
		//Test empty username when verifying
		@Test
		public void testVerifyEmptyUsername() {
			
			assertThrows(InvalidCommandException.class, ()->{
				
				    manager.verifyAccess("", "hello123");
			});	
		}
				
		
		//Test non-existent username when verifying
		
		@Test
		public void testVerifyUserNotFound() {
			
			assertThrows(UserNotFoundException.class, ()->{
				
				    manager.verifyAccess("Unknown", "hello123");
			});	
		}
		
		
		//Test UserAccounttoString() 
		@Test
		public void testToString() {
			String  encryptedPassword = Utilities.encryptPassword("hello123");
			
			       UserAccount account= new UserAccount ("abdullah", encryptedPassword);
			
			assertEquals("abdullah", account.toString());
					
		}
		
		//TestUseraccount Equals
		public void testEquals() {
			
			UserAccount account1 = new UserAccount("abdullah", "password1");
			
			UserAccount account2 = new UserAccount("abdullah", "password2");
			
			assertEquals(account1, account2);
		}
		
		
		//Test successful login resets failure count
		@Test
		public void testSuccessfulLoginAfterFailedAttempt() throws Exception{
			
			       String  encryptedPassword = Utilities.encryptPassword("hello123");
			
			manager.addUser("abdullah", encryptedPassword);
			
			//One failed attempt
			assertThrows(PasswordIncorrectException.class, ()->{
				
				
				     manager.verifyAccess("abdullah", Utilities.encryptPassword("wrong"));
			});
			
			//Correct passwprd should work and reset failures
			
			
			assertTrue(manager.verifyAccess("abdullah", Utilities.encryptPassword("hello123")));
		}		
		
	}
	

