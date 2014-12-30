function Event() {
    this.$ = $(".event");
}

Event.prototype = {

    getAvailableCars: function(eventId, cb) {
        $.ajax({
            url:"/events/availableCars?eventId="+eventId,
            dataType:"json",
            success:cb
        });
    },

    attendEvent:function(eventId, carId, cb){
        $.ajax({
            url:"/events/attend?type=meerijden&eventId="+eventId+"&carId="+carId,
            success:cb
        });
    },

    rsvpModal:function() {
        var eventId = 1;
        var self = this;
        this.getAvailableCars(eventId, function(data){
            var html = '<ul class="list-group">';

            data.cars.forEach(function(item){
                html += '<li class="list-group-item"><a href="#" data-cid="'+item.id+'">'+item.desc+' <span class="label label-default">'+item.places+'</span></a></li>';
            });

            html += '</ul>';

            $("#meerijden_modal .places").html(html);
            $("#meerijden_modal .places ul li a").click(function(){
                var that = $(this);
                var cid = that.attr("data-cid");
                self.attendEvent(eventId, cid, function(res){
                    $("#meerijden_modal").modal('hide');
                    window.location.reload();
                });
            });
            $("#meerijden_modal").modal('show');
        });
    },

    readyScope: function(){
        var rsvp = $(".rsvp");
        var self = this;
        rsvp.find("ul .meerijden a").click(function(e){
            e.preventDefault();
            self.rsvpModal();
            return false;
        });

    }
};

var eventInstance = new Event();

$(function(e) {
    eventInstance.readyScope();
});