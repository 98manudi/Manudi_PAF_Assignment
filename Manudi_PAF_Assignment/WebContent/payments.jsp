<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/payments.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Payment Management</h1>
<form id="formItem" name="formItem">
 NIC:
 <input id="NIC" name="NIC" type="text"
 class="form-control form-control-sm">
 <br> Product ID:
 <input id="productID" name="productID" type="text"
 class="form-control form-control-sm">
 <br> Credit Number:
 <input id="creditNumber" name="creditNumber" type="text"
 class="form-control form-control-sm">
 <br> cvv:
 <input id="cvv" name="cvv" type="text"
 class="form-control form-control-sm">
 <br> Expire Date:
 <input id="expireDate" name="expireDate" type="text"
 class="form-control form-control-sm">
 <br> Date:
 <input id="date" name="date" type="text"
 class="form-control form-control-sm">
 <br> amount:
 <input id="amount" name="amount" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave"
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Payment paymentObj = new Payment();
 out.print(paymentObj.readPayment());
 %>
</div>
</div> </div> </div>
</body>
</html>