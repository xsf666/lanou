<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>

<body>
	<form name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="view"><a href="#"
								onclick="formSubmit('toview.action','_self');this.blur();">查看</a></li>
							<li id="delete"><a href="#"
								onclick="formSubmit('deleteBatch.action','_self');this.blur();">删除</a></li>
							<li id="new"><a href="#"
								onclick="formSubmit('${ctx}/cargo/contract/pigeouthole.action','_self');this.blur();">取消</a></li>
							<li id="print"><a href="#"
								onclick="formSubmit('${ctx}/ContractHis/exportExcel','_self');this.blur();">打印</a></li>
							<!-- <li id="print"><a href="#"
								onclick="formSubmit('${ctx}/extCproduct/exportExcel','_self');">打印</a></li> -->
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">历史购销合同列表</div>
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
								<td class="tableHeader">合同号</td>
								<td class="tableHeader">货物数/附件数</td>
								<td class="tableHeader">客户名称</td>
								<td class="tableHeader">交期</td>
								<td class="tableHeader">船期</td>
								<td class="tableHeader">签单日期</td>
								<td class="tableHeader">总金额</td>
							</tr>
						</thead>
						<tbody class="tableBody">

							<c:forEach items="${dataList}" var="o" varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id"
										value="${o.CONTRACT_ID}" /></td>
									<td>${status.index+1}</td>
									<td><a href="toview.action?id=${o.CONTRACT_ID}">${o.CONTRACT_NO}</a></td>
									<td>${o.PNUM}/${o.EXTNUM}</td>
									<td>${o.CUSTOM_NAME}</td>
									<td><fmt:formatDate value="${o.DELIVERY_PERIOD}"
											pattern="yyyy-MM-dd" /></td>
									<td><fmt:formatDate value="${o.SHIP_TIME}"
											pattern="yyyy-MM-dd" /></td>
									<td><fmt:formatDate value="${o.SIGNING_DATE}"
											pattern="yyyy-MM-dd" /></td>
									<td>${o.TOTAL_AMOUNT}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>

			</div>
	</form>
</body>
</html>

