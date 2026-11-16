<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tìm kiếm sản phẩm</title>
    <!-- Có thể nhúng link CSS Bootstrap vào đây để giao diện đẹp hơn -->
    <style>
        .product-grid { display: flex; flex-wrap: wrap; gap: 20px; margin-top: 20px; }
        .product-card { border: 1px solid #ddd; padding: 15px; width: 200px; text-align: center; }
        .product-card img { max-width: 100%; height: auto; }
        .price { color: red; font-weight: bold; }
    </style>
</head>
<body>

<h2>TÌM KIẾM SẢN PHẨM</h2>

<!-- Form tìm kiếm và bộ lọc sắp xếp -->
<form action="${pageContext.request.contextPath}/khachhang/search" method="post" class="search-container">
    <!-- Bộ lọc sắp xếp đặt ngay dưới h2 -->
    <div class="filter-box">
        <label for="sortBy"><strong>Sắp xếp theo:</strong></label>
        <select name="sortBy" id="sortBy" onchange="if(this.form.keyword.value.trim() !== '') { this.form.submit(); }">
            <option value="az" ${inSortBy == 'az' || empty inSortBy ? 'selected' : ''}>Thứ tự A - Z (Mặc định)</option>
            <option value="za" ${inSortBy == 'za' ? 'selected' : ''}>Thứ tự Z - A</option>
            <option value="price_asc" ${inSortBy == 'price_asc' ? 'selected' : ''}>Giá tăng dần</option>
            <option value="price_desc" ${inSortBy == 'price_desc' ? 'selected' : ''}>Giá giảm dần</option>
        </select>
    </div>

    <!-- Thanh nhập từ khóa -->
    <div class="search-box">
        <input type="text" name="keyword" value="<c:out value='${inKeyword}'/>" placeholder="Nhập tên sản phẩm..." required>
        <button type="submit">Tìm kiếm</button>
    </div>
</form>

<!-- Thông báo nếu không tìm thấy -->
<c:if test="${not empty outMessage}">
    <p style="color: orange;">${outMessage}</p>
</c:if>

<!-- Hiển thị danh sách sản phẩm -->
<c:if test="${not empty outProductList}">
    <div class="product-grid">
        <c:forEach var="item" items="${outProductList}">
            <div class="product-card">
                <img src="${item.thumbnailImageUrl}" alt="Ảnh sản phẩm">
                <h4>${item.productName}</h4>
                <p class="price">${item.salePrice} VNĐ</p>

                <a href="${pageContext.request.contextPath}/khachhang/detail?id=${item.id}">
                    <button type="button">Xem chi tiết</button>
                </a>
            </div>
        </c:forEach>
    </div>
</c:if>

</body>
</html>