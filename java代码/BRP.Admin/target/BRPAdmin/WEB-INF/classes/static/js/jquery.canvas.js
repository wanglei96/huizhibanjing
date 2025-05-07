

jQuery.fn.extend({
    downloadCanvas: function (options) {
        return jQuery.canvas.download(this, options);
    }
});

jQuery.extend({
    canvas: {
        options: {
            maxSize: 5 * 1024 * 1024,
            fileUploadID: null,
            isImage: true,
            isPreviewImage: true, //是否显示预览图片
            previewImageID: null,
            autoUpload: true
        },
        download: function (element, options) {
            //alert("uploadify");
            var options = $.extend({}, jQuery.dragger.options, options);
            var type = options.type;
            // 图片导出为 png 格式
            var imgData = $("#" + options.elementID)[0].toDataURL(type);
            /**
            * 获取mimeType
            * @param  {String} type the old mime-type
            * @return the new mime-type
            */
            var _fixType = function (type) {
                type = type.toLowerCase().replace(/jpg/i, 'jpeg');
                var r = type.match(/png|jpeg|bmp|gif/)[0];
                return 'image/' + r;
            };
            // 加工image data，替换mime type
            var mineType = _fixType(type);
            imgData = imgData.replace(mineType, 'image/octet-stream');
            /**
            * 在本地进行文件保存
            * @param  {String} data     要保存到本地的图片数据
            * @param  {String} filename 文件名
            */
            var saveFile = function (data, filename) {
                var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
                save_link.href = data;
                save_link.download = filename;

                var event = document.createEvent('MouseEvents');
                event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
                save_link.dispatchEvent(event);
            };

            // 下载后的问题名
            var filename = options.fileName + (new Date()).getTime() + '.' + type;
            // download
            saveFile(imgData, filename);
            return element;
        }
    }
});