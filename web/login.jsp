<%-- 
    Document   : login
    Created on : 1-dec-2014, 18:59:09
    Author     : tomasharkema
--%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <link rel="shortcut icon" href="img/favicon.ico">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/dist/dryves.css" rel="stylesheet">
    <title>The Affable Bean Green Grocer</title>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container" id="dryves">
        <div class="form-signin">
            <form role="form" id="login">
              <h2 class="form-signin-heading">Please sign in</h2>
              <label for="inputEmail" class="sr-only">Email address</label>
              <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
              <label for="inputPassword" class="sr-only">Password</label>
              <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
              <div class="checkbox">
                <label>
                  <input type="checkbox" value="remember-me"> Remember me
                </label>
              </div>
              <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

            </form>
            
            <hr>
            <button class="btn btn-lg btn-block btn-facebook" id="fblogin" disabled>Login with Facebook</button>
        </div>
    </div> <!-- /container -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/lib/dependencies/_jquery.min.js"></script>
    <script src="/js/lib/dependencies/jquery.timeago.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/lib/dependencies/bootstrap.min.js"></script>
    <script src="js/dist/dryves.js"></script>
    <script src="js/lib/page/login.js"></script>
  </body>
</html>

