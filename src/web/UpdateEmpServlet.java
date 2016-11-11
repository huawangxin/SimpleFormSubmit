package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.*;

//获取提取到的新数据，执行修改的保存动作
public class UpdateEmpServlet extends HttpServlet{
	public void service(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		double salary=Double.parseDouble(request.getParameter("salary"));
		int age=Integer.parseInt(request.getParameter("age"));
		Connection con=null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.71.90:1521:orcl","huawangxin","huawangxin");
		PreparedStatement ps=con.prepareStatement("update t_emp_wangxin set name=?,salary=?,age=?  where id=? ");
		ps.setString(1, name);
		ps.setDouble(2, salary);
		ps.setInt(3, age);
		ps.setInt(4, id);
		ps.executeUpdate();
		response.sendRedirect("list");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(con!=null){
				try{
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
