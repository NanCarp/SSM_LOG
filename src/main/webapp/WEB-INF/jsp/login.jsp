<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 上面这两行是java代码的引用 --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript" src="/static/js/jquery-3.1.1.min.js"></script>

<head>
    <title>仓库管理系统→登录</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="/static/js/login.js"></script>
    <link href="/static/css/login2.css" rel="stylesheet" type="text/css"/>
</head>
<html>
<body>

<h1>仓库管理系统登陆注册<sup>2016</sup></h1>

<div class="login" style="margin-top:50px;">

    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);"
                                           tabindex="7">快速登录</a>
            <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a>

            <div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>


    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">

        <!--登录-->
        <div class="web_login" id="web_login">


            <div class="login-box">


                <div class="login_form">
                    <form action="" name="loginform"
                          accept-charset="utf-8" id="login_form" class="loginForm"
                          method="post"><input type="hidden" name="did" value="0"/>
                        <input type="hidden" name="to" value="log"/>

                        <div class="uinArea" id="uinArea">
                            <label class="input-tips" for="u">帐号：</label>

                            <div class="inputOuter" id="uArea">

                                <input type="text" id="u" name="loginId" class="inputstyle"/>
                            </div>
                        </div>
                        <div class="pwdArea" id="pwdArea">
                            <label class="input-tips" for="p">密码：</label>

                            <div class="inputOuter" id="pArea">

                                <input type="password" id="p" name="pwd" class="inputstyle"/>
                            </div>
                        </div>

                        <div style="padding-left:50px;margin-top:20px;">
                            <input type="button"
                                   id="btn_login"
                                   value="登 录"
                                   onclick="webLogin();"
                                   style="width:150px;"
                                   class="button_blue"/></div>
                    </form>
                </div>

            </div>

        </div>
        <!--登录end-->
    </div>

    <!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">

        <div class="web_login">
            <form name="form2" id="regUser" accept-charset="utf-8" action=""
                  method="post">
                <input type="hidden" name="to" value="reg"/>
                <input type="hidden" name="did" value="0"/>
                <ul class="reg_form" id="reg-ul">
                    <div id="userCue" class="cue">快速注册请注意格式</div>
                    <li>

                        <label for="user" class="input-tips2">用户名：</label>

                        <div class="inputOuter2">
                            <input type="text" id="user" name="loginId" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>
                    <li>

                        <label for="user" class="input-tips2">姓名：</label>

                        <div class="inputOuter2">
                            <input type="text" id="name" name="name" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="passwd" class="input-tips2">密码：</label>

                        <div class="inputOuter2">
                            <input type="password" id="passwd" name="pwd" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>
                    <li>
                        <label for="passwd2" class="input-tips2">确认密码：</label>

                        <div class="inputOuter2">
                            <input type="password" id="passwd2" name="" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="cellNumber" class="input-tips2">手机号：</label>

                        <div class="inputOuter2">

                            <input type="text" id="cellnumber" name="cellnumber" maxlength="18" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="sex" class="input-tips2">性别：</label>

                        <div class="inputOuter2">

                            <input type="text" id="sex" name="sex" maxlength="18" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <label for="age" class="input-tips2">年龄：</label>

                        <div class="inputOuter2">

                            <input type="age" id="age" name="age" maxlength="18" class="inputstyle2"/>
                        </div>

                    </li>

                    <li>
                        <div class="inputArea">
                            <input type="button"
                                   id="reg"
                                   onclick="webReg()"
                                   style="margin-top:10px;margin-left:85px;"
                                   class="button_blue"
                                   value="同意协议并注册"/>
                            <a href="#"
                               class="zcxy"
                               target="_blank">注册协议</a>
                        </div>

                    </li>
                    <div class="cl"></div>
                </ul>
            </form>


        </div>


    </div>
    <!--注册end-->
</div>
<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>

<script>
    function webLogin() {
        var loginname = $("#u").val();
        //var是申明一个变量的关键字，loginname为变量名，
        //$("#u")是找到一个标签ID为"u"的标签，.val() 是获取对应ID标签的值
        if ("" == loginname) {  //u标签的值为空
            //只有通过 $("#u") 的形式才能获取一个标签。
            $("#u").tips({  // .tips 是js提示标签的调用方法，具体的轮廓如上面的登陆页面的提示标签
                side: 2,
                msg: '用户名不得为空',  //提示的信息
                bg: '#AE81FF',  //背景色
                time: 3 //呈现的时间
            });
            $("#u").focus();    //让u标签获取输入焦点
            return false;   //返回false，打断js的执行
        }

        var loginpwd = $("#p").val();
        if (loginpwd == "") {
            $("#p").tips({
                side: 2,
                msg: '密码不得为空',
                bg: '#AE81FF',
                time: 3
            });
            $("#p").focus();
            return false;
        }

        $.ajax({
            type: "POST",
            url: '<%=request.getContextPath()%>/userAction/login',
            data: {loginId: loginname, pwd: loginpwd},
            dataType: 'json',
            cache: false,
            success: function (data) {
                if (data.code == 1) {
                    window.location.href = "<%=request.getContextPath()%>/mvc/home";
                } else {
                    alert(data.msg);    //弹出对话框，提示返回的错误信息
                    $("#u").focus();
                }
            }
        })

    }
</script>

</body>
</html>
