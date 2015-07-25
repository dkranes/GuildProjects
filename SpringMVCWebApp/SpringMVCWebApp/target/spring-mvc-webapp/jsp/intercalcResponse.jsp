<%-- 
    Document   : response
    Created on : Jul 13, 2015, 12:11:44 AM
    Author     : apprentice
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="http://getbootstrap.com/assets/ico/favicon.ico">

        <title>Interest Calculator Results</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/starter-template.css" rel="stylesheet">

    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1>Software Craftsmanship Guild Java Cohort</h1>
                <h1>JSP Site Lab</h1>
            </div>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home.jsp">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/lucky7s">Lucky 7's</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/factorizer">Factorizer</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/intercalc">Interest Calculator</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/flooring">Flooring Calculator</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/tipCalc">Tip Calculator</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/unitConverter">Unit Converter</a></li>
                </ul>    
            </div>

            <div class="container">

                <h1>Interest Calculation Complete:</h1>


                <b>You calculated interest for an initial balance of: $${originalBalance}</b><br />
                <b>Duration:</b> ${years} years<br/>
                <b>Interest Rate:</b> ${interestRate}%<br/>
                <b>Interest was compounded:</b> ${periods} times per year<br/>
                <b>Interest earned:</b> $${interestEarned}<br/>
                <b>Ending balance:</b> $${newBalance}<br/>
                <br/>
                <b>Interest earned per year:</b><br/>



                <c:forEach var="type" items="${annualInterest}">

                    Year ${type.key} Interest Earned: $${type.value}<br/>

                </c:forEach>

                <br/>
                <form action="intercalc" method="get">
                    <input type="submit" value="Calculate Again" />
                </form>

                </body>
                </html>
