package driver;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	
	// this is a neat feature to implement that will store currant race team
	private static Map<Integer, Car> raceTeam;
	
	private Keyboard kb;
	private Connection connection;
	
	
	//constructor
	public TeamManager() {
		super();
		kb   = new Keyboard();
	}


	// getters
	public List<Driver> getDrivers() {
		return drivers;
	}

	public List<Car> getRaceCars() {
		return raceCars;
	}

	public Map<Integer, Car> getRaceTeam() {
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
	
	
	// method to continuously refresh data lists every 5 seconds
	public void CDCListener() {
		
		while(true) {
			try {
				retrieveData();
				Thread.sleep(5000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// method to connect to the database, retrieve all data, and populate static lists
	void retrieveData() {
		try {
			connection = connectDB();
			
			drivers = retrieveDrivers(connection);
			raceCars = retrieveCars(connection, drivers);
			System.out.println("Yeow");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// method to retrieve driver data from database
	private static List<Driver> retrieveDrivers(Connection con) throws SQLException {
		
		List<Driver> drivers = new ArrayList<>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM driver");
		
	    while(rs.next()) {
	    	
	    	Driver driver = new Driver();
	    	driver.setDriverID(rs.getInt("driverID"));
	    	driver.setName(rs.getString("name"));
	    	driver.setExperienceLevel(rs.getString("experienceLevel"));
	    	
	    	drivers.add(driver);
	    }
	    rs.close();
	    stmt.close();
	    
	    return drivers;
	}
	
	
	// method to retrieve car data from database
	private static List<Car> retrieveCars(Connection con, List<Driver> drivers) throws SQLException {
		
		List<Car> cars = new ArrayList<>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM car");
		
		while(rs.next()) {
			
			Car car = new Car();
			car.setCarID(rs.getInt("carID"));
			car.setTopSpeed(rs.getInt("topSpeed"));
			car.setModel(rs.getString("modelType"));
			
			for(Driver d : drivers) {
				if(d.getDriverID() == car.getDriverID()) {
					car.addDriver(d);
				}
			}
			cars.add(car);
		}
		rs.close();
		stmt.close();
		
		return cars;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
