<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thống kê khách hàng theo doanh thu</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; color: #333; }
        .filter-form {
            background: #f8f9fa;
            padding: 15px 20px;
            border: 1px solid #ddd;
            border-radius: 6px;
            display: inline-flex;
            align-items: center;
            gap: 15px;
            margin-bottom: 20px;
        }
        .filter-form input[type="date"] { padding: 6px 10px; border: 1px solid #ccc; border-radius: 4px; }
        .filter-form button {
            padding: 7px 16px;
            background-color: #0056b3;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }
        .filter-form button:hover { background-color: #004494; }
        .error-msg { color: #d9534f; font-weight: bold; margin-bottom: 15px; }
        table { width: 100%; border-collapse: collapse; margin-top: 10px; }
        th, td { border: 1px solid #ddd; padding: 10px 12px; text-align: left; }
        th { background-color: #f1f3f5; }
        .text-right { text-align: right; }
        .text-center { text-align: center; }
        .revenue { color: #d9534f; font-weight: bold; }
        .btn-detail {
            padding: 5px 12px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-detail:hover { background-color: #218838; }
    </style>
</head>
<body>

<h2>THỐNG KÊ KHÁCH HÀNG THEO DOANH THU</h2>

<form action="${pageContext.request.contextPath}/quanli/customer-stats" method="post" class="filter-form">
    <div>
        <label for="startDate"><strong>Từ ngày:</strong></label>
        <input type="date" id="startDate" name="startDate" value="${inStartDate}" required>
    </div>
    <div>
        <label for="endDate"><strong>Đến ngày:</strong></label>
        <input type="date" id="endDate" name="endDate" value="${inEndDate}" required>
    </div>
    <button type="submit">Xem báo cáo</button>
</form>

<c:if test="${not empty outErrorMessage}">
    <div class="error-msg">${outErrorMessage}</div>
</c:if>

<c:if test="${not empty outStatsList}">
    <table>
        <thead>
        <tr>
            <th class="text-center">STT</th>
            <th class="text-center">Mã KH</th>
            <th>Họ và Tên</th>
            <th>Số điện thoại</th>
            <th class="text-center">Hạng thành viên</th>
            <th class="text-right">Điểm tích lũy</th>
            <th class="text-right">Tổng doanh thu</th>
            <th class="text-center">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cust" items="${outStatsList}" varStatus="status">
            <tr>
                <td class="text-center">${status.index + 1}</td>
                <td class="text-center">KH${cust.id}</td>
                <td><strong><c:out value="${cust.fullName}"/></strong></td>
                <td><c:out value="${cust.phoneNumber}"/></td>
                <td class="text-center"><c:out value="${cust.membershipTier}"/></td>
                <td class="text-right"><fmt:formatNumber value="${cust.loyaltyPoints}" type="number"/></td>
                <td class="text-right revenue">
                    <fmt:formatNumber value="${cust.calculatedRevenue}" type="number" maxFractionDigits="0"/> VNĐ
                </td>
                <td class="text-center">
                    <c:url var="detailUrl" value="/quanli/customer-transactions">
                        <c:param name="customerId" value="${cust.id}"/>
                        <c:param name="customerName" value="${cust.fullName}"/>
                        <c:param name="startDate" value="${inStartDate}"/>
                        <c:param name="endDate" value="${inEndDate}"/>
                    </c:url>
                    <a href="${detailUrl}">
                        <button type="button" class="btn-detail">Xem chi tiết</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>