window.addEventListener('DOMContentLoaded', function() {
    "use strict";
    var ql = new QueryLoader2(document.querySelector("body"), {
        barColor: "#0D4E94",
        backgroundColor: "#0C141D",
        percentage: false,
        barHeight: 10,
        minimumTime: 300,
        fadeOutTime: 1500
    });
});
