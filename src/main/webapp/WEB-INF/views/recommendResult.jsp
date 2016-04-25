<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Dota-Recommender</title>
</head>
<style>
body {
    background-image:url("http://p1.pichost.me/i/8/1309969.jpg");
    background-repeat:no-repeat;
	background-attachment:fixed;
	background-position:center;
}
h2 {
	text-align:center;
	background-color: rgba(0, 225, 255, 0.5);
}
h4 {
	text-align:center;
	background-color: rgba(255, 225, 0, 0.5);;
}
body {
	margin:0pxauto;  
	text-align:center; 
}
</style>
<body>
<br><br><br><br><br><br><br><br>
<h2>The Hero Recommender System For Dota2</h2>
<h4 style="text-align:center;">Made by Han Zhang and Zhenzhong Mao</h4>
<P style="text-align:center; background-color:rgba(255, 0, 0, 0.5);">  The time on the server is ${serverTime}. </P>
<div class="enter">
<form method="GET" action="/recommender/recommendResult">
<p style="background-color:rgba(214, 152, 238, 0.5);" >
Recommend Results:<br /> <br />
</p>
<p style="background-color:rgba(130, 250, 170, 0.5);">
Cosine Similarity:<br><br>
<c:forEach var="window" items="${cosine}">
    <c:out value="${window}"/> <br><br>
</c:forEach> <br> <br>
Pearson Correlation Similarity<br><br>
<c:forEach var="window" items="${pearson}">
    <c:out value="${window}"/> <br><br>
</c:forEach>
</p>
</form>
</div>
</body>
</html>
