<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript">
	function shangbao() {
		// 获取所有多选按钮
		var names = document.getElementsByName("id");
		//上报合同的个数
		var count = 0;
		for (var i = 0; i < names.length; i++) {
			if (names[i].checked) {
				var ns = names[i].nextElementSibling; //获得s的下一个兄弟节点
				alert(ns.value);
				if (ns.value == '1') {
					names[i].checked = true;
					count++;
				} else if (ns.value == '2') {
					names[i].checked = false;
					count = -1;
				} else {
					names[i].checked = false;
				}
			}
		}
		if (count > 0) {
			formSubmit('${ctx}/PackingList/submit.action', '_self')
		} else if (count < 0) {
			alert("上报过的装箱单无法再上报！");
		} else {
			alert("请选择状态是草稿的装箱单！");
		}
	}

	/* from表单提交事件 */
	function page_nav(frm, num) {
		frm.pageNo.value = num;
		frm.submit();
	}
	/* 点击事件  当重新选取一页显示条数 时   当前页的页码改为1 */
	function pageSizeChange() {
		document.forms[0].pageNo.value = 1;
		formSubmit('${ctx}/PackingList/jPackingListList', '_self');
	}
</script>
</head>

<body>
	<form name="icform" method="post">
		<!-- onclick="formSubmit('${ctx}/contract/delete?id=${o.CONTRACT_ID}','_self');this.blur();">删除</a></li> -->
		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="view"><a href="#"
								onclick="formSubmit('toview.action','_self');this.blur();">查看</a></li>
							<li id="update"><a href="#"
								onclick="formSubmit('toupdate.action','_self');this.blur();">修改</a></li>
							<li id="delete"><a href="#"
								onclick="formSubmit('${ctx}/PackingList/deleteAll','_self');this.blur();">删除</a></li>
							<!-- <li id="new"><a href="#"
								onclick="formSubmit('','_self');this.blur();">上报</a></li> -->
							<li id="new"><a href="#" onclick="shangbao();this.blur();">报运</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">装箱单列表</div>
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
								<td class="tableHeader">卖家</td>
								<td class="tableHeader">买家</td>
								<td class="tableHeader">报运号</td>
								<td class="tableHeader">发票号</td>
								<td class="tableHeader">发票时间</td>
								<td class="tableHeader">状态</td>
								<!-- <td class="tableHeader">唛头</td>
								<td class="tableHeader">备注</td> -->
							</tr>
						</thead>
						<tbody class="tableBody">
							<c:forEach items="${dataList}" var="o" varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id"
										value="${o.PACKING_LIST_ID}" /></td>
									<td>${status.index+1}</td>
									<td>${o.SELLER}</td>
									<td>${o.BUYER}</td>
									<td>${o.EXPORT_NOS}</td>
									<td>${o.INVOICE_NO}</td>
									<td><fmt:formatDate value="${o.INVOICE_DATE}"
											pattern="yyyy-MM-dd" /></td>
									<td><c:if test="${o.STATE==1}">
											<font color="green">已上报</font>
										</c:if> <c:if test="${o.STATE==0}">草稿</c:if></td>
									<%-- <td>${o.MARKS}</td>
									<td>${o.DESCRIPTIONS}</td> --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
	</form>
</body>
</html>


