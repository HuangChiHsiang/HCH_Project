<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>派車管理</title>

<style>
table#table-1 {
	width: 930px;
	background-color: #DADFDF;
	margin-top: 5px;
	margin-bottom: 10px;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>派車管理頁面</h3>
				</td>
		</tr>
	</table>

	<p>後端</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllCarType.jsp'>列出</a>所有車型 <br>
		<br></li>


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/cartype/cartype.do">
				<b>輸入車型編號 (如1001):</b> <input type="text" name="cartypeno">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="cartypeSvc" scope="page"
			class="com.cartype.model.CarTypeService" />

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/cartype/cartype.do">

				<b>選擇車型編號:</b> <select size="1" name="cartypeno">
					<c:forEach var="cartypeVO" items="${cartypeSvc.all}">
						<option value="${cartypeVO.cartypeno}">${cartypeVO.cartypeno}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/cartype/cartype.do">
				<b>選擇車型名稱:</b> <select size="1" name="cartypeno">
					<c:forEach var="cartypeVO" items="${cartypeSvc.all}">
						<option value="${cartypeVO.cartypeno}">${cartypeVO.cartypename}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>車型管理</h3>

	<ul>
		<li><a href='addCarType.jsp'>新增</a>一筆新車型</li>
	</ul>

</body>
</html>