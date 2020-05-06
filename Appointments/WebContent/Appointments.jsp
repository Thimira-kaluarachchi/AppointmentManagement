<%@page import="com.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment Management</title>
<link rel="stylesheet" href="views/boostrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Appointment.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Appointment Management</h1>
				<form id="formAppointment" name="formAppointment">
					Appointment code: <input id="AppointmentCode" name="AppointmentCode" type="text"
						class="form-control form-control-sm"> <br> 
					Doctor  Name: <input id="DoctorName" name="DoctorName" type="text"
						class="form-control form-control-sm"> <br>
					Patient Name: <input id="PatientName" name="PatientName" type="text"
						class="form-control form-control-sm"> <br>
					Appointment Date: <input id="AppointmentDate" name="AppointmentDate" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidAppointmentIDSave" name="hidAppointmentIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divAppointmentGrid">
					<%
						Appointment appointmentObj = new Appointment();
					out.print(appointmentObj.readAppointment());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>