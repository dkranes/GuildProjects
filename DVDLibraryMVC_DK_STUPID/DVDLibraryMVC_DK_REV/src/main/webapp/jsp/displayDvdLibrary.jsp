<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Library</title>
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
            <!--a href="displayNewDvdForm">Add New DVD</a><br/-->
            
            <form class="form-horizontal"
                      role="form"
                      action="displayNewDvdForm"
                      method="GET">
            
            <div class="form-group">
                <div class="col-md-8">
                    <button type="submit"
                            id="add-button"
                            class="btn btn-default">Add New DVD</button>
                </div>
            </div>
            </form>
            
            
            <form class="form-horizontal"
                      role="form"
                      action="loadSampleDvds"
                      method="POST">
            
            <div class="form-group">
                <div class="col-md-8">
                    <button type="submit"
                            id="add-button"
                            class="btn btn-default">Load Sample DVDs</button>
                </div>
            </div>
            </form>
            
            <form class="form-horizontal"
                      role="form"
                      action="saveSampleDvds"
                      method="POST">
            
            <div class="form-group">
                <div class="col-md-8">
                    <button type="submit"
                            id="add-button"
                            class="btn btn-default">Save to Sample DVDs File</button>
                </div>
            </div>
            </form>
            
            <hr/>
            


            <div class="container">
                <c:forEach var="dvd" items="${dvdLibrary}">

                    <s:url value="deleteDvd" var="deleteDvd_url">
                        <s:param name="dvdId" value="${dvd.dvdId}"/>
                    </s:url>

                    <s:url value="displayEditDvd" var="editDvd_url">
                        <s:param name="dvdId" value="${dvd.dvdId}"/>
                    </s:url>
                    Title: ${dvd.title} |
                    <a href="${deleteDvd_url}">Delete Dvd</a> |
                    <a href="${editDvd_url}">Edit Dvd</a><br/>   
                    Release Date: ${dvd.releaseDate}<br/>
                    Rating: ${dvd.mpaaRating}<br/>
                    Director: ${dvd.directorName}<br/>
                    Studio: ${dvd.studio}<br/>
                    Notes: ${dvd.userNote.toString().replaceAll("\\[|\\]", "")}<br/>
                    <hr/>
                </c:forEach>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


    </body>
</html>


