<%--
 | (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 | Source code generated by Celerio, a Jaxio product
 | Want to use Celerio within your company? email us at info@jaxio.com
 | Follow us on twitter: @springfuse
 | Template pack-mvc-3-sd:src/main/webapp/WEB-INF/views/domain/update.e.vm.jsp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"
%><%@ taglib prefix="spring" uri="http://www.springframework.org/tags"
%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"
%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><head>
	<title><fmt:message key="document" />: <fmt:message key="crud.functionalities.update" /></title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/domain/document/search" class="ajaxy button button-search"><fmt:message key="document" /></a>
	<a href="${pageContext.request.contextPath}/domain/document/show/${document.id}" class="ajaxy button button-show"><fmt:message key="crud.show.button" /></a>
	<span class="ui-state-highlight button button-edit"><fmt:message key="crud.edit.button" /></span>
	<a href="${pageContext.request.contextPath}/domain/document/delete/${document.id}" class="modal button button-delete"><fmt:message key="crud.delete.button" /></a>

	<form:form action="${pageContext.request.contextPath}/domain/document/update/${document.id}" modelAttribute="document" enctype="multipart/form-data" method="POST">
		<form:errors cssClass="error"/>
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<table class="edit">
			<tbody>
				<tr>
					<th><fmt:message key="document_accountId" /> <em>*</em></th>
					<td>
						<c:choose>
						<c:when test="${not empty document.account}">
							<spring:eval expression="document.account" />
						</c:when>
						<c:otherwise>
						<input type="hidden" id="accountLink" name="account" value="<spring:eval expression="document.accountId" javaScriptEscape="true"/>" />
						<input type="text" id="accountLabel" value="<spring:eval expression="document.account" javaScriptEscape="true"/>" size="100">
						<script>
						$(function() {
							$("#accountLabel").autocomplete('destroy');
							$("#accountLabel").autocomplete({
								source: "${pageContext.request.contextPath}/domain/rest/account/autocomplete",
								select: function(event, ui) {
									$("#accountLink").val(ui.item.id);
									$("#accountLabel").val(ui.item.label);
								}
							});
						});
						</script>
 						<form:errors path="account" cssClass="error"/>
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><fmt:message key="document_documentBinary" /></th>
					<td>
						<label for="documentBinaryWeb">${document.documentFileName}</label>
						<input type="file" name="documentBinaryWeb"/>
						<form:errors path="documentBinary" cssClass="error"/>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<input type="submit" class="button button-save" value="<fmt:message key="crud.save.button" />" />
					</td>
				</tr>
			</tfoot>
		</table>
	</form:form>
</body>
