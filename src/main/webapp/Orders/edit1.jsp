<%@page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../model/list_header.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../libs/css/edit.css">
<script type="text/javascript" src="../libs/js/jquery.min.js"></script>
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
         <form class="f1" action="insert" method="post">
          <div class="inputview">
		  <input type="hidden"  class="inputinput" name="orders_id" value="${requestScope.orders_id}">
		  <input type="hidden"  class="inputinput" name="dest_status" value="${requestScope.dest_status}">
		 <input  type="hidden"  class="inputinput" name="date" value="${requestScope.date}">
		 <input  type="hidden"  class="inputinput" name="amount" value="${0}">
		 <input  type="hidden"  class="inputinput" name="num" value="${0}">
		<span class="inputspan"> 
		<label class="inputtext">运单号:</label> 
		 <input  class="inputinput" name="info" value="${requestScope.info1.info}">
		</span>
		<span class="inputspan"> 
		<label class="inputtext">运单公司:</label> 
		 <input class="inputinput" name="comments" value="${requestScope.info1.comments}">
		</span>
		
		</div>
		
		<div>
		<button class="layui-btn" type="button"  style="position:absolute; margin-left: 150px"  onclick="save();">保存</button>
		</div>
	</form>


</body>
</html>
