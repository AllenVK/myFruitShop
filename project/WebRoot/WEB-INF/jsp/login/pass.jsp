<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>用户安全中心</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/resource/user/css/style.css">
    <script src="${ctx}/resource/user/js/jquery-1.8.3.min.js"></script>
    <script src="${ctx}/resource/user/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body>
    <%@include file="/common/uTop.jsp"%>

    <%--导航条--%>
    <div class="width100" style="height: 45px;background-color: #dd4545; margin-top: 40px; position: relative; z-index: 100;">
        <%--中间部分--%>
        <div class="width1200 center_yh relative_yh" style="height: 45px">
            <%--普通导航--%>
            <div class="left_yh font16" id="pageNav">
                <a href="${ctx}/login/uIndex">首页</a>
                <a href="${ctx}/news/list">公告</a>
                <a href="${ctx}/message/add">留言</a>
            </div>
        </div>
    </div>

    <div class="width1200 center_yh hidden_yh font14" style="height: 40px;line-height: 40px;">
        <span>当前位置：</span><a href="${ctx}/login/uIndex" class="c_66">首页</a>
        > <a href="#" class="c_66">个人中心</a>
        > <a href="#" class="c_66">修改密码</a>
    </div>

    <div class="width100 hidden_yh" style="background-color: #f0f0f0;padding-top: 34px;padding-bottom: 34px;">
        <div class="width1200 hidden_yh center_yh">
            <div id="vipNav">
                <a href="${ctx}/user/view" >个人信息</a>
                <a href="${ctx}/itemOrder/my">我的订单</a>
                <a href="${ctx}/sc/findBySql">我的收藏</a>
                <a href="${ctx}/login/pass" class="on">修改密码</a>
            </div>
            <div id="vipRight">
                <div class="font24" style="height: 60px;line-height: 60px;text-indent: 50px;background: #f5f8fa;border-bottom: 1px solid #ddd;">
                    修改密码
                </div>
                <input type="hidden" id="yuan" value="${obj.passWord}">
                <div class="bj_fff hidden_yh" style="width: 838px;border: 1px solid #ddd;margin-top: 30px;padding: 50px;">
                    <div class="width100" style="height: 32px;line-height: 32px;margin: 15px;">
                        <div class="left_yh font16 tright" style="width: 120px;">
                            <font class="red">*</font>旧密码：
                        </div>
                        <input type="password" id="jiu" style="width: 243px;border: 1px solid #ddd;outline: none;height: 30px;line-height: 30px;text-indent: 10px;">
                    </div>
                    <div class="width100" style="height: 32px;line-height: 32px;margin: 15px;">
                        <div class="left_yh font16 tright" style="width: 120px;">
                            <font class="red">*</font>新密码：
                        </div>
                        <input type="password" id="xin" style="width: 243px;border: 1px solid #ddd;outline: none;height: 30px;line-height: 30px;text-indent: 10px;">
                    </div>
                    <div class="width100" style="height: 32px;line-height: 32px;margin: 15px;">
                        <div class="left_yh font16 tright" style="width: 120px;">
                            <font class="red">*</font>确认密码：
                        </div>
                        <input type="password" id="que" style="width: 243px;border: 1px solid #ddd;outline: none;height: 30px;line-height: 30px;text-indent: 10px;">
                    </div>
                    <div class="width100" style="height: 32px;line-height: 32px;margin: 15px;">
                        <a href="javascript:void(0)" class="left_yh block_yh font16 tcenter ff5802 con" style="width: 244px;height: 33px;line-height: 33px;margin-left: 120px;">
                            保存修改
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--底部--%>
    <%@include file="/common/uFooter.jsp"%>

</body>
    <script>
        $(".con").click(function () {
            var yuan = $("#yuan").val();
            var jiu = $("#jiu").val();
            var xin = $("#xin").val();
            var que = $("#que").val();
            if(jiu == null || jiu == ''){
                alert("旧密码不能为空");
                return false;
            }
            if(xin == null || xin == ''){
                alert("新密码不能为空");
                return false;
            }
            if(que == null || que == ''){
                alert("确认密码不能为空");
                return false;
            }
            if(jiu != yuan){
                alert("旧密码输入不正确");
                return false;
            }
            if(xin == yuan){
                alert("新密码不能与原密码一样");
                return false;
            }
            if(xin != que){
                alert("新密码与确认密码不一致，请重新输入");
                return false;
            }
            $.ajax({
                type:"POST",
                url:"${ctx}/login/upass.do",
                data:{
                    "password":xin
                },
                success:function (result) {
                    if(result.res==0){
                        alert("请先登录");
                    }else{
                        alert("密码修改成功!请重新登录");
                    }
                    top.location.href = "${ctx}/login/uLogin";
                }
            });
        });
    </script>
</html>
