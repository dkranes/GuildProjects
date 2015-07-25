<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

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
                 <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/home.jsp">Home</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/lucky7s">Lucky 7's</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/factorizer">Factorizer</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/intercalc">Interest Calculator</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/flooring">Flooring Calculator</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/tipCalc">Tip Calculator</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/unitConverter">Unit Converter</a></li>
                 </ul>    
            </div>
            <h4>Select one of the tabs to access the corresponding mini-app</h4>
            <br/>
            
            <hr/>
            
            <div class="col-sm-12">
                <div class="col-sm-10">
                    <img src="http://www.ojibwacasino.com/images/luck7-logo.png" width="20%"/> &nbsp;&MediumSpace;
                  
                  <img src="http://localhost:8080/FactorizerWeb/FactorizerLogo.png" width="20%"/>&nbsp;&MediumSpace;
                
                    <img src="http://localhost:8080/SpringMVCWebApp/flooring.png" width="20%"/>&nbsp;&MediumSpace;
                    <br/>
                    
                    <img src="http://localhost:8080/InterestCalculatorWeb/TextArt_150714114217.jpg" width="20%"/>&nbsp; &MediumSpace;
                    
                    <img src="https://arctouch.com/wp-content/uploads/2009/08/Tip-Em-Icon.png" width="12%"/>&nbsp;&MediumSpace;
                    
                    <img src="http://cdn.marketplaceimages.windowsphone.com/v8/images/c16a3f5b-9481-4b8d-909b-b4584a619a52?imageType=ws_icon_large" width="12%"/>
                </div>
            </div>
            
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

