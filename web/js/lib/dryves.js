/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var debug = true;
var root = "/";

function Dryves(){
    var self = this;
    this.$ = $("#dryves");
    this.fb = new FBLogin();
    $(document).ready(function(){
        self.readyScope();
    });
}

Dryves.prototype = {
    readyScope:function(){
        this.fb.ready();
        this.injectLinks();
    },
    injectLinks:function(){
        this.$.find('#navbar li').each(function(e){
            var self = $(this);
            var url = self.find('a').attr('href');
            if (window.location.pathname == url) {
                self.addClass("active");
            } else {
                self.removeClass("active");
            }
        });

        this.$.find(".ret").each(function(){
            var self = $(this);
            self.removeClass("ret").attr({href:self.attr("href") + "?ret=" + encodeURIComponent(window.location.href)});
        });
    },
    redirect:function(url){
        window.location.href = url;
        /*$.ajax({url:url})
                .done(function(res){
                   console.log("YAY!", res); 
                });*/
    }
};

var dryves = new Dryves();