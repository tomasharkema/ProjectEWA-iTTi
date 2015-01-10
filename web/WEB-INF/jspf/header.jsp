<!DOCTYPE html>

<%--
    Document   : header
    Created on : May 20, 2010, 12:20:12 AM
    Author     : tgiunipero
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="nl">
    <head>
        <link rel="shortcut icon" href="img/favicon.ico">
        <meta content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="/js/ie-emulation-modes-warning.js"></script>
        <!--link href="css/bootstrap.min.css" rel="stylesheet"-->

        <title>Dryves</title>
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <!-- Custom styles for this template -->
        <link href="/css/dist/dryves.css" rel="stylesheet">
    </head>
    <body id="dryves">
        <div class="navbar-wrapper">
            <div class="container">

                <nav class="navbar navbar-default navbar-static-top" role="navigation">
                    <div class="container">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="/">Dryves</a>
                        </div>
                        <div id="navbar" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav">
                                <li class="active"><a href="/">Home</a></li>
                                <c:choose>
                                    <c:when test="${userId == null}">
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="/overview">Overview</a></li>
                                        <li><a href="/events">Events</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <c:choose>
                                <c:when test="${userId == null}">
                                <li><a href="/login.jsp">Login</a></li>
                                </c:when>
                                <c:otherwise>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-bell"></span> <span class="caret"></span></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="/overview/profile">Profile</a></li>
                                        <li class="divider"></li>
                                        <!--li class="dropdown-header">Nav header</li-->
                                        <li><a href="/logout">Logout</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><img src="${currentUser.userAvatar}" class="avatar-nav"> ${currentUser.name} <span class="caret"></span></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="/overview/profile">Profile</a></li>
                                        <li class="divider"></li>
                                        <!--li class="dropdown-header">Nav header</li-->
                                        <li><a href="/logout">Logout</a></li>
                                    </ul>
                                </li>
                                </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                    </div>
                </nav>

            </div>
        </div>
