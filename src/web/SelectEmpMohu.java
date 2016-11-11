package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class SelectEmpMohu  extends HttpServlet{
	public void service(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		double salary=0;
		int age=0;
		//将以上三个数据放入查询语句中
		//加载驱动
		Connection con=null;
		PreparedStatement ps1=null;
		ResultSet rs =null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			//获取连接
			con=DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.71.90:1521:orcl","huawangxin","huawangxin");
			//创建命令对象
			String sql = "SELECT * FROM t_emp_wangxin where name like '%"+name+"%' order by id asc" ;
			ps1=con.prepareStatement(sql);
			rs =ps1.executeQuery() ;
			out.println("<HTML>");
			out.println("<HEAD></HEAD>");
			out.println("<BODY>");
			out.println("<table border='1' cellpadding='0' cellspacing='0' width='60%'>");
			out.println("<caption>员工信息列表</caption>");
			out.println("<tr><td>ID</td><td>姓名</td><td>薪水</td><td>年龄</td></tr>");
			while (rs.next()) {
				int id=rs.getInt("id");
				String name1 = rs.getString("name") ;
				Double salary1=rs.getDouble("salary");
				int age1 = rs.getInt("age") ;
				out.println("<tr><td>"+id+"</td><td>"+name1+"</td><td>"+salary+"</td><td>"+age1+"</td><tr>");
			}
			out.print("</table>");
			out.print("<a href='addEmp.html'>增加员工信息</a>");
			out.println("</BODY>");
			out.println("</HTML>");
			rs.close() ;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(con!=null){
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			out.println("添加成功");
			out.close();
		}
	}
}

