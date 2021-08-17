<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/12 0012
  Time: 下午 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html lang="zh_CN" style="background-color: #d6a280;">
<head>

    <title><spring:message code="core.login.title" text="登录"/></title>

    <link href="nxoa.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        $(function() {

            if(window.top !== window.self) {
                window.top.location = window.location;
            }

            focusUsername();
        });

        function focusTenant() {
            if (document.f.tenant.value == '') {
                document.f.tenant.focus();
            } else {
                focusUsername();
            }
        }

        function focusUsername() {
            if (document.f.j_username.value == '') {
                document.f.j_username.focus();
            } else {
                document.f.j_password.focus();
            }
        }
    </script>

    <style type="text/css">
        .popWindow {
            background-color:#fff;
            width: 100%;
            height: 100%;
            left: 0;
            top: 0;
            filter: alpha(opacity=100);
            opacity: 1;
            z-index: 1;
            position: absolute;
        }
        .maskLayer {
            background-color: #fff;
            width: 100%;
            height: auto;
            line-height: 30px;
            margin:35% 0;
            color: #fff;
            z-index: 2;
            position: absolute;
            text-align: center;
        }
        .loading_img{
            width: 30%;
            margin: 0;
        }
    </style>

    <script language="javascript" type="text/javascript">
        //显示遮罩
        function showDiv() {
            var u = navigator.userAgent;
            if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1 || u.indexOf('iPhone') > -1) {//移动端
                //展示loading
                document.getElementById('maskLayer').style.display = 'block';
            } else {//Web端
                //取消背景
                document.getElementById('popWindow').style.display = 'none';
            }
        }
        //自动提交
        function submitForm(username,password){
            document.getElementById("username").value = username;
            document.getElementById("password").value = password;
            document.getElementById("mobileVersion").value = '1';
            document.getElementById("userForm").submit();
        }
    </script>

</head>

<body onload="showDiv()" >
<div  class="login_bg">
    <div id="popWindow" class="popWindow" style="display: block;"></div>
    <div id="maskLayer" class="maskLayer" style="display: none;">
        <img src="../common/image/loading.gif" class="loading_img"/>
    </div>

    <!-- start of header bar -->
    <%--<div class="navbar navbar-default navbar-fixed-top">--%>
    <%--<div class="container-fluid">--%>
    <%--<div class="navbar-header">--%>
    <%--<a class="navbar-brand" href="${tenantPrefix}" style="color: #fff;">--%>
    <%--<img src="${cdnPrefix}/logo32.png" class="img-responsive pull-left" style="margin-top:-5px;margin-right:5px;">--%>
    <%--诺信协同办公系统<sub><small></small></sub>--%>
    <%--</a>--%>
    <%--</div>--%>

    <%--<div class="navbar-collapse collapse">--%>

    <%--<ul class="nav navbar-nav navbar-right">--%>
    <%--<li>--%>
    <%--<a href="?locale=zh_CN"><img src="${cdnPrefix}/flags/china.gif" height="20"></a>--%>
    <%--</li>--%>
    <%--<li>--%>
    <%--<a href="?locale=en_US"><img src="${cdnPrefix}/flags/us.gif" height="20"></a>--%>
    <%--</li>--%>
    <%--</ul>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</div>--%>
    <!-- end of header bar -->

    <div class="login_box">
        <div class="container-fluid" style="display: block; width: 50%;height: auto">

            <%--<div class="col-md-4"></div>--%>

            <!-- start of main -->
            <section class="login_boxes">

                <%--<img src="image/login_logo.png" class="login_logo">--%>
                <article class="panel panel-default" style="background-color: inherit;">
                    <%--<header class="panel-heading">--%>
                    <%--<spring:message code="core.login.title" text="欢迎登录"/>--%>
                    <%--</header>--%>

                    <div class="panel-body">

                        <form id="userForm" name="f" method="post" action="${tenantPrefix}/j_spring_security_check" class="form-horizontal">
                            <input type="text" id="mobileVersion" name="mobileVersion" value="0" hidden></input>
                            <%--<div class="form-group" style="display:none">--%>
                            <%--<label class="col-md-2 control-label" for="tenant">租户</label>--%>
                            <%--<div class="col-md-10">--%>
                            <%--<input type='text' id="tenant" name='tenant' class="form-control" value="${empty sessionScope['SECURITY_LAST_TENANT'] ? cookie['SECURITY_LAST_TENANT'].value : sessionScope['SECURITY_LAST_TENANT']}">--%>
                            <%--<span id="tenantText" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="right:15px;cursor:pointer;pointer-events:auto;display:none;"></span>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <%--<label class="control-label" for="username" ><spring:message code="core.login.username" text="账号" /></label>--%>
                                <div class="inp_box_login">
                                    <input type='text' id="username" name='j_username' class="form-control"  aria-describedby="inputSuccess3Status"  style="text-align: center"  placeholder="请输入用户名">
                                    <span id="usernameText" class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true" style="right:15px;cursor:pointer;pointer-events:auto;display:none;"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <%--<label class="control-label" for="password"><spring:message code="core.login.password" text="密码"/></label>--%>
                                <div class="inp_box_login">
                                    <input type='password' id="password" name='j_password' class="form-control" value='' style="text-align: center"  placeholder="请输入密码">
                                </div>
                            </div>
                            <c:if test="${sessionScope['captchaSessionToken']}">
                                <div class="form-group" id="captchaArea">
                                        <%--<label class="control-label yanzheng_txt" for="password">验证码</label>--%>
                                    <div class="inp_box_login" style="width: 100%; display: table">
                                        <input type='text' id="captcha" name='captcha' class="form-control" value='' style="float:left; width: 60%;text-align: center" placeholder="请输入验证码">
                                        <img id="captchaPicture" src="captcha.jsp?_=<%=System.currentTimeMillis()%>" onclick="this.src='captcha.jsp?_=' + new Date().getTime()" style="float: right;width:30%; height: 34px;">
                                    </div>
                                </div>
                            </c:if>
                            <!--                   <div class="form-group"> -->
                            <!--                     <label class="col-md-2 control-label" for="username">&nbsp;</label> -->
                            <!--                     <div class="col-md-10"> -->
                            <!--                       <input type='checkbox' name='_spring_security_remember_me' id="_spring_security_remember_me" /> -->
                            <!--                       <label for="_spring_security_remember_me">两周内自动登陆</label> -->
                            <!--                     </div> -->
                            <!--                   </div> -->
                            <div class="form-group">
                                <div class="">
                                    <input class="btn btn-default nxorange_btn" name="submitBtn" type="submit" value="<spring:message code='core.login.submit' text='提交'/>" style="    margin-top: 10%;"/>
                                </div>
                            </div>
                            <div class="alert alert-danger" role="alert" ${param.error==true ? '' : 'style="display:none"'}>
                                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                <strong><spring:message code="core.login.failure" text="登陆失败"/></strong>
                                <br>
                                ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
                            </div>
                        </form>
                    </div>
                </article>

                <%--<div class="m-spacer"></div>--%>
            </section>
            <!-- end of main -->

            <div class="col-md-4"></div>
        </div>
    </div>

    <p style="min-height: 20px; position: fixed; bottom: 0px; width: 100%; text-align: center; color: #fff;">Copyright © 2014-2017 chinanx.com.cn 版权所有/中永诺信（北京）投资有限公司 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 备案号：<a target="_blank" href="http://www.miitbeian.gov.cn">京ICP备14028485号-1</a></p>
</div>
</body>
</html>

