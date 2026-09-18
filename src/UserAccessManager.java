/**
 * Manages a collection of user accounts.
 * Provides operations for loading, adding, removing,and verifying user accounts
 */



import java.util.List;

import java.util.ArrayList;

import java.io.FileNotFoundException;

public class UserAccessManager {
	
	private List<UserAccount> accounts;
	
	
	/** Creates an empty UserAccessManager. */
	
	public UserAccessManager() {
		accounts= new ArrayList<>();
	}
	
	/**
	  
	  Load accounts in the file 
	  
	 * @param filename the name of the file
	
	 * @throws FileNotFoundException the file is not found
	 */
	
	void loadAccounts(String filename) throws FileNotFoundException{
		
		    Utilities.readAccountFile(filename,  this);
	}
	
	/**
	 * 
	 * @param username
	 * @param encryptedPassword
	 * @throws DuplicateUserException
	 * @throws InvalidCommandException
	 */
	
	void addUser(String username, String encryptedPassword) 
			throws DuplicateUserException, InvalidCommandException{
		
		if (username == null|| username.isEmpty() || encryptedPassword == null || encryptedPassword.isEmpty())
			
			        throw new InvalidCommandException();
		
		
		for (UserAccount account : accounts) {
			
			if (account.getUsername().equals(username)) {
				
				      throw new DuplicateUserException();
			}
		}
		 
		UserAccount newAccount= 
				new UserAccount(username, encryptedPassword);
		
		
		accounts.add(newAccount);
		
	}
	
	/**
	 * 
	 * @param username
	 * @throws UserNotFoundException
	 * @throws InvalidCommandException
	 */
	
	void removeUser(String username) 
			throws UserNotFoundException, InvalidCommandException{
		
		
		if (username== null || username.isEmpty())
			
			     throw new InvalidCommandException();
			
	for (int i=0; i< accounts.size(); i++) {
		
		      UserAccount account = accounts.get(i);
		
		if (account.getUsername().equals(username)) {
			
			
			accounts.remove(i);
			
			return;
		}
	}
		
		
		
		
		throw new UserNotFoundException();
	}
		
		/**
		 * 
		 * @param username
		 * @param encryptedPassword
		 * @return
		 * @throws UserNotFoundException
		 * @throws AccountLockedException
		 * @throws InvalidCommandException
		 * @throws PasswordIncorrectException
		 */
	
		boolean verifyAccess(String username, String encryptedPassword)
				throws UserNotFoundException, AccountLockedException, 
		InvalidCommandException, PasswordIncorrectException{
			
			if (username == null || username.isEmpty() || encryptedPassword == null || encryptedPassword.isEmpty()) {
				
				throw new InvalidCommandException();
			}
			
		for (UserAccount account: accounts) {
			
			if (account.getUsername().equals(username)){
				
				return account.checkPassword(encryptedPassword);
					
				}
			}
		
			throw new UserNotFoundException();
			
		}
			
		
		
		
	

}

