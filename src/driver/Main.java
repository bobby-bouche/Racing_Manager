package driver;



import java.sql.SQLException;
import java.util.List;

import data_classes.Car;
import data_classes.Driver;
import inputValidation.Keyboard;

public class Main {
	
	private static TeamManager dbManager;
	private static Keyboard kb;
	
	
	public Main() {
		dbManager = new TeamManager();
		kb 		  = new Keyboard();
	}
	
	
	public void launchProgram() throws SQLException {
		
		int choice;
		boolean proceed = true;
		
		String promptMsg = "Make a selection:\n";
		String errorMsg = "Invalid entry, enter and integer value in the range ()\n";
		
		while(proceed) {
			
			System.out.println("-------Race Team Management System-------\n"
					+ "\n--------Home Menu---------\n"
					+ "1. CRUD Driver Menu\n"
					+ "2. CRUD RaceCar Menu\n"
					+ "3. Current Line-up\n"
					+ "4. Race Menu\n"
					+ "5. Quit\n");
			
			choice = kb.readInteger(promptMsg, errorMsg, 1, 5);
			
			switch(choice) {
			
				case 1:
					runDriverMenu();
					break;
					
				case 2:
					for(Car c : dbManager.getRaceCars()) {
						System.out.println(c + "\n");
					}
					break;
					
				case 3:
					System.out.println(dbManager.getRaceTeam());
					break;
					
				case 4:
					System.out.println("launching race menu soon..");
					break;
					
				case 5:
					proceed = false;
					System.out.println("bye for now!");
					break;
					
				default:
					System.out.println("Invalid entry");
			
			}
			
		}
	}
	
	
	void runDriverMenu() throws SQLException {
		
		int choice;
		boolean proceed = true;
		
		
		String promptMsg = "Make a selection:\n";
		String errorMsg = "Invalid selection, enter an integer in the range (1-6)";
		
		while(proceed) {
			
			System.out.println("--------Driver CRUD Menu--------\n"
					+ "1. Create Driver\n"
					+ "2. Read Driver Information\n"
					+ "3. Read Driver Roster\n"
					+ "4. Update Driver Information\n"
					+ "5. Delete Driver\n"
					+ "6. Back");
			
			choice = kb.readInteger(promptMsg, errorMsg, 1, 6);
			
			switch(choice) {
			
			case 1:
				dbManager.createNewDriver();
				break;
				
			case 2:
				int inputId;
				String IdPromptMsg = "enter driverID: \n";
				String IdErrorMsg  = "Invlaid id";
				
				inputId = kb.readInteger(IdPromptMsg, IdErrorMsg);
				System.out.println(dbManager.readDriverInfo(inputId));
				break;
				
			case 3:
				System.out.println("Current Driver Roster:\n");
				for(Driver d : dbManager.getDrivers()) {
					System.out.println(d + "\n");
				}
				break;
				
			case 4:
				int inputUpdate;
				String updatePromptMsg = "enter driverID: \n";
				String updateErrorMsg  = "Invlaid id";
				
				inputUpdate = kb.readInteger(updatePromptMsg, updateErrorMsg);
				dbManager.updateDriverInfo(inputUpdate);
				break;
				
			case 5:
				int inputDelete;
				String deletePromptMsg = "enter driverID: \n";
				String deleteErrorMsg  = "Invlaid id";
				
				inputDelete = kb.readInteger(deletePromptMsg, deleteErrorMsg);
				dbManager.deleteDriver(inputDelete);
				break;
				
			case 6:
				proceed = false;
				System.out.println("Returning to previous menu..");
				break;
				
			default:
				System.out.println("invalid entry");
				
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		
		Main driver = new Main();
		
		try {
			
			dbManager.retrieveData();
			
			Thread listenerThread = new Thread(() -> dbManager.CDCListener());
			listenerThread.start();
			
			driver.launchProgram();
		}
		catch(IllegalArgumentException | SQLException e) {
			System.out.println(e);	
		}


	
	}

}
