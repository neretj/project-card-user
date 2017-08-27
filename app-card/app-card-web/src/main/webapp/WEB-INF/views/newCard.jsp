<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Register</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

<div class="container">
	
	<c:if test="${pageContext.request.userPrincipal.name != null}">
	
	<jsp:include page="layout.jsp" />
	
    <form:form method="POST" modelAttribute="cardForm" class="form-signin">
        <h2 class="form-signin-heading">Create a new Card</h2>
        <spring:bind path="nameCard">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="nameCard" class="form-control" placeholder="NameCard"
                            autofocus="true"></form:input>
                <form:errors path="nameCard"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="numberCard">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="numberCard" type="text" maxlength="16" pattern="\d*" class="form-control" placeholder="NumberCard"></form:input>
                <form:errors path="numberCard"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="expireDate">
            <div class="form-group ${status.error ? 'has-error' : ''}">
				<form:input type="month" path="expireDate" value="${theFormattedDate}"></form:input>
				<form:errors path="expireDate"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        
    </form:form>
    
    </c:if>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>