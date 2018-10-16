<%@page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="model/list_header.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="libs/css/edit.css">
<script type="text/javascript" src="libs/js/jquery.min.js"></script>
<script type="text/javascript">
function save() {
		$.post($(".f1").attr("action"),$(".f1").serialize(),function(json){
			if(json.status>0){
				parent.fresh();
				var index =parent.layer.getFrameIndex(window.name);
			    parent.layer.close(index);
			}else{
				alert(json.text)
			}
		},"json");
	}

</script>

</head>
<body>
	<form class="f1" action="Operator/update1" method="post">
	 <div class="inputview">
	  <input type="hidden" class="inputinput" name="id" value="${sessionScope.sdetail.id}">
		<span class="inputspan"> 
		<label class="inputtext">旧密码:</label> 
		 <input class="inputinput" name="password1" >
		 </span>
		 	<span class="inputspan"> 
		<label class="inputtext ">新密码:</label> 
		 <input class="inputinput" name="password2" type="password" >
		  </span>
		 	<span class="inputspan"> 
		<label class="inputtext">新密码:</label> 
		 <input class="inputinput" name="password3"  type="password" >
		</span>
		 <button class="layui-btn" type="button"  style="margin-left: 100px"  onclick="save();">保存</button>
		</div>
		
		
	</form>


</body>
</html>
