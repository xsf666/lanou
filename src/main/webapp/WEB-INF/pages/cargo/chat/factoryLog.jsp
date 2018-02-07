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
	$(function() {
		$.get("${ctx}/chat/factoryRecord", function(data) {
			//标题名称
			var titles = new Array();
			//销售数据
			var numbers = new Array();

			for (var i = 0; i < data.length; i++) {
				titles[i] = data[i].factory_name;
				numbers[i] = data[i].countnum;
			}
			//获取柱状图放的位置
			var main = echarts.init(document.getElementById('main'));
			option = {
				tooltip : {
					trigger : 'axis',
					axisPointer : { // 坐标轴指示器，坐标轴触发有效
						type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				xAxis : [ {
					type : 'category',
					data : titles
				} ],
				yAxis : [ {
					type : 'value'
				} ],
				series : [ {
					data : numbers,
					type : 'bar'
				} ]
			};
			// 使用刚指定的配置项和数据显示图表。
			main.setOption(option);
		});

	})
</script>

</head>
<body>
	<div class="textbox" id="centerTextbox">
		<div class="textbox-header">
			<div class="textbox-inner-header">
				<div class="textbox-title">购销合同列表</div>
			</div>
		</div>
		<div>
			<div class="extremeTable">
				<!-- 柱状图 -->
				<div id="main" style="width: 1000px; height: 400px;"></div>
			</div>
</body>
</html>