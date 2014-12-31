<%-- 
    Document   : header.jsp
    Created on : 22-dec-2014, 19:51:46
    Author     : tomas
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="emptyHeader"></div>
<div class="container match-parent">
    <div class="row match-parent">
        <div class="col-xs-6 col-sm-3">
            <div data-spy="affix" data-offset-top="10" data-offset-bottom="200">
                <div class="avatar">
                    <img class="img-circle avatar-small" src="${loggedinuser.userAvatar}"> ${loggedinuser.name}
                </div>
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation"><a href="/overview"><span class="glyphicon glyphicon-th-list"></span> Nieuwsoverzicht</a></li>
                    <li role="presentation"><a href="#"><span class="glyphicon glyphicon-calendar"></span> Evenementen</a></li>
                    <li role="presentation"><a href="/overview/friends"><span class="glyphicon glyphicon-user"></span> Vrienden</a></li>
                    <li role="presentation"><a href="#"><span class="glyphicon glyphicon-pencil"></span> Profiel bewerken</a></li>
                </ul>
            </div>
        </div>
        <div class="col-xs-12 col-sm-9">
