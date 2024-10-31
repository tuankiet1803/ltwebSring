<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<a href="${pageContext.request.contextPath}/admin/categories/add">Add Category</a>
<form action="/admin/categories/searchpaginated">
<input type="text" name="name" id="name" placeholder="Nhập từ khóa để tìm" />
<button>Search</button>
</form>
<c:if test="${!categoryPage.hasContent()}">
No Category
</c:if>
<c:if test="${categoryPage.hasContent()}">
<table style="border: 1px solid black; width: 100%;">
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>Category Name</th>
		<th>Status
	</tr>
	<c:forEach items="${listcategory}" var="cate" varStatus="STT">
		<tr class="odd gradeX">
			<td>${STT.index+1 }</td>
			<c:if test="${cate.images.substring(0,5)== 'https' }">
				<c:url value="${cate.images}" var="imgUrl"></c:url>

			</c:if>
			<c:if test="${cate.images.substring(0,5)!='https' }">
				<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>

			</c:if>

			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${cate.name}</td>
			<td>${cate.status}</td>
			<td><a
				href="<c:url value='/admin/categories/edit/${cate.id}'/>">Sửa</a>
				| <a
				href="<c:url value='/admin/categories/delete/${cate.id}'/>">Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table>
</c:if>
<form action="">
Page size:
<select name="size" id="size" onchange="this.form.submit()">
<option ${categoryPage.size == 3 ? 'selected':''} value="3">3</option>
<option ${categoryPage.size == 5 ? 'selected':''} value="5">5</option>
<option ${categoryPage.size == 10 ? 'selected':''} value="10">10</option>
<option ${categoryPage.size == 15 ? 'selected':''} value="15">15</option>
<option ${categoryPage.size == 20 ? 'selected':''} value="20">20</option>
</select>
</form>
<c:if test="${categoryPage.totalPages > 0}">
<ul >
<c:forEach items="${pageNumbers}" var="pageNumber">
<c:if test="${categoryPage.totalPages > 1}">
<li class="${pageNumber == categoryPage.number + 1 ? 'page-item active' : 'page-item'}">
<a href="<c:url value='/admin/categories/searchpaginated?name=${name}&size=${categoryPage.size}&page=${pageNumber}'/>">${pageNumber}</a>
</li>
</c:if>


</c:forEach>



</ul>



</c:if>
