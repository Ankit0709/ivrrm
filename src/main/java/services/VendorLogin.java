package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import beans.User;
import beans.Vendor;

@WebServlet("/vendorLogin")
public class VendorLogin extends HttpServlet
{
	Session session=null;
	@Override
	public void init() throws ServletException 
	{   
		 session=HibernateSessionProvider.getSession();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException
	{
		String email=req.getParameter("vendor_email");
		String password=req.getParameter("vendor_password");
		
		Vendor vendor=(Vendor)session.get(Vendor.class,email);
		
		
		if(vendor!=null)
		{
					
		
			if(vendor.getPassword().equals(password))
			{   req.getSession().setAttribute("Vendor",vendor);
			session.close();
				res.sendRedirect("addProduct.jsp");
			}
			else
			{ 
				res.sendRedirect("vendor_login.jsp");
				
			}
		}
		else
		{
			
			res.sendRedirect("vendor_registration.jsp");
		}
			
		
		
		
	}
}


