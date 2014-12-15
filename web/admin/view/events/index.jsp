<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- http://stackoverflow.com/questions/6162401/formatting-a-date-in-jsp#answer-6162507 -->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
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
                            <div class="well">
                                <h4>DataTables Usage Information</h4>
                                <p>DataTables is a very flexible, advanced tables plugin for jQuery. In SB Admin, we are using a specialized version of DataTables built for Bootstrap 3. We have also customized the table headings to use Font Awesome icons in place of images. For complete documentation on DataTables, visit their website at <a target="_blank" href="https://datatables.net/">https://datatables.net/</a>.</p>
                                <a class="btn btn-default btn-lg btn-block" target="_blank" href="https://datatables.net/">View DataTables Documentation</a>
                            </div>
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