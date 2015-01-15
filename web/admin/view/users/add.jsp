        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Add a user
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
                                            <label for="name" class="col-sm-2 control-label">Name</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="name" name="name" placeholder="Name" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="address" class="col-sm-2 control-label">Address</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="address" name="address" placeholder="Address" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="town" class="col-sm-2 control-label">Town</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="town" name="town" placeholder="Town" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="zipcode" class="col-sm-2 control-label">Zipcode</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="Zipcode" required="required">
                                                <p class="help-block">i.e.: 1234 AB</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="gender" class="col-sm-2 control-label">Gender</label>
                                            <div class="col-sm-10">
                                                <select class="form-control" id="gender" name="gender">
                                                    <option value="m">Male</option>
                                                    <option value="f">Female</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="phone" class="col-sm-2 control-label">Phone</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="phone" name="phone" placeholder="Phone" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="phone" class="col-sm-2 control-label">Email</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="email" name="email" placeholder="Email" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="userAvatar" class="col-sm-2 control-label">Avatar</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="userAvatar" name="userAvatar">
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