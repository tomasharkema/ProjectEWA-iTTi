/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function FBLogin() {
    
    
}

FBLogin.prototype = {
    ready:function(){
        var self = this;
        $.ajaxSetup({ cache: true });
        $.getScript('//connect.facebook.net/en_UK/all.js', function(){
            FB.init({
                appId: '1654973028062626',
            });
            self.fbinit();
        });
    },
    fbinit:function(){
        $('#fblogin').removeAttr('disabled');
        FB.getLoginStatus(this.updateStatusCallback);
    }, 
    updateStatusCallback:function(response){
        if (response.status === 'connected') {
            console.log('Logged in.');
          }
          else {
            FB.login();
          }
    },
    FBclickCallback:function(){
        FB.login();
    },
    registerFBLoginButton:function(el){
        $(el).click(this.FBclickCallback);
    }
};