<%-- 
    Document   : userprofile
    Created on : 24-dec-2014, 15:04:27
    Author     : Xiang
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../jspf/overviewHeader.jsp" />

    <img class="img-circle" src="${currentUser.userAvatar}" alt="Profile photo" style="width: 140px; height: 140px;">
    <h3>My profile</h3>
    <form class="form-horizontal" action="/overview/changeUser" method="post">
        <div class="form-group">
            <label for="inputName" class="col-sm-2 control-label">Name</label>
            <div class="col-sm-10">
                <input type="text" name="name" class="form-control" id="inputName" placeholder="Name" value="${currentUser.name}" required>
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail" class="col-sm-2 control-label">Email</label>
            <div class="col-sm-10">
                <input type="email" name="email" class="form-control" id="inputEmail" placeholder="Email" value="${currentUser.email}" required>
            </div>
        </div>
        <!--div class="form-group">
            <label for="inputPassword" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword" placeholder="Password">
            </div>
        </div-->
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Update My profile</button>
            </div>
        </div>
    </form>
    <hr>
    <h3>My Cars</h3>
    <c:if test="${currentUser.getCarList().size() == 0}">
        <em>You don't have added cars jet. Please add one below.</em>
    </c:if>
    <c:forEach items="${currentUser.getCarList()}" var="car">

    <form class="form-horizontal" method="post" action="/overview/updateCar">
        <!--img class="img-circle" src="#" alt="Vehicle photo #1" style="width: 140px; height: 140px;"-->
        <h4>${car.brand} ${car.type}</h4>
        <input type="hidden" name="cid" value="${car.registration}">
        <div class="form-group">
            <label for="inputBrand" class="col-sm-2 control-label">Brand</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="brand" id="inputBrand" placeholder="Brand" value="${car.brand}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputModel" class="col-sm-2 control-label">Type</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="type" id="inputModel" placeholder="Type" value="${car.type}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputSeats" class="col-sm-2 control-label">Seats</label>
            <div class="col-sm-2">
                <input type="number" class="form-control" name="seats" id="inputSeats" value="${car.numberSeats}"> <small>Including yourself</small>
            </div>
        </div>
        <div class="form-group">
            <label for="inputColor" class="col-sm-2 control-label">Color</label>
            <div class="col-sm-10">
                <select class="colorselector" name="color" id="inputColor" data-value="${car.color}">
                    <option value="BROWN" data-color="#A0522D">sienna</option>
                    <option value="DARKRED" data-color="#CD5C5C">indianred</option>
                    <option value="RED" data-color="#FF4500">orangered</option>
                    <option value="OCEANGREEN" data-color="#008B8B">darkcyan</option>
                    <option value="BROWN" data-color="#B8860B">darkgoldenrod</option>
                    <option value="GREEN" data-color="#32CD32">limegreen</option>
                    <option value="GOLD" data-color="#FFD700">gold</option>
                    <option value="OCEANBLUE" data-color="#48D1CC">mediumturquoise</option>
                    <option value="BLUE" data-color="#87CEEB">skyblue</option>
                    <option value="PINK" data-color="#FF69B4">hotpink</option>
                    <option value="LIGHTBLUE" data-color="#87CEFA">lightskyblue</option>
                    <option value="DEEPBLUE" data-color="#6495ED">cornflowerblue</option>
                    <option value="CRIMSON" data-color="#DC143C">crimson</option>
                    <option value="DARKORANGE" data-color="#FF8C00">darkorange</option>
                    <option value="VIOLET" data-color="#C71585">mediumvioletred</option>
                    <option value="BLACK" data-color="#000000">black</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Update this car</button>
            </div>
        </div>
    </form>
    </c:forEach>
    <hr>
    <form class="form-horizontal" method="post" action="/overview/addCar">

        <h4>Add new car</h4>
        <input type="hidden" name="cid" value="${car.registration}">
        <div class="form-group">
            <label for="inputBrand" class="col-sm-2 control-label">Brand</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="brand" id="inputBrand" placeholder="Brand" value="${car.brand}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputModel" class="col-sm-2 control-label">Type</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="type" id="inputModel" placeholder="Type" value="${car.type}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputSeats" class="col-sm-2 control-label">Seats</label>
            <div class="col-sm-2">
                <input type="number" class="form-control" name="seats" id="inputSeats" value="4"> <small>Including yourself</small>
            </div>
        </div>
        <div class="form-group">
            <label for="inputColor" class="col-sm-2 control-label">Color</label>
            <div class="col-sm-10">
                <select class="colorselector" name="color" id="inputColor" data-value="${car.color}">
                    <option value="BROWN" data-color="#A0522D">sienna</option>
                    <option value="DARKRED" data-color="#CD5C5C">indianred</option>
                    <option value="RED" data-color="#FF4500">orangered</option>
                    <option value="OCEANGREEN" data-color="#008B8B">darkcyan</option>
                    <option value="BROWN" data-color="#B8860B">darkgoldenrod</option>
                    <option value="GREEN" data-color="#32CD32">limegreen</option>
                    <option value="GOLD" data-color="#FFD700">gold</option>
                    <option value="OCEANBLUE" data-color="#48D1CC">mediumturquoise</option>
                    <option value="BLUE" data-color="#87CEEB">skyblue</option>
                    <option value="PINK" data-color="#FF69B4">hotpink</option>
                    <option value="LIGHTBLUE" data-color="#87CEFA">lightskyblue</option>
                    <option value="DEEPBLUE" data-color="#6495ED">cornflowerblue</option>
                    <option value="CRIMSON" data-color="#DC143C">crimson</option>
                    <option value="DARKORANGE" data-color="#FF8C00">darkorange</option>
                    <option value="VIOLET" data-color="#C71585">mediumvioletred</option>
                    <option value="BLACK" data-color="#000000">black</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Insert</button>
            </div>
        </div>
    </form>

<jsp:include page="../../jspf/overviewFooter.jsp" />