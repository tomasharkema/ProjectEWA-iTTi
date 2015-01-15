/**
 * Created by tomas on 30-12-14.
 */

function Search(el, result) {
    var self = this;
    this.$textfield = $(el);
    this.$result = $(result);

    this.query = "";
    this.type = this.$textfield.attr("data-type");
    this.throttle = 500;

    this._resultHandler = function(){};
    this._queryHandler = function(){};

    var keydownThrottled = _.throttle(self.onSearch.bind(self), self.throttle, {leading: false});
    this.$textfield.keydown(keydownThrottled);
}

Search.prototype = {
    onQuery:function(q){
        this._queryHandler = q;
    },
    onResult:function(r){
        this._resultHandler = r;
    },
    onSearch:function(){
        var self = this;
        var query = this.$textfield.val();
        self._queryHandler.call(self, query);
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
                    self._resultHandler.call(self, data);
                }
            });
        }
        this.query = query;
    }
};