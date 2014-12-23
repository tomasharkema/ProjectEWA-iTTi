<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Edit an event
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
                            ${event.eventName}
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form action="edit?id=${event.idevent}" method="post" class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label for="eventName" class="col-sm-2 control-label">Name</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="eventName" name="eventName" value="${event.eventName}" required="required">
                                            </div>
                                        </div>
                                        <!--div class="form-group">
                                            <label for="eventLocation" class="col-sm-2 control-label">Location</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="eventLocation" name="eventLocation" value="" required="required">
                                            </div>
                                        </div-->
                                        <div class="form-group">
                                            <label for="eventDate" class="col-sm-2 control-label">Date</label>
                                            <div class="col-sm-10">
                                                <input type="date" class="form-control" id="eventDate" name="eventDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${event.eventDate}" />" required="required">
                                                <p class="help-block">dd/mm/yyyy</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="eventLogo" class="col-sm-2 control-label">Logo</label>
                                            <div class="col-sm-10">
                                                <input type="text" id="eventLogo" name="eventLogo" value="${event.eventLogo}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="eventLogo" class="col-sm-2 control-label">Description</label>
                                            <div class="col-sm-10">
                                                <textarea id="description" name="description" width="100%">${event.description}</textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10">
                                                <button type="submit" class="btn btn-default">Submit</button>
                                                <!--button type="submit" class="btn btn-default">Save and Continue Edit</button-->
                                                <!--button type="reset" class="btn btn-default">Reset Button</button-->
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <img src="http://placehold.it/350x150" class="img-responsive" alt="Responsive image">
                                    </div>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <!--div class="col-lg-6">
                                    <h1>Disabled Form States</h1>
                                    <form role="form">
                                        <fieldset disabled>
                                            <div class="form-group">
                                                <label for="disabledSelect">Disabled input</label>
                                                <input class="form-control" id="disabledInput" type="text" placeholder="Disabled input" disabled>
                                            </div>
                                            <div class="form-group">
                                                <label for="disabledSelect">Disabled select menu</label>
                                                <select id="disabledSelect" class="form-control">
                                                    <option>Disabled select</option>
                                                </select>
                                            </div>
                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox">Disabled Checkbox
                                                </label>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Disabled Button</button>
                                        </fieldset>
                                    </form>
                                </div-->
                                <!-- /.col-lg-6 (nested) -->
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
                <h4 class="modal-title" id="myModalLabel">Delete Event</h4>
            </div>
            <div class="modal-body">
              Are you sure you want to delete this?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a href="delete?id=${event.idevent}" class="btn btn-danger" role="button">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete
                </a>
            </div>
        </div>
    </div>
</div>
        