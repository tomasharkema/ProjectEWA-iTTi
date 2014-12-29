<%-- 
    Document   : overview
    Created on : 11-dec-2014, 21:50:01
    Author     : tomasharkema
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="backdrop header" style="background-image:url('${event.eventWall}');"></div>
<div class="container event">
    <header>
        
        <c:choose>
            <c:when test="${isAttending}">
                <div class="btn-group pull-right rsvp" role="group">
                    <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                      <span class="glyphicon glyphicon-ok"></span> I go
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/events/attend?eventId=${event.idevent}&type=cancel"><span class="glyphicon glyphicon-remove"></span> Cancel</a></li>
                    </ul>
                </div>
            </c:when>

            <c:otherwise>
                <div class="btn-group pull-right rsvp" role="group">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                      RSVP
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/events/attend?eventId=${event.idevent}&type=ikrijzelf"><span class="glyphicon glyphicon-hand-up"></span> I'll drive</a></li>
                        <li class="meerijden"><a href="/events/attend?eventId=${event.idevent}&type=meerijden"><span class="glyphicon glyphicon-question-sign"></span> Request place</a></li>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>

        <img class="img-thumbnail event-ava pull-left" src="${event.eventLogo}">
        <h1>${event.eventName} <small>- <fmt:formatDate value="${event.eventDate}" pattern="dd-MM-yyyy" /></small></h1>
    </header>
    
    <p class="offset">${event.description}</p>
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