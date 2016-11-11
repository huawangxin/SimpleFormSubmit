package web;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DeleteEmpServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5491362752815330020L;

	@SuppressWarnings("unused")
	public void service(
			HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException,IOException{
		response.setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = 
			response.getWriter();
		int id = 
			Integer.parseInt(
			request.getParameter("id"));
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
						"jdbc:oracle:thin:@192.168.71.90:1521:orcl","huawangxin","huawangxin");
			ps =con.prepareStatement(
				"delete from t_emp_wangxin where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
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

