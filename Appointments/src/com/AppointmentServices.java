package com;
import com.Appointment;


import javax.ws.rs.*; 

import javax.ws.rs.core.MediaType;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.parser.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("appointments")
public class AppointmentServices {
    
	Appointment AppointmentObj = new Appointment();
	@GET
	@Path("appointment")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointment()
	{
	return AppointmentObj.readAppointment();
	}
	
	@POST
	@Path("appointment")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("AppointmentCode") String AppointmentCode,
			@FormParam("DoctorName") String DoctorName,
			@FormParam("PatientName") String PatientName,
			@FormParam("AppointmentDate") String AppointmentDate)
	{
	 String output = AppointmentObj.insertAppointment(AppointmentCode, DoctorName, PatientName, AppointmentDate);
	return output;
	}

	@PUT
	@Path("appointment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String AppointmentData)
	{
	//Convert the input string to a JSON object
	 JsonObject appointmentObject = new JsonParser().parse(AppointmentData).getAsJsonObject();
	//Read the values from the JSON object
	 String AppointmentID = appointmentObject.get("AppointmentID").getAsString();
	 String AppointmentCode = appointmentObject.get("AppointmentCode").getAsString();
	 String DoctorName = appointmentObject.get("DoctorName").getAsString();
	 String PatientName = appointmentObject.get("PatientName").getAsString();
	 String AppointmentDate = appointmentObject.get("AppointmentDate").getAsString();
	 String output = AppointmentObj.updateAppointment(AppointmentID,AppointmentCode,DoctorName,PatientName,AppointmentDate);
	return output;
	} 
	
	@DELETE
	@Path("appointment")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String AppointmentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(AppointmentData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String AppointmentID = doc.select("AppointmentID").text();
	 String output = AppointmentObj.deleteAppointment(AppointmentID);
	return output;
	}
	
}
