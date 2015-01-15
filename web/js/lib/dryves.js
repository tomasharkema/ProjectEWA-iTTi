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
    $(function(){
        self.readyScope();
    });
}

Dryves.prototype = {
    readyScope:function(){
        this.fb.ready();
        this.injectLinks();
        this.injectColorDropdown();
        this.initHeaderSearch();
        $.timeago.settings.allowFuture = true;
        $("time.timeago").timeago();
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
    injectColorDropdown:function(){
        $('.colorselector').colorselector({
            callback: function (value, color, title) {
                $("#colorValue").val(value);
                $("#colorColor").val(color);
                $("#colorTitle").val(title);
            }
        });
    },
    initHeaderSearch:function(){
        var headerSearch = new Search("#header-search", "#big-search-results");
        headerSearch.onResult(function(data){
            var resultBox = headerSearch.$result.find(".search-results");
            if (data === undefined) return;

            var html = $("<div>").html("");

            if (data.count > 0) {

                if (data.events !== undefined && data.events.count > 0) {
                    html.append($("<small>").addClass("subtitle").html("EVENTS"))
                        .append(_.reduce(data.events.events, function (mem, event) {
                            return mem.append(
                                $("<a>").addClass("search-res media").attr({href:event.link}).html('<span class="media-left"><img src="' + event.eventLogo + '"></span>').append($("<div>").addClass("media-body").html($('<h4>').addClass("media-heading").html(event.name)))
                            );
                        }, $("<div>")));
                }
                if (data.users !== undefined && data.users.count > 0) {
                    html.append($("<small>").addClass("subtitle").html("USERS"))
                        .append(_.reduce(data.users.users, function (mem, user) {
                            return mem.append(
                                $("<a>").addClass("search-res media").attr({href:user.link}).html('<span class="media-left"><img src="' + user.avatar + '"></span>').append($("<div>").addClass("media-body").html($('<h4>').addClass("media-heading").html(user.name)))
                            );
                        }, $("<div>")));
                }
            } else {
                html.append($("<h5>").html("Found nothing for: "+headerSearch.query+""));
            }

            resultBox.html(html);
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