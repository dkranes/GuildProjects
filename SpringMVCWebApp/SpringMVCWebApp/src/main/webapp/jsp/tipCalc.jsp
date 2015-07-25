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

        <title>Tip Calculator</title>

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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/tipCalc">Tip Calculator</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/unitConverter">Unit Converter</a></li>
                </ul>    
            </div>


        </div>

        <div class="container">

            <div class ="starter-template">


                <img src="https://arctouch.com/wp-content/uploads/2009/08/Tip-Em-Icon.png" width="15%"/>


                <h4>Tip Calculator!</h4>


                Complete the form below to calculate the tip:<br/>
                <br/>
                <!--action must be exact spelling and case -->

                <div class="col-xs-12">
                    <div class="col-xs-6 col-md-offset-0">
                        <form action="tipCalc" method="post">


                            <div class="form-group">
                                <label for="originalAmount" class="col-sm-5 control-label">Original Amount:</label>
                                <div class="col-sm-3">
                                    <input type="number" step="0.01" min="0" name="originalAmount"/>
                                </div>
                                <br/>
                                <br/>

                                <div class="form-group">
                                    <label for="tipPercentage" class="col-sm-5 control-label">Tip Percentage:</label>
                                    <div class="col-sm-3">
                                        <input type="number" step="1" min="0" name="tipPercentage"/>
                                    </div>
                                    <br/>
                                    <br/>

                                    <input type="submit" value="Calculate" class="btn btn-primary"/>

                                    </form>
                                </div>
                            </div>

                    </div>

                </div><!-- /.container -->
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="js/bootstrap.min.js"></script>


</body>
</html>
