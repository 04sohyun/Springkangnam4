<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function chk(){
	if(!frm.id.value){
		alert("id입력하세요!!!");
		frm.id.foucs();
		return false;
	}
	return true;
}
</script>
<body>
	<form action="student" method="post" name="frm" onsubmit="return chk()">
	<!-- <form action="student" method="get"> -->
		student id : <input type="text" name="id"><br>
		<input type="submit" value="전송">
	</form>
</body>
</html>