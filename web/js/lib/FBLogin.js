
function FBLogin() {
    this.shouldInvokeFBLogin = false;
    this.FBRedirect = "";
}

FBLogin.prototype = {
    ready:function(){
        var self = this;
        $.ajaxSetup({ cache: true });
        $.getScript('//connect.facebook.net/en_UK/all.js', function(){
            FB.init({
                appId: '1654973028062626',
            });
            self.FBInit.bind(self)();
        });
    },
    FBInit:function(){
        $('#fblogin').removeAttr('disabled');
        FB.getLoginStatus(this.updateStatusCallback.bind(this));
    },
    FBGetUserInfo:function(cb){
        FB.api('/me', cb);
    },
    FBCheckUserAccount:function(fbuser, cb){
        $.ajax({
            url:"login",
            method:"post",
            dataType:"json",
            data:{
                name:fbuser.name,
                fbid:fbuser.id,
                isFB:true
            }
        }).done(function(res){
            cb(res);
        }).fail(function(jqXHR, err){
            console.log("fail", err);
        });
    },
    updateStatusCallback:function(response){
        console.log('login change', response);
        if (response.status === 'connected') {
            if (this.shouldInvokeFBLogin) {
                this.FBGetUserInfo(function(fbuser){
                    console.log("FBUser", fbuser);
                    this.FBCheckUserAccount(fbuser, function(res){
                        console.log(res);
                        if (res.success) {
                            dryves.redirect(this.FBRedirect);
                        }
                        
                        if (res.newUser) {
                            dryves.redirect("/newuser.jsp?fb=true");
                        }
                    });
                }.bind(this));
            }
        }
    },
    FBLoginCallback:function(res){
        console.log("OJOO", res);
    },
    FBclickCallback:function(){
        FB.login(this.FBLoginCallback, {scope:'publish_actions'});
    },
    registerFBLoginButton:function(el){
        $(el).click(this.FBclickCallback);
    },
    setFBLoginRedirect:function(url){
        this.shouldInvokeFBLogin = true;
        this.FBRedirect = url;
    }
};