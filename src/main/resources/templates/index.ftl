<#import "spring.ftl" as s>
<!DOCTYPE html>
<html>
<head>
    <title>微信授权Demo程序</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link rel="stylesheet" href="/lib/weui.css">
    <link rel="stylesheet" href="/lib/jquery-weui.css">
</head>

<body ontouchstart>
<header class='demos-header'>
    <h1 class="demos-title">微信授权Demo程序</h1>
</header>

<div class="weui-grids">
    <a href="${appHost}/survey/index.html" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/assist/images/survey.jpg" alt="">
        </div>
        <p class="weui-grid__label">
            下户助手
        </p>
    </a>
    <a href="${appHost}/interview/index.html" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/assist/images/interview.png" alt="">
        </div>
        <p class="weui-grid__label">
            面签助手
        </p>
    </a>
    <a href="${appHost}/warrant/index.html" class="weui-grid js_grid">
        <div class="weui-grid__icon">
            <img src="/assist/images/warrant.png" alt="">
        </div>
        <p class="weui-grid__label">
            权证助手
        </p>
    </a>
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
<script>
    $(function() {
        FastClick.attach(document.body);
    });
</script>
<script src="/lib/jquery-weui.js"></script>

</body>

</html>

