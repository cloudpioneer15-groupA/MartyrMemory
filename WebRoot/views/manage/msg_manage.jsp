<%@ page import="com.simpletech.MartyrMemory.model.Message" %>
<%--
  Created by IntelliJ IDEA.
  User: qqzeng
  Date: 2015/12/16
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
  application.setAttribute("basePath",basePath);
%>
<head>
  <title>后台管理</title>
  <script type="text/javascript"  src="${basePath}/js/jquery-2.1.1.min.js"></script>
  <link type="text/css" href="${basePath}/css/msg_manange.css"  rel="stylesheet"/>

  <script type="text/javascript">
    //跳转指定页
    function goTo(totalPage){
      var currentPage = parseInt($("input:text").val()) - 1;
      var regNum = /^[0-9]*$/;
      if(regNum.test(currentPage)){
        currentPage =  currentPage >totalPage-1?totalPage-1:currentPage;
        window.location.href="${basePath}/message/list/${page.datas[0].status}/${page.pageSize}/"+currentPage;
      }else{
        alert("请输入正确的页码！");
        $("input:text").val("");
      }
    }
    //编辑指定留言通过状态
    function doEditStatus(status, inputNode, id){
      var res = window.confirm("确认修改指定留言的审核状态吗？")
      if(res){
        var url = "${pageContext.request.contextPath}/message/updateStatus/"+id;
        $.post(url, {"status" : status}, function(backData, textStatus, ajax){
          var jsonJS = eval("("+ajax.responseText+")");
          //移除页面记录,并重加载页面
          $(inputNode).parent().remove();
          window.location.reload(true);
          alert(jsonJS.result);
        });
      }
    }

    $(function(){
      //禁止部分页面元素被双击或者是鼠标选中
        $("body").children().each(function(){
            this.onselectstart = function () {
              return false;
            }
        });
      //改变指操作的按钮的颜色及全选框的状态
      $(":checkbox").click(function(){
          if($("input:checkbox[name=selectedRow]:checked").length > 0){
           $(".valid_batch").css("background", "#1C1A1A");
            $(".delete_batch").css("background", "#1C1A1A");
            $(".valid_batch").addClass("background_gradual_change_over");
            $(".delete_batch").addClass("background_gradual_change_over");
          }else{
            $(".valid_batch").css("background", "#858584");
            $(".delete_batch").css("background", "#858584");
            $(".valid_batch").addClass("background_gradual_change_out");
            $(".delete_batch").addClass("background_gradual_change_out");
          }
          if($("input:checkbox[name=selectedRow]:checked").length == $("input:checkbox[name=selectedRow]").length){
            $("#selAll").prop("checked", true);
          }else{
            $("#selAll").prop("checked", false);
          }
      });
      //更改每页条数
      $("#page_size_select").change(function(){
        var $pageSize = $("#page_size_select").val();
        if($pageSize > 0){
        window.location.href="${basePath}/message/list/${page.datas[0].status}/"+ $pageSize
                +"/${page.currentPage }"
        }
      });
    });
    //批量选中
    function doSelectAll() {
      //$("input[name=selectedRow]").attr("checked", $("#selAll").is(":checked"));
      $("input[name=selectedRow]").prop("checked", $("#selAll").is(":checked"));
    }
    //批量更改记录状态
    function doEditSelected(status){
      var res = window.confirm("确认修改选中记录的审核状态吗？")
      if(res){
        if($(":checkbox[name=selectedRow]:checked").length <= 0){
          alert("请先选中记录！");
          return false;
        }
        document.forms[0].action = "${basePath}/message/editSelectedMessage/"+ status + "/"+ "${page.pageSize}/"+"${page.currentPage}";
        document.forms[0].submit();
      }
    }
  </script>

