<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Welcome</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>
	<div>
		<form id="logoutForm" method="POST" action="${contextPath}/logout">
	    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
		<div style="float:right"><h5> Welcome ${pageContext.request.userPrincipal.name} | <a class="btn btn-default" onclick="document.forms['logoutForm'].submit()">Logout</a></h5></div>
	 		 
	 	<form id="newCardForm" action="${contextPath}/newCard">
	 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	
		<a class="btn btn-default" onclick="document.forms['newCardForm'].submit()">New Card</a></h2>
	
		<form id="listCardForm" action="${contextPath}/listCards">
	 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	
		<a class="btn btn-default" onclick="document.forms['listCardForm'].submit()">List Card</a></h2>
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>