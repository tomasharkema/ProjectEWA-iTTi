        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Add an event
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="javascript:history.back()" class="btn btn-primary" role="button">
                                    <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back to overview
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
                            Basic Form Elements
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form action="add" method="post" class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label for="eventName" class="col-sm-2 control-label">Name</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="eventName" name="eventName" placeholder="Name" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="location" class="col-sm-2 control-label">Location</label>
                                            <div class="col-sm-10">
                                                <select class="form-control" id="location" name="location" required="required">
                                                    <c:forEach items="${locations}" var="location">
                                                    <option value="${location.idlocation}">${location.locationname}</option>
                                                    </c:forEach>
                                                </select>
                                                <p class="help-block">
                                                    <a href="/admin/locations/add" title="Add a new location">Add a new location</a>
                                                </p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="eventDate" class="col-sm-2 control-label">Date</label>
                                            <div class="col-sm-10">
                                                <input type="date" class="form-control" id="eventDate" name="eventDate" placeholder="Date" required="required">
                                                <p class="help-block">dd/mm/yyyy</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="eventLogo" class="col-sm-2 control-label">Logo</label>
                                            <div class="col-sm-10">
                                                <input class="form-control" type="text" id="eventLogo" name="eventLogo" placeholder="Link to logo image" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="eventWall" class="col-sm-2 control-label">Cover</label>
                                            <div class="col-sm-10">
                                                <input class="form-control" type="text" id="eventWall" name="eventWall" placeholder="Link to cover image" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="description" class="col-sm-2 control-label">Description</label>
                                            <div class="col-sm-10">
                                                <textarea class="form-control" rows="3" id="description" name="description" required="required"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="fbevent" class="col-sm-2 control-label">Facebook event</label>
                                            <div class="col-sm-10">
                                                <input class="form-control" type="text" id="fbevent" name="fbevent" placeholder="Link to Facebook event">
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