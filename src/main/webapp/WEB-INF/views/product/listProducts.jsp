<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${contextPath}/resources/css/listProducts.css">
    <%--구글 폰트--%>
    <link href="https://fonts.googleapis.com/css?family=Cute+Font&display=swap" rel="stylesheet">
    <title>todayEat</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://kit.fontawesome.com/3d9a5d8845.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
            crossorigin="anonymous"></script>
    <script>
        // 전체 선택
        $(document).ready(function () {
            $("#all").click(function () {
                var chk = $(this).is(":checked");//.attr('checked');
                if (chk) $(".checkProduct").prop('checked', true);
                else $(".checkProduct").prop('checked', false);
            });
        });
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

        function productDelete() {

            //체크박스 체크된 항목
            const query = 'input[name="checkProduct"]:checked'
            const selectedElements = document.querySelectorAll(query)

            //체크박스 체크된 항목의 개수
            const selectedElementsCnt = selectedElements.length;

            if (selectedElementsCnt == 0) {
                alert("삭제할 항목을 선택해주세요.");
                return false;
            } else {
                const arr = new Array(selectedElementsCnt);
                document.querySelectorAll('input[name="checkProduct"]:checked').forEach(function (v, i) {
                    arr[i] = v.value;
                });
                var product = {
                    "id": arr
                }
                console.log(product);

                $.ajax({
                    url: '${contextPath}/product/listProducts/remove.do',
                    type: 'POST',
                    traditional: true,
                    data: product,
                    cache: false,
                    success: function (jdata) {
                        if (jdata = 1) {
                            console.log("삭제 성공");
                            location.href = "${contextPath}/product/listProducts.do";
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

        // 검색
        $(function productSearch() {
            $('#search-btn').click(function () {
                self.location = "${contextPath}/product/listProducts.do" + '${pageMaker.makeQueryPage(pageNum)}' + "&keyword=" + encodeURIComponent($('#keyword').val());
            });
        });
        // 정렬
        $(function productSearchSort() {
            // 검색 파라미터 가져오기
            const urlParams = new URL(location.href).searchParams;
            const keyword = urlParams.get('keyword');
            console.log(keyword)
            if (keyword == null) {
                // 1. 일반 정렬(키워드 x)
                $('#sort').change(function () {
                    self.location = "${contextPath}/product/listProducts.do" + '${pageMaker.makeQueryPage(pageNum)}' + "&sortType=" + encodeURIComponent($("#sort option:selected").val());
                });
            } else {
                // 2. 검색 후 정렬(파라미터에서 키워드를 가져와서 정렬)
                $('#sort').change(function () {
                    self.location = "${contextPath}/product/listProducts.do" + '${pageMaker.makeQueryPage(pageNum)}' + "&keyword=" + keyword + "&sortType=" + encodeURIComponent($("#sort option:selected").val());
                });
            }
        });

    </script>
</head>
<body>
    <div class="searchSortBar">
        <div class="all">
            <input type="checkbox" name="checkboxAll" id="all">
            <label for="all">전체 선택</label>
            <button type="submit" class="all-btn" onclick="productDelete()">선택 삭제</button>
        	
        </div>
        <div class="search">
            <select class="select" id="sort">
                <option value="">정렬 방식</option>
                <option id="korean" value="korean">가나다순</option>
                <option id="priceAsc" value="priceAsc">가격 오름차순</option>
                <option id="priceDesc" value="priceDesc">가격 내림차순</option>
                <option id="ratingAsc" value="ratingAsc">별점 오름차순</option>
                <option id="ratingDesc" value="ratingDesc">별점 내림차순</option>
            </select>
            <div class="searchBox">
                <input type="text" class="keyword" id="keyword" placeholder="검색어 입력">
                <button class="search-btn" id="search-btn" type="submit">
                    <i class="fa-solid fa-magnifying-glass fa-lg"></i>
                </button>
            </div>
        </div>
    </div>

<hr>
<br>
<div class="productListWrap">
    <c:choose>
        <c:when test="${empty productsList}">
            <div class="isNull">
                <p>검색 결과가 없습니다.</p>
            </div>
        </c:when>
        <c:when test="${!empty productsList}">
            <div class="productList">
                <c:forEach var="product" items="${productsList}">
                    <div class="productt">
                        <div class="product-top">
                            <input type="checkbox" name="checkProduct" class="checkProduct" id=${product.rNum} value="${product.prodno}">
                            <div class="review">
                                <div class="rating" data-rate=${product.avgRating}>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                    <i class="fas fa-star"></i>
                                </div>
                            </div>
                        </div>
                        <div class="product-detail">
                                <%--상품 조회 기능 완성하면 URL 확인 후 변경--%>
                            <a href="${contextPath}/product/viewProduct.do?prodno=${product.prodno }">
                                <div class="product-img-wrap">
                                    <c:choose>
                                        <c:when test="${not empty product.img && product.img!='null' }">
                                            <div class="product-img-div">
                                                <img src="${contextPath}/download.do/thumbnail?prodno=${product.prodno}&img=${product.img}"
                                                     onerror='this.src="http://www.ledshine.co.kr/app/dubu_board/docs/imgs/h/lg_h15496012553427_1.jpg"'
                                                     class="product-img"/>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <div class="product-info">
                                    <div class="product-name">
                                            ${product.prodname}
                                    </div>
                                    <div class="product-price">
                                        <fmt:formatNumber pattern="#,###원">${product.price}</fmt:formatNumber>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="pagination_wrap">
                <ul class="pagination">
                        <%--        이전 버튼--%>
                    <c:if test="${pageMaker.prev }">
                        <a href='<c:url value="/product/listProducts.do${pageMaker.makeQueryPage(pageMaker.startPage-1)}${pageMaker.isKeyword()}${pageMaker.isSort()}"/>'>이전</a>
                    </c:if>
                        <%--    페이지 번호 버튼 --%>
                    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNum">
                        <a href='<c:url value="/product/listProducts.do${pageMaker.makeQueryPage(pageNum) }${pageMaker.isKeyword()}${pageMaker.isSort()}"/>'>${pageNum}</a>
                    </c:forEach>
                        <%--  다음 버튼--%>
                    <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
                        <a href='<c:url value="/product/listProducts.do/${pageMaker.makeQueryPage(pageMaker.endPage+1)}${pageMaker.isKeyword()}${pageMaker.isSort()}"/>'>다음</a>
                    </c:if>
                </ul>
            </div>
        </c:when>
    </c:choose>
</div>


</body>
</html>
