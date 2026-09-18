
public class UserAccount {
	
	
	String username;
	String encryptedPassword;
	int failureCount = 0;
	boolean locked = false;
	
	/**
	 * 
	 * @param username
	 * @param encryptedPassword
	 */
	
	
	public UserAccount(String username, String encryptedPassword) {
		
		this.username = username;
		this.encryptedPassword = encryptedPassword;
		
	}
	
	/**
	 * 
	 * @return
	 */
	
	public String getUsername() {
		return username;
	}
	
	
	/**
	 * 
	 * @return
	 */
	
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	
	
	
public void incrementfailureCount() {
	failureCount++;
	
	
	
	 if (failureCount >= 3) {
		 locked = true;
		 
		 
	 }
}


public void resetFailureCount() {
	failureCount=0;
}


/**
 * 
 * @return
 */

public boolean locked() {	 	
	return locked;
}


/**
 * 
 * @param encryptedPassword
 * @return
 * @throws AccountLockedException
 * @throws PasswordIncorrectException
 */

public boolean checkPassword(String encryptedPassword) 
		throws AccountLockedException, PasswordIncorrectException {
	
	if (locked) {
		throw new AccountLockedException();
	}
	
	
	
	if (!encryptedPassword.equals(this.encryptedPassword)) {
		incrementfailureCount();
		
		
		
		if(locked) {
			throw new AccountLockedException();
		}
		
		throw new PasswordIncorrectException();
		
	}
	
	
		resetFailureCount();
		
		return true;
	}




@Override
public boolean equals(Object obj) {
	
	if (obj instanceof UserAccount) {
		
		UserAccount other = (UserAccount) obj;
		
		return username.equals(other.username);
	}
	return false;
}


@Override
public String toString() {
	
	return username;
}


}
