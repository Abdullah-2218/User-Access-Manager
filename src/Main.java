import java.util.Scanner;


import java.io.FileNotFoundException;

public class Main {
	
	public static void main (String[] args) {
		
		
		Scanner keyboard = new Scanner(System.in);
		
		UserAccessManager manager = new UserAccessManager();
		
		System.out.println("User access manager ready.");
		
		while (true) {
			
			System.out.println("User Access Manager> ");
			
			if (!keyboard.hasNextLine()) {
				
				break;
			}
			
			String line = keyboard.nextLine().trim();
			
			if (line.isEmpty()) {
				
				continue;
				
			}
			
			String[] parts = line.split("\\s+");
			
			String command = parts[0];
			
			try {
				
				
				if (command.equalsIgnoreCase("exit")) {
					break;
				}
			
				 /** Making sure commands other than exit have an argument
				  * 
				  */
				
				if(parts.length != 2) {
					throw new InvalidCommandException();
				}
				
				String argument = parts[1];
				
				/**Load
				 *  
				 */
				
				if (command.equalsIgnoreCase("load")) {
					
					try {
						manager.loadAccounts(argument);
					}
					catch (FileNotFoundException e) {
						System.out.println("Unable to load file: "+ argument);
					}
				}
				
				/** Add
				 * 
				 */
				else if (command.equalsIgnoreCase("add")) {
					
					System.out.print("Password: ");
					
					String password = keyboard.nextLine();
					
					String encryptedPassword =
							Utilities.encryptPassword(password);
					
					manager.addUser(argument, encryptedPassword);
					
				}
				
				/** REMOVE
				 * 
				 */
				
				else if (command.equalsIgnoreCase("remove")) {
							
					manager.removeUser(argument);
					
				}
				
				/** VERIFY
				 *  
				 */
				else if (command.equalsIgnoreCase("verify")) {
					
					System.out.print("Password: ");
					
					String password = keyboard.nextLine();
					
					String encryptedPassword =
							Utilities.encryptPassword(password);
					
					if(manager.verifyAccess(argument, encryptedPassword)) {
						System.out.println("Access verified");
					}
				}
				
				/** UNKNOWN COMMAND
				 * 
				 */
				
				else {
					throw new InvalidCommandException();
				}
	}
			
			catch(DuplicateUserException e) {
				System.out.println("User '" + argumentFrom(parts)+
						"' account already exists.");
				
			}
		
			catch(UserNotFoundException e) {
				
				System.out.println("User not found");
				
			}
			
			catch(PasswordIncorrectException e) {
				
				System.out.println("Incorrect Password");
			}
			
			catch(AccountLockedException e) {
				
				System.out.println("User '" + argumentFrom(parts)+
						
						"' account is locked.");
				
			}
			
			catch(InvalidCommandException e) {
				
				System.out.println("Invalid Command");
			}
		
	}
		keyboard.close();

}
	/**
	 * 
	 * @param parts
	 * @return
	 */
	private static String argumentFrom(String[] parts) {
		
		if (parts.length >= 2) {
			
			return parts[1];
		}
		
		return "";
	}
}
