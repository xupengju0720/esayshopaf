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
	<c:if test ="${requestScope.info==null}">
    <form class="f1" action="insert" method="post">
	<input type="hidden" name="parentid"  value="${requestScope.parentid}">
	</c:if>
	<c:if test ="${requestScope.info!=null}">
	<form class="f1" action="update" method="post">
	<input type="hidden" name="id"  value="${requestScope.info.id}">
	</c:if>
       <div class="inputview">
       
		<span class="inputspan"> 
		<label class="inputtext">名称:</label> 
		 <input class="inputinput" name="name" value="${requestScope.info.name}">
		</span>.
		</div>
		
		<div>
		<button class="layui-btn" type="button"  style="position:absolute; margin-left: 150px"  onclick="save();">保存</button>
		</div>
	</form>


</body>
</html>
