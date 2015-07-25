<%-- 
    Document   : displayAddressBookNoAjax
    Created on : Jul 19, 2015, 10:34:03 PM
    Author     : DanKranefuss
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Address Book</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Address Book</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/search">Search</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayAddressBookNoAjax">Address Book (No Ajax)</a></li>
                </ul>    
            </div>              
        </div>

                    <div class="container">
                        <h1>Address Book</h1>
                        
                        <a href="displayNewContactFormNoAjax">Add a Contact</a><br/>
                        <hr/>

                        
                        <c:forEach var="contact" items="${addressBook}">
                            
                            <s:url value="deleteContactNoAjax"
                                   var="deleteContact_url">
                                <s:param name="contactId" value="${contact.contactId}" />
                            </s:url>
                            
                            <s:url value="displayEditContactFormNoAjax"
                                   var="editContact_url">
                                <s:param name="contactId" value="${contact.contactId}" />
                            </s:url>
                          
                            Name: ${contact.firstName} ${contact.lastName} | 
                            <a href="${deleteContact_url}">Delete</a> | 
                            <a href="${editContact_url}">Edit</a><br/>
                            Street: ${contact.street}<br/>
                            City: ${contact.city}<br/>
                            State: ${contact.state}<br/>
                            Zip: ${contact.zip}<br/>
                            <hr>
                        </c:forEach>
                    </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
