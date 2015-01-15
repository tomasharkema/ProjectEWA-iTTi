<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- http://stackoverflow.com/questions/6162401/formatting-a-date-in-jsp#answer-6162507 -->
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
                                        <div class="form-group">
                                            <label for="location" class="col-sm-2 control-label">Location</label>
                                            <div class="col-sm-10">
                                                <select class="form-control" id="location" name="location">
                                                    <c:forEach items="${locations}" var="location">
                                                        <c:choose>
                                                            <c:when test="${location.idlocation == event.getLocationid().getIdlocation()}">
                                                                <option value="${location.idlocation}" selected>${location.locationname}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${location.idlocation}">${location.locationname}</option>
                                                            </c:otherwise>
                                                        </c:choose>
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
                                                <input type="date" class="form-control" id="eventDate" name="eventDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${event.eventDate}" />" required="required">
                                                <p class="help-block">dd/mm/yyyy</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="eventLogo" class="col-sm-2 control-label">Logo</label>
                                            <div class="col-sm-10">
                                                <input class="form-control" type="text" id="eventLogo" name="eventLogo" value="${event.eventLogo}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="eventWall" class="col-sm-2 control-label">Cover</label>
                                            <div class="col-sm-10">
                                                <input class="form-control" type="text" id="eventWall" name="eventWall" value="${event.eventWall}" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="description" class="col-sm-2 control-label">Description</label>
                                            <div class="col-sm-10">
                                                <textarea class="form-control" rows="3" id="description" name="description">${event.description}</textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="fbevent" class="col-sm-2 control-label">Facebook event</label>
                                            <div class="col-sm-10">
                                                <input class="form-control" type="text" id="fbevent" name="fbevent" value="${event.fbEvent}">
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
                                        <img src="<c:out value="${event.eventLogo}"/>" class="img-responsive" alt="Responsive image">
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <h2>People attending</h2>
                                    <div class="table-responsive">
                                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                            <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Date of RSVP</th>
                                                    <th>Car</th>
                                                    <th>Is the driver?</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${event.getUserHasEventList()}" var="eventUser" varStatus="iter">
                                                <tr class="${((iter.index % 2) == 0) ? 'even' : 'odd'} clickable" onclick="window.location = '/admin/users/view?id=${eventUser.getUser().iduser}'" title="View user">
                                                    <td><a href="/admin/users/view?id=${eventUser.getUser().iduser}" title="View user">${eventUser.getUser().name}</a></td>
                                                    <td><fmt:formatDate value="${eventUser.getDate()}" pattern="d MMMMM yyyy" /></td>
                                                    <td>${eventUser.getCarId().getBrand()}</td>
                                                    <td>${eventUser.getCarId().getUserIduser() == eventUser.getUser() ? 'yes' : 'no'}</td>
                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
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
        