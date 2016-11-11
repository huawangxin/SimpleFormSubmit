package web;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
/*
 * 1，根据参数中的id值，
 * 2，根据ID查询对应的员工信息
 * 3，将要修改的数据以表单的形式显示给用户
 * 
 */
public class LoadEmpServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3433164422356534924L;

	public void service(HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException,IOException{
		//后两句有先后顺序！
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//1，获取传入的参数
		int id=Integer.parseInt(request.getParameter("id"));
		//根据id查询要修改的员工信息
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.71.90:1521:orcl"
					,"huawangxin","huawangxin");
			//创建命令对象
			ps=con.prepareStatement("select * from t_emp_wangxin where id=?");
			ps.setInt(1, id);
			//执行查询语句
			rs=ps.executeQuery();
			//输出表单前面的信息
			out.println("<html><head></head><body>");
			out.println("<form action='update' method='post'>");
			while(rs.next()){
				String name=rs.getString("name");
				double salary=rs.getDouble("salary");
				int age=rs.getInt("age");
				//将查询出来的值以表单形式显示
				out.println("编号:"+id+"<br>");
				//添加隐藏表单域，用于提交id
				out.println(
						"<input type='hidden' value='"+id+"' name='id'/>");
				out.println(
						"姓名:<input name='name' value='"+name+"'/><br>");
				out.println(
						"薪水:<input name='salary' value='"+salary+"'/><br>");
				out.println(
						"年龄:<input name='age' value='"+age+"'/><br>");
			}
			out.println("<input type='submit' value='保存'/>");
			out.println("</form></body></html>");
			} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(con!=null){
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
