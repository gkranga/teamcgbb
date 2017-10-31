<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="header-container">
 
    <div class="site-name">Online Shop</div>
 
    <div class="header-bar">
        <c:url var="logoutUrl" value="/logout" />
        <form id="logout" action="${logoutUrl}" method="post" >
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
       <c:if test="${pageContext.request.userPrincipal.name != null}">
        Hello
           <a href="#">
                ${pageContext.request.userPrincipal.name} </a>
        &nbsp;|&nbsp;
            <!-- <a href="${pageContext.request.contextPath}/logout">Logout</a> -->
            <a href="javascript:document.getElementById('logout').submit()">Logout</a>
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <a href="${pageContext.request.contextPath}/login">Login</a>
            |
            <a href="${pageContext.request.contextPath}/signUp">Sign Up</a>
        </c:if>
    </div>
</div>