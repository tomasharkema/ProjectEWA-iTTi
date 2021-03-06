        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Add a location
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
                                    <form action="add" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label for="locationname" class="col-sm-2 control-label">Name</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="locationname" name="locationname" placeholder="Name" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="address" class="col-sm-2 control-label">Address</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="address" name="address" placeholder="Address" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="city" class="col-sm-2 control-label">City</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="city" name="city" placeholder="City" required="required">
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