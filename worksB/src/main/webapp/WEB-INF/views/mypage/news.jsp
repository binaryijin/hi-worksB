<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:forEach items="${news }" var="news">
			<p></p>
		</c:forEach>
	</div>
</body>
<script>
	var news = ${news}
	console.log(news);
	
</script>
</html>