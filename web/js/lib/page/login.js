/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

dryves.fb.setFBLoginRedirect("/");

$(function(){
    var login = new Login("#login");
    dryves.fb.registerFBLoginButton("#fblogin");
});