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
    
    init();
}

Login.prototype = {
    done : function(cb){
        if (typeof cb !== "function") {
            throw new Error("callback must be a function");
        }
        this.doneCallback = cb;
    }
};

function init() {
    this.$.submit(function(e){
        e.preventDefault();
        
        
        
        return false;
    });
}