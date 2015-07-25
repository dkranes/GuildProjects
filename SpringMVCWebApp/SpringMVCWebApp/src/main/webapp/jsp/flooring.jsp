<%-- 
    Document   : index
    Created on : Jul 14, 2015, 3:55:28 PM
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

        <title>Flooring Calculator</title>

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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/flooring">Flooring Calculator</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/tipCalc">Tip Calculator</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/unitConverter">Unit Converter</a></li>
                </ul>    
            </div>
            

        </div>

        <div class="container">

            <div class ="starter-template">


                <img src="http://localhost:8080/SpringMVCWebApp/flooring.png" width="25%"/>


                <h4>Calculate ALL the Flooring!</h4>


                Complete the form below to generate a flooring quote:<br/>
                <br/>
                <!--action must be exact spelling and case -->

                <div class="col-xs-12">
                    <div class="col-xs-6 col-md-offset-0">
                        <form action="flooring" method="post">


                            <div class="form-group">
                                <label for="myWidth" class="col-sm-5 control-label">Width:</label>
                                <div class="col-sm-3">
                                    <input type="number" step="0.01" min="0" name="myWidth"/>
                                </div>
                                <br/>
                                <br/>

                                <div class="form-group">
                                    <label for="myLength" class="col-sm-5 control-label">Length:</label>
                                    <div class="col-sm-3">
                                        <input type="number" step="0.01" min="0" name="myLength"/>
                                    </div>
                                    <br/>
                                    <br/>

                                    <div class="form-group">
                                        <label for="myCost" class="col-sm-5 control-label">Cost per Sq Ft of Flooring:</label>
                                        <div class="col-sm-3">
                                            <input type="number" step="0.01" min="0" name="myCost"/>
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


                    </body></html>