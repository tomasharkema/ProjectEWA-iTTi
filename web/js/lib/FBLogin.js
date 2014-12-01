
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
    updateStatusCallback:function(response){
        console.log('login change', response);
        if (response.status === 'connected') {
            if (this.shouldInvokeFBLogin) {
                dryves.redirect(this.FBRedirect);
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