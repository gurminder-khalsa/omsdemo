jQuery(document).ready(function($) {
		$("#apppliedLeaveId").click(function(event) {
			var leaveStatusMap = {};
			jQuery("[name='leaveStatusSelect']").each(function() {
				$("#status"+this.id).val(this.value);
			});
			

		});
});

function sendLeaveStatus(data) {
	
	//data["query"] = $("#query").val();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "http://localhost:8181/leavemgt/lms/manager/sendLeaveStatus",
		data : JSON.stringify(data),
		dataType : 'json',
		timeout : 100000,
		success : function(data, textStatus, jqXHR) {
			console.log("SUCCESS: ", data);
		},
		error : function(e) {
			console.log("ERROR: ", e);
			
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}