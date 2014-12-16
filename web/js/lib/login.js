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
    this.doneCallback = function(){};
    this.failCallback = function(){};
    
    var self = this;
    this.$.submit(function(e){
        e.preventDefault();
        $.ajax({
            url:"/login",
            method:"post",
            data:{
                name:self.$.find("#name").val(),
                password:self.$.find("#password").val(),
                rememberMe:self.$.find("#rememberMe").checked
            }
        }).done(self.doneCallback).fail(self.failCallback);
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
    },
    
};