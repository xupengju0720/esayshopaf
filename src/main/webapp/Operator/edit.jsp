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
	</c:if>
	<c:if test ="${requestScope.info!=null}">
	<form class="f1" action="update" method="post">
	<input type="hidden" name="id"  value="${requestScope.info.id}">
	</c:if>
       <div class="inputview">
       
		<span class="inputspan"> 
		<label class="inputtext">用户名:</label> 
		 <input class="inputinput" name="nike" value="${requestScope.info.nike}">
		</span>
		<span class="inputspan"> 
		 <input type="hidden" class="inputinput" name="password" value="123">
		</span>
		
		<span class="inputspan"> 
		<label class="inputtext">姓名:</label> 
		 <input class="inputinput" name="name" value="${requestScope.info.name}">
		</span>.
		
		<span class="inputspan"> 
		<label class="inputtext">电话号码：</label> 
		 <input class="inputinput" name="tel" value="${requestScope.info.tel}">
		</span>
	
		
		<span class="inputspan"> 	
		<label class="inputtext">状态:</label>  
		<select name="status" class="inputselect">
			<c:forEach items="${requestScope.status}"  var="r" varStatus="v">
			<c:if test="${requestScope.info.status!=v.index}">
			<option  value="${v.index}">${r}</option>
			</c:if>
			<c:if test="${requestScope.info.status==v.index}">
			<option  selected="selected" value="${v.index}">${r}</option>
			</c:if>
			</c:forEach>
		</select>
		</span>
		
		
	<span class="inputspan"> 	
		<label class="inputtext">性别:</label>  
		<select name="sex" class="inputselect">
			<c:forEach items="${requestScope.sex}"  var="r" varStatus="v">
			<c:if test="${requestScope.info.sex!=v.index}">
			<option  value="${v.index}">${r}</option>
			</c:if>
			<c:if test="${requestScope.info.sex==v.index}">
			<option  selected="selected" value="${v.index}">${r}</option>
			</c:if>
			</c:forEach>
		</select>
		</span>
		
		
		<span class="inputspan"> 	
		<label class="inputtext">权利:</label>  
		<select name="power" class="inputselect">
			<c:forEach items="${requestScope.power}"  var="r" varStatus="v">
			<c:if test="${requestScope.info.power!=v.index}">
			<option  value="${v.index}">${r}</option>
			</c:if>
			<c:if test="${requestScope.info.power==v.index}">
			<option  selected="selected" value="${v.index}">${r}</option>
			</c:if>
			</c:forEach>
		</select>
		</span>
		
		<span class="inputspan"> 
		<label class="inputtext">备注:</label> 
		 <input class="inputinput" name="comments" value="${requestScope.info.comments}">
		
		</span>
		
		</div>
		
		<div>
		<button class="layui-btn" type="button"  style="position:absolute; margin-left: 150px"  onclick="save();">保存</button>
		</div>
	</form>


</body>
</html>
