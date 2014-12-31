/**
 * Created by tomas on 30-12-14.
 */

function Search(el, resel) {
    var self = this;
    this.$textfield = $(el);
    this.$result = $(resel);

    this.query = "";
    this.type = this.$textfield.attr("data-type");
    this.throttle = 500;

    this.onResultHandler = function(){};
    this.onQueryHandler = function(){};

    var keydownThrottled = _.throttle(self.onSearch.bind(self), self.throttle, {leading: false});
    this.$textfield.keydown(keydownThrottled);
}

Search.prototype = {
    onSearch:function(){
        var self = this;
        var query = this.$textfield.val();
        self.onQueryHandler(query);
        if (query.length === 0) {
            self.$result.addClass("hidden");
        } else {
            self.$result.removeClass("hidden");
            $.ajax({
                url: "/search",
                type: "get",
                dataType: "json",
                data: {
                    q: query,
                    type: this.type
                },
                success: function (data) {
                    self.onResultHandler(data);
                }
            });
        }
        this.query = query;
    }
};