<%-- 
    Document   : overview
    Created on : 11-dec-2014, 21:50:01
    Author     : tomasharkema
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <div class="emptyHeader"></div>
    <div class="events">
        <h1>Events</h1>
        <input type="search" class="form-control search search-events" placeholder="Search event..." data-type="events">
        <div class="search-results hidden"></div>
        <ul class="list-group events-list">
        <c:if test="${empty events}">
            <i>Er zijn nog geen events.</i>
        </c:if>
        <c:forEach items="${events}" var="event">
            <li class="list-group-item">
                <img src="${event.eventLogo}" class="event-ava pull-left"/>
                <h3><a href="?eventId=${event.idevent}">${event.eventName}</a></h3>
                <p><fmt:formatDate value="${event.eventDate}" pattern="E dd MMM yyyy" dateStyle="long"/></p>
                <p>${event.getLocationid()}</p>
            </li>
        </c:forEach>
        </ul>
    </div>
</div>