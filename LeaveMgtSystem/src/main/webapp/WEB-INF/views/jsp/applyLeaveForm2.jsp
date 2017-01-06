	<%@include file="header.jsp"%>
        <!-- Navigation -->
		<c:set var="applyLeaveSelected" value="true"/>
        <%@include file="navigation.jsp"%>		
        <div id="page-wrapper">

            <div class="container-fluid">
			<form:form modelAttribute="applyLeave" action="submitLeaveForm" method="POST">
                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Apply Leave
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Apply Leave
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <!-- Morris Charts -->               

                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-edit"></i> Please enter leave details</h3>
                            </div>
                            <div class="panel-body">
                                <div id="morris-area-chart">
									<div class="form-group row">
										<label for="example-text-input" class="col-xs-2 col-form-label">Leave Type:</label>
										<div class="col-xs-3">	
											<c:choose>
												<c:when test="${empty  applyLeave.id}">				
													<form:select path="appliedLeaveType" class="form-control">
														<form:options items="${leaveTypes}" itemValue="leaveType" itemLabel="leaveDescription"/>
													</form:select> 
													
												</c:when>
												<c:otherwise>
													<c:set var="leaveTypeString" />
													 <c:forEach var="leaveType" items="${leaveTypes}" varStatus="loopCounter">
													 	<c:if test="${applyLeave.appliedLeaveType eq leaveType.leaveType}">
													 		<c:set var="leaveTypeString" value="${leaveType.leaveDescription}"/>
													 	</c:if>
													 </c:forEach>
													 <c:out value="${leaveTypeString}"/>
													<form:hidden path="appliedLeaveType"/>
													
												</c:otherwise>
											</c:choose>
										</div>	
										<div class="col-xs-4"><font color="red"><form:errors path="appliedLeaveType" cssClass="error" element="div"/></font></div>									
									</div>
									
									<div class="form-group row">
										<label for="example-text-input" class="col-xs-2 col-form-label">Start Date:</label>
										<div class="col-xs-3" >					
											<!--<form:input path="startDate" class="datepicker form-control" data-date-format="mm/dd/yyyy" /> -->
											<form:input path="startDate" class="form-control" name="startDate" id="startDate" type="date" data-date-format="MM/DD/YYYY"/>
										</div>
									</div>
									<div class="form-group row">
										<label for="example-text-input" class="col-xs-2 col-form-label">End Date:</label>
										<div class="col-xs-3">					
											<form:input path="endDate" class="form-control" name="endDate" id="endDate" type="date" data-date-format="MM/DD/YYYY"/>
										</div>	
									</div>
									
									<div class="form-group row">
										<label for="example-text-input" class="col-xs-2 col-form-label">Leave Reason:</label>
										<div class="col-xs-3">					
											<form:input path="leaveReason" class="form-control" /> 
										</div>	
									</div>
									<div class="form-buttons">
										<div class="col-xs-2 col-sm-2 col-md-2">
											<input type="submit" id="save" name="actionButton" value="Save" class="btn btn-lg btn-success btn-block"/>&#160;
											 
										</div>
										<c:if test="${not empty  applyLeave.id}">		
											<div class="col-xs-2 col-sm-2 col-md-2">
												<input type="submit" name="actionButton" value="Cancel" class="btn btn-lg btn-success btn-block"/>&#160;										 
											</div>	
										</c:if>					 	        
										
									</div>
								</div>
                            </div>
                        </div>
                    </div>
                </div>
               <form:hidden path="id" name="id"/>
				</form:form>
            </div>
			
            <!-- /.container-fluid -->
			
        </div>
		
        <!-- /#page-wrapper -->
   <%@include file="footer.jsp"%>