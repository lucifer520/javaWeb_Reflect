<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="mangues.model.Student" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
     <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery 2.1.1.min.js"></script>
    <script type="text/javascript" src="js/stringFormat.js"></script>
    <script type="text/javascript" src="js/sweet-alert.min.js"></script>
  </head>
  <body>
   <table class="table table-hover table-striped table-bordered">
   <caption class="jumbotron alert-info" >学生信息</caption>
   <thead>
      <tr>
         <th>学号</th>
         <th>姓名</th>
         <th>年龄</th>
         <th>性别</th>
         <th>操作</th>
      </tr>
   </thead>
   <tbody class="tables">
      <c:forEach var="student" items='<%=request.getAttribute("students") %>' varStatus="status">
       <tr>
         <td> <c:out value="${student.sno}"></c:out></td>
         <td> <c:out value="${student.sname}"></c:out></td>
         <td> <c:out value="${student.sage}"></c:out></td>
         <td> <c:out value="${student.ssex}"></c:out></td>
         <td><button type="button" class="btn btn-primary btn-xs xiugai">修改</button>      <button type="button" class="btn btn-primary btn-xs btn-danger" onclick="copyText('${student.sno}','${student.sname}',${student.sage},'${student.ssex}')">删除</button></td>
      </tr>
  </c:forEach>
  </tbody>
</table>
<button type="button" class="btn btn-default zenjia">增加学生信息</button>
<div id="HBox1">
			<form action="add" method="post" >
				<ul class="list" style="list-style-type:none; color: #000;">
					<li>
						<strong>学号 <font color="#ff0000">*</font></strong>
						<div class="fl"><input type="text" name="student.sno" value="" class="ipt nickname" /></div>
					</li>
					<li>
						<strong>姓名<font color="#ff0000">*</font></strong>
						<div class="fl"><input type="text" name="student.sname" value="" class="ipt phone" /></div>
					</li>
					<li>
						<strong>年龄 <font color="#ff0000">*</font></strong>
						<div class="fl"><input type="text" name="student.sage" value="" class="ipt email" /></div>
					</li>
					<li>
						<strong>性 别 <font color="#ff0000">&nbsp;</font></strong>
						<div class="fl">
							<label class="mr10"><input type="radio" name="student.ssex" value="男"/>男</label>
							<label class="mr10"><input type="radio" name="student.ssex" value="女"/>女</label>
						</div>
					</li>
					<li><input type="submit" value="确认提交" class="submitBtn" /></li>
				</ul>
			</form>
		</div><!-- HBox end -->	
  <div id="HBox" style="z-index:10000;">
			<form action="update" method="post" >
				<ul class="list" style="list-style-type:none;  color: #000;">
					<li>
						<strong>学号 <font color="#ff0000">*</font></strong>
						<div class="fl"><input type="text" name="student.sno" value="" class="ipt nickname" /></div>
					</li>
					<li>
						<strong>姓名<font color="#ff0000">*</font></strong>
						<div class="fl"><input type="text" name="student.sname" value="" class="ipt phone" /></div>
					</li>
					<li>
						<strong>年龄 <font color="#ff0000">*</font></strong>
						<div class="fl"><input type="text" name="student.sage" value="" class="ipt email" /></div>
					</li>
					<li>
						<strong>性 别 <font color="#ff0000">&nbsp;</font></strong>
						<div class="fl">
							<label class="mr10"><input type="radio" name="student.ssex" value="男"/>男</label>
							<label class="mr10"><input type="radio" name="student.ssex" value="女"/>女</label>
						</div>
					</li>
					<li><input type="submit" value="确认提交" class="submitBtn" /></li>
				</ul>
			</form>
		</div><!-- HBox end -->	
  </body>
  <script type="text/javascript" src="js/jquery.hDialog.min.js"></script>
      <link href="css/animate.min.css" rel="stylesheet">
      <link href="css/common.css" rel="stylesheet">
      <link href="css/sweet-alert.css" rel="stylesheet">
      <script>
       $(function(){
          $(".zenjia").hDialog({box: '#HBox1'}); //默认调用弹框；
          $(".xiugai").hDialog({box: '#HBox'}); //默认调用弹框；
       });
       var copyText = function(sno,sname,sage,ssex){
          swal({   title: "确定删除?",   text: "你将要删除这行数据!",   type: "warning",   showCancelButton: true,   confirmButtonColor: "#DD6B55",   confirmButtonText: "删了!",   closeOnConfirm: false }, 
          function(){   
           
           var callback =function(data){
               swal("已删!", "这行数据已删.", "success"); 
              window.location.href="query";
           };
           var param={"student.sno":sno,"student.sname":sname,"student.sage":sage,"student.ssex":ssex};
           $.manguesAjax("remove",param,callback);
          });
     }
  jQuery.manguesAjax = function(url,data,callback){
     console.log(data);
	 $.ajax({
		        url: url,
                type: "POST",
                data:data,
                dataType: "json",
                success: function(data) {
                  callback(data);
                },
                error:function(data){
                	
                }
            });
 };  
       
      </script>
</html>
