/**
 * Created by Saurabh on 17-Feb-16.
 */

$(document).ready(function () {

    $(".holearea").click(function (event) {
        var pebbleCount = event.target.innerHTML;
        if (pebbleCount == 0) {
            console.log("No pebbles present!")
        } else {
            console.log("Make ajax call!");
            for(var i=1; i<13; i++) {
                $("#hole"+i).html(Math.floor(Math.random() * 10));
            }
        }
    });

});