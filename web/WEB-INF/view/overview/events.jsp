<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 03-01-15
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<jsp:include page="../../jspf/overviewHeader.jsp" />
<h2>Events</h2>
<div class="events">
  <c:if test="${hasNoFriends}">
    <div class="well">
      <h4>Je hebt nog geen vrienden :(</h4>
      <p>JA! Help mij mijn vrienden te vinden!</p>
    </div>
  </c:if>
  <ul class="media-list">
    <c:set var="isInPast" value="false" scope="page"/>
    <c:forEach items="${events}" var="event">
      <c:if test="${event.getEvent().eventDate lt now && isInPast == false}">
        <c:set var="isInPast" value="true" scope="page"/>
        <li class="media">
          <small class="subtitle">PAST</small>
        </li>
      </c:if>
      <li class="media">
        <a class="media-left avatar" href="/events?eventId=${event.getEvent().id}">
          <img src="${event.getEvent().eventLogo}" alt="" class="avatar-small">
        </a>
        <div class="media-body">
          <a class="" href="/events?eventId=${event.getEvent().id}">
            <h4 class="media-heading">${event.getEvent().eventName}</h4>
            <p><fmt:formatDate value="${event.getEvent().eventDate}" pattern="E dd MMM yyyy" dateStyle="long"/></p>
          </a>
        </div>
      </li>
    </c:forEach>
  </ul>
</div>
<jsp:include page="../../jspf/overviewFooter.jsp" />