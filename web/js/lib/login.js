/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function Login(identifier) {
    
    if (identifier === undefined) { 
        throw new Error(identifier + " has not been found...");
    }
    
    this.$ = $(identifier);
    this.doneCallback;
    this.failCallback;
    
    this.$.submit(function(e){
        e.preventDefault();
        $.ajax({
            url:"login/",
            method:"post",
            data:{
                name:this.$.find("#name").val(),
                password:this.$.find("#password").val(),
                rememberMe:this.$.find("#rememberMe").checked
            }
        }).done(doneCallback).fail(failCallback);
        return false;
    });
}

Login.prototype = {
    done : function(cb){
        if (typeof cb !== "function") {
            throw new Error("callback must be a function");
        }
        this.doneCallback = cb;
    },
    fail:function(cb){
        if (typeof cb !== "function") {
            throw new Error("callback must be a function");
        }
        this.failCallback = cb;
    }
};