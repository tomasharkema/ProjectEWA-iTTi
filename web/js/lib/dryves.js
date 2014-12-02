/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var debug = true;
var root = "http://localhost:8080/Dryves";

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
    },
    redirect:function(url){
        window.location.href = root+url;
        /*$.ajax({url:url})
                .done(function(res){
                   console.log("YAY!", res); 
                });*/
    }
};

var dryves = new Dryves();