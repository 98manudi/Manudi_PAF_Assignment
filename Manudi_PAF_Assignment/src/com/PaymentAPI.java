package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class PaymentAPI
 */
@WebServlet("/PaymentAPI")
public class PaymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Payment paymentObj = new Payment();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

 // Convert request parameters to a Map
    private static Map getParasMap(HttpServletRequest request)
    {
     Map<String, String> map = new HashMap<String, String>();
    try
     {
	     Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	     String queryString = scanner.hasNext() ?
	     scanner.useDelimiter("\\A").next() : "";
	     scanner.close();
	     String[] params = queryString.split("&");
     for (String param : params)
     { 
    
	    String[] p = param.split("=");
	     map.put(p[0], p[1]);
	 }
     }
    catch (Exception e)
     {
     }
    return map;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String output = paymentObj.insertPayment(request.getParameter("NIC"),
				 request.getParameter("productID"),
				request.getParameter("creditNumber"),
				request.getParameter("cvv"),
				request.getParameter("expireDate"),
				request.getParameter("date"),
				request.getParameter("amount"));
				response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = paymentObj.updatePayment(paras.get("hidItemIDSave").toString(),
		 paras.get("NIC").toString(),
		 paras.get("productID").toString(),
		paras.get("creditNumber").toString(),
		paras.get("cvv").toString(),
		paras.get("expireDate").toString(),
		paras.get("date").toString(),
		paras.get("amount").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = paymentObj.deleteItem(paras.get("paymentID").toString());
		response.getWriter().write(output); 
	}

}
