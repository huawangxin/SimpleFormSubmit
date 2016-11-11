package web;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
public class AddEmpServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1858398952662637674L;

	@SuppressWarnings("unused")
	public void service(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException,IOException{
		System.out.println("11111");
		request.setCharacterEncoding("utf-8");
		response.setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = 
			response.getWriter();
		String  name = 
			request.getParameter("name");
		double salary = 
			Double.parseDouble(
			request.getParameter("salary"));
		int age = 
			Integer.parseInt(
			request.getParameter("age"));
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.71.90:1521:orcl","huawangxin","huawangxin");
			ps = 
				con.prepareStatement(
				"insert into t_emp_wangxin values" +
				"(emp_id_seq_wangxin.nextval,?,?,?)");
			ps.setString(1, name);
			ps.setDouble(2, salary);
			ps.setInt(3, age);
			//重定向到查询功能
			ps.executeUpdate();
			//重定向原理很重要！！
			response.sendRedirect("list");
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
