<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cartype.model.*"%>

<%
	CarTypeService cartypeSvc = new CarTypeService();
	List<CarTypeVO> list = cartypeSvc.getAll();
	pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>
所有車型資料
</title>
<style>
 table#table-1 {
 	width: 99%;
 	height: 100px;
	background-color: #DADFDF;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h3 {
    color: blue;
    display: inline;
  }
  
  table {
	width: 100%;
	height:100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    width:30%;
    text-align: center;
  }
  #cartypepic {
	width:200px;
}
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		<h3>所有員工資料</h3>
		<h4><a href="<%= request.getContextPath() %>/back/cartype/main.jsp"><img src="<%= request.getContextPath() %>/back/images/back1.gif" width="80" height="80" border="0">回首頁</a></h4>
	</td></tr>
</table>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>	
	</ul>
</c:if>

<table>
	<tr>
		<th>車型編號</th>
		<th>車型名稱</th>
		<th>描述</th>
		<th>租賃價格</th>
		<th>車型圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="cartypeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
	
		<tr>
			<td>${cartypeVO.cartypeno}</td>
			<td>${cartypeVO.cartypename}</td>
			<td>${cartypeVO.description}</td>
			<td>${cartypeVO.rentalrates}</td>
			<td><img id="cartypepic" src="<%=request.getContextPath()%>/cartype/DBG.do?cartypeno=${cartypeVO.cartypeno}"></td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cartype/cartype.do" style="margin-bottom:0px;">
					<input type="submit" value="修改">
					<input type="hidden" name="cartypeno" value ="${cartypeVO.cartypeno}">
					<input type="hidden" name="action" value="getOne_For_Update"></FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cartype/cartype.do" style="margin-bottom:0px;">
					<input type="submit" value="刪除">
					<input type="hidden" name="cartypeno" value="${cartypeVO.cartypeno}">
					<input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
			
</table>
<%@ include file="page2.file" %>

</body>
</html>