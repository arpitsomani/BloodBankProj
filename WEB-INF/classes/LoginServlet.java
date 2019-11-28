import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet
{
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		Connection con=null;
		Statement st1=null;
		ResultSet rs=null;
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
        	String mob=req.getParameter("mob");
			String pwd=req.getParameter("pwd");
        try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/donatetheblood","root","");
		}
		catch(ClassNotFoundException e1)
		{
			pw.println(e1);
		}
		catch(SQLException e2)
		{
			pw.println(e2);
		}
		
		try
		{
			st1=con.createStatement();
			String a="",b="",c="",d="",e="",f="",g="",h="";
			rs=st1.executeQuery("select * from donor_login where mobile='"+mob+"' and password='"+pwd+"'");
			String s="<html><head><title>Blood Bank</title>"+
"<link type='text/css' rel='stylesheet' href='proj1.css'>"+
"<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>"+
"<script src='https://code.jquery.com/jquery-3.3.1.slim.min.js' integrity='sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo' crossorigin='anonymous'></script>"+
"<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js' integrity='sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1' crossorigin='anonymous'></script>"+
"<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js' integrity='sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM' crossorigin='anonymous'></script>"+
"</head>"+
"<body class='foo'>"+
"<aside class='container'>"+
"<nav class='navbar navbar-expand-xl navbar-primary bg-danger'>"+
"<a class='navbar-brand c'><img src='logo.png' width='50' height='50'>Donate Blood,Save Life</a>"+
"<button type='button' class='navbar-toggler' data-toggle='collapse' data-target='#N1'>"+
"<span class='navbar-toggler-icon'></span></button>"+
"<div class='collapse navbar-collapse justify-content-end' id='N1'>"+
"<ul class='navbar-nav'>"+
"<li class='nav-item'><a href='index.html' class='nav-link'>Home</a></li>"+
"<li class='nav-item'><a href='donor.html' class='nav-link'>Register as Donor</a></li>"+
"<li class='nav-item'><a href='search.html' class='nav-link'>Search</a></li>"+
"<li class='nav-item'><a href='login.html' class='nav-link'>SignIn</a></li>"+
"<li class='nav-item'><a href='about.html' class='nav-link'>about us</a></li>"+
"</ul></div></nav></aside>";
			pw.println(s);
			if(rs.next())
			{
				rs=st1.executeQuery("select * from donor where mobile='"+mob+"'");
				if(rs.next())
				{
					a=rs.getString(1);
				b=rs.getString(2);
				c=rs.getString(3);
				d=rs.getString(4);
				e=rs.getString(5);
				f=rs.getString(6);
				g=rs.getString(7);
				h=rs.getString(8);
				}
				pw.println("your profile<br>");
				pw.println("<nav class='lin'><div>name="+a+"</div><div> mobile="+b+"</div><div> blood group="+c+"</div><div> date of birth="+d+"</div><div> gender="+e+"</div><div> email="+f+"</div><div> address="+g+"</div><div> city="+h+"</div></nav>");
			    pw.println("</table></body></html>");
			}	
			else
			{
				pw.println("you are not register yet,please register");
				res.sendRedirect("donor.html");
			}
			con.close();
		}
		catch(SQLException e3)
		{
			pw.println(e3);
		}
		catch(Exception e4)
		{
			pw.println(e4);
		}
    }
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doGet(req,res);
	}
}