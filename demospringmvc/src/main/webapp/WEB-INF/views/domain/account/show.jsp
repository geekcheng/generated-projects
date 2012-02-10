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
	<title><fmt:message key="account" />: <fmt:message key="crud.functionalities.show" /></title>
</head>
<body>

	<a href="${pageContext.request.contextPath}/domain/account/search" class="ajaxy button button-search"><fmt:message key="account" /></a>
	<span class="ui-state-highlight button button-show"><fmt:message key="crud.show.button" /></span>
	<a href="${pageContext.request.contextPath}/domain/account/update/${account.id}" class="ajaxy button button-edit"><fmt:message key="crud.edit.button" /></a>
	<a href="${pageContext.request.contextPath}/domain/account/delete/${account.id}" class="modal button button-delete"><fmt:message key="crud.delete.button" /></a>

	<table class="show">
		<tbody>
			<tr>
				<th><fmt:message key="account_username" /></th>
				<td><spring:eval expression="account.username"/></td>
			</tr>
			<tr>
				<th><fmt:message key="account_password" /></th>
				<td><spring:eval expression="account.password"/></td>
			</tr>
			<tr>
				<th><fmt:message key="account_email" /></th>
				<td><spring:eval expression="account.email"/></td>
			</tr>
			<tr>
				<th><fmt:message key="account_isEnabled" /></th>
				<td><spring:eval expression="account.isEnabled"/></td>
			</tr>
			<tr>
				<th><fmt:message key="account_civility" /></th>
				<td><spring:eval expression="account.civility"/></td>
			</tr>
			<tr>
				<th><fmt:message key="account_firstName" /></th>
				<td><spring:eval expression="account.firstName"/></td>
			</tr>
			<tr>
				<th><fmt:message key="account_lastName" /></th>
				<td><spring:eval expression="account.lastName"/></td>
			</tr>
			<tr>
				<th><fmt:message key="account_birthDate" /></th>
				<td><spring:eval expression="account.birthDate"/></td>
			</tr>
			<tr>
				<th><fmt:message key="account_addressId" /></th>
				<td>
					<c:if test="${account.homeAddress != null}">
						<spring:eval expression="account.homeAddress" />
						<a href="${pageContext.request.contextPath}/domain/address/show/${account.homeAddress.id}" class="button button-show miniature"><fmt:message key="crud.show.button" /></a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th><fmt:message key="role" /></th>
				<td>
					<ul class="inline">
						<c:forEach items="${account.roles}" var="role" varStatus="status">
							<li>
								<spring:eval expression="role" /><a href="${pageContext.request.contextPath}/domain/role/show/${role.id}" class="button button-show miniature"><fmt:message key="crud.show.button" /></a>
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
		</tbody>
	</table>
	<h6>One to many relationship with : <fmt:message key="book" /></h6>
	<table class="list show-one-to-many">
		<thead>
			<tr>
				<th><fmt:message key="book_title"/></th>
				<th><fmt:message key="book_numberOfPages"/></th>
				<th><a href="${pageContext.request.contextPath}/domain/book/create?account=${account.id}" class="button button-create miniature"><fmt:message key="crud.create.button" /></a></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${account.books}" var="book">
				<tr>
					<td><spring:eval expression="book.title"/></td>
					<td><spring:eval expression="book.numberOfPages"/></td>
					<td>
						<a href="${pageContext.request.contextPath}/domain/book/show/${book.id}" class="button button-show miniature"><fmt:message key="crud.show.button" /></a>
						<a href="${pageContext.request.contextPath}/domain/book/update/${book.id}" class="button button-edit miniature"><fmt:message key="crud.edit.button" /></a>
						<a href="${pageContext.request.contextPath}/domain/book/delete/${book.id}" class="button button-delete miniature modal"><fmt:message key="crud.delete.button" /></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h6>One to many relationship with : <fmt:message key="document" /></h6>
	<table class="list show-one-to-many">
		<thead>
			<tr>
				<th><fmt:message key="document_documentContentType"/></th>
				<th><fmt:message key="document_documentSize"/></th>
				<th><fmt:message key="document_documentFileName"/></th>
				<th><fmt:message key="document_documentBinary"/></th>
				<th><a href="${pageContext.request.contextPath}/domain/document/create?account=${account.id}" class="button button-create miniature"><fmt:message key="crud.create.button" /></a></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${account.documents}" var="document">
				<tr>
					<td><spring:eval expression="document.documentContentType"/></td>
					<td><spring:eval expression="document.documentSize"/></td>
					<td><spring:eval expression="document.documentFileName"/></td>
					<td><a href="${pageContext.request.contextPath}/domain/document/download/documentBinary/${document.id}/${document.documentFileName}" class="button button-download"><fmt:message key="crud.download.button" /></a></td>
					<td>
						<a href="${pageContext.request.contextPath}/domain/document/show/${document.id}" class="button button-show miniature"><fmt:message key="crud.show.button" /></a>
						<a href="${pageContext.request.contextPath}/domain/document/update/${document.id}" class="button button-edit miniature"><fmt:message key="crud.edit.button" /></a>
						<a href="${pageContext.request.contextPath}/domain/document/delete/${document.id}" class="button button-delete miniature modal"><fmt:message key="crud.delete.button" /></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>