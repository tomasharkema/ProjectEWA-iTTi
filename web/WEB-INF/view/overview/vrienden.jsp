<%-- 
    Document   : overview
    Created on : 11-dec-2014, 21:50:01
    Author     : tomasharkema
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../jspf/overviewHeader.jsp" />
<div class="vrienden">
    <c:if test="${hasNoFriends}">
        <div class="well">
            <h4>Je hebt nog geen vrienden :(</h4>
            <p>JA! Help mij mijn vrienden te vinden!</p>
        </div>
    </c:if>
    <c:forEach items="${vrienden}" var="vriend">
        <div class="well well-sm avatar">
            <img src="${item.image}" class="img-circle avatar-timeline pull-left">
            <h4>${item.title}</h4>
            <p>
                <small class="text-muted"><i class="glyphicon glyphicon-time"></i> ${item.time}</small>
            </p>
        </div>
    </c:forEach>
</div>
<jsp:include page="../../jspf/overviewFooter.jsp" />