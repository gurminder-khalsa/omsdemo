	<%@include file="header.jsp"%>
        <!-- Navigation -->
		<c:set var="leaveDetailsForReportingSelected" value="true"/>
        <%@include file="navigation.jsp"%>		
        <div id="page-wrapper">

            <div class="container-fluid">
			<form:form modelAttribute="leaveDetailsForm" action="sendLeaveStatus" method="POST">
                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            <spring:message code="reporting.applied.leaves"/>
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="/leavemgt"><spring:message code="dashboard.title"/></a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> Reporting Leaves
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
                                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> <spring:message code="leave.details"/></h3>
                            </div>
                            <div class="panel-body">
                                <div id="morris-area-chart">
									
									<div class="div-table">
							             <div class="div-table-row-bg">
											<!-- <div class="div-table-col" style="width:50px;"><label for="example-text-input" class="col-xs-12 col-form-label">Select</label></div> -->
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-12 col-form-label"><spring:message code="label.employee.name"/></label></div>
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-8 col-form-label"><spring:message code="label.leave.type"/></label></div>
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-8 col-form-label"><spring:message code="label.start.date"/></label></div>
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-8 col-form-label"><spring:message code="label.end.date"/></label></div>
							                <div class="div-table-col" style="width:150px;"><label for="example-text-input" class="col-xs-8 col-form-label"><spring:message code="label.action"/></label></div>
							             </div>
							            <!-- <div class="div-table-row">
							                  <div class="div-table-col">001</div>
							                <div class="div-table-col">002</div>
							                <div class="div-table-col">003</div>
							            </div> -->
							            <c:forEach var="leaveDetail" items="${leaveDetailsForm.applyLeaves}" varStatus="loopCounter">
							            	<c:set var="tableRowClass" value="div-table-row"/>	
							            	<c:if test="${loopCounter.count % 2 == 0}">
							            		<c:set var="tableRowClass" value="div-table-row-bg"/>	 
											</c:if>								    
											<div class="${tableRowClass}">
												<%-- <div class="div-table-col" style="width:50px;"><div class="col-xs-12"><input type="checkbox" name="checkStatus" id="${leaveDetail.id}"></div></div> --%>
												<div class="div-table-col"><div class="col-xs-12">${leaveDetail.appliedBy.firstName} ${leaveDetail.appliedBy.lastName}</div></div>
												<div class="div-table-col">
													<div class="col-xs-12">
														<c:set var="leaveTypeString" />
														 <c:forEach var="leaveType" items="${leaveTypes}">
														 	<c:if test="${leaveDetail.appliedLeaveType eq leaveType.leaveType}">
														 		<c:set var="leaveTypeString" value="${leaveType.leaveDescription}"/>
														 	</c:if>
														 </c:forEach>
														 <c:out value="${leaveTypeString}"/> 	
													</div>
												</div>
												<div class="div-table-col"><div class="col-xs-8"><fmt:formatDate pattern="MM-dd-yyyy" value="${leaveDetail.startDate}"/></div></div>
												<div class="div-table-col"><div class="col-xs-8"><fmt:formatDate pattern="MM-dd-yyyy" value="${leaveDetail.endDate}"/></div></div>
												<div class="div-table-col" style="width:150px;">
													<div class="col-xs-12">
														<c:choose>
														<c:when test="${leaveDetail.status=='Pending'}">
															<select class="form-control" id="${leaveDetail.id}" name="leaveStatusSelect">
																<c:forEach var="leaveStatus" items="${leaveStatus}">
																	<option value="${leaveStatus.status}">${leaveStatus.status}</option>
																</c:forEach>														
															</select>
														</c:when>
														<c:otherwise>
															${leaveDetail.status}
														</c:otherwise>
														</c:choose>
													</div>
													<form:hidden path="applyLeaves[${loopCounter.index}].id"/>
													<form:hidden id="status${leaveDetail.id}" path="applyLeaves[${loopCounter.index}].status"/>
													
												</div>
											</div>
										</c:forEach>							
							      </div>									
								</div>	
								<div id="morris-area-chart">
									<button type="submit" class="btn btn-sm btn-success" style="margin-top: 10px;" id="apppliedLeaveId" ><spring:message code="button.submit"/></button>								  
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
   <script src="/leavemgt/js/leaveDetailsReporting.js"></script>
   <%@include file="footer.jsp"%>
   