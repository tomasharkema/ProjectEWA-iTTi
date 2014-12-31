function Timeline() {
    this.$timeline = $(".time-line");
    this.init();
}

Timeline.prototype = {
    init:function(){
        var self = this;
        this.$timeline.find('.time-line-node').each(function(){
            self.timeLineNode.call(self, this);
        });
    },

    timeLineNode:function(el){
        // Replace ubb
        var html = $(el).html()
            .replace(/\[user id='(.*?)'](.*?)\[\/user]/g, '<b><a href="/user?userId=$1">$2</a></b>')
            .replace(/\[event id='(.*?)'](.*?)\[\/event]/g, '<b><a href="/events?eventId=$1">$2</a></b>');
        $(el).html(html);
    }
};

var timeline;
$(function(){
    if ($(".time-line").length > 0) {
        timeline = new Timeline();
    }
});