<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết giao dịch khách hàng</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; color: #333; }
        .btn-back {
            display: inline-block;
            margin-bottom: 15px;
            padding: 7px 14px;
            background-color: #6c757d;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .btn-back:hover { background-color: #5a6268; }
        .customer-info {
            background: #f8f9fa;
            padding: 15px 20px;
            border-left: 4px solid #0056b3;
            margin-bottom: 20px;
        }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 10px 12px; text-align: left; }
        th { background-color: #f1f3f5; }
        .text-right { text-align: right; }
        .text-center { text-align: center; }
        .badge {
            padding: 4px 8px;
            border-radius: 4px;
            font-size: 12px;
            font-weight: bold;
            color: white;
        }
        .badge-success { background-color: #28a745; }
        .badge-cancel { background-color: #dc3545; }
        .badge-pending { background-color: #ffc107; color: #333; }
    </style>
</head>
<body>

<c:url var="backUrl" value="/quanli/customer-stats">
    <c:param name="startDate" value="${inStartDate}"/>
    <c:param name="endDate" value="${inEndDate}"/>
</c:url>
<a href="${backUrl}" class="btn-back">&larr; Quay lại bảng thống kê</a>

<div class="customer-info">
    <h2 style="margin: 0 0 8px 0;">LỊCH SỬ GIAO DỊCH KHÁCH HÀNG</h2>
    <p style="margin: 4px 0;"><strong>Khách hàng:</strong> <c:out value="${outCustomerName}"/> (Mã: KH${inCustomerId})</p>
    <p style="margin: 4px 0;"><strong>Khoảng thời gian lọc:</strong> Từ <code>${inStartDate}</code> đến <code>${inEndDate}</code></p>
</div>

<c:choose>
    <c:when test="${not empty outOrderList}">
        <table>
            <thead>
            <tr>
                <th class="text-center">Mã HĐ</th>
                <th class="text-center">Ngày đặt hàng</th>
                <th>Người nhận & Địa chỉ giao</th>
                <th>Thanh toán</th>
                <th class="text-center">Trạng thái đơn</th>
                <th class="text-right">Tổng tiền</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ord" items="${outOrderList}">
                <tr>
                    <td class="text-center"><strong>HD${ord.id}</strong></td>
                    <td class="text-center">
                        <fmt:formatDate value="${ord.orderDate}" pattern="dd/MM/yyyy HH:mm"/>
                    </td>
                    <td>
                        <strong><c:out value="${ord.recipientName}"/></strong><br>
                        <small><c:out value="${ord.shippingAddress}"/></small>
                    </td>
                    <td>
                        <c:out value="${ord.paymentMethod}"/><br>
                        <small>(<c:out value="${ord.paymentStatus}"/>)</small>
                    </td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${ord.orderStatus == 'Hoàn thành'}">
                                <span class="badge badge-success">${ord.orderStatus}</span>
                            </c:when>
                            <c:when test="${ord.orderStatus == 'Đã hủy'}">
                                <span class="badge badge-cancel">${ord.orderStatus}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-pending">${ord.orderStatus}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-right">
                        <strong><fmt:formatNumber value="${ord.totalAmountPayable}" type="number" maxFractionDigits="0"/> VNĐ</strong>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Khách hàng này chưa có giao dịch nào trong khoảng thời gian đã chọn.</p>
    </c:otherwise>
</c:choose>

</body>
</html>