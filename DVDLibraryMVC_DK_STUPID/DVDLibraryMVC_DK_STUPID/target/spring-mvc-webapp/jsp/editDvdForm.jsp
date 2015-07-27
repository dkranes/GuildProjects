<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf"  uri="http://www.springframework.org/tags/form"%>
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
            <h1>Dvd Library</h1>
            <hr/>
            <div class="container">
                <h1>Edit DVD Form</h1>
                <a href="displayDvdLibrary">Dvd List</a> 
                <hr/>

                <sf:form class="form-horizontal" role="form" modelAttribute="dvd" action="editDvd" method="POST" >
                
                    <div class="form-group">
                        <label for="add-title" class="col-md-4 control-label">Title:</label>
                        <div class="col-md-8">
                            <sf:input type="text"
                                   class="form-control"
                                   id="add-title"
                                   path="title"
                                   placeholder="Title" />
                            <sf:errors path="title" cssClass="error"></sf:errors>
                            <sf:hidden path="dvdId"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="add-releaseDate" class="col-md-4 control-label">Release Date:</label>
                        <div class="col-md-8">
                            <sf:input type="date"
                                   class="form-control"
                                   id="add-releaseDate"
                                   path="releaseDate"
                                   placeholder="Release Date" />
                            <sf:errors path="releaseDate" cssClass="error" ></sf:errors>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="add-mpaaRating" class="col-md-4 control-label">MPAA Rating:</label>
                        <div class="col-md-8">
                            <sf:input type="text"
                                   class="form-control"
                                   id="add-mpaaRating"
                                   path="mpaaRating"
                                   placeholder="MPAA Rating" />
                            <sf:errors path="mpaaRating" cssClass="error" ></sf:errors>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="add-directorName" class="col-md-4 control-label">Director Name:</label>
                        <div class="col-md-8">
                            <sf:input type="text"
                                   class="form-control"
                                   id="add-directorName"
                                   path="directorName"
                                   placeholder="Director Name" />
                            <sf:errors path="directorName" cssClass="error"></sf:errors>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="add-studio" class="col-md-4 control-label">Studio:</label>
                        <div class="col-md-8">
                            <sf:input type="text"
                                   class="form-control"
                                   id="add-studio"
                                   path="studio"
                                   placeholder="Studio" />
                            <sf:errors path="studio" cssClass="error"></sf:errors>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="add-note" class="col-md-4 control-label">Note:</label>
                        <div class="col-md-8">
                            <sf:input type="text"
                                   class="form-control"
                                   id="add-note"
                                   path="userNote"
                                   placeholder="Note" />
                            <sf:errors path="userNote" cssClass="error"></sf:errors>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit"
                                    id="add-button"
                                    class="btn btn-default">Edit DVD</button>
                        </div>
                    </div>
                </sf:form>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>


