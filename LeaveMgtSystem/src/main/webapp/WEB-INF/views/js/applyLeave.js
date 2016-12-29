jQuery(document).ready(function($) {
	$("#apply-form").submit(function(event) {

		// Prevent the form from submitting via the browser.
		event.preventDefault();
		sendApplyLeave();

	});
});

function sendApplyLeave() {
	var data = {
			leaveType: "PL",
			startDate: "12/19/2016",
			endDate: "12/19/2016",
			leaveReason: "ABC"
	}
	//data["query"] = $("#query").val();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "http://localhost:8181/leavemgt/lms/submitleave",
		data : JSON.stringify(data),
		dataType : 'json',
		timeout : 100000,
		success : function(data, textStatus, jqXHR) {
			console.log("SUCCESS: ", data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}