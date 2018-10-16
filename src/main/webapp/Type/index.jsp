<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false" pageEncoding="utf-8"%>
<%@page isELIgnored="false"%>
<%@ include file="../model/list_header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../assets/css/ace.min.css" />
<link rel="stylesheet" href="../assets/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="../Widget/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link href="../Widget/icheck/icheck.css" rel="stylesheet"
	type="text/css" />
<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script src="../assets/js/typeahead-bs2.min.js"></script>
<!-- page specific plugin scripts -->
<script src="../assets/js/jquery.dataTables.min.js"></script>
<script src="../assets/js/jquery.dataTables.bootstrap.js"></script>
<script type="text/javascript" src="../js/H-ui.js"></script>
<script type="text/javascript" src="../js/H-ui.admin.js"></script>
<script src="../assets/layer/layer.js" type="text/javascript"></script>
<script src="../assets/laydate/laydate.js" type="text/javascript"></script>
<script type="text/javascript"
	src="../Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="../js/lrtk.js" type="text/javascript"></script>
<title>管理员列表</title>
<script type="text/javascript">
	/*  var select = ${requestScope.select};
	    var txt = ${requestScope.txt}; 
		$(function() {
			$(".sselect").on("change",function() {$(".sinput").css("display", "none").prop("disabled","disabled");
						$($(".sinput")[$(".sselect").val()]).css("display",
								"inline-block").removeAttr("disabled");
					                              });
			$(".sselect").val(select);
			$(".sinput").css("display", "none").prop("disabled", "disabled");
			$($(".sinput")[select]).css("display", "inline-block").removeAttr(
					"disabled").val(txt);
		});*/
		
		function changer(o) {
			$(o).nextAll().remove();
			$.getJSON("gettypelist",{id:(o.value?o.value:0)},function(json){
				if(json.length>0){
					var select=$("<select onchange='changer(this);'><lect>");
					for(var i=0;i<json.length;i++){
						select.append($("<option value='"+json[i].id+"'>"+json[i].name+"</option>"));
					}
					$(o).after(select);
					changer(select[0]);
				}
			});
		}
		
</script>

</head>
<body>

	<div class=" page-content clearfix">
		<div id="products_style">
			<div class="search_style">
		
			<!-- <ul class="search_content clearfix">
					<form action="index" method="post">
					<li><select class="sselect" name="select">
			        <option value="0">类型名称</option>
			        <option value="1">id</option>
		          	<option value="2">父类id</option>
	                	</select> </li>
						<li><input name="txt" type="text" class="text_add"
							placeholder="输入类型" style="width: 250px" /></li>
						<li style="width: 90px;"><button type="submit"
								class="btn_search">
								<i class="icon-search"></i>查询
							</button></li>
					</form> -->
			</div>
			<div>
			<label>上级:</label><label>${requestScope.path}</label>
			<button class="btn btn-warning Order_form" type="button" onclick="member_add('新增','300','180');">新增</button>
			</div>
			
			<div class="table_menu_list" id="testIframe">
				<table
					class="table table-striped table-bordered table-hover dataTable no-footer"
					id="sample-table" role="grid" aria-describedby="sample-table_info">
					<thead>
						<tr>
							<th>名称</th>
							<th>上级名称</th>
							<th>功能</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.list}" var="r">
							<tr>
								<td width="20%">
	                            ${r.name}                    						
								</td>
								<td width="20%">
									<c:if test="${r.parent_name==null}">
									最上级
								</c:if>
								<c:if test="${r.parent_name!=null}">
								${r.parent_name}
								</c:if>
								</td>
								<td class="td-manage" width="20%"><a title="编辑"
									onclick="member_edit('编辑','${r.id}','300','300')"
									class="btn btn-xs btn-info"> <i
										class="icon-edit bigger-120"></i></a> 
										 <a class="btn btn-warning Order_form" href="index?parentid=${r.id}">下级</a> 
								
										</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
<script>


$(function(){
	$(".selectall").on("click", function() {
		if (event.target.checked)
			$(".chk").prop("checked", "checked")
		else
			$(".chk").prop("checked", "")
	})
});

jQuery(function($) {
		var oTable1 = $('#sample-table').dataTable( {
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,2,3,4,5,8,9]}// 制定列不参与排序
		] } );
				
				
				$('table th input:checkbox').on('click' , function(){
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
						
				});
			
			
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			});


</script>
<script type="text/javascript">
//初始化宽度、高度  
 $(".widget-box").height($(window).height()-215); 
$(".table_menu_list").width($(window).width()-260);
 $(".table_menu_list").height($(window).height()-215);
  //当文档窗口发生改变时 触发  
    $(window).resize(function(){
	$(".widget-box").height($(window).height()-215);
	 $(".table_menu_list").width($(window).width()-260);
	  $(".table_menu_list").height($(window).height()-215);
	})
 
/*******树状图*******/
var setting = {
	view: {
		dblClickExpand: false,
		showLine: false,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				demoIframe.attr("src",treeNode.file + ".html");
				return true;
			}
		}
	}
};

var zNodes =[
	{ id:1, pId:0, name:"商城分类列表", open:true},
	{ id:11, pId:1, name:"蔬菜水果"},
	{ id:111, pId:11, name:"蔬菜"},
	{ id:112, pId:11, name:"苹果"},
	{ id:113, pId:11, name:"大蒜"},
	{ id:114, pId:11, name:"白菜"},
	{ id:115, pId:11, name:"青菜"},
	{ id:12, pId:1, name:"手机数码"},
	{ id:121, pId:12, name:"手机 "},
	{ id:122, pId:12, name:"照相机 "},
	{ id:13, pId:1, name:"电脑配件"},
	{ id:131, pId:13, name:"手机 "},
	{ id:122, pId:13, name:"照相机 "},
	{ id:14, pId:1, name:"服装鞋帽"},
	{ id:141, pId:14, name:"手机 "},
	{ id:42, pId:14, name:"照相机 "},
];
		
var code;
		
function showCode(str) {
	if (!code) code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
}
		
$(document).ready(function(){
	var t = $("#treeDemo");
	t = $.fn.zTree.init(t, setting, zNodes);
	demoIframe = $("#testIframe");
	//demoIframe.bind("load", loadReady);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	//zTree.selectNode(zTree.getNodeByParam("id",'11'));
});	
/*产品-停用*/
function member_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*产品-启用*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
}
/*产品-编辑*/
function member_edit(title,id,w,h){
	layer_show(title,"edit?id="+id,w,h);
}
var parentid =${requestScope.parentid};
/*产品-新增*/
function member_add(title,w,h){
	layer_show(title,"add?parentid="+parentid,w,h);
}
function fresh() {
	location.href = "index?parentid="+parentid;
}
//面包屑返回值
var index = parent.layer.getFrameIndex(window.name);
parent.layer.iframeAuto(index);
$('.Order_form').on('click', function(){
	var cname = $(this).attr("title");
	var chref = $(this).attr("href");
	var cnames = parent.$('.Current_page').html();
	var herf = parent.$("#iframe").attr("src");
    parent.$('#parentIframe').html(cname);
    parent.$('#iframe').attr("src",chref).ready();;
	parent.$('#parentIframe').css("display","inline-block");
	parent.$('.Current_page').attr({"name":herf,"href":"javascript:void(0)"}).css({"color":"#4c8fbd","cursor":"pointer"});
	//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
    parent.layer.close(index);
	
});
</script>
