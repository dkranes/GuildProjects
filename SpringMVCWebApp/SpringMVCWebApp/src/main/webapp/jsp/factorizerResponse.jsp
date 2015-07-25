<%-- 
    Document   : response
    Created on : Jul 13, 2015, 12:11:44 AM
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

        <title>Factorizer Results</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/starter-template.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy this line! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
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
                <li role="presentation"><a href="${pageContext.request.contextPath}/lucky7s">Lucky 7's</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/factorizer">Factorizer</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/intercalc">Interest Calculator</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/flooring">Flooring Calculator</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/tipCalc">Tip Calculator</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/unitConverter">Unit Converter</a></li>
                 </ul>    
            </div>
            <h2>Lucky 7's</h2>
            
        </div>

        <div class="container">

        <h1>Factorization Complete:</h1>
        
        
        You factorized the number ${param.myAnswer}<br />
        The factors of ${param.myAnswer} are: ${factors}<br />
        ${param.myAnswer} is ${isOrIsNotPerfect} a perfect number.<br />
        ${param.myAnswer} is ${isOrIsNotPrime} a prime number.
        <br/>
        <br/>
                <form action="factorizer" method="get">
                    <input type="submit" value="Factorize Again" />
                </form>
                
    </body>
</html>
