package services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.hibernate.Session;
import org.hibernate.query.Query;

import beans.User;
import beans.Vendor;

@WebServlet("/vendorLogin")
public class VendorLogin extends HttpServlet
{
	Session session=null;
	@Override
	public void init() throws ServletException 
	{   
		 
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException
	{session=HibernateSessionProvider.getSession();
		String email=req.getParameter("vendor_email");
		String password=req.getParameter("vendor_password");
		
		Query<Vendor> query=session.createQuery("from Vendor where Vendor_Email='"+email+"'");
		
		 if(query!=null)
		  {
			  List<Vendor> vendor=query.list();
			  for(Vendor v:vendor)
			  {
				  if(v!=null)
					{
								
					
						if(v.getPassword().equals(password))
						{   req.getSession().setAttribute("Vendor",v);
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
		 else
			{
				
				res.sendRedirect("vendor_registration.jsp");
			}
		
		
	}
}


