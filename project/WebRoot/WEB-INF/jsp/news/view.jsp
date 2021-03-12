<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>公告</title>
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
        > <a href="#" class="c_66">公告</a>
    </div>

    <div class="width100 hidden_yh" style="background: #f0f0f0;padding-top: 34px;padding-bottom: 34px;">
        <div class="width1200 hidden_yh center_yh">
            <div id="vipRight" style="width: 1200px;">
                <div class="hidden_yh bj_fff" style="width: 1198px;border: 1px solid #ddd;">
                    <div class="width100 font24" style="height: 50px;line-height: 50px;width: 200px;text-align: center;">
                        ${obj.name}
                    </div>
                    <div class="hidden_yh" style="padding: 20px;width: 1100px;margin:0 auto;">
                        <div class="width100 hidden_yh" style="border-bottom: 1px solid #ddd;padding-top: 10px;padding-bottom: 10px;">
                            <div class="left_yh" style="width: 100%;color: black;">
                                ${obj.content}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script>
        //总选择数量
        var es = $(".speCific").length;

        //给小计赋值
        (function cx() {
            for(var a=0;a<es;a++){
                var lt = $(".xzJg").eq(a).find("font").html();
                var num = $(".xzSl").eq(a).find("input").val();
                var xj = lt*num;
                $(".xzXj").eq(a).find("font").html(xj)
            }
        })();

        //选择或者不选择一项
        $(".xzWxz").click(function () {
            if($(this).hasClass("on")){
                $(this).removeClass("on");
            }else{
                $(this).addClass("on");
            }
            var ty = $(".xzWxz.on").length;
            $(".sXd1").find("font").html(ty);
            if(ty==es){
                $(".ifAll").addClass("on");
            }else{
                $(".ifAll").removeClass("on");
            }
            jsZj();
        });

        // 计算总计
        function jsZj() {
            var total = 0;
            $(".xzWxz.on").each(function () {
                var price = $(this).parent().find(".xzJg").find("font").html();
                var num = $(this).parent().find(".xzSl").find("input").val();
                total += price*num;
            });
            $("#zjJg").html(sswr(total));
        }

        //保留两位小数
        function sswr(num) {
            return num.toFixed(2);
        }

        // 当加或减商品数量时，结算金额和小计金额会做相应的变化（未勾选的商品对结算金额无影响）
        $(".Aadd").click(function () {
            var t = $(this).siblings(".cont").val()*1;
            var price = $(this).parent().parent().siblings(".xzJg").find("font").html()*1;
            t++;
            $(this).siblings(".cont").val(t);
            $(this).parent().parent().siblings(".xzXj").find("font").html(sswr(price*t));
            jsZj();
        });

        $(".Amin").click(function () {
            var t = $(this).siblings(".cont").val()*1;
            var price = $(this).parent().parent().siblings(".xzJg").find("font").html()*1;
            t--;
            if(t<1){
                t=1;
            }
            $(this).siblings(".cont").val(t);
            $(this).parent().parent().siblings(".xzXj").find("font").html(sswr(price*t));
            jsZj();
        });

        //全选复选框
        $(".ifAll").click(function () {
            if($(".ifAll").hasClass("on")){
                $(this).removeClass("on");
                $(".xzWxz").removeClass("on");
                $(".sXd1").find("font").html(0);
            }else{
                $(this).addClass("on");
                $(".xzWxz").addClass("on");
                $(".sXd1").find("font").html(es);
            }
            jsZj();
        });

        //单项删除功能
        $(".Dels").click(function () {
            var id = $(this).attr("data-id");
            $.ajax({
                type:"POST",
                url:"${ctx}/car/delete?id="+id,
                contentType:"application/json",
                success:function (result) {
                }
            });
            alert("删除成功");
            $(this).parent().parent().parent().remove();
            jsZj();
        });

        //批量删除功能
        $(".ifDel").click(function () {
            $(".xzWxz.on").each(function () {
                var id = $(this).parent().attr("data-id");
                $.ajax({
                    type:"POST",
                    url:"${ctx}/car/delete?id="+id,
                    contentType:"application/json",
                    success:function (result) {
                    }
                });
            });
            alert("删除成功");
            $(".xzWxz.on").parent().remove();
            jsZj();
        });

        /**
         * 结算并进入购买流程
         * @returns {boolean}
         */
        function ifJs() {
            var arr = new Array();
            $(".xzWxz.on").each(function () {
                var $id = $(this).parent().attr("data-id");
                var $num = $(this).parent().find(".xzSl").find("input").val();
                var obj = new Object();
                obj.id = $id;
                obj.num = $num;
                arr.push(obj);
            });
            if(arr.length == 0){
                alert("请至少选择一样商品后进行结算");
                return false;
            }
            $.ajax({
                type:"POST",
                url:"${ctx}/itemOrder/exAdd",
                data:JSON.stringify(arr),
                contentType:"application/json",
                success:function (result) {
                    if(result.res == 0){
                        alert("请登录");
                        window.location.href="${ctx}/login/uLogin";
                    }else if (result.res == 2){
                        alert("请选择或者添加地址");
                    }else{
                        alert("商品购买成功！");
                        window.location.reload();
                    }
                }
            });
        }
        
    </script>

    <%--底部--%>
    <%@include file="/common/uFooter.jsp"%>

</body>
</html>
