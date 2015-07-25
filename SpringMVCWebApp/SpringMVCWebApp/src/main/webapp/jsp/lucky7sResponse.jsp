<%-- 
    Document   : response
    Created on : Jul 9, 2015, 3:57:44 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="http://getbootstrap.com/assets/ico/favicon.ico">

        <title>Lucky 7's Results</title>

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
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/lucky7s">Lucky 7's</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/factorizer">Factorizer</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/intercalc">Interest Calculator</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/flooring">Flooring Calculator</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/tipCalc">Tip Calculator</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/unitConverter">Unit Converter</a></li>
                 </ul>    
            </div>
            <h2>Lucky 7's</h2>
            
        </div>

        <div class="container">

        <h1>Lucky 7's Game Result:</h1>
        
        ${message}<br /><br />
        Your ante was ${param.myAnswer}<br />
        You are broke after ${rolls} rolls. <BR /> You should have quit after ${timeToStop} rolls when you had $${maxMoney}. 
                <BR />Next time find a game you can actually win.
                <br/>
                <br/>
                <form action="lucky7s" method="get">
                    <input type="submit" value="Play Again" />
                </form>
        
    </body>
</html>
