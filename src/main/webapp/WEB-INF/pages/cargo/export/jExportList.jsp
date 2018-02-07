<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>
<script type="text/javascript">
	function baoyun() {
		// 获取所有多选按钮
		var names = document.getElementsByName("id");
		//上报合同的个数
		var count = 0;
		for (var i = 0; i < names.length; i++) {
			if (names[i].checked) {
				var ns = names[i].nextElementSibling; //获得s的下一个兄弟节点
				alert(ns.value);
				if (ns.value == '0') {
					names[i].checked = true;
					count++;
				} else {
					names[i].checked = false;
				}
			}
		}
		if (count > 0) {
			formSubmit('${ctx}/PackingList/tocreate.action', '_self')
		} else {
			alert("请选择未装箱的合同！");
		}
	}

	//必须选择一个  
	function selectOne() {
		var names = document.getElementsByName("id");
		var flag = false;//标记判断是否选中一个                 
		for (var i = 0; i < names.length; i++) {
			if (names[i].checked) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			alert("请最少选择一项！");
			return false;
		}
	}
</script>

<body>
	<form name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<!-- <li id="view"><a href="#" onclick="return false;">查看</a></li>
 -->
							<li id="update"><a href="#"
								onclick="formSubmit('toupdate.action','_self');this.blur();">修改</a></li>
							<li id="delete"><a href="#"
								onclick="formSubmit('deleteBatch.action','_self');this.blur();">删除</a></li>
							<li id="new"><a href="#" onclick="baoyun();this.blur();">装箱</a></li>
							<%-- <li id="new"><a href="#"
								onclick="formSubmit('${ctx}/PackingList/tocreate.action','_self');this.blur();">装箱</a></li> --%>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">出口报运列表</div>
				</div>
			</div>

			<div>

				<div class="eXtremeTable">
					<table id="ec_table" class="tableRegion" width="98%">
						<thead>
							<tr>
								<td class="tableHeader"><input type="checkbox" name="selid"
									onclick="checkAll('id',this)"></td>
								<td class="tableHeader">序号</td>
								<td class="tableHeader">报运号</td>
								<td class="tableHeader">货物数/附件数</td>
								<td class="tableHeader">L/C</td>
								<td class="tableHeader">装运港</td>
								<td class="tableHeader">收货人及地址</td>
								<td class="tableHeader">运输方式</td>
								<td class="tableHeader">价格条件</td>
								<td class="tableHeader">录入日期</td>
								<td class="tableHeader">状态</td>
							</tr>
						</thead>
						<tbody class="tableBody">

							<c:forEach items="${dataList}" var="o" varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id"
										value="${o.EXPORT_ID}" /> <input type="hidden"
										value="${o.STATE }" /></td>
									<td>${status.index+1}</td>
									<td>${o.CUSTOMER_CONTRACT}</td>
									<td>${o.EXTNUM}/${o.PNUM}</td>
									<td>${o.LCNO}</td>
									<td>${o.SHIPMENT_PORT}</td>
									<td>${o.CONSIGNEE}</td>
									<td>${o.TRANSPORT_MODE}</td>
									<td>${o.PRICE_CONDITION}</td>
									<td><fmt:formatDate value="${o.INPUT_DATE}"
											pattern="yyyy-MM-dd" /></td>
									<!-- 0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务 -->
									<td><c:if test="${o.STATE==0}">草稿</c:if> <c:if
											test="${o.STATE==1}">
											<font color="green">已上报</font>
										</c:if> <c:if test="${o.STATE==2}">
											<font color="green">装箱</font>
										</c:if> <c:if test="${o.STATE==3}">
											<font color="green">委托</font>
										</c:if> <c:if test="${o.STATE==4}">
											<font color="green">发票</font>
										</c:if> <c:if test="${o.STATE==5}">
											<font color="green">财务</font>
										</c:if></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>

			</div>
	</form>
</body>
</html>


