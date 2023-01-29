<%@include file="./header.jspf"%>

<h3 class="text-danger">OOPS. Error while processing request.</h3>
<p>Please retry after a while.</p>

<button class="btn btn-link text-decoration-none"
	onClick="showErrorDetails()">Show Technical Details</button>

<pre style="visibility: hidden" id="errDetails">
	<%
	Exception ex = (Exception) request.getAttribute("ex");
	ex.printStackTrace(new java.io.PrintWriter(out));
	%>
</pre>
<%@include file="./footer.jspf"%>

<script>
	function showErrorDetails() {
		document.getElementById("errDetails").style.visibility = "visible";
	}
</script>