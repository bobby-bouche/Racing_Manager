package driver;

import java.sql.SQLException;

import data_classes.Car;
import data_classes.Driver;
import inputValidation.Keyboard;

public class Main {
	
	// Main fields
	private static TeamManager dbManager;
	private static Keyboard kb;
	
	
	// constructor
	public Main() {
		dbManager = TeamManager.getInstance();
		kb 		  = new Keyboard();
	}
	
	
	// method to launch progrom
	public void runProgram() throws SQLException {
		
		int choice;
		boolean proceed = true;
		
		String promptMsg = "Make a selection:\n";
		String errorMsg  = "Invalid entry, enter and integer value in the range ()\n";
		
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
					runRaceCarMenu();
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
	
	
	// method to launch Driver menu
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
				int driverID;
				String newExperienceLevel;
				String driverIDPromptMsg = "enter driverID: \n";
				String driverIDErrorMsg  = "Invalid id";
				
				driverID = kb.readInteger(driverIDPromptMsg, driverIDErrorMsg);
				for(Driver d : dbManager.getDrivers()) {
					if(d.getDriverID() == driverID) {
						
						String updateExperienceLevelPromptMsg = "enter new experience level:\n";
						String updateExperienceLevelErrorMsg = "Invalid experience level. enter one of (rookie, established, seasoned pro";
						newExperienceLevel = kb.readString(updateExperienceLevelPromptMsg, updateExperienceLevelErrorMsg);
						d.setExperienceLevel(newExperienceLevel.toUpperCase());
						dbManager.updateDriverInfo(d);
						System.out.println("Driver information updated..\n");
						break;
					}
				}
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
	
	
	// method to launch raceCar menu
	void runRaceCarMenu() {
		
		int choice;
		boolean proceed = true;
		
		String driverPrompTMsg = "Make a selection:\n";
		String driverErrorMSg  = "Invalid selection, enter an integer in range(1-4)\n";
		
		while(proceed) {
			
			System.out.println("--------Racing Menu--------\n"
					+ "1. Register driver to car\n"
					+ "2. Remove driver from car\n"
					+ "3. View Racing line-up\n"
					+ "4. Back\n");
			
			choice = kb.readInteger(driverPrompTMsg, driverErrorMSg, 1, 4);
			
			switch(choice) {
			
			case 1:
				System.out.println("munya");
				break;
				
			case 2:
				String carIdPromptMsg = "enter car id:\n";
				String carIdErrorMsg  = "Invalid car id";
				
				int carId = kb.readInteger(carIdPromptMsg, carIdErrorMsg);
				for(Car car : dbManager.getRaceCars()) {
					if(car.getCarID() == carId) {
						car.removeDriver();
					}
					else {
						System.out.println("No car with driver with id: " + carId);
					}
				}
				break;
				
			case 3:
				System.out.println(dbManager.getRaceTeam());
				break;
				
			case 4:
				proceed = false;
				System.out.println("Returning to previous menu..");
				break;
				
			default:
				System.out.println("invalid entry");
			}
		}
	}
	
	
	//Main method
	public static void main(String[] args) {
		
		Main driver = new Main();
		
		try {
			
			dbManager.retrieveData();
			
			Thread listenerThread = new Thread(() -> dbManager.CDCListener());
			listenerThread.start();
			
			driver.runProgram();
		}
		catch(IllegalArgumentException | SQLException e) {
			System.out.println(e);	
		}
	}
	

}
