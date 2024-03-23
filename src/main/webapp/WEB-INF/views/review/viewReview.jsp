<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
    <title>View Review</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${contextPath}/resources/css/viewReview.css">
    <%--구글 폰트--%>
    <link href="https://fonts.googleapis.com/css?family=Cute+Font&display=swap" rel="stylesheet">
    <title>todayEat</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://kit.fontawesome.com/3d9a5d8845.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
            crossorigin="anonymous"></script>
    <script>
        // 상품 별점 표시
        $(document).ready(function () {
            $(function () {
                var rating = $('.review .rating');
                rating.each(function () {
                    var targetScore = $(this).attr('data-rate');
                    $(this).find('svg:nth-child(-n+' + targetScore + ')').css({color: '#FFCC00'});
                });
            });
        });

        // 삭제
        function reviewDelete() {

            //체크박스 체크된 항목
            const query = 'input[name="checkReview"]:checked'
            const selectedElements = document.querySelectorAll(query)

            //체크박스 체크된 항목의 개수
            const selectedElementsCnt = selectedElements.length;

            if (selectedElementsCnt == 0) {
                alert("삭제할 항목을 선택해주세요.");
                return false;
            } else {
                const arr = new Array(selectedElementsCnt);
                document.querySelectorAll('input[name="checkReview"]:checked').forEach(function (v, i) {
                    arr[i] = v.id;
                });
                var review = {
                    "id": arr
                }
                console.log(product);

                $.ajax({
                    url: '/review/viewReview.do/remove',
                    type: 'POST',
                    traditional: true,
                    data: review,
                    cache: false,
                    success: function (jdata) {
                        if (jdata = 1) {
                            console.log("삭제 성공");
                            location.reload(true);
                        } else {
                            console.log("삭제 실패");
                        }
                    },
                    error: function (request, status, error) {
                        console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                    },
                })
            }
        }
    </script>

</head>
<body>

<div id="page-wrapper">
    <c:choose>
        <c:when test="${empty product}">
            <p>리뷰가 없습니다.</p>
        </c:when>
        <c:when test="${!empty product}">
            <div class="reviewHeader">
                <div class="reviewHeaderWrap">
                    <table>
                        <tr>
                            <td class="rvHeaderN">상품 ID</td>
                            <td class="rvHeaderD">${product.prodno}</td>
                        </tr>
                        <tr>
                            <td class="rvHeaderN">상품명</td>
                            <td class="rvHeaderD">${product.prodname}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <c:forEach var="review" items="${reviewList}">
                <div class="content">
                    <!--체크박스-->
                    <div>
                        <input type="checkbox" id="${review.revno}" name="checkReview" value="checkbox">
                    </div>
                    <!--프로필사진-->
                    <div class="contentImgWrap">
                        <img src="https://ifh.cc/g/d0Hxb0.png">
                    </div>
                    <div class="reviewDetail">
                        <div class="review">
                            <div class="rating" data-rate=${review.rating}>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                                <i class="fas fa-star"></i>
                            </div>
                        </div>
                        <table>
                            <tr>
                                <td>${review.userid}</td>
                                <td>${review.indate}</td>
                                <td>답변 완료</td>
                            </tr>
                            <tr>
                                <td colspan="3">${review.content}</td>
                            </tr>
                        </table>
                    </div>
                    <!--리뷰사진-->
                    <div class="product-img-div">
                        <img src="${contextPath}/download.do/thumbnail?prodno=${review.prodno}&img=${review.img}"
                             onerror='this.src="http://www.ledshine.co.kr/app/dubu_board/docs/imgs/h/lg_h15496012553427_1.jpg"'
                             class="product-img"/>
                    </div>
                    <!--삭제하기-->
                </div>
                <hr>
            </c:forEach>
        </c:when>
    </c:choose>
</div>
<!--main-->

<!--footer-->
<div class="deleteBtn">
    <button type="submit" class="delete" onclick="reviewDelete()">선택 삭제</button>
</div>

</body>

</html>
