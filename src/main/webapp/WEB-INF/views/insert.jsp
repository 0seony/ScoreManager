<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/insert.css">
    <title>입력</title>
</head>
<body>
   <section class="wrap">
        <form action="insert_action">
            <label> 이름 입력 : 
                <input type="text" name="student_name" placeholder="이름" />
            </label>
            <label> 중간고사 점수 : 
                <input type="number" name="midscore" placeholder="중간고사" />
            </label>
            <label> 기말고사 점수 : 
                <input type="number" name="finscore" placeholder="기말고사" />
            </label>
            <input type="submit" value="입력 완료" />
        </form>
    </section>
</body>
</html>