	<%@include file="header.jsp"%>
        <!-- Navigation -->
		<c:set var="leaveDetailsSelected" value="true"/>
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
                                <i class="fa fa-bar-chart-o"></i> Apply Leave
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
                                <h3 class="panel-title"><i class="fa fa-bar-chart-o"></i> Please enter leave details</h3>
                            </div>
                            <div class="panel-body">
                                <div id="morris-area-chart">
									
									<div class="div-table">
							             <div class="div-table-row-bg">
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-6 col-form-label">Sr #</label></div>
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-8 col-form-label">Leave Type</label></div>
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-8 col-form-label">Start Date</label></div>
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-8 col-form-label">End Date</label></div>
							                <div class="div-table-col"><label for="example-text-input" class="col-xs-8 col-form-label">Status</label></div>
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
												<div class="div-table-col"><div class="col-xs-6">${leaveDetail.appliedLeaveType}</div></div>
												<div class="div-table-col"><div class="col-xs-8"><fmt:formatDate pattern="MM-dd-yyyy" value="${leaveDetail.startDate}"/></div></div>
												<div class="div-table-col"><div class="col-xs-8"><fmt:formatDate pattern="MM-dd-yyyy" value="${leaveDetail.endDate}"/></div></div>
												<div class="div-table-col"><div class="col-xs-12">${leaveDetail.status} <c:if test="${leaveDetail.status == 'Pending'}"><button type="button" class="btn btn-xs btn-primary" style="margin-left: 25px;">Edit</button></c:if></div></div>
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