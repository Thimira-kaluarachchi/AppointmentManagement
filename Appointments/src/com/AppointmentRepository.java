//package com;
//
//import java.sql.*;
//
//public class AppointmentRepository {
//	// A common method to connect to the DB
//	private Connection connect() {
//		Connection con = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//
//			// Provide the correct details: DBServer/DBName, username, password
//
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/health_care?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return con;
//	}
//
//	public String insertAppointment(String AppointmentCode, String DoctorName, String PatientName, String AppointmentDate)  
//	{   
//		String output = ""; 
//	 
//		try   
//		{    
//			Connection con = connect(); 
//	 
//			if (con == null)    
//			{return "Error while connecting to the database for inserting appointment."; } 
//	 
//			// create a prepared statement    
//			String query = " insert into appointments(`AppointmentID`,`AppointmentCode`,`DoctorName`,`PatientName`,`AppointmentDate`)" + " values (?, ?, ?, ?, ?)"; 
//	 
//			PreparedStatement preparedStmt = con.prepareStatement(query); 
//	 
//			// binding values    
//			preparedStmt.setInt(1, 0);    
//			preparedStmt.setString(2, AppointmentCode);    
//			preparedStmt.setString(3, DoctorName);    
//			preparedStmt.setString(4, PatientName);    
//			preparedStmt.setString(5, AppointmentDate);
//			
//			// execute the statement    
//			preparedStmt.execute();    
//			con.close(); 
//	   
//			String newAppointments = readAppointment(); 
//			output =  "{\"status\":\"success\", \"data\": \"" +        
//					newAppointments + "\"}";    
//		}   
//		catch (Exception e)   
//		{    
//			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the appointment.\"}";  
//			System.err.println(e.getMessage());   
//		} 
//		
//	  return output;  
//	} 
//
//	public String readAppointment() {
//		String output = "";
//		try {
//			Connection con = connect();
//			if (con == null) {
//				return "Error while connecting to the database for reading appointment.";
//			}
//			// Prepare the html table to be displayed
//			 output = "<table border='1'><tr><th>AppointmentCode</th><th>DoctorName</th><th>PatientName</th>"+ "<th>AppointmentDate</th><th>Update</th><th>Remove</th></tr>";
//			String query = "select * from appointments";
//			Statement stmt = (Statement) con.createStatement();
//			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
//			
//			// iterate through the rows in the result set
//			while (rs.next()) {
//				String AppointmentID = Integer.toString(rs.getInt("AppointmentID"));
//				String AppointmentCode = rs.getString("AppointmentCode");
//				String DoctorName = rs.getString("DoctorName");
//				String PatientName = rs.getString("PatientName");
//				String AppointmentDate = rs.getString("AppointmentDate");
//				// Add into the html table
//				output += "<tr><td><input id='hidAppointmentIDUpdate'name='hidAppointmentIDUpdate' type='hidden'value='" + AppointmentID + "'>" + AppointmentCode + "</td>";
//				output += "<td>" + DoctorName + "</td>";
//				output += "<td>" + PatientName + "</td>";
//				output += "<td>" + AppointmentDate + "</td>";
//				
//				// buttons
//				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-appointmentid='"+ AppointmentID + "'>" + "</td></tr>"; 			}
//			con.close();
//			
//			// Complete the html table
//			output += "</table>";
//		} catch (Exception e) {
//			output = "Error while reading the appointment.";
//			System.err.println(e.getMessage());
//		}
//		return output;
//	}
//
//	public String updateAppointment(String AppointmentID, String AppointmentCode, String DoctorName, String PatientName, String AppointmentDate) {
//		String output = "";
//		try {
//			Connection con = connect();
//			if (con == null) {
//				return "Error while connecting to the database for updating appointment.";
//			}
//			
//			// create a prepared statement
//			String query = "UPDATE appointments SET AppointmentCode=?,DoctorName=?,PatientName=?,AppointmentDate=? WHERE AppointmentID=?";
//			PreparedStatement preparedStmt = con.prepareStatement(query);
//
//			// binding values
//			preparedStmt.setString(1, AppointmentCode);
//			preparedStmt.setString(2, DoctorName);
//			preparedStmt.setString(3, PatientName);
//			preparedStmt.setString(4, AppointmentDate);
//			preparedStmt.setInt(5, Integer.parseInt(AppointmentID));
//			
//			// execute the statement
//			preparedStmt.execute();
//			con.close();
//			
//			//output = "Updated successfully";
//			
//			String newHospitals = readAppointment();
//			 output = "{\"status\":\"success\", \"data\": \"" +
//					 newHospitals + "\"}"; 
//			
//		} catch (Exception e) {
//			output = "{\"status\":\"error\", \"data\": \"Error while updating the appointment.\"}";
//			 System.err.println(e.getMessage()); 
//		}
//		return output;
//	}
//
//	public String deleteAppointment(String AppointmentID)  
//	{   
//		String output = ""; 
//	 
//		try   
//		{    
//			Connection con = connect(); 
//	 
//			if (con == null)    
//			{return "Error while connecting to the database for deleting."; } 
//	 
//			// create a prepared statement    
//			String query = "delete from appointments where AppointmentID=?"; 
//	 
//			PreparedStatement preparedStmt = con.prepareStatement(query); 
//	 
//			// binding values    
//			preparedStmt.setInt(1, Integer.parseInt(AppointmentID)); 
//	 
//			// execute the statement    
//			preparedStmt.execute();    
//			con.close(); 
//	 
//			String newAppointment = readAppointment();    
//			output = "{\"status\":\"success\", \"data\": \"" +       
//					newAppointment + "\"}";    
//		}   
//		catch (Exception e)   
//		{    
//			output = "Error while deleting the appointment.";    
//			System.err.println(e.getMessage());   
//		} 
//	 
//		return output;  
//	}
//	 
//	 
//}
//
//
//
//
