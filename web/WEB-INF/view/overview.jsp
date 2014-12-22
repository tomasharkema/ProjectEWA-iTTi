<%-- 
    Document   : overview
    Created on : 11-dec-2014, 21:50:01
    Author     : tomasharkema
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container match-parent">
    <div class="emptyHeader"></div>
    
    <div class="row match-parent">
        <div class="col-xs-6 col-sm-3">
            <div data-spy="affix" data-offset-top="10" data-offset-bottom="200">
                <div class="avatar">
                    <img class="img-circle avatar-small" src="${loggedinuser.userAvatar}"> ${loggedinuser.name}
                </div>
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation"><a href="#"><span class="glyphicon glyphicon-pencil"></span> Profiel bewerken</a></li>
                    <li role="presentation"><a href="#"><span class="glyphicon glyphicon-th-list"></span> Nieuwsoverzicht</a></li>
                    <li role="presentation"><a href="#"><span class="glyphicon glyphicon-calendar"></span> Evenementen</a></li>
                </ul>
            </div>
        </div>
        <div class="col-xs-12 col-sm-9 time-line">
            <c:forEach items="${timeline}" var="item">
                <div class="well well-sm avatar">
                    <img src="${item.image}" class="img-circle avatar-timeline pull-left">
                    <h4>${item.title}</h4>
                    <p>
                        <small class="text-muted"><i class="glyphicon glyphicon-time"></i> ${item.time}</small>
                    </p>
                </div>
            </c:forEach>
        </div>
    </div>
    
</div>