<%-- 
    Document   : overview
    Created on : 11-dec-2014, 21:50:01
    Author     : tomasharkema
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="emptyHeader"></div>
    
    <h1>Events</h1>
    <ul class="list-group">
    <c:forEach items="${events}" var="event">
        <li class="list-group-item"><a href="?eventId=${event.idevennt}">${event.eventName}</a></li>
    </c:forEach>
    </ul>
</div>