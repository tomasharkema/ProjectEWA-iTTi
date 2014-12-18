<%-- 
    Document   : overview
    Created on : 11-dec-2014, 21:50:01
    Author     : tomasharkema
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="emptyHeader">
</div>
<div class="bg" style="background-image:url('${event.eventLogo}');"></div>
<div class="container event">
    
    <h1>${event.eventName} <small>- <fmt:formatDate value="${event.eventDate}" pattern="dd-MM-yyyy" /></small></h1>
</div>