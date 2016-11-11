package web;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ListEmpServlet extends HttpServlet{
	public void service(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException,IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = 
			response.getWriter();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.71.90:1521:orcl","huawangxin","huawangxin");
			ps = con.prepareStatement(
				"select * from t_emp_wangxin");
			rs = ps.executeQuery();
			out.println("<table border='1' width='70%'>");
			while(rs.next()){
				out.println("<tr>");
				out.println("<td>"+
					rs.getInt("id")+"</td>");
				out.println("<td>"+
					rs.getString("name")+"</td>");
				out.println("<td>"+
					rs.getDouble("salary")+"</td>");
				out.println("<td>"+
					rs.getInt("age")+"</td>");
				out.println("<td><a href='del?id="+
						rs.getInt("id")+"'>É¾³ý</a></td>");
				out.println("<td><a href='load?id="+
						rs.getInt("id")+"'>ÐÞ¸Ä</a></td>");
				out.println("</tr>");
			}
			out.print("</table>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}







