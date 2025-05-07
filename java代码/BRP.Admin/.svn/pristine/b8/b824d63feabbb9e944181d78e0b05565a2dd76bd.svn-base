

jQuery.fn.extend({
    startDrag: function (options) {
        return jQuery.dragger.startDrag(this, options);
    }
});

var params = {
    left: 0,
    top: 0,
    currentX: 0,
    currentY: 0,
    flag: false
};
jQuery.extend({
    dragger: {

        options: {
        },
        startDrag: function (element, options) {
            //alert("uploadify");
            var options = $.extend({}, jQuery.dragger.options, options);
            var $target = $(options.target);
            console.log("target:" + $target);
            $target.css("left", 10);
            element.each(function () {
                console.log("left:" + $target.css("left"));
                params.left = $target.css("left");
                params.top = $target.css("top");
                //o是移动对象
                $(document).unbind("mousedown");
                $(this).mousedown(function (event) {
                    params.flag = true;
                    if (!event) {
                        event = window.event;
                        //防止IE文字选中
                        $(this)[0].onselectstart = function () {
                            return false;
                        }
                    }
                    var e = event;
                    params.currentX = e.clientX;
                    params.currentY = e.clientY;
                });
                $(document).unbind("mouseup");
                $(document).mouseup(function () {
                    params.flag = false;
                    params.left = $target.css("left");
                    params.top = $target.css("top");
                });
                $(document).unbind("mousemove");
                $(document).mousemove(function (event) {
                    console.log(params);
                    var e = event ? event : window.event;
                    if (params.flag) {
                        var nowX = e.clientX, nowY = e.clientY;
                        console.log("nowX:" + nowX);
                        var disX = nowX - params.currentX
                        var disY = nowY - params.currentY;
                        console.log("disX:" + disX);
                        $target.css("left", parseInt(params.left) + disX + "px");
                        $target.css("top", parseInt(params.top) + disY + "px");

                        if (typeof options.moveCallback == "function") {
                            options.moveCallback(parseInt(params.left) + disX, parseInt(params.top) + disY);
                        }
                    }

                });
            });
            return element;
        }
    }
});