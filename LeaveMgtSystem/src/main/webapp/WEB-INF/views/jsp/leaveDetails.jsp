	<%@include file="header.jsp"%>
        <!-- Navigation -->
		<c:set var="leaveDetailsSelected" value="true"/>
        <%@include file="navigation.jsp"%>		
        <div id="page-wrapper">

            <div class="container-fluid">
			<form:form action="editLeaveForm" method="GET">
                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            <spring:message code="my.applied.leaves"/>
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="/leavemgt"><spring:message code="dashboard.title"/></a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> <spring:message code="my.applied.leaves"/>
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
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-6 col-form-label"><spring:message code="label.serial.number"/></label></div>
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-8 col-form-label"><spring:message code="label.leave.type"/></label></div>
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-8 col-form-label"><spring:message code="label.start.date"/></label></div>
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-8 col-form-label"><spring:message code="label.end.date"/></label></div>
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-8 col-form-label"><spring:message code="label.status"/></label></div>
							             </div>
							            <!-- <div class="div-table-row">
							                  <div class="div-table-col">001</div>
							                <div class="div-table-col">002</div>
							                <div class="div-table-col">003</div>
							            </div> -->
							            <c:forEach var="leaveDetail" items="${leaveDetails}" varStatus="loopCounter">
							            	<c:set var="tableRowClass" value="div-table-row"/>	
							            	<c:if test="${loopCounter.count % 2 == 0}">
							            		<c:set var="tableRowClass" value="div-table-row-bg"/>	 
											</c:if>								    
											<div class="${tableRowClass}">
												<div class="div-table-col"><div class="col-xs-6">${loopCounter.count}</div></div>
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
												<div class="div-table-col"><div class="col-xs-12">${leaveDetail.status} <c:if test="${leaveDetail.status == 'Pending'}"><button type="submit" class="btn btn-xs btn-primary" style="margin-left: 25px;" name="apppliedLeaveId" value="${leaveDetail.id}"><spring:message code="label.edit"/></button></c:if></div></div>
											</div>
										</c:forEach>							
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