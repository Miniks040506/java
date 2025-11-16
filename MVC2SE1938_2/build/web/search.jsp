<%-- 
    Document   : search
    Created on : Oct 22, 2025, 12:17:53 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel="stylesheet" href="css/search_style.css">
    </head>
    <body>
        <font class="welcome">
            Welcome, ${sessionScope.USER_INFO.lastname}
        </font><br/>
        <c:url var="logoutLink" value="DispatchServlet">
            <c:param name="btAction" value="Logout"/>
        </c:url>
        <a href="logout">Sign Out</a>
        <form action="searchLastname">
            <fieldset>
                <legend>Search</legend>

                <label for="searchValue">Name to search for</label>
                <input id="searchValue" type="text" name="txtSearchValue" 
                       value="${param.txtSearchValue}" placeholder="Enter name..."/>

                <input type="submit" value="Search" name="btAction" />
            </fieldset>
        </form><br/>
        
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateAccount" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.lastname}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.isAdmin}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="deleteAccount">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${param.txtSearchValue}" />
                                </td>
                            </tr>
                         </form>                           
                        </c:forEach> 
                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <h2 class="error-message">
                    <font color="red">
                    No record is matched!!!
                    </font>
                </h2>
            </c:if>
        </c:if>
        
        <%--<h1>Welcome to BD Servlet</h1>
        <form action="DispatchServlet">
            Name <input type="text" name="txtSearchValue" 
                        value="<%= request.getParameter("txtSearchValue") %>" /><br>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = 
                        (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) { // FOUND
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count = 0;
                                for (RegistrationDTO dto : result) {
                                    String urlRewriting = "DispatchServlet"
                                    + "?btAction=delete"
                                    + "&pk=" + dto.getUsername()
                                    + "&lastSearchValue=" + searchValue; //refresh giao dien
                                    %>
                            <tr>
                                <td>
                                    <%= count++ %>
                                </td>
                                <td>
                                    <%= dto.getUsername() %>
                                </td>
                                <td>
                                    <%= dto.getPassword() %>
                                </td>
                                <td>
                                    <%= dto.getFullname() %>
                                </td>
                                <td>
                                    <%= dto.isRole() %>
                                </td>
                                <td>
                                    <a href ="<%= urlRewriting %>">Delete</a>
                                </td>
                            </tr>        
                            <%
                                }
                            %>
                        </tbody>
                    </table>

        <%
                } else { // NOT FOUND
                    %>
                    <h2>
                        <font color="red">
                            No record is matched!!!
                        </font>
                    </h2>
        <%
                }
            } // search value is valid
        %>--%>
    </body>
</html>
