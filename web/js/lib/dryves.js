/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var SCOPE = {};

function Dryves(){
    var self = this;
    this.$ = $("#dryves");
    this.fb = new FBLogin();
    $(function(){
        self.readyScope();
    });
}

Dryves.prototype = {
    readyScope:function(){
        this.fb.ready();
    }
};

var dryves = new Dryves();