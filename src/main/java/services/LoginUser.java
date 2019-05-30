package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;

import beans.User;
@WebServlet("/login")
public class LoginUser extends HttpServlet
{
	 static Session session=null;
		@Override
		public void init() throws ServletException 
		{   System.out.println("Init executes");
			 session=HibernateSessionProvider.getSession();
		}
		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException
	{
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		Session session=HibernateSessionProvider.getSession();
		User user=(User)session.get(User.class,email);
		session.getSessionFactory().close();
		session.close();
		if(user!=null)
		{
			System.out.println(user.getPassword());
			
			
			System.out.println(password);
			if(user.getPassword().equals(password))
			{    req.getSession().setAttribute("User",user);
				res.sendRedirect("index.jsp");
			}
			else
			{
				res.sendRedirect("login.jsp");
			}
		}
		else
			res.sendRedirect("registration.jsp");
		
		
		
	}

}
