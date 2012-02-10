<%--
 | (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 | Source code generated by Celerio, a Jaxio product
 | Want to use Celerio within your company? email us at info@jaxio.com
 | Follow us on twitter: @springfuse
 | Template pack-mvc-3-sd:src/main/webapp/WEB-INF/views/domain/show.e.vm.jsp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"
%><%@ taglib prefix="spring" uri="http://www.springframework.org/tags"
%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"
%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><head>
	<title><fmt:message key="moreTypesDemo" />: <fmt:message key="crud.functionalities.show" /></title>
</head>
<body>

	<a href="${pageContext.request.contextPath}/domain/moretypesdemo/search" class="ajaxy button button-search"><fmt:message key="moreTypesDemo" /></a>
	<span class="ui-state-highlight button button-show"><fmt:message key="crud.show.button" /></span>
	<a href="${pageContext.request.contextPath}/domain/moretypesdemo/update/${moreTypesDemo.id}" class="ajaxy button button-edit"><fmt:message key="crud.edit.button" /></a>
	<a href="${pageContext.request.contextPath}/domain/moretypesdemo/delete/${moreTypesDemo.id}" class="modal button button-delete"><fmt:message key="crud.delete.button" /></a>

	<table class="show">
		<tbody>
			<tr>
				<th><fmt:message key="moreTypesDemo_numberInt" /></th>
				<td><spring:eval expression="moreTypesDemo.numberInt"/></td>
			</tr>
			<tr>
				<th><fmt:message key="moreTypesDemo_numberLong" /></th>
				<td><spring:eval expression="moreTypesDemo.numberLong"/></td>
			</tr>
			<tr>
				<th><fmt:message key="moreTypesDemo_numberDouble" /></th>
				<td><spring:eval expression="moreTypesDemo.numberDouble"/></td>
			</tr>
			<tr>
				<th><fmt:message key="moreTypesDemo_numberFloat" /></th>
				<td><spring:eval expression="moreTypesDemo.numberFloat"/></td>
			</tr>
			<tr>
				<th><fmt:message key="moreTypesDemo_numberBigInteger" /></th>
				<td><spring:eval expression="moreTypesDemo.numberBigInteger"/></td>
			</tr>
			<tr>
				<th><fmt:message key="moreTypesDemo_numberBigDecimal" /></th>
				<td><spring:eval expression="moreTypesDemo.numberBigDecimal"/></td>
			</tr>
			<tr>
				<th><fmt:message key="moreTypesDemo_dateJavaTemporalDate" /></th>
				<td><spring:eval expression="moreTypesDemo.dateJavaTemporalDate"/></td>
			</tr>
			<tr>
				<th><fmt:message key="moreTypesDemo_dateJavaTemporalTimestamp" /></th>
				<td><spring:eval expression="moreTypesDemo.dateJavaTemporalTimestamp"/></td>
			</tr>
			<tr>
				<th><fmt:message key="moreTypesDemo_dateJoda" /></th>
				<td><spring:eval expression="moreTypesDemo.dateJoda"/></td>
			</tr>
			<tr>
				<th><fmt:message key="moreTypesDemo_dateTimeJoda" /></th>
				<td><spring:eval expression="moreTypesDemo.dateTimeJoda"/></td>
			</tr>
		</tbody>
	</table>
</body>