package web;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
/*
 * 1�����ݲ����е�idֵ��
 * 2������ID��ѯ��Ӧ��Ա����Ϣ
 * 3����Ҫ�޸ĵ������Ա�����ʽ��ʾ���û�
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
		//���������Ⱥ�˳��
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//1����ȡ����Ĳ���
		int id=Integer.parseInt(request.getParameter("id"));
		//����id��ѯҪ�޸ĵ�Ա����Ϣ
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.71.90:1521:orcl"
					,"huawangxin","huawangxin");
			//�����������
			ps=con.prepareStatement("select * from t_emp_wangxin where id=?");
			ps.setInt(1, id);
			//ִ�в�ѯ���
			rs=ps.executeQuery();
			//�����ǰ�����Ϣ
			out.println("<html><head></head><body>");
			out.println("<form action='update' method='post'>");
			while(rs.next()){
				String name=rs.getString("name");
				double salary=rs.getDouble("salary");
				int age=rs.getInt("age");
				//����ѯ������ֵ�Ա���ʽ��ʾ
				out.println("���:"+id+"<br>");
				//������ر��������ύid
				out.println(
						"<input type='hidden' value='"+id+"' name='id'/>");
				out.println(
						"����:<input name='name' value='"+name+"'/><br>");
				out.println(
						"нˮ:<input name='salary' value='"+salary+"'/><br>");
				out.println(
						"����:<input name='age' value='"+age+"'/><br>");
			}
			out.println("<input type='submit' value='����'/>");
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
