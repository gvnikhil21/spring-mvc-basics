<%@ include file="header.jspf"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<sf:form modelAttribute="pr" action="save-product">
	<sf:hidden path="productNo" />
	<div class="form-group row">
		<label class="col-md-2" for="name">Product Name</label>
		<div class="col-md-6">
			<sf:input path="name" cssClass="form-control" />
			<sf:errors path="name" cssClass="text-danger" />
		</div>
	</div>
	<div class="form-group row">
		<label class="col-md-2" for="price">Price</label>
		<div class="col-md-6">
			<sf:input path="price" cssClass="form-control" />
			<sf:errors path="price" cssClass="text-danger" />
		</div>
	</div>
	<div class="form-group row">
		<label class="col-md-2" for="discontinued">Discontinued</label>
		<div class="col-md-6">
			<label> <sf:radiobutton path="discontinued" value="1" />Yes
			</label> <label> <sf:radiobutton path="discontinued" value="0" />No
			</label>
			<sf:errors path="discontinued" cssClass="text-danger" />
		</div>
	</div>
	<div class="form-group row">
		<label class="col-md-2" for="name"></label>
		<div class="col-md-6">
			<button class="btn btn-primary">${pr.productNo==null?"Add Product":"Update Product"}</button>
		</div>
	</div>
</sf:form>

<%@ include file="footer.jspf"%>