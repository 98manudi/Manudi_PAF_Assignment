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
 $("#formItem").submit();
});
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
	// CODE
	if ($("#NIC").val().trim() == "")
	 {
	 return "Insert NIC.";
	 }
	// NAME
	if ($("#productID").val().trim() == "")
	 {
	 return "Insert product ID.";
	 } 
	
	// PRICE-------------------------------
	if ($("#creditNumber").val().trim() == "")
	 {
	 return "Insert creditNumber.";
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