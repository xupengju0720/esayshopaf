/*
 * oukichikou
 * 2018-08-09
 * 
 * jquery 3.3.1
 * font-awesome
 * 
 * 
<div class="picList" name="fileurl"  width="300"  height="200" rows="2" cols="5" >
</div> 

<item url="/day6-upload/uploadFile/1533803182553.png" >			//jstl替换
<item url="/day6-upload/uploadFile/1533803172528.png" >
<item url="/day6-upload/uploadFile/1533803176878.png" >

 * */

$(function(){
	var addicon="fa-plus-square";
	var delicon="fa-minus-square";
	
	function addpic(url){
		var c=$(".picList");
		var w=c.attr("width")/c.attr("cols");
		var h=c.attr("height")/c.attr("rows");
		var item=$("<div class='picItem'></div>").css({width:w+"px",height:h+"px",position:"relative",display: "inline"});	
		var img=$("<img  src='"+url+"'>").css({width:w+"px",height:h+"px"});
		item.append(img);
		var addbt=$("<i class='fa "+delicon+"'></i>").css({"right":"0px","font-size":"30px",position:"absolute"});
		item.append(addbt);
		var hinput=$("<input type='hidden'   name='"+$(".picList").attr("name")+"' value='"+url+"' >");
		item.append(hinput);
		c.append(item);
	}
	
	function additem(){
		var c=$(".picList");
		var w=c.attr("width")/c.attr("cols");
		var h=c.attr("height")/c.attr("rows");
		var item=$("<div class='picItem'></div>").css({width:w+"px",height:h+"px",position:"relative",display: "inline"});	
		var img=$("<img  src='../fileupload/img/back.png'>").css({width:w+"px",height:h+"px"});
		item.append(img);
		var addbt=$("<i class='fa "+addicon+"'></i>").css({"right":"0px","font-size":"30px",position:"absolute"});
		item.append(addbt);
		var hinput=$("<input type='hidden' disabled='disabled'  name='"+$(".picList").attr("name")+"' >");
		item.append(hinput);
		c.append(item);
	}
	var current;
	$(document).on("change","[type=file]",function(){
		
		var formData = new FormData($('.fileform')[0]);
        $.ajax({
            type: 'post',
            url: "../upload",
            data: formData,
            cache: false,
            processData: false,
            contentType: false,
            dataType:"json",
            success:function (json) {
            	if(json.status>0){
            		current.removeClass(addicon);
            		current.addClass(delicon);
            		current.prev().attr("src",json.url);
            		current.next().removeAttr("disabled");
            		current.next().val(json.url);
            		additem();
            		}
            	else{
            		alert("失败");
            	}
            }
        });

	});
	$(document).on("click","."+addicon,function(){
		current=$(event.target);
		$("[type=file]")[0].click();
	});
	$(document).on("click","."+delicon,function(){
		$(event.target).parent().remove();	
		//发送到后台
	});
	
	
	
	var c=$(".picList");
	c.css({width:c.attr("width")+"px",height:c.attr("height")+"px"});
	var form=$("<form class='fileform' action='upload' method='post' enctype='multipart/form-data' style='display:none;' ></form>");
	var fileinput=$("<input type='file' name='file'>");
	form.append(fileinput);
	c.append(form);
	$("item").each(function(){
		addpic($(this).attr("url"));
	});
	$("item").remove();
	additem();
	
})