<%-- 
    Document   : tipCalc
    Created on : Jul 21, 2015, 10:29:39 AM
    Author     : DanKranefuss
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

        <title>Unit Converter</title>

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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/lucky7s">Lucky 7's</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/factorizer">Factorizer</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/intercalc">Interest Calculator</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/flooring">Flooring Calculator</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/tipCalc">Tip Calculator</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/unitConverter">Unit Converter</a></li>
                </ul>    
            </div>


        </div>

        <div class="container">




            <img src="http://cdn.marketplaceimages.windowsphone.com/v8/images/c16a3f5b-9481-4b8d-909b-b4584a619a52?imageType=ws_icon_large" width="15%"/>
            <br/><br/>


            <!--action must be exact spelling and case -->


            <form action="unitConverter" method="post">
                
                <br/>
                <br/>
                
                <br/><br/>
                <div class="col-sm-8">
                    <div class ="col-sm-3">
                    Conversion type: <br />
                    Temperature <input type="radio" name="conversionType" value="temperature" checked= />
                    <br/>
                    Currency <input type="radio" name="conversionType" value="currency"/>
                    <br/>
                    Volume <input type="radio" name="conversionType" value="volume"/>
                    <br/>
                    Mass <input type="radio" name="conversionType" value="mass" />
                    <!--
                    <select name="conversionType">
                        <option value="temperature">Temperature</option>
                        <option value="currency">Currency</option>
                        <option value="volume">Volume</option>
                        <option value="mass">Mass</option>
                    </select><br />
                    -->
                    <br />
                    </div>
                    
                    <div class ="col-sm-3">
                    Convert from: <br />
                    <select name="convertFrom">
                        <option value="Fahrenheit">Fahrenheit</option>
                        <option value="Celsius">Celsius</option>
                        <option value="Kelvin">Kelvin</option>
                        <option value="Dollar">US Dollar</option>
                        <option value="Euro">Euro</option>
                        <option value="Gallon">US Gallon</option>
                        <option value="Liter">Liter</option>
                        <option value="Pound">US Pounds</option>
                        <option value="Kilogram">Kilogram</option>
                    </select><br />
                    <br />
                    </div>
                    
                    <div class ="col-sm-3">
                    Convert to: <br />
                    <select name="convertTo">
                        <option value="Fahrenheit">Fahrenheit</option>
                        <option value="Celsius">Celsius</option>
                        <option value="Kelvin">Kelvin</option>
                        <option value="Dollar">US Dollar</option>
                        <option value="Euro">Euro</option>
                        <option value="Gallon">US Gallon</option>
                        <option value="Liter">Liter</option>
                        <option value="Pound">US Pounds</option>
                        <option value="Kilogram">Kilogram</option>
                    </select><br />
                    <br />
                    </div>
                    Value to Convert:<br/>
                <input type="number" step = "0.01" name="originalValue"/>
                <input type="submit" value="Convert" />
                </div>
                
                

            </form>


        </div>


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">


    </body>
</html>
