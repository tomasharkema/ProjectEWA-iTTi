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

  <!--div class="row info">
    <div class="col-sm-8">

      <ul class="list-group">
        <li class="list-group-item">

          <span class="glyphicon glyphicon-time"></span>

          <strong><fmt:formatDate value="${event.eventDate}" type="both" dateStyle="long" timeStyle="short" /></strong>

        </li>
        <li class="list-group-item">

          <span class="glyphicon glyphicon-pushpin"></span>

          ${event.getLocationid()}

        </li>
        <li class="list-group-item">

          <span class="glyphicon glyphicon-bullhorn"></span>

          <a href="${event.fbEvent}" target="_blank">${event.eventName} on Facebook</a>

        </li>
      </ul>

      <ul class="list-group">
        <li class="list-group-item">
          ${markdownDescription}
        </li>
      </ul>

      <div class="messages">
        <h4><small>MESSAGES</small></h4>
        <c:forEach items="${userHasEventList}" var="user">
          <div class="well well-sm avatar">
            <img src="${user.getUser().userAvatar}" class="img-circle avatar-timeline pull-left">
            <h4>${user.getUser().name} goes</h4>
            <time class="timeago" datetime="<fmt:formatDate value="${user.date}" pattern="yyyy-MM-dd'T'HH:mm:ss" />"><fmt:formatDate value="${user.date}" pattern="dd-MM-yyyy" /></time>
          </div>
        </c:forEach>
      </div>

    </div>
    <div class="col-sm-4">

      <ul class="list-group">
        <c:if test="${attendingFriends != null}">
          <c:if test="${attendingFriends.size() > 0}">
            <li class="list-group-item">
              <small>FRIENDS</small>
              <div class="friend-avatars">
                <c:forEach items="${attendingFriends}" var="friend">
                  <img class="img-circle" src="${friend.userAvatar}">
                </c:forEach>
              </div>
              <c:choose>
                <c:when test="${attendingFriends.size() > 1}">
                  ${attendingFriends.get(0).name} and ${attendingFriends.size()-1} other friends are attending.
                </c:when>
                <c:otherwise>
                  ${attendingFriends.get(0).name} is attending.
                </c:otherwise>
              </c:choose>
            </li>
          </c:if>
        </c:if>
        <li class="list-group-item">
          <small>GUESTS</small>
          <div class="attending">
            <c:choose>
              <c:when test="${attendees.size() > 0}">
                <div class="row">
                  <div class="col-sm-6">
                    <div class="number">${attendees.size()}</div> going
                  </div>
                  <div class="col-sm-6">
                    <div class="number">${drivers.size()}</div> driving
                  </div>
                </div>
              </c:when>
              <c:otherwise>
                No one is attending.
              </c:otherwise>
            </c:choose>
          </div>
        </li>
      </ul>

    </div>
  </div>
</div>

<div class="modal fade" id="meerijden_modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Request Place</h4>
      </div>
      <div class="modal-body">
        <h5>Places:</h5>
        <div class="places"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->