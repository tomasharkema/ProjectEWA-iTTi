/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function(){
    console.log(dryvesInstance);
    dryvesInstance.fb.setFBLoginRedirect("/");
    var login = new Login("#login");
    dryvesInstance.fb.registerFBLoginButton("#fblogin");
});