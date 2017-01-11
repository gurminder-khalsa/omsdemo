	<%@include file="header.jsp"%>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">	 
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	  
        <!-- Navigation -->
		<c:set var="homeSelected" value="true"/>
        <%@include file="navigation.jsp"%>		
        <div id="page-wrapper">

            <div class="container-fluid">
			<form:form action="submitLeaveForm" method="POST">
                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            <spring:message code="label.user.profile"/>
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html"><spring:message code="dashboard.title"/></a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> <spring:message code="label.user.profile"/>
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
                                <h3 class="panel-title"><i class="fa fa-edit"></i> <spring:message code="user.details.text"/></h3>
                            </div>
                            <div class="panel-body">
                                <div id="morris-area-chart">
									<div class="form-group row">
										<label for="example-text-input" class="col-xs-2 col-form-label"><spring:message code="label.first.name"/> :</label>
										<div class="col-xs-3">	
											${userDetails.firstName}
										</div>	
										<div class="col-xs-4"><font color="red"><form:errors path="appliedLeaveType" cssClass="error" element="div"/></font></div>									
									</div>
									
									<div class="form-group row">
										<label for="example-text-input" class="col-xs-2 col-form-label"><spring:message code="label.last.name"/> :</label>
										<div class="col-xs-3" >					
											${userDetails.lastName}
										</div>
									</div>
									<div class="form-group row">
										<%-- <label for="example-text-input" class="col-xs-2 col-form-label"><spring:message code="label.end.date"/>:</label>
										<div class="col-xs-3">					
											<form:input path="endDate" class="form-control" name="endDate" id="endDate"  />
										</div>	 --%>
										<div class="col-lg-6">
										 <div class="panel panel-primary">
				                            <div class="panel-heading">
				                                <h3 class="panel-title"><spring:message code="leave.details"/></h3>
				                            </div>
				                            <div class="panel-body">
				                                <div class="div-table">
										             <div class="div-table-row-bg">
										                <div class="div-table-col" style="width:60%"><label for="example-text-input" class="col-xs-12 col-form-label"><spring:message code="label.leave.type"/></label></div>
										                <div class="div-table-col" style="width:40%"><label for="example-text-input" class="col-xs-12 col-form-label"><spring:message code="label.leaves.available"/></label></div>
										             </div>									           
										            <c:forEach var="userLeave" items="${userLeaves}" varStatus="loopCounter">
										            	<c:set var="tableRowClass" value="div-table-row"/>	
										            	<c:if test="${loopCounter.count % 2 == 0}">
										            		<c:set var="tableRowClass" value="div-table-row-bg"/>	 
														</c:if>								    
														<div class="${tableRowClass}">
															<div class="div-table-col" style="width:60%"><div class="col-xs-12">${userLeave.leaveType.leaveDescription}</div></div>
															<div class="div-table-col" style="width:40%;text-align:center"><div class="col-xs-12">${userLeave.numberOfLeaves}</div></div>
														</div>
													</c:forEach>							
							      				</div>
				                            </div>
				                        </div>
										</div>
									</div>
								</div>
                            </div>
                        </div>
                    </div>
                </div>               
				</form:form>
            </div>
			
            <!-- /.container-fluid -->
			
        </div>
		
        <!-- /#page-wrapper -->
   <%@include file="footer.jsp"%>