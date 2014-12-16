<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Edit an event</h1>
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
                                    <form action="edit?id=${event.idevennt}" method="post" class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label for="eventName" class="col-sm-2 control-label">Name</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="eventName" name="eventName" value="${event.eventName}" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="eventLocation" class="col-sm-2 control-label">Location</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="eventLocation" name="eventLocation" value="${event.eventLocation}" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="eventDate" class="col-sm-2 control-label">Date</label>
                                            <div class="col-sm-10">
                                                <input type="date" class="form-control" id="eventDate" name="eventDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${event.evenDate}" />" required="required">
                                                <p class="help-block">dd/mm/yyyy</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="eventLogo" class="col-sm-2 control-label">Logo</label>
                                            <div class="col-sm-10">
                                                <input type="file" id="eventLogo" name="eventLogo">
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