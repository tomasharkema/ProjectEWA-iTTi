<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- http://stackoverflow.com/questions/6162401/formatting-a-date-in-jsp#answer-6162507 -->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Events
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="events/add" class="btn btn-primary" role="button">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add new
                                </a>
                            </div>
                        </div>
                    </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Location</th>
                                            <th>Date</th>
                                            <th>Engine version</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${events}" var="event" varStatus="iter">
                                        <tr class="${((iter.index % 2) == 0) ? 'even' : 'odd'} clickable" onclick="window.location = 'events/edit?id=${event.idevennt}'" title="Edit event">
                                            <td><a href="events/edit?id=${event.idevennt}" title="Edit event">${event.eventName}</a></td>
                                            <td>${event.eventLocation}</td>
                                            <td><fmt:formatDate value="${event.evenDate}" pattern="d MMMMM yyyy" /></td>
                                            <td class="center">4</td>
                                            <td class="center">Edit | Delete</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->