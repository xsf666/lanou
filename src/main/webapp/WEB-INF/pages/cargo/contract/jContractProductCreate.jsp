<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
	<script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>
</head>
<body>
<form method="post" action="">
	<input type="text" name="contractId" value="${contractId}"> 

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('insert.action?factoryId=${f.FACTORY_ID}','_self');">确定</a></li>
<li id="back"><a href="${ctx}/contract/pageList">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>

<div class="textbox" id="centerTextbox">

    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		新增货物信息
    </div> 
    </div>
    </div>
<div>
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">生产厂家：</td>
	            <td class="tableContentAuto">
	            	<select name="FACTORY_ID">
	            		<option value="">--请选择--</option>
	            		<c:forEach items="${factoryList}" var="f">
	            			<option value="${f.FACTORY_ID}" >${f.FACTORY_NAME}</option>
	            		</c:forEach>
	            	</select>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">货号：</td>
	            <td class="tableContent"><input type="text" name="PRODUCT_NO"/></td>
	            <td class="columnTitle_mustbe">数量：</td>
	            <td class="tableContent"><input type="text" name="CNUMBER"/></td>	        
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">包装单位：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="PACKING_UNIT" value="PCS" class="input" checked>只
	            	<input type="radio" name="PACKING_UNIT" value="SETS" class="input">套
	            </td>	            
	            <td class="columnTitle_mustbe">单价：</td>
	            <td class="tableContent"><input type="text" name="PRICE"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">货物描述：</td>
	            <td class="tableContent"><textarea name="PRODUCT_DESC" style="height:120px;"></textarea></td>
	        </tr>
		</table>
	</div>
	
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		货物信息列表
    </div> 
    </div>
    </div>
    
    
	<div class="eXtremeTable" >
	<table id="ec_table" class="tableRegion" width="98%" >
		<thead>
		<tr>
			<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
			<td class="tableHeader">序号</td>
			<td class="tableHeader">厂家</td>
			<td class="tableHeader">货号</td>
			<td class="tableHeader" width="200">货物描述</td>
			<td class="tableHeader">数量</td>
			<td class="tableHeader">包装单位</td>
			<td class="tableHeader">单价</td>
			<td class="tableHeader">总金额</td>
			<td class="tableHeader">操作</td>
		</tr>
		</thead>
		<tbody class="tableBody" >
		
		<c:forEach items="${dataList}" var="o" varStatus="status">
		<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
			<td align="center"><input type="checkbox" name="id" value="${o.CONTRACT_PRODUCT_ID}"/></td>
			<td align="center">${status.index+1}</td>
			<td align="center">${o.FACTORY_NAME}</td>
			<td align="center">${o.PRODUCT_NO}</td>
			<td width="200" align="center">${o.PRODUCT_DESC}</td>
			<td align="center">${o.CNUMBER}</td>
			<td align="center">${o.PACKING_UNIT}</td>
			<td align="center">${o.PRICE}</td>
			<td align="center">${o.AMOUNT}</td>
			<%-- <td>
				<a href="toupdate.action?id=${o.CONTRACT_PRODUCT_ID}">[修改]</a>
				<a href="delete.action?id=${o.CONTRACT_PRODUCT_ID}&contractId=${CONTRACT_ID}">[删除]</a>
				<a href="${ctx}/cargo/extcproduct/tocreate.action?contractProductId=${o.CONTRACT_PRODUCT_ID}">[附件]</a>
			</td> --%>
			<td align="center">
		<span><a class="modifyUser" href="${ctx}/contractProduct/toupdate?contractProductId=${o.CONTRACT_PRODUCT_ID}"><img src="${ctx }/images/xiugai.png" alt="修改" title="修改"/></a></span>
		<span><a class="deleteUser" href="javascript:if(confirm('确实要删除吗?'))location='${ctx}/contractProduct/delete.action?productId=${o.CONTRACT_PRODUCT_ID}&contractId=${CONTRACT_ID}'" ><img src="${ctx }/images/schu.png" alt="删除" title="删除"/></a></span>
		<span><a class="viewUser" href="${ctx}/extCproduct/tocreate?contractProductId=${o.CONTRACT_PRODUCT_ID}" ><img src="${ctx }/images/read.png" alt="附件" title="附件"/></a></span>
		</td>
		</tr>
		</c:forEach>
		
		</tbody>
	</table>
	</div>
	
</div>
 
 
</form>
</body>
</html>

