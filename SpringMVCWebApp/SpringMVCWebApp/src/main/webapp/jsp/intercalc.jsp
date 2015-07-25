<%-- 
    Document   : index
    Created on : Jul 12, 2015, 11:34:19 PM
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

        <title>Interest Calculator</title>

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
                <li role="presentation"><a href="${pageContext.request.contextPath}/factorizer">Factorizer</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/intercalc">Interest Calculator</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/flooring">Flooring Calculator</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/tipCalc">Tip Calculator</a></li>
                 <li role="presentation"><a href="${pageContext.request.contextPath}/unitConverter">Unit Converter</a></li>
                 </ul>    
            </div>

            
        </div>

        <div class="container">

            <div class ="starter-template">


                <img src="http://localhost:8080/InterestCalculatorWeb/TextArt_150714114217.jpg" width="30%"/>
                <h1>Interest Calculator</h1>

                <h4>Calculate ALL the interests!</h4>


                Complete the form to deposit money in the fund:<br/>
                <br/>
                <!--action must be exact spelling and case -->

                <div class="col-xs-12">
                    <div class="col-xs-6 col-md-offset-0">
                        <form action="intercalc" method="post">

                            
                            <div class="form-group">
                                <label for="myAnswer" class="col-sm-5 control-label">Amount:</label>
                                <div class="col-sm-3">
                            <input type="number" step="0.01" min="0" name="myAnswer"/>
                                </div>
                            <br/>
                            <br/>
                            
                            <div class="form-group">
                                <label for="myRate" class="col-sm-5 control-label">Interest Rate:</label>
                                <div class="col-sm-3">
                            <input type="number" step="0.01" min="0" name="myRate"/>
                                </div>
                            <br/>
                            <br/>
                            
                            <div class="form-group">
                                <label for="myYears" class="col-sm-5 control-label">Number of Years:</label>
                                <div class="col-sm-3">
                            <input type="number" min="0" name="myYears"/>
                                </div>
                            <br/>
                            <br/>
                           
                            <div class="form-group">
                                <label for="myPeriods" class="col-sm-5 control-label">Number of Compound Periods per Year:</label>
                                <div class="col-sm-3">
                            <input type="number" min="0" name="myPeriods"/>
                                </div>
                            <br/>
                            <br/>
                           
                            
                            <input type="submit" value="Calculate" class="btn btn-primary"/>



                        </form>
                    </div>
                </div>

            </div>

        </div><!-- /.container -->


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>


    </body></html>