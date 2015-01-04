<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 04-01-15
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="backdrop header" style="background-image:url('${event.eventWall}');"></div>
<div class="container event">
  <header>

    <div class="btn-group pull-right rsvp" role="group">
      <c:if test="${friendRelation eq 'Pending'}">
      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
          <span class="glyphicon glyphicon-ok"></span> Friendrequest send
          <span class="caret"></span>
      </button>
      <ul class="dropdown-menu" role="menu">
        <li><a href="/user/friend?action=cancel&uid=${user.iduser}"><span class="glyphicon glyphicon-remove"></span> Cancel</a></li>
      </ul>
      </c:if>
      <c:if test="${friendRelation eq 'NotConfirmed'}">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
          Respond to friendrequest
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
          <li><a href="/user/friend?action=approve&uid=${user.iduser}"><span class="glyphicon glyphicon-ok"></span> Approve</a></li>
          <li><a href="/user/friend?action=cancel&uid=${user.iduser}"><span class="glyphicon glyphicon-remove"></span> Decline</a></li>
        </ul>
      </c:if>
      <c:if test="${friendRelation eq 'NoFriends'}">
        <a href="/user/friend?action=add&uid=${user.iduser}" type="button" class="btn btn-default">
          <span class="glyphicon glyphicon-plus"></span> Add as friend
        </a>
      </c:if>
      <c:if test="${friendRelation eq 'Friends'}">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
          <span class="glyphicon glyphicon-ok"></span> Friends
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
          <li><a href="/user/friend?action=cancel&uid=${user.iduser}"><span class="glyphicon glyphicon-remove"></span> Remove as friend</a></li>
        </ul>
      </c:if>
    </div>
    <img class="img-thumbnail event-ava pull-left" src="${user.userAvatar}">
    <h1>${user.name}</h1>
  </header>

  <div class="container">
    <div class="row">
      <div class="col-sm-8">
        <small class="subTitle">MESSAGES</small>
        <div class="time-line">
          <c:forEach items="${userTimeline}" var="item">
            <div class="well well-sm time-line-node">
              <img src="${item.getPictureOne()}" class="img-circle avatar-timeline pull-left">
              <h4>${item.getMergeLine()}</h4>
              <p>
                <small class="text-muted"><i class="glyphicon glyphicon-time"></i> <time class="timeago" datetime="<fmt:formatDate value="${item.getDate()}" pattern="yyyy-MM-dd'T'HH:mm:ss" />"><fmt:formatDate value="${item.getDate()}" pattern="dd-MM-yyyy" /></time></small>
              </p>
            </div>
          </c:forEach>
        </div>
      </div>
      <div class="col-sm-4">
        <small class="subTitle">EVENTS</small>
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
    </div>
  </div>