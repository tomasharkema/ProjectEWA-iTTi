<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Users
                        <div class="pull-right">
                            <div class="btn-group">
                                <a href="users/add" class="btn btn-primary" role="button">
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
                            Users
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Town</th>
                                            <th>Address</th>
                                            <th>Zipcode</th>
                                            <th>Phone</th>
                                            <th>Email</th>
                                            <th>Gender</th>
                                            <th>Admin?</th>
                                            <th>Avatar</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${users}" var="user" varStatus="iter">
                                        <tr class="${((iter.index % 2) == 0) ? 'even' : 'odd'} clickable" onclick="window.location = 'users/view?id=${user.iduser}'" title="View user">
                                            <td><a href="users/view?id=${user.iduser}" title="View user">${user.name}</a></td>
                                            <td><c:out value="${user.town}"/></td>
                                            <td><c:out value="${user.address}"/></td>
                                            <td><c:out value="${user.zipcode}"/></td>
                                            <td><c:out value="${user.phone}"/></td>
                                            <td><c:out value="${user.email}"/></td>
                                            <td class="text-center"><c:out value="${user.gender}"/></td>
                                            <td class="text-center"><c:out value="${(user.admin == 0) ? 'no' : 'yes'}"/></td>
                                            <td class="center"><img src="<c:out value="${user.getUserAvatar()}"/>" class="img-responsive img-circle center-block" width="60" height="60" alt="Responsive image"></td>
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