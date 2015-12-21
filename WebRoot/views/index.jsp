<%--
  Created by IntelliJ IDEA.
  User: qqzeng
  Date: 2015/12/15
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
  application.setAttribute("basePath",basePath);
%>
<html>
<head>
  <title>首页</title>

  <meta http-equiv="keywords" content="抗战纪念活动,抗战胜利,抗战胜利70周年">
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <script type="text/javascript" src="${basePath}/js/jquery-2.1.1.min.js"></script>
  <link rel="stylesheet" type="text/css" href="${basePath}/css/style-page.css" >
  <link rel="stylesheet" type="text/css" href="${basePath}/css/style-text.css" >
  <script type="text/javascript">
    //异步交互
    function sendFlower(url, sendData){
      $.post(url, sendData, function(backData, textStatus, ajax){
        var jsonJAVA = ajax.responseText;
        var jsonJS = eval("("+jsonJAVA+")");
        $("#flower_num").text(jsonJS.result);
      });
    }

    //点击送花处理函数
    function doSendFlower(){
      // $("#flower_num").text(parseInt($("#flower_num").text()) + 1);
      var url = "${pageContext.request.contextPath}/flower/add.action";
      var sendData={
        id          : null,
        deleteFlag  : 0,
      };
      sendFlower(url, sendData);
    }

    //网友留言处理
    function doLeaveWord(){
      var $call = $("#call").val();
      var $words = $("#words").val();
      if($call == null || $.trim($call) == ""){
        alert("单位(个人)信息不能为空！");
        return false;
      }
      if($words == null || $.trim($words) == ""){
        alert("留言不能为空！");
        return false;
      }

      //异步插入一条留言
      var url = "${pageContext.request.contextPath}/message/addMsg.action";
      var sendData={
        call : $call,
        words : $words
      };
      $.post(url, sendData, function(backData, textStatus, ajax){
        var jsonJAVA = ajax.responseText;
        var jsonJS = eval("("+jsonJAVA+")");
        alert(jsonJS.result);
        $("#call").val("");
        $("#words").val("");
        //刷新留言
        loadMsg();
      });
    }

    //页面加载初始化显示数据
    $(function(){
      $(".words_show").hide();
      //获取献花总数
      countFlower();
      //异步加载留言
      loadMsg();
      //设置分页栏样式
      setStyleForPagination();
    });

    //设置分页栏样式
    function setStyleForPagination(){
      $(".words_show").hover(
              function () {
                $(".pagination").slideDown();
                $(".pagination").removeClass("pagination_out");
              },
              function () {
                $(".pagination").slideUp();
                $(".pagination").addClass("pagination_out");
              }
      );
    }

    //计算花的总数
    function countFlower(){
      var url = "${pageContext.request.contextPath}/flower/count.action";
      var sendData= null;
      sendFlower(url, sendData);
    }

    //加载特定页、特定条数的留言数据
    function loadMsg(){
      var url = "${basePath}/message/listUI/"+$(":hidden[name=pageSize]").val()+"/"+parseInt($(":hidden[name=pageNo]").val());
      var sendData = null;
      $.post(url, sendData, function(backData, textStatus, ajax){
          if(eval("("+ajax.responseText+")").status){
            var $res = eval("("+ajax.responseText+")").result;
            //设置相关页数
            $(":hidden[name=pageNo]").val($res['currentPage']);
            $(":hidden[name=pageSize]").val($res['pageSize']);
            $(":hidden[name=totalPage]").val($res['totalPage']);
            $("#page_num_display").text(($res['currentPage']+1)+"/"+($res['totalPage']));
            //替换留言数据
            $(".words_nameSP").each(function(num){
              if($res['datas'][num] != null) {
                $(this).parent().show();
                $(this).text($res['datas'][num]['visitor']['visitorName']);
              }else{
                $(this).parent().hide();
              }
            });
            $(".leave_word_sp").each(function(num){
              if($res['datas'][num] != null) {
                $(this).parent().show();
                $(this).text($res['datas'][num]['content']);
              }else{
                $(this).parent().hide();
              }
            });
            $(".words_show").show();
          }
      });
    }

    //翻页
    //1. 首页
    function firstPage(){
      $(":hidden[name=pageNo]").val("0");
      loadMsg();
    }
    //2. 末页
    function lastPage(){
      $(":hidden[name=pageNo]").val(parseInt($(":hidden[name=totalPage]").val()) - 1);
      loadMsg();
    }
    //3. 上一页
    function pageUp(){
      var pageNo = $(":hidden[name=pageNo]").val();
      $(":hidden[name=pageNo]").val(pageNo>0?pageNo-1:0);
      loadMsg();
    }
    //4. 下一页
    function pageDown(){
      var pageNo = $(":hidden[name=pageNo]").val();
      var totalPage = $(":hidden[name=totalPage]").val();
      $(":hidden[name=pageNo]").val(pageNo<totalPage-1?(parseInt(pageNo)+1):totalPage-1);
      loadMsg();
    }
  </script>
