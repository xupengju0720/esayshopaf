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
		<label class="inputtext">订单日期</label> 
		 <input class="inputinput" name="date" value="${requestScope.info.date}">
		</span>
		
		
		
		<span class="inputspan"> 
		<label class="inputtext">订单编号</label> 
		 <input class="inputinput" name="code" value="${requestScope.info.code}">
		</span>.
		
		<span class="inputspan"> 
		<label class="inputtext">总金额</label> 
		 <input class="inputinput" name="amount" value="${requestScope.info.amount}">
		</span>
	    <span class="inputspan"> 
		<label class="inputtext">现总金额</label> 
		 <input class="inputinput" name="nowamount" value="${requestScope.info.nowamount}">
		</span>
	
		<span class="inputspan"> 
		<label class="inputtext">地址id</label> 
		 <input class="inputinput" name=address_id value="${requestScope.info.address_id}">
		</span>
		<span class="inputspan"> 
		<label class="inputtext">用户id</label> 
		 <input class="inputinput" name="user_id" value="${requestScope.info.user_id}">
		</span>
		
		<span class="inputspan"> 	
		<label class="inputtext">状态:</label>  
		<select name="status" class="inputselect">
			<c:forEach items="${requestScope.statuss}"  var="r" varStatus="v">
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
		<label class="inputtext">评论</label>  
		<select name="assessstatus" class="inputselect">
			<c:forEach items="${requestScope.assessstatuss}"  var="r" varStatus="v">
			<c:if test="${requestScope.info.assessstatus!=v.index}">
			<option  value="${v.index}">${r}</option>
			</c:if>
			<c:if test="${requestScope.info.assessstatus==v.index}">
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
