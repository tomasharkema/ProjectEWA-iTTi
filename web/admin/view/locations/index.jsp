<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Locations
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="locations/add" class="btn btn-primary" role="button">
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
                            Locations
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Location name</th>
                                            <th>City</th>
                                            <th>Address</th>
                                            <th>Location picture</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${locations}" var="location" varStatus="iter">
                                        <tr class="${((iter.index % 2) == 0) ? 'even' : 'odd'} clickable" onclick="window.location = 'locations/edit?id=${location.idlocation}'" title="Edit location">
                                            <td><a href="locations/edit?id=${location.idlocation}" title="Edit location">${location.locationname}</a></td>
                                            <!--td></td-->
                                            <td>${location.city}</td>
                                            <td>${location.address}</td>
                                            <td>${location.locationpicture}
                                            <!-- voor de demo even uitgecomment! -->
                                            <td class="center"><!--Edit | Delete--></td>
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