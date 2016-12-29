<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Leave Management System</title>
<script src="/leavemgt/js/jquery.js"></script>
<script type="text/javascript" src="/leavemgt/js/applyLeave.js"></script>
</head>

<body>
	<h1>This is Spring MVC Training</h1>
	<p>
		<c:if test="${not empty name}">
			Hello ${name}
		</c:if>

		<c:if test="${empty name}">
			Welcome Welcome!
		</c:if>
    </p>  
    
    <form class="form-horizontal" id="apply-form">
    		<button type="submit" id="bth-search" class="btn btn-primary btn-lg">Search</button>
    </form>  

</body>
</html>