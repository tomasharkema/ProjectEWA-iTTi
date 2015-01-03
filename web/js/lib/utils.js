/**
 * Created by tomas on 29-12-14.
 */

// utils

// urlParams
$.urlParam = function(name, url) {
    if (!url) {
        url = window.location.href;
    }
    var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(url);
    if (!results) {
        return undefined;
    }
    return results[1] || undefined;
};