<%-- 
    Document   : overview
    Created on : 11-dec-2014, 21:50:01
    Author     : tomasharkema
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../jspf/overviewHeader.jsp" />
<div class="time-line">
    <c:forEach items="${timeline}" var="item">
        <div class="well well-sm time-line-node">
            <img src="${item.getPictureOne()}" class="img-circle avatar-timeline pull-left">
            <h4>${item.getMergeLine()}</h4>
            <p>
                <small class="text-muted"><i class="glyphicon glyphicon-time"></i> <time class="timeago" datetime="<fmt:formatDate value="${item.getDate()}" pattern="yyyy-MM-dd'T'HH:mm:ss" />"><fmt:formatDate value="${item.getDate()}" pattern="dd-MM-yyyy" /></time></small>
            </p>
        </div>
    </c:forEach>
</div>
<jsp:include page="../jspf/overviewFooter.jsp" />