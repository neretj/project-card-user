<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <title>List Cards</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    
</head>

<body>

<div class="container">

	<c:if test="${pageContext.request.userPrincipal.name != null}">
	
		<jsp:include page="layout.jsp" />
		
		<form:form method="POST" modelAttribute="listCardForm" class="form-signin">
			<spring:bind path="searchNumber">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" maxlength="16" pattern="\d*" path="searchNumber" class="form-control" placeholder="SearchNumber"
                            autofocus="true"></form:input>
                <form:errors path="searchNumber"></form:errors>
            </div>
        </spring:bind>
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
	        
        </form:form>        
	    
	    <div align="center">
	   	<c:choose>
		    <c:when test="${fn:length(cards) gt 0}">
		       <table class="table" border="1" cellpadding="5">
		            <caption><h2>List of cards</h2></caption>	            
		            <tr>
		                <th>Card name</th>
		                <th>Card number</th>
		                <th>Expire Date</th>
		            </tr>
		            <c:forEach var="card" items="${cards}">
		                <tr>
		                    <td><c:out value="${card.nameCard}" /></td>
		                    <td><c:out value="${card.numberCard}" /></td>
		                    <td>
		                    	<fmt:formatDate var="expireDate" pattern='yyyy/MM' value='${card.expireDate}'/>
		                    	<c:out value="${expireDate}" />
		                    </td>
		                </tr>
		            </c:forEach>
		        </table>
		   	</c:when>  
		    <c:otherwise>
				<div style="align:center">
					<span>${message}</span>
				</div>
		    </c:otherwise>
		    </c:choose>
	    </div>
	    
	</c:if>
</div>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>