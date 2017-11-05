<#import "spring.ftl" as s>
<!DOCTYPE html>
<html>
<head>
    <title>微信授权Demo程序</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link rel="stylesheet" href="/lib/weui.min.css">
    <link rel="stylesheet" href="/lib/jquery-weui.css">

</head>

<body ontouchstart>
<header class='demos-header'>
    <h1 class="demos-title">微信授权Demo程序</h1>
    <p class='demos-sub-title'>在使用之前请先绑定员工手机号码</p>
</header>

<div class="weui-cells weui-cells_form">
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <div class="weui-uploader">
                <div class="weui-uploader__hd">
                    <p class="weui-uploader__title">图片上传</p>
                    <div class="weui-uploader__info">0/2</div>
                </div>
                <div class="weui-uploader__bd">
                    <ul class="weui-uploader__files" id="uploaderFiles">
                    </ul>
                    <div class="weui-uploader__input-box">
                        <div id="uploaderInput" class="weui-uploader__input"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="weui-footer">
    <p class="weui-footer__text">Copyright © 2017 wallbase.tv</p>
</div>

<style>
    .weui-footer {
        margin: 25px 0 10px 0;
    }
</style>


<script src="/lib/jquery-2.1.4.js"></script>
<script src="/lib/fastclick.js"></script>
<script src="/lib/jquery-weui.js"></script>
<script src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<script>
    $(function () {
        FastClick.attach(document.body);
        $.ajax({
            url: "/assist/user/get_ticket",
            data: {url: "https://dev.zhongfl.com/assist/uploadDemo"},
            type: "POST",
            dataType: "json",
            success: function (result) {
                wx.config({
                    debug: false,
                    appId: result.data.appId,
                    timestamp: result.data.timestamp.toString(),
                    nonceStr: result.data.noncestr,   //生成签名的随机串
                    signature: result.data.signature,  //签名
                    jsApiList: ['chooseImage', 'uploadImage', 'downloadImage']
                });
            }
        });//AJAX
    });

    wx.ready(function () {
        var tmpl = '<li class="weui-uploader__file" style="background-image:url(#url#)"></li>';
        var $uploaderFiles = $("#uploaderFiles");

        var syncUpload = function (localIds) {
            var localId = localIds.pop();
            wx.uploadImage({
                localId: localId,
                isShowProgressTips: 1,
                success: function (res) {
                    var serverId = res.serverId; // 返回图片的服务器端ID
                    $.ajax({
                        url: "/assist/api/v1/images/add_medias",
                        data: {
                            mediaIds: serverId,
                            loanOrderNo: "123445677",
                            videoKey: "100",
                            loanUserId: "",
                            imageType: "100"
                        },
                        type: "POST",
                        dataType: "json",
                        success: function (result) {
                            $uploaderFiles.append($(tmpl.replace('#url#', localId)));
                        }
                    });//AJAX
                    //其他对serverId做处理的代码
                    if (localIds.length > 0) {
                        syncUpload(localIds);
                    }
                }
            });
        };
        //点击图片上传时间，
        //王坤 注意一定要异步的，然后不要一次多张上传，选择可以是多张
        //@see link https://www.cnsecer.com/8864.html
        $("#uploaderInput").click(function () {
            wx.chooseImage({
                count: 9, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    var localIds = res.localIds;
                    syncUpload(localIds);
                },
                fail: function (err) {
                    alert(err.errMsg);
                }
            });//chooseImage END
        });
    });

    wx.error(function (res) {

    });
</script>

</body>

</html>

