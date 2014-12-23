
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
    FBGetUserAvatar:function(cb){
        FB.api('/me/picture', {
            "redirect": false,
            "height": "400",
            "type": "normal",
            "width": "400"
        },function(res){
            if (res && !res.error) {
                cb(res.data.url);
            } else {
                cb(null);
            }
        });
    },
    FBCheckUserAccount:function(obj, cb){
        var fbuser = obj.fbuser;
        fbuser.avatar = obj.avatar;
        $.ajax({
            url:"login",
            method:"post",
            dataType:"json",
            data:fbuser,
            cache:false
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
                    this.FBGetUserAvatar(function(avatarUrl){
                        this.FBCheckUserAccount({fbuser:fbuser, avatar:avatarUrl}, function(res){
                            console.log(res);
                            if (res.loggedin) {
                                dryvesInstance.redirect(this.FBRedirect);
                            }
                        }.bind(this));
                    }.bind(this));
                }.bind(this));
            }
        }
    },
    FBLoginCallback:function(res){
        console.log("OJOO", res);
    },
    FBclickCallback:function(){
        FB.login(this.FBLoginCallback, {scope:'publish_actions email user_location'});
    },
    registerFBLoginButton:function(el){
        $(el).click(this.FBclickCallback);
    },
    setFBLoginRedirect:function(url){
        this.shouldInvokeFBLogin = true;
        this.FBRedirect = url;
    }
};