</head>
<body>
<div class="wrap">
  <div class="top">
    <div class="top_title">
      <div class="span_title_bar_top"></div>
      <span class="span_title">后台管理界面</span>
      <div class="welcome_div">
        <span>欢迎您，${sessionScope.admin.adminName}</span>
        <span > <a href="${basePath}/admin/logout">安全退出</a></span>
      </div>
      <div class="span_title_bar_bottom"></div>
    </div><!--顶部标题区结束-->
    <div class="top_data">
      <div class="num_flower">
        <span class="num_flower_lable">献花人数</span><!--标题区结束-->
					<span class="num_flower_content" style="font-size:20px;">
						${requestScope.numFlower}
					</span><!--数量显示框结束-->
      </div><!--献花总数区-->
      <div class="num_message">
        <span class="num_message_lable">留言人数</span><!--标题区结束-->
					<span class="num_message_content" style="font-size:20px;">
						${requestScope.numMessage}
					</span><!--数量显示框结束-->
      </div><!--留言总数区-->
      <div class="num_message_unchecked">
        <span class="num_message_unchecked_lable">待审核留言数:</span><!--标题区结束-->
            <c:if test="${not empty requestScope.numUnCheckedMsg}">
              <c:if test="${requestScope.numUnCheckedMsg >= 0 && requestScope.numUnCheckedMsg <= 9}">
                  <span class="num_message_unchecked_content_1" style="font-size:16px;">
                    ${requestScope.numUnCheckedMsg}
                  </span>
              </c:if>
              <c:if test="${requestScope.numUnCheckedMsg > 9 && requestScope.numUnCheckedMsg <= 99}">
                    <span class="num_message_unchecked_content_2" style="font-size:16px;">
                        ${requestScope.numUnCheckedMsg}
                    </span>
              </c:if>
              <c:if test="${requestScope.numUnCheckedMsg > 99}">
                   <span class="num_message_unchecked_content_3" style="font-size:16px;">
                         99+
                   </span>
              </c:if>
            </c:if><!--数量显示框结束-->
      </div><!--未审核的留言总数区-->
    </div><!--顶部数据统计区结束-->
  </div><!--顶部区结束-->
  <div class="main">
    <div class="main_left">
      <div class="new_message_title" onclick="location.href='${basePath }/message/list/<%=Message.UNCHECKED_MSG%>/${page.pageSize}/0'">
        <span class="new_message_span" >待审核</span>
        <div class="icon_title_left_unchecked"><img src="${basePath}/img/images/unchecked_msg.jpg" width="20px" height="20px"></div>
      </div><!--新增留言标题区结束-->
      <div class="legal_message_title">
        <span class="legal_message_span" onclick="location.href='${basePath }/message/list/<%=Message.LEGAL_MSG%>/${page.pageSize}/0'">已审核</span>
        <div class="icon_title_left_valid"><img src="${basePath}/img/images/valid_msg.jpg" width="20px" height="20px"></div>
      </div><!--已经审核的标题区结束-->
      <div class="illegal_message_title">
        <span class="illegal_message_span" onclick="location.href='${basePath }/message/list/<%=Message.ILLEGAL_MSG%>/${page.pageSize}/0'">回收站</span>
        <div class="icon_title_left_recycle_bin"><img src="${basePath}/img/images/recycle_bin_2.jpg" width="20px" height="20px"></div>
      </div><!--不合法的留言标题区结束-->
    </div><!--左边分类导航区结束-->
    <form method="post">
    <div class="main_content">
      <div class="data_message">
        <div class="message_item_title">
          <input type="checkbox"  class="selAll" onclick="doSelectAll()"  id="selAll" />
          <span class="visitor_name_title">姓名(单位)</span><span class="separator"></span>
          <span class="content_title">留言内容</span><span class="separator"></span>
          <span class="create_time_title">日期</span><span class="separator"></span>
          <span class="opt_title">操作</span>
        </div>

        <c:choose>
          <c:when test="${not empty page.datas}">
            <c:forEach items="${page.datas}" var="message" varStatus="vs">
               <div class="message_item">
                 <input type="checkbox"  name="selectedRow"  value="${message.id}"  class="selectedRow" />
                 <span class="visitor_name">${message.visitor.visitorName}</span>
                 <span class="content"> ${message.content} </span>
                 <span class="create_time"> ${message.createTime.toLocaleString()}</span>
                 <c:choose>
                   <c:when test="${'0'.equals(message.status)}">
                     <span class="delete_unchecked" onclick="doEditStatus('<%=Message.LEGAL_MSG%>' , this, '${message.id}')">通过</span>
                     <span class="delete_unchecked" style="float: right;" onclick="doEditStatus('<%=Message.ILLEGAL_MSG%>' , this, '${message.id}')">删除</span>
                   </c:when>
                   <c:otherwise>
                     <c:choose>
                       <c:when test="${'1'.equals(message.status)}">
                         <span class="delete" onclick="doEditStatus('<%=Message.ILLEGAL_MSG%>' , this, '${message.id}')">删除</span>
                       </c:when>
                       <c:otherwise>
                         <span class="delete" onclick="doEditStatus('<%=Message.LEGAL_MSG%>' , this, '${message.id}')">还原</span>
                       </c:otherwise>
                     </c:choose>
                   </c:otherwise>
                 </c:choose>
               </div>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <div class="no_message_tip">暂时还没有数据！</div>
          </c:otherwise>
        </c:choose>
      <c:if test="${not empty page.datas}">
        <div class="batch_opt">
          <span class="delete_batch" onclick="doEditSelected('<%=Message.ILLEGAL_MSG%>')" >删除</span>
          <span class="valid_batch" onclick="doEditSelected('<%=Message.LEGAL_MSG%>')" >通过/还原</span>
        </div>
      </c:if>
      </div><!--分页展示的数据区-->
      <c:if test="${not empty page.datas}">
        <div class="pagination_area">
          <span class="page_up"   onclick="location.href='${basePath}/message/list/${page.datas[0].status}/${page.pageSize}/${page.currentPage-1>= 0?page.currentPage-1:0 }'">上一页</span>
          <span class="page_down" onclick=" location.href='${basePath}/message/list/${page.datas[0].status}/${page.pageSize}/${page.currentPage+1 < page.totalPage?page.currentPage+1:page.totalPage-1 }'">下一页</span>
          <span class="back_home" onclick="location.href='${pageContext.servletContext.contextPath }/message/list/${page.datas[0].status}/${page.pageSize}/0'">首页</span>
          <span class="page_num_area">
              <label >第</label>
            <input type="text" class="current_page" maxlength="4"; value="${requestScope.page.currentPage+1}"/>
            <label style="font-weight:bold;">/</label>
            <span class="total_page">${requestScope.page.totalPage}</span><label> 页</label>
          </span>
          <span class="page_size_div">
            <select class="page_size_select" name="page_size_select" id="page_size_select">
              <option value="-1">--每页条数--</option>
              <option value="4" <c:if test="${page.pageSize==4}">selected="selected"</c:if>>4</option>
              <option value="5" <c:if test="${page.pageSize==5}">selected="selected"</c:if>>5</option>
              <option value="6" <c:if test="${page.pageSize==6}">selected="selected"</c:if>>6</option>
              <option value="7" <c:if test="${page.pageSize==7}">selected="selected"</c:if>>7</option>
              <option value="8" <c:if test="${page.pageSize==8}">selected="selected"</c:if>>8</option>
              <option value="9" <c:if test="${page.pageSize==9}">selected="selected"</c:if>>9</option>
              <option value="10" <c:if test="${page.pageSize==10}">selected="selected"</c:if> >10</option>
            </select>
          </span>
          <span class="go_to" onclick=" goTo('${page.totalPage}')">跳转</span>
          <span class="to_final" onclick="location.href='${pageContext.servletContext.contextPath }/message/list/${page.datas[0].status}/${page.pageSize}/${page.totalPage-1}'">末页</span>
        </div><!--分页工具栏区结束-->
      </c:if>
    </div><!--右边数据展示区结束-->
    </form>
  </div><!--主体区结束-->
</div><!--包裹层结束-->
</body>
</html>

