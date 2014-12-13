<%-- 
    Document   : overview
    Created on : 11-dec-2014, 21:50:01
    Author     : tomasharkema
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="emptyHeader"></div>
<h1>Overview</h1>

<hr>

<div class="friends">
    <div class="row">
    <c:forEach items="${users}" var="user">
        <div class="col-lg-4">
            <img class="img-circle" src="${user.userAvatar}" alt="Generic placeholder image" style="width: 140px; height: 140px;">
            <h2>${user.name}</h2>
            <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna.</p>
            <p><a class="btn btn-default" href="#" role="button">View details »</a></p>
        </div>
    </c:forEach>
    </div>
</div>