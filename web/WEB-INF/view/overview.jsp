<%-- 
    Document   : overview
    Created on : 11-dec-2014, 21:50:01
    Author     : tomasharkema
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../jspf/overviewHeader.jsp" />
<div class="time-line">
    <c:choose>
        <c:when test="${timeline.size() == 0}">
            <div class="well">
                <h4>There isn't anything to show in your timeline (yet).</h4>
                <p>Your timeline will be filled once you've added friends and/or attend events.</p>
                <p>You can add friends by going to their profile page and click the button at the top right of the page <button type="button" class="btn btn-default" disabled="disabled"><span class="glyphicon glyphicon-plus"></span> Add as friend</button>.</p>
                <p>Events can be attended to by clicking the <button type="button" class="btn btn-default" disabled="disabled">RSVP <span class="caret"></span></button> button at the top right of the event page.</p>
                <ul class="nav nav-pills">
                    <li role="presentation"><a href="#">Users</a></li>
                    <li role="presentation"><a href="/events">Events</a></li>
                </ul>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach items="${timeline}" var="item">
                <div class="well well-sm time-line-node">
                    <img src="${item.getPictureOne()}" class="img-circle avatar-timeline pull-left">
                    <h4>${item.getMergeLine()}</h4>
                    <p>
                        <small class="text-muted"><i class="glyphicon glyphicon-time"></i> <time class="timeago" datetime="<fmt:formatDate value="${item.getDate()}" pattern="yyyy-MM-dd'T'HH:mm:ss" />"><fmt:formatDate value="${item.getDate()}" pattern="dd-MM-yyyy" /></time></small>
                    </p>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="../jspf/overviewFooter.jsp" />