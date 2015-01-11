<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Edit a location
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="javascript:history.back()" class="btn btn-primary" role="button">
                                    <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back to overview
                                </a>
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete
                                </button>
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
                            Basic Form Elements
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form action="edit?id=${location.idlocation}" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label for="locationname" class="col-sm-2 control-label">Name</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="locationname" name="locationname" value="${location.locationname}" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="address" class="col-sm-2 control-label">Address</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="address" name="address" value="${location.address}" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="city" class="col-sm-2 control-label">City</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="city" name="city" value="${location.city}" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="locationpicture" class="col-sm-2 control-label">Picture</label>
                                            <div class="col-sm-10">
                                                <input type="file" class="form-control" id="locationpicture" name="locationpicture">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <button type="submit" class="btn btn-default">Submit</button>
                                                <!--button type="reset" class="btn btn-default">Reset Button</button-->
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <c:set var="locationPicture" value="${location.getLocationpicture()}"/>
                                        <img src="<c:out value="${fn:substring(locationPicture, fn:indexOf(locationPicture, '/uploads'), -1)}"/>" class="img-responsive" alt="Responsive image">
                                    </div>
                                </div>
                            </div>
                            <!-- /.row (nested) -->
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
        
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Delete Location</h4>
            </div>
            <div class="modal-body">
              Are you sure you want to delete this?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a href="delete?id=${location.idlocation}" class="btn btn-danger" role="button">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete
                </a>
            </div>
        </div>
    </div>
</div>