<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>>${category.isEdit ? 'Edit' : 'Add'}</h2>
<form action="/admin/categories/save" method="post" enctype="multipart/form-data">
	<input type="hidden" value="${category.id}" name="id">
	<input type="hidden" value="${category.isEdit}" name="isEdit">
  <label for="catename">Category Name:</label><br>
  <input type="text" id="catename" name="name" value="${category.name}"><br>
  <label for="catename">Images:</label><br>
  <input type="text" id="images" name="images" value="${category.images}"><br>
  <label for="catename">Status:</label><br>
  <input type="text" id="status" name="status" value="${category.status}"><br>
  <button type="submit" value="Insert">Them</button>
</form>