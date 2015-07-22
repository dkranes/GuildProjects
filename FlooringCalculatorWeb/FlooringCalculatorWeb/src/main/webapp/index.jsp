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

        <!-- Just for debugging purposes. Don't actually copy this line! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand">Flooring Calculator</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="http://localhost:8080/FlooringCalculatorWeb#">Home</a></li>
                        <li><a href="https://en.wikipedia.org/wiki/Flooring" target="_blank">Learn about Flooring</a></li>
                        <li><a href="https://github.com/swcguild/DanielKranefuss" target="_blank">Contact</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>

        <div class="container">

            <div class ="starter-template">


                <img src="http://localhost:8080/FlooringCalculatorWeb/flooring.png"/>
                

                <h4>Calculate ALL the Flooring!</h4>


                Complete the form below to generate a flooring quote:<br/>
                <br/>
                <!--action must be exact spelling and case -->

                <div class="col-xs-12">
                    <div class="col-xs-6 col-md-offset-0">
                        <form action="FlooringCalculatorWebServlet" method="post">

                            
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


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>


    </body></html>