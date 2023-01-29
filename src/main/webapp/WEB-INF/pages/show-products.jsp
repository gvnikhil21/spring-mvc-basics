<%@ include file="./header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>${pageTitle}</h2>

<table class="table table-bordered table-striped">
	<thead>
		<tr>
			<th>Sl No</th>
			<th>Product Name</th>
			<th>Unit Price (in Rs.)</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${products}" var="p" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>
				<a href="./product-details?id=${p.productNo}" class="btn btn-link text-decoration-none">${p.name}
				</a></td>
				<td>${p.price}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file="./footer.jspf"%>