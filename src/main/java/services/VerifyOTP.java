package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Vendor;
@WebServlet("/verifyOTP")
public class VerifyOTP extends HttpServlet{
	 
@Override
protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException 
{
	
	int otp=Integer.parseInt(req.getParameter("codeBox1")+req.getParameter("codeBox2")+req.getParameter("codeBox3")
	  +req.getParameter("codeBox4")+req.getParameter("codeBox5")+req.getParameter("codeBox6"));
			
    int otp2=(Integer)req.getSession().getAttribute("otp");
     
	if(otp==otp2)
	{
		Vendor vendor=(Vendor) req.getSession().getAttribute("vendor");
		vendor.setVerified("Yes");
		req.getSession().removeAttribute("vendor");
		req.getSession().removeAttribute("otp");
		res.sendRedirect("vendor_login.jsp");
	 }
	else
	{
		
		res.sendRedirect("otp.jsp");
	}
}
}