</head>

<body background="${basePath}/img/images/bg.jpg" >
<div  >

  <!-- top -->
  <div  class="container-top">
    <img  src="${basePath}/img/images/TOP_ff.jpg">
  </div>

  <!-- 寄语 -->
  <div class="container-senWord">
    <img src="${basePath}/img/images/dy.jpg">
  </div>

  <!-- 我为烈士鲜花 -->

  <div class="container-senFlower" >
    <div class="words_show">
      <div class="word_content">
        <div class="words_show1">
          <p>姓名:<span class="words_nameSP"></span> 留言内容:<span class="leave_word_sp"></span></p>
        </div>
        <div class="words_show1">
          <p>姓名:<span class="words_nameSP"></span>  留言内容:<span class="leave_word_sp"></span></p>
        </div>
        <div class="words_show1">
          <p>姓名:<span class="words_nameSP"></span>  留言内容:<span class="leave_word_sp"></span></p>
        </div>
        <div class="words_show1">
          <p>姓名:<span class="words_nameSP"></span>  留言内容:<span class="leave_word_sp"></span></p>
        </div>
      </div>
      <div class="pagination">
        <span class="currentpage_totalpage">当前页/总页数&nbsp;&nbsp;<span id="page_num_display"></span></span>
        <span class="first_page" onclick="firstPage()">【首页】</span>
        <span class="page_up" onclick="pageUp()">【上一页】</span>
        <span class="page_down" onclick="pageDown()">【下一页】</span>
        <span class="last_page" onclick="lastPage()">【末页】</span>
      </div>
      <input type="hidden" value="0" name="pageNo" />
      <input type="hidden" value="4" name="pageSize" />
      <input type="hidden" name="totalPage" />

    </div><!--留言显示-->


    <div class="sendFlower" onclick="doSendFlower()"></div>
    <p id="worship-num">共有<span id="flower_num" >0</span>位网友参与祭拜</p>
    <div class="send-massage">

      <p>
      <form method="post">
        姓名（单位）: <input type="text" id="call">
        留言: <input type="text"  id="words" maxlength="20">
        <input type="button" value="提交" id="submit" onclick="doLeaveWord()"  />
      </form>
      </p>
    </div>
  </div>


  <!-- 抗战大事记 -->
  <div class="container">
    <div class="event-pit">
      <img src="${basePath}/img/images/kzdsj.jpg">
    </div>

    <div class="con-time1">
      <a  class="con-date">1937年&nbsp; 7月7日<p class="con-dot">●</p></a>
      <a class="con-date">1940年&nbsp; 8月20日<p class="con-dot">●</p></a>
      <a class="con-date">1945年&nbsp;  8月9日<p class="con-dot">●</p> </a>
      <a class="con-date">1945年&nbsp; 8月10日<p class="con-dot">●</p></a>
        <a class="con-date">1945年&nbsp; 8月15日<p class="con-dot">●</p></a>
        <a class="con-date">1945年&nbsp;  9月2日<p class="con-dot">●</p></a>
        <a class="con-date">1945年&nbsp;  9月3日<p class="con-dot">●</p></a>
    </div>
    <div class="con-point">
      <p >···············································································</p>
    </div>
    <div class="container-bottom">
      <div class="war-pic">
        <img src="${basePath}/img/matter/war-7,7.png">
      </div>
      <div class="war-re" >
        <h2 > 中国人民抗日战争全面爆发</h2>
        <p class="war-substance">
          1937年7月7日夜，日军在北平西南卢沟桥附近演习时，借口一名士兵“失踪”，要求进入宛平县城搜查，遭到中国守军第29军严辞拒绝。日军遂向中国守军开枪射击，又炮轰宛平城。
          第29军奋起抗战。这就是震惊中外的七七事变，又称卢沟桥事变。七七事变是日本帝国主义全面侵华战争的开始，也是中华民族进行全面抗战的起点。
          <a href="http://gz.wenming.cn/zt/20150831_jyl/xgwz/201508/t20150831_2829387.shtml" class="article-detail">..&lt;&lt;详细</a>
        </p>
      </div>
    </div>
  </div>
  <!-- 抗战英烈 -->
  <div class="container">
    <div class="container-head">
      <p class="title-first">抗战
     	<span class="title-line">
     		<span class="title-second">英烈</span>
     		<input type="text"  class="full-space" >
            <a href="http://bbs.tiexue.net/post2_4037105_1.html"class="title-index">更多&gt;&gt;</a>
     	</span>
      </p>
    </div>
    <div class="container-bottom">
      <table  cellspacing="25">
        <tr>
          <td>
            <div class="heroes_pic">
              <img src="${basePath}/img/matter/heroes-HZZ.png">
            </div>
            <div class="heroes_re">
              <h4>何知重</h4>
              <p class="heroes_intro">
                何知重是桐梓县人,1935年任一○三师师长,曾率部参加淞沪抗战,完成任务后
                <a href="http://www.haosou.com/link?url=http%3A%2F%2Fbaike.haosou.com%2Fdoc%2F2679042-2828920.html&q=何知重&ts=1449986194&t=74df129903db3954b1cc7b5da99a6a5&src=haosou" class="article-detail">
                  ...&lt;&lt;详细
                </a>
              </p>
            </div>
          </td>
          <td>
            <div class="heroes_pic">
              <img src="${basePath}/img/matter/heroes-HSC.png">
            </div>
            <div class="heroes_re">
              <h4>霍世才</h4>
              <p class="heroes_intro">
                霍世才团长悲山河之破碎，痛百姓之苦难，横戈立马，驰骋疆场，以自己的热血和生命
                <a href="http://www.haosou.com/link?url=http%3A%2F%2Fbaike.haosou.com%2Fdoc%2F2679042-2828920.html&q=何知重&ts=1449986194&t=74df129903db3954b1cc7b5da99a6a5&src=haosou"class="article-detail">
                  ...&lt;&lt;详细
                </a>
              </p>
            </div>
          </td>
          <td>
            <div class="heroes_pic">
              <img src="${basePath}/img/matter/heroes-LYJ.png">
            </div>
            <div class="heroes_re">
              <h4>蓝运臧</h4>
              <p class="heroes_intro">
                出生于金沙县的蓝运臧,是贵州地下党的早期女共产党员,丈夫寇述彭、妹妹蓝运铮与
                <a href="http://www.gzdyjyw.com/dyw/gmrw/839.htm" class="article-detail">
                  ...&lt;&lt;详细
                </a>
              </p>
            </div>
          </td>
        </tr>
        <tr>
          <td>
            <div class="heroes_pic">
              <img src="${basePath}/img/matter/heroes-LSR.png">
            </div>
            <div class="heroes_re">
              <h4>柳树人</h4>
              <p class="heroes_intro">
                为抗击日寇,前方战场鏖战激,敌后的抗日救国活动也在积极开展。抗战爆发后,普
                <a href="http://baike.haosou.com/doc/2090825-2211782.html" class="article-detail">
                  ...&lt;详细
                </a>
              </p>
            </div>
          </td>
          <td>
            <div class="heroes_pic">
              <img src="${basePath}/img/matter/heroes-LHL.png">
            </div>
            <div class="heroes_re">
              <h4>罗会廉</h4>
              <p class="heroes_intro">
                为抗击日寇,前方战场鏖战激,敌后的抗日救国活动也在积极开展。抗战爆发后,普
                <a href="http://baike.haosou.com/doc/3910597.html" class="article-detail">
                  ...&lt;&lt;详细
                </a>
              </p>
            </div>
          </td>
          <td>
            <div class="heroes_pic">
              <img src="${basePath}/img/matter/heroes-WRF.png">
            </div>
            <div class="heroes_re">
              <h4>王若飞</h4>
              <p class="heroes_intro">
                王若飞(一八九六年至一九四六年)，字继任，安顺人。早年就读于贵阳达德学校，
                <a href="http://baike.haosou.com/doc/130803-138154.html" class="article-detail">
                  ...&lt;&lt;详细
                </a>
              </p>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </div>

  <!-- 日军罪行 -->
  <div class="container">
    <div class="container-head">
      <p class="title-first">日军
     	<span class="title-line">
     		<span class="title-second">罪行</span>
     		<input type="text"  class="full-space" >
     		<a href="http://baike.haosou.com/doc/9884799-10231921.html" class="title-index">更多&gt;&gt; </a>
     	</span>
      </p>
    </div>
    <!-- 日军罪行描述-->
    <div class="container-bottom">
      <div class="crime-pic">
        <img src="${basePath}/img/matter/crime1.png">
      </div>
      <div class="crime-re">
        <p class="article-title"> 惨无人道的南京大屠杀：血肉狼藉 尸体纵横</p>
        <p class="article-substance">
          1937年12月13日，日本侵略军侵占南京后，在日本华中方面军司令官松井石根和第6师师长谷寿夫指挥下，
          在全城进行了40多天的血腥屠杀，使用集体枪杀、活埋、刀劈、火烧等惨绝人寰的方法，杀害中国平民和被俘军人达30余万人。
          <a href="http://gz.wenming.cn/zt/20150831_jyl/xgwz/201508/t20150831_2829548.shtml" class="article-detail">..&lt;&lt;详细 </a>
        </p>
        <p class="article-title" >慰安妇的血泪史：饱受日军奴役、摧残</p>
        <p class="article-substance">慰安妇，是日本军队在第二次世界大战期间征招的随军妓女和为日军提供性服务的女性，
          中韩历史学者认为主要是通过诱骗和强迫。大部分慰安妇来自中国大陆
          <a href="http://gz.wenming.cn/zt/20150831_jyl/xgwz/201508/t20150831_2829578.shtml" class="article-detail">..&lt;&lt;详细 </a>
        </p>
        <ul class="small-tit">
          <li><a href="http://gz.wenming.cn/zt/20150831_jyl/xgwz/201508/t20150831_2829591.shtml">日中将级战犯自供侵华行为：强奸妇女50余人</a></li>
          <li><a href="http://gz.wenming.cn/zt/20150831_jyl/xgwz/201508/t20150831_2829636.shtml">日战犯相乐圭二：杀害中国人831名强奸34妇女</a></li>
          <li><a href="http://gz.wenming.cn/zt/20150831_jyl/xgwz/201508/t20150831_2829695.shtml">日寇暴行又添新证</a></li>
          <li><a href="http://gz.wenming.cn/zt/20150831_jyl/xgwz/201508/t20150831_2829713.shtml">日战犯船木健次郎供述：活烧42名妇孺 孕妇也不放过</a></li>
        </ul>
      </div>
    </div>
  </div>

  <!-- 爱国主义教育基地 -->
  <div class="container">
    <div class="container-head-love">
      <p class="title-first">爱国主义
    	 <span class="title-line">
    		 <span class="title-second">教育基地</span>
     		 <input type="text"  class="full-space-lo" >
             <a href="http://baike.haosou.com/doc/6763326-6978040.html" class="title-index">更多&gt;&gt;</a>
      	</span>
      </p>
    </div>
    <div class="container-bottom">
      <table   cellspacing="20" >
        <tr>
          <td class="love-edu"><a href="http://baike.haosou.com/doc/7867104-8141199.html"> <img src="${basePath}/img/matter/love1.png"></a> &nbsp; 猴场会议会址</td>
          <td class="love-edu"><a href="http://baike.haosou.com/doc/6088615-6301720.html"><img src="${basePath}/img/matter/love2.png"></a> &nbsp; 周逸群故居</td>
          <td class="love-edu"><a href="http://baike.haosou.com/doc/6673904-6887752.html"><img src="${basePath}/img/matter/love3.png"></a> &nbsp; 四渡赤水纪念馆</td>
          <td class="love-edu"><a href="http://baike.haosou.com/doc/1519532-1606488.html"></a><img src="${basePath}/img/matter/love4.png"></a> &nbsp; 天安门广场</td>
        </tr>
        <tr>
          <td class="love-edu"><a href="http://baike.haosou.com/doc/5584907.html"><img src="${basePath}/img/matter/love5.png"></a> &nbsp; 中国历史博物馆</td>
          <td class="love-edu"><a href="http://baike.haosou.com/doc/6227863-6441186.html"><img src="${basePath}/img/matter/love6.png"></a> &nbsp; 中国人民革命军事博物馆 </td>
          <td class="love-edu"><a href="http://baike.haosou.com/doc/5366680-5602406.html"><img src="${basePath}/img/matter/love7.png"></a> &nbsp; 中国人民抗日战争纪念馆 </td>
          <td class="love-edu"><a href="http://baike.haosou.com/doc/3060661-3226237.html?from=125164&sid=3226237&redirect=search"><img src="${basePath}/img/matter/love8.png"></a> &nbsp; 中国国家博物馆 </td>
        </tr>
      </table>
    </div>
  </div>

  <div >
    <img src="${basePath}/img/images/dz.jpg">
  </div>


</div>
</body>
</html>
