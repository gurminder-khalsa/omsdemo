<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><spring:message code="application.title"/></title>

    <!-- Bootstrap Core CSS -->
    <link href="/leavemgt/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/leavemgt/css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/leavemgt/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/leavemgt/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<script src="/leavemgt/js/jquery.js"></script>
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	.div-table{
		  display:table;         
		  width:100%;         
		  background-color:#eee;         
		  border:1px solid  #666666;         
		  border-spacing:5px;/*cellspacing:poor IE support for  this*/
		}
		.div-table-row{
		  display:table-row;
		  width:100%;
		  clear:both;
		}
		.div-table-col{
		  float:left;/*fix for  buggy browsers*/
		  display:table-column;         
		  width:20%;         
		  
		}		
		.div-table-row-bg{
		  display:table-row;
		  width:100%;
		  clear:both;
		  background-color:#ccc;  
		}
		
    </style>

</head>

<body>

    <div id="wrapper">