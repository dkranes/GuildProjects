<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Controller Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>Spring MVC Application from Archetype</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayDvdLibrary">Home (non-Ajax)</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/search">Search DVD Library</a></li>

                </ul>    
            </div>
            <h2>DVD Library</h2>
            <h3>${message}</h3>
            <div class="container">
                <h1>Add New DVD Form</h1>
                <a href="displayDvdLibrary">Dvd List</a> <br/>
                <hr/>

                <form class="form-horizontal"
                      role="form"
                      action="addNewDvd"
                      method="POST">
                    <div class="form-group">
                        <label for="add-title" class="col-md-4 control-label">Title:</label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="add-title"
                                   name="title"
                                   placeholder="Title" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-releaseDate" class="col-md-4 control-label">Release Date:</label>
                        <div class="col-md-8">
                            <input type="date"
                                   class="form-control"
                                   id="add-releaseDate"
                                   name="releaseDate"
                                   placeholder="Release Date" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-mpaaRating" class="col-md-4 control-label">MPAA Rating:</label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="add-mpaaRating"
                                   name="mpaaRating"
                                   placeholder="MPAA Rating" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-directorName" class="col-md-4 control-label">Director Name:</label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="add-directorName"
                                   name="directorName"
                                   placeholder="Director Name" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-studio" class="col-md-4 control-label">Studio:</label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="add-studio"
                                   name="studio"
                                   placeholder="Studio" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-note" class="col-md-4 control-label">Note:</label>
                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   id="add-note"
                                   name="userNote"
                                   placeholder="Note" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit"
                                    id="add-button"
                                    class="btn btn-default">Add New DVD</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>


