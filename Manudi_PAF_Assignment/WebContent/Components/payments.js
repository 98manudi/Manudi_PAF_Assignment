$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
		{
		// Clear alerts---------------------
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();
		// Form validation-------------------
		var status = validateItemForm();
		if (status != true)
			 {
			 $("#alertError").text(status);
			 $("#alertError").show();
			 return;
		 }
		// If valid------------------------
		var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
		 $.ajax(
		 {
			 url : "PaymentAPI",
			 type : type,
			 data : $("#formItem").serialize(),
			 dataType : "text",
			 complete : function(response, status)
		 {
		 onItemSaveComplete(response.responseText, status);
		 }
		 });
		});

function onItemSaveComplete(response, status)
{
if (status == "success")
 {
	 var resultSet = JSON.parse(response);
	 if (resultSet.status.trim() == "success")
	 {
		 $("#alertSuccess").text("Successfully saved.");
		 $("#alertSuccess").show();
		 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
	 $("#alertError").text(resultSet.data);
	 $("#alertError").show();
 }
 } else if (status == "error")
 {
	 $("#alertError").text("Error while saving.");
	 $("#alertError").show();
 } else
 {
	 $("#alertError").text("Unknown error while saving..");
	 $("#alertError").show();
 } 

	 $("#hidItemIDSave").val("");
	 $("#formItem")[0].reset();
}
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
 $("#NIC").val($(this).closest("tr").find('td:eq(0)').text());
 $("#productID").val($(this).closest("tr").find('td:eq(1)').text());
 $("#creditNumber").val($(this).closest("tr").find('td:eq(2)').text());
 $("#cvv").val($(this).closest("tr").find('td:eq(3)').text());
 $("#expireDate").val($(this).closest("tr").find('td:eq(4)').text());
 $("#date").val($(this).closest("tr").find('td:eq(5)').text());
 $("#amount").val($(this).closest("tr").find('td:eq(6)').text());
});
// CLIENT-MODEL================================================================
function validateItemForm()
{
	// NIC
	if ($("#NIC").val().trim() == "")
	 {
	 return "Insert NIC.";
	 }
	// PRODUCT_ID
	if ($("#productID").val().trim() == "")
	 {
	 return "Insert product ID.";
	 } 
	
	// CREDIT NUMBER
	if ($("#creditNumber").val().trim() == "")
	 {
	 return "Insert creditNumber.";
	 }

	// CVV
	if ($("#cvv").val().trim() == "")
	 {
	 return "Insert cvv.";
	 }
	
	//CHECK EXPIRE DATE
	if ($("#expireDate").val().trim() == "")
	 {
	 return "Insert expire date.";
	 }
	
	//DATE
	if ($("#date").val().trim() == "")
	 {
	 return "Insert date.";
	 }
	
	// AMOUNT
	if ($("#amount").val().trim() == "")
	 {
	 return "Insert amount.";
	 }
	// is numerical value
	var tmpPrice = $("#amount").val().trim();
	if (!$.isNumeric(tmpPrice))
	 {
	 return "Insert a numerical value for amount.";
	 }
	// convert to decimal price
	 $("#amount").val(parseFloat(tmpPrice).toFixed(2));
	
	return true;
}

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "PaymentAPI",
		 type : "DELETE",
		 data : "paymentID=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onItemDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}