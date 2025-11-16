<%-- 
    Document   : createAccount
    Created on : Nov 3, 2025, 3:28:59 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%-- 
    Document   : createAccount
    Created on : Nov 3, 2025, 3:28:59 PM
    Author     : Admin
--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
        <link rel="stylesheet" href="css/SignIn_style.css"/> 
    </head>
    <body>
        <form action="createAccount" method="POST">
            <fieldset>
                <legend>Create Account</legend>
                <c:set var="errors" value="${requestScope.CREATE_ERRORS}" />
                
                <label for="username">Username*</label>
                <input id="username" type="text" name="txtUsername" 
                       value="${param.txtUsername}" placeholder="(6-20 chars)"/>
                
                <div class="error-placeholder">
                    <c:if test="${not empty errors.userNameLengthErr}">
                        <font color="red">${errors.userNameLengthErr}</font><br/>
                    </c:if>
                    <c:if test="${not empty errors.usernameIsExisted}">
                        <font color="red">${errors.usernameIsExisted}</font><br/>
                    </c:if>
                </div>

                <label for="password">Password*</label>
                <input id="password" type="password" name="txtPassword" value="" 
                       placeholder="(6-30 chars)" />
                
                <div class="error-placeholder">
                    <c:if test="${not empty errors.passwordLengthErr}">
                        <font color="red">${errors.passwordLengthErr}</font><br/>
                    </c:if>
                </div>

                <label for="confirm">Confirm*</label>
                <input id="confirm" type="password" name="txtConfirm" value="" 
                       placeholder="confirm" />
                
                <div class="error-placeholder">
                    <c:if test="${not empty errors.confirmNotMatched}">
                        <font color="red">${errors.confirmNotMatched}</font><br/>
                    </c:if>
                </div>

                <label for="fullname">Full name*</label>
                <input id="fullname" type="text" name="txtFullname" 
                       value="${param.txtFullname}" placeholder="(2-50 chars)" />
                
                <div class="error-placeholder">
                    <c:if test="${not empty errors.fullnameLengthErr}">
                        <font color="red">${errors.fullnameLengthErr}</font><br/>
                    </c:if>
                </div>
                
                <div class="button-group">
                    <input type="submit" value="Create new Account" name="btAction" />
                    <input type="reset" value="Reset" />
                </div>
            </fieldset>
        </form>
        <br/>
        <a href="login.html">Click here to go back</a>
    </body>
</html>