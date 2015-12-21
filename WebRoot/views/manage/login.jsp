<%--
  Created by IntelliJ IDEA.
  User: qqzeng
  Date: 2015/12/18
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
  application.setAttribute("basePath",basePath);

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

  <title>登录页</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <script type="text/javascript" src="${basePath }/js/jquery-2.1.1.min.js"></script>
  <link type="text/css" href="${basePath }/css/login.css" rel="stylesheet" />
  <script type="text/javascript">

    $(function(){
      //禁止部分页面元素被双击或者是鼠标选中
      $("body").children().each(function(){
        this.onselectstart = function () {
          return false;
        }
      });
      //提交
        $("#submit_btn").click(function(){
          var str_admin_name = $.trim($(".admin_name_1").val());
          var str_admin_password = $.trim($(".admin_password_1").val());
          //防止表单在没有填写的情况下重复提交
          if(str_admin_name != "" && str_admin_password != "" && "${empty requestScope.login_error}"){
            $("#admin_login_form").submit();
          }else{
            alert("请填写用户名与密码！");
            return false;
          }
        });
      //取消
        $("#reset_btn").click(function(){
          $.trim($(".admin_name_1").val(""));
         $.trim($(".admin_password_1").val(""));
        });
      //禁止按键F5
      if(((event.keyCode==116))&& "${not empty requestScope.login_error}" != "false")
      {
        event.keyCode=0;
        event.returnValue=false;
      };

    //禁止鼠标右键菜单
      document.oncontextmenu = function(e){
        if("${not empty requestScope.login_error}" != "false") {
          return false;
        }
      }
    })

  </script>
</head>

<body style="overflow:hidden;overflow-y:hidden">
<div class="wrap">
  <div class="welcome_div"><img src="${basePath }/img/images/welcome_img.png" width="500px" height="270px" /></div>
  <div class="top_div"></div>
  <div class="main_div">
    <div class="half_top"></div>
    <div class="half_bottom"></div>
    <div class="login_div">
      <form action="${basePath}/admin/login" method="post" id="admin_login_form">
        <span class="title">管理登录</span>
        <table cellpadding="10" cellspacing="15">
          <tr>
            <td><div style="width:36px">账号：</div></td>
            <td width="167px">
              <div class="admin_name_div" style="width:167px;">
                <img src="${basePath }/img/images/admin_img.png" style="float:left">
                <input class="admin_name_1" type="text" name="name" maxlength="10" />
              </div>
            </td>
          </tr>
          <tr>
            <td><div style="width:36px">密码：</div></td>
            <td width="167px">
              <div class="admin_password_div" style="width:167px;">
                <img src="${basePath }/img/images/password_img.png" style="float:left">
                <input class="admin_password_1" type="password" name="password" maxlength="10" />
              </div>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <input class="reset_btn" id="reset_btn"  type="button"/>
              <input class="submit_btn" id="submit_btn" type="button" />
            </td>
          </tr>
          <c:if test="${not empty requestScope.login_error}">
            <div style="color: #f00; position:absolute; top: 200px; left: 65px;"> ${requestScope.login_error}</div>
          </c:if>
        </table>
      </form>
    </div>
  </div>
  <div class="copyright_div">
    <span>Copyright &copy; 2015 <a href="${basePath}/views/index.jsp">http://www.martrymemory70.com</a> All Rights Reserved 京ICP备 13046642号-2</span>
  </div>
  <div class="bottom_div"></div>
</div>
</body>
</html>
