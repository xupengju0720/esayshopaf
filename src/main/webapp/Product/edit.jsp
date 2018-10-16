<%@page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../model/list_header.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../libs/css/edit.css">
<script type="text/javascript" src="../fileupload/js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="../fileupload/css/font-awesome.min.css">
<script type="text/javascript" src="../fileupload/js/piclist.js">
</script>
    <script type="text/javascript" charset="utf-8" src="../ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="../ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
function save() {
	var typeid=$(".lastid:last").val();
	var data=$.param({'type_id':typeid})+'&'+$(".f1").serialize();
	$.post($(".f1").attr("action"),data,function(json){
		if(json.status>0){
			parent.fresh();
			var index =parent.layer.getFrameIndex(window.name);
		   parent.layer.close(index);
		}else{
			alert(json.text)
		}
	},"json");
}

function changer(o) {
	$(o).nextAll().remove();
	$.getJSON("gettypelist",{id:(o.value?o.value:0)},function(json){
		if(json.length>0){
			var select=$("<select class='lastid' onchange='changer(this);'><select>");
			for(var i=0;i<json.length;i++){
				select.append($("<option value='"+json[i].id+"'>"+json[i].name+"</option>"));
			}
			$(o).after(select);
			changer(select[0]);
		}
	});
}
$(function(){
	if($(".myselect").attr("dataid")&&$(".myselect").attr("dataid")>0){
		$.getJSON("getalltype",{id:$(".myselect").attr("dataid")},function(json){
		for(var i=0;i<json.length;i++){
			var select=$("<select class='lastid' onchange='changer(this);'><lect>");
			for(var j=0;j<json[i].length;j++){
				if(json[i][j].parent_name=="1"){
					select.append($("<option class='lastid' selected='selected' value='"+json[i][j].id+"'>"+json[i][j].name+"</option>"));
				}else{
					select.append($("<option value='"+json[i][j].id+"'>"+json[i][j].name+"</option>"));
				}
				
			}
			$(".myselect").parent().append(select);
		}	
		});
	}else{
		changer($(".myselect")[0]);
	}
	
});
</script>
<style type="text/css">
ul {
margin:0;
padding:0;
}
li {
float:left;
}
a {
float:left;
}

</style>
</head>
<body>
	<c:if test ="${requestScope.info==null}">
    <form class="f1" action="insert".val method="post">
	</c:if>
	<c:if test ="${requestScope.info!=null}">
	<form class="f1" action="update" method="post">
	<input type="hidden" name="id"  value="${requestScope.info.id}">
	</c:if>
       <div class="inputview" >
       <ul>
       <li>
        <span class="inputspan"> 
		<label class="inputtext">商品全名:</label> 
		 <input class="inputinput" name="fullname" value="${requestScope.info.fullname}">
		</span>
         <span class="inputspan"> 
		<label class="inputtext">优先级</label> 
		 <input class="inputinput" name="priority" value="${requestScope.info.priority}">
		</span>
       <span class="inputspan"> 
		<label class="inputtext">活动:</label> 
	    <input class="inputinput" name="activity" value="${requestScope.info.activity}">
		</span>
		</li>
        <li>
       <span class="inputspan"> 
		<label class="inputtext">促销信息</label> 
		 <input class="inputinput" name="sale" value="${requestScope.info.sale}">
		</span>
         <span class="inputspan">
       <label class="inputtext">原价</label> 
		 <input class="inputinput" name="price" value="${requestScope.info.price}">
		</span>
       <span class="inputspan"> 
		<label class="inputtext">现价</label> 
		 <input class="inputinput" name="nowprice" value="${requestScope.info.nowprice}">
		</span>
		</li>
       <li>
        <span class="inputspan"> 
		<label class="inputtext">提示</label> 
		<input class="inputinput" name="tip" value="${requestScope.info.tip}">
		</span>
       <span class="inputspan"> 
		<label class="inputtext">销售数量</label> 
		 <input class="inputinput" name="salecount" value="${requestScope.info.salecount}">
		</span>
       <span class="inputspan"> 
		<label class="inputtext">储藏数量</label> 
		 <input class="inputinput" name="collectcount" value="${requestScope.info.collectcount}">
		</span>
       </li>
       <li>
       <a>
       <span class="inputspan"> 
		<label class="inputtext">备注</label> 
		 <input class="inputinput" name="comments" value="${requestScope.info.comments}">
		</span>
	   </a>
       <a>
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
		</a>
       <a>
        <span class="inputspan"> 
		<label class="inputtext">商品类型:</label> 
		<b class="myselect"  dataid="${requestScope.info.type_id}"></b>
		</span>
		</a>
		</li>
         <li>
		<div class="picList" name="pics"  rows="2" cols="5" height="400" weight="800">
          <c:forEach items="${requestScope.info.piclist}" var="p"  >
          <item url="${p}">
          </c:forEach>
        </div>
       <div style="margin-left: 20px">
     <label>商品详情</label> 
     <script id="editor" type="text/plain" style="width:900px;height:500px;"></script>
     </div>
     <button class="layui-btn" type="button" onclick="save();" style="margin-left: 450px;">保存</button>
     </li>
      </ul>
     </div>
	</form>
<script type="text/javascript">
var ue = UE.getEditor('editor');
ue.ready(function(){
	ue.setContent("${requestScope.info.info}");
});
</script>
</body>
</html>
