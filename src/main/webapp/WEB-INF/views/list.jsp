<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/list.css">
    <title>목록</title>
</head>
<body>
    <section class="wrap">
        <table>
            <thead>
                <tr>
                    <th>idx</th><th>이름</th><th>중간고사 점수</th><th>기말고사 점수</th><th>생성일</th><th></th>
                </tr>
            </thead>
            <tbody>
                ${list}
            </tbody>
        </table>
    </section>
</body>
</html>