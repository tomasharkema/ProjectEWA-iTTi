<%-- 
    Document   : overview
    Created on : 11-dec-2014, 21:50:01
    Author     : tomasharkema
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../jspf/overviewHeader.jsp" />
<div class="friends">
    <c:if test="${hasNoFriends}">
        <div class="well">
            <h4>Je hebt nog geen vrienden :(</h4>
            <p>JA! Help mij mijn vrienden te vinden!</p>
        </div>
    </c:if>
    <!-- Yay, the alphabet http://stackoverflow.com/questions/2312656/generating-ordinal-characters-in-jsp-el -->
    <ul class="media-list">
        <c:set var="prevChar" value="" scope="page"/>
        <c:forEach items="${friends}" var="friend">

            <c:set var="currentChar" value="${fn:substring(friend.getUser().name, 0, 1)}" scope="page"/>
            <c:if test="${currentChar != prevChar}">
                <c:set var="prevChar" value="${currentChar}" scope="page"/>
                <li class="media letter-header">
                    <h4>${currentChar}</h4>
                </li>
            </c:if>

            <li class="media">
                <a class="media-left avatar" href="#">
                    <img src="${friend.getUser().userAvatar}" alt="" class="avatar-small">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">${friend.getUser().name}</h4>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
<jsp:include page="../../jspf/overviewFooter.jsp" />