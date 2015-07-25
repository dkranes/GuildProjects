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


            <div class ="col-md-12">
                <div class ="col-md-3">
                    <img src="http://cdn.marketplaceimages.windowsphone.com/v8/images/c16a3f5b-9481-4b8d-909b-b4584a619a52?imageType=ws_icon_large" width="100%"/>
                </div>

                <div class ="col-md-8">
                    <form action="unitConverter" method="post" class="form-horizontal">

                        <fieldset>
                            <div class ="form-group">
                                <label class="col-md-4 control-label" for="conversionType">Conversion Type:</label>
                                <div class="col-md-4">
                                    <select id="conversionType" name="conversionType" class="form-control">
                                        <option value="Temperature">Temperature</option>
                                        <option value="Currency">Currency</option>
                                        <option value="Volume">Volume</option>
                                        <option value="Mass">Mass</option>
                                    </select>
                                </div>
                            </div>    
                        </fieldset>           
                        
                        <fieldset>
                            <div class ="form-group">
                                <label class="col-md-4 control-label" for="convertFrom">Convert From:</label>
                                <div class="col-md-4">
                                    <select id="convertFrom" name="convertFrom" class="form-control" for="convertFrom"></select>
                                </div>
                            </div>    
                        </fieldset>
                        
                        <fieldset>
                            <div class ="form-group">
                                <label class="col-md-4 control-label" for="convertTo">Convert To:</label>
                                <div class="col-md-4">
                                    <select id="convertTo" name="convertTo" class="form-control" for="convertTo"></select>
                                </div>
                            </div>    
                        </fieldset>
                        
                        
                        

                        <fieldset>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="originalValue">Value to Convert:</label>  
                                <div class="col-md-4">
                                    <input id="originalValue" name="originalValue" type="text" class="form-control input-md">
                                    <br/>
                                    <input type="submit" value="Convert" />
                                </div>
                            </div>

                        </fieldset>
                    </form>
                </div>

            </div>

        </div>


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/units.js"></script>
        <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">


    </body>
</html>
