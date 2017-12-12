<%--标签 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id='Loading'
	style="position: absolute; z-index: 1000; top: 0px; left: 0px; width: 100%; height: 100%; background: #FFFFFF; text-align: center; padding-top: 15%;">
	<h1>
		<image src='${base}/static/img/loading.gif' />
	</h1>
</div>
<script>
   function closes(){
	   	$("#Loading").fadeOut("normal",function(){
	   		$(this).remove();
	   	});
   }
   var pc;
   $.parser.onComplete = function(){
	   	if(pc) clearTimeout(pc);
	   	pc = setTimeout(closes, 1000);
   }
</script>








