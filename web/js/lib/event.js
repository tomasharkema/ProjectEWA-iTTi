function Event() {
    this.$ = $(".event");
}

Event.prototype = {
    readyScope: function(){
        var rsvp = this.$.find(".rsvp");
        console.log(rsvp);
    }.bind(this)
};

var eventInstance = new Event();

$(document).ready(function(){
    eventInstance.readyScope();
});