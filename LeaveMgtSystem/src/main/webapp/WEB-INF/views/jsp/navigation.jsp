<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"><spring:message code="application.title"/></a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                
               <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${userDetails.firstName} ${userDetails.lastName} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/leavemgt/logout"><i class="fa fa-fw fa-power-off"></i> <spring:message code="label.logout"/></a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">					
                    <li <c:if test="${homeSelected}"> class="active" </c:if>>
                        <a href="/leavemgt"><i class="fa fa-fw fa-dashboard"></i> <spring:message code="dashboard.title"/></a>
                    </li>					
                    <li <c:if test="${applyLeaveSelected}"> class="active" </c:if>>
                        <a href="/leavemgt/lms/applyLeaveForm"><i class="fa fa-fw fa-edit"></i> <spring:message code="apply.leave"/></a>
                    </li>
                    <li <c:if test="${leaveDetailsSelected}"> class="active" </c:if>>
                        <a href="/leavemgt/lms/leaveDetails"><i class="fa fa-fw fa-table"></i> <spring:message code="my.applied.leaves"/></a>
                    </li>
                    <sec:authorize access="hasRole('ROLE_MANAGER')">
					    <li <c:if test="${leaveDetailsForReportingSelected}"> class="active" </c:if>>
	                        <a href="/leavemgt/lms/manager/leaveDetails"><i class="fa fa-fw fa-table"></i> <spring:message code="reporting.applied.leaves"/></a>
	                    </li>
					</sec:authorize>
                    
                    <!-- <li>
                        <a href="bootstrap-elements.html"><i class="fa fa-fw fa-desktop"></i> Bootstrap Elements</a>
                    </li>
                    <li>
                        <a href="bootstrap-grid.html"><i class="fa fa-fw fa-wrench"></i> Bootstrap Grid</a>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Dropdown <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="#">Dropdown Item</a>
                            </li>
                            <li>
                                <a href="#">Dropdown Item</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="blank-page.html"><i class="fa fa-fw fa-file"></i> Blank Page</a>
                    </li>
                    <li>
                        <a href="index-rtl.html"><i class="fa fa-fw fa-dashboard"></i> RTL Dashboard</a>
                    </li> -->
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>