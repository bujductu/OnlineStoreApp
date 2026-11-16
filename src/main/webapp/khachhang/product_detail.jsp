<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết sản phẩm</title>
    <style>
        .detail-container { display: flex; gap: 40px; margin-top: 20px; }
        .image-section { width: 300px; }
        .image-section img { width: 100%; }
        .info-section { max-width: 500px; }
        .sale-price { font-size: 24px; color: red; font-weight: bold; }
        .retail-price { text-decoration: line-through; color: gray; }
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/khachhang/search">< Quay lại tìm kiếm</a>
<hr>

<c:if test="${not empty outProduct}">
    <div class="detail-container">
        <div class="image-section">
            <img src="${outProduct.thumbnailImageUrl}" alt="${outProduct.productName}">
        </div>
        <div class="info-section">
            <h2>${outProduct.productName}</h2>
            <p>Tình trạng:
                <c:choose>
                    <c:when test="${outProduct.stockQuantity > 0}">Còn hàng (${outProduct.stockQuantity})</c:when>
                    <c:otherwise><span style="color: red; font-weight: bold;">Hết hàng</span></c:otherwise>
                </c:choose>
            </p>
            <p>
                <c:if test="${outProduct.retailPrice != null && outProduct.retailPrice > 0 && outProduct.retailPrice > outProduct.salePrice}">
                    <span class="retail-price">${outProduct.retailPrice} VNĐ</span><br>
                </c:if>
                <span class="sale-price">${outProduct.salePrice} VNĐ</span>
            </p>
            <c:if test="${outProduct.stockQuantity > 0}">
                <button>Thêm vào giỏ hàng</button>
            </c:if>
            <div style="margin-top: 30px;">
                <h3>Mô tả chi tiết:</h3>
                <p style="white-space: pre-line;line-height: 1">
                    <c:out value="${outProduct.detailedDescription}"/></p>
            </div>
        </div>
    </div>
</c:if>

</body>
</html>