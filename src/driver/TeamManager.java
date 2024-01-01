package driver;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import data_classes.Car;
import data_classes.Driver;
import inputValidation.Keyboard;

public class TeamManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//teamManager fields
	private static List<Driver> drivers;
	private static List<Car> raceCars;
	private static Map<Integer, Car> raceTeam;
	
	private Keyboard kb;
	private Connection connection;
	
	
	//constructor
	public TeamManager() {
		
		super();
		kb   = new Keyboard();
	
	}


	// getters
	public static List<Driver> getDrivers() {
		return drivers;
	}

	public static List<Car> getRaceCars() {
		return raceCars;
	}

	public static Map<Integer, Car> getRaceTeam() {
		return raceTeam;
	}
	
	
	//method to create database connection
	public Connection connectDB() {
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/racing_manager_db", "root", "ronaldo");
			System.out.println("success");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	

}
