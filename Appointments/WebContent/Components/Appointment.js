$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
//Save
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	
	
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateAppointmentForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

//Valid
	var type = ($("#hidAppointmentIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "AppointmentAPI",
		type : type,
		data : $("#formAppointment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onAppointmentSaveComplete(response.responseText, status);
		}
	});
// $("#formAppointment").submit();
});
function onAppointmentSaveComplete(response, status) {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success") {
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		location.reload();	
		$("#divAppointmentsGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error") {
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}

	else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidAppointmentIDSave").val("");
	$("#formAppointment")[0].reset();

}

// UPDATE
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			
			$("#hidAppointmentIDSave").val(
					$(this).closest("tr").find('#hidAppointmentIDUpdate').val());
			$("#AppointmentCode").val($(this).closest("tr").find('td:eq(0)').text());
			$("#DoctorName").val($(this).closest("tr").find('td:eq(1)').text());
			$("#PatientName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#AppointmentDate").val($(this).closest("tr").find('td:eq(3)').text());
		});

// Remove
$(document).on("click", ".btnRemove", function(event) 
		{  	
			$.ajax(  
			{   
				url : "AppointmentAPI",   
				type : "DELETE",   
				data : "AppointmentID=" + $(this).data("appointmentid"),   
				dataType : "text",   
				complete : function(response, status)   
				{    
					onAppointmentDeleteComplete(response.responseText, status);   
				}  
			}); 
		}); 

		function onAppointmentDeleteComplete(response, status) 
		{  
			if (status == "success")  
			{   
				var resultSet = JSON.parse(response); 

				if (resultSet.status.trim() == "success")   
				{    
					$("#alertSuccess").text("Successfully deleted.");    
					$("#alertSuccess").show(); 
					location.reload();	
					$("#divAppointmentsGrid").html(resultSet.data);   
				} else if (resultSet.status.trim() == "error")   
				{    
					$("#alertError").text(resultSet.data);    
					$("#alertError").show();   
				}

			} else if (status == "error")  
			{   
				$("#alertError").text("Error while deleting.");   
				$("#alertError").show();  
			} else  
			{   
				$("#alertError").text("Unknown error while deleting..");   
				$("#alertError").show();  
			}
		}
// CLIENTMODE
function validateAppointmentForm() {
	// CODE
	if ($("#AppointmentCode").val().trim() == "") {
		return "Insert Appointment Code.";
	}
	// DoctorName
	if ($("#DoctorName").val().trim() == "") {
		return "Insert Doctor Name.";
	}

	// PatientName
	if ($("#PatientName").val().trim() == "") {
		return "Insert Patient Name.";
	}
	// AppointmentDate
	if ($("#AppointmentDate").val().trim() == "") {
		return "Insert Appointment Date.";
	}
	return true;
}