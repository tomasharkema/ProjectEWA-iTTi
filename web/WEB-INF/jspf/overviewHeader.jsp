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
                    <li role="presentation"><a href="/overview"><span class="glyphicon glyphicon-th-list"></span> Newsfeed</a></li>
                    <li role="presentation"><a href="/overview/events"><span class="glyphicon glyphicon-calendar"></span> Events</a></li>
                    <li role="presentation"><a href="/overview/friends"><span class="glyphicon glyphicon-user"></span> Friends</a></li>
                    <li role="presentation"><a href="#"><span class="glyphicon glyphicon-pencil"></span> Edit Profile</a></li>
                </ul>
            </div>
        </div>
        <div class="col-xs-12 col-sm-9">
