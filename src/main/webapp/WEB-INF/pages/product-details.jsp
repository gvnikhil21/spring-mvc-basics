<%@ include file="header.jspf"%>

<h2>Product Details for id ${param.id}</h2>

<a href="./edit-product?id=${pr.productNo}" class="btn btn-primary">Edit</a>

<div class="row">
	<div class="col">
		<table class="table">
			<tbody>
				<tr>
					<td>Name</td>
					<td>${pr.name}</td>
				</tr>
				<tr>
					<td>Price</td>
					<td>${pr.price}</td>
				</tr>
				<tr>
					<td>Discontinued</td>
					<td>${pr.discontinued==1?"Yes":"No"}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="col"></div>
</div>

<%@ include file="footer.jspf"%>