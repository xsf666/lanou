<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	//创建对象
	function createLog(yearTime, LoginNumber) {
		var obj = new Object();
		obj.yearTime = yearTime;
		obj.LoginNumber = LoginNumber;
		return obj;
	}
	
	$(function() {
				var date = new Date();
				var year = date.getFullYear();
				yearzhe(year);
			})
		function yearzhe(year){
		var dom = document.getElementById("main");
		var myChart = echarts.init(dom);
		$.get("${ctx}/chat/LoginLogRecord", {'year' : year}, function(data) {
			var main = $(dom);
			if (data.length==0) {
				var main = $(dom);
				main.hidden();
			}else{
				
				main.show();
				var xValue = new Array();
				var yValue = new Array();
				
				
				for (var i = 0; i < data.length; i++) {
					xValue[i] = data[i].yearTime;
					yValue[i] = data[i].loginNumber;
				}
				option = {
					title : {
						text : '登录记录',
						subtext : '纯属虚构'
					},
					tooltip : {
						trigger : 'axis',
						axisPointer : {
							type : 'cross'
						}
					},
					toolbox : {
						show : true,
						feature : {
							saveAsImage : {}
						}
					},
					xAxis : {
						type : 'category',
						boundaryGap : false,
						data : xValue
					},
					yAxis : {
						type : 'value',
						axisLabel : {
							formatter : '{value} 个'
						},
						axisPointer : {
							snap : true
						}
					},
					visualMap : {
						show : false,
						dimension : 0,
						pieces : [ {
							lte : 6,
							color : 'green'
						}, {
							gt : 6,
							lte : 8,
							color : 'green'
						}, {
							gt : 8,
							lte : 14,
							color : 'green'
						}, {
							gt : 14,
							lte : 17,
							color : 'green'
						}, {
							gt : 17,
							color : 'green'
						} ]
					},
					series : [ {
						name : '访问次数',
						type : 'line',
						smooth : true,
						data : yValue
					} 
				  ]
				};
				myChart.setOption(option, true);
			}
		    }	
		);	
	}
</script>
</head>
<body>
	<div class="textbox" id="centerTextbox">
		<div class="textbox-header">
			<div class="textbox-inner-header">
				<div class="textbox-title">登录日志记录</div>
			</div>
		</div>
		<div>
			<div class="extremeTable">
				<!-- 折线图-->
				<div id="navMenubar">
					<ul>
						<c:forEach items="${list}" var="o">
							<li id="view"><a href="#" onclick="yearzhe(${o})">${o}</a></li>
						</c:forEach>

					</ul>
				</div>
			</div>
			<div id="main" style="width: 1000px; height: 400px; margin-top: 40px"></div>
		</div>
</body>
</html>