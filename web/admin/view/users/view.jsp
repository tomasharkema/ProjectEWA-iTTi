<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        View a user
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
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label for="name" class="col-sm-2 control-label">Name</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="name" name="name" value="<c:out value="${user.getName()}"/>" disabled="disabled">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="address" class="col-sm-2 control-label">Address</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="address" name="address" value="<c:out value="${user.getAddress()}"/>" disabled="disabled">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="town" class="col-sm-2 control-label">Town</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="town" name="town" value="<c:out value="${user.getTown()}"/>" disabled="disabled">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="zipcode" class="col-sm-2 control-label">Zipcode</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="zipcode" name="zipcode" value="<c:out value="${user.getZipcode()}"/>" disabled="disabled">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="gender" class="col-sm-2 control-label">Gender</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="gender" name="gender" value="<c:out value="${user.getGender()}"/>" disabled="disabled">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="phone" class="col-sm-2 control-label">Phone</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="phone" name="phone" value="<c:out value="${user.getPhone()}"/>" disabled="disabled">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="email" class="col-sm-2 control-label">Email</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="email" name="email" value="<c:out value="${user.getEmail()}"/>" disabled="disabled">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="userAvatar" class="col-sm-2 control-label">Avatar</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="userAvatar" name="userAvatar" value="<c:out value="${user.getUserAvatar()}"/>" disabled="disabled">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <img src="<c:out value="${user.getUserAvatar()}"/>" class="img-responsive img-rounded" width="140" height="140" alt="Responsive image">
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