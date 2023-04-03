import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/AJAX03")
public class AJAX03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuffer sb=new StringBuffer();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String url="jdbc:mysql://localhost:3306/bjpowernode";
        String username="root";
        String password="Zh20010324..";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn= DriverManager.getConnection(url,username,password);
            String sql="select * from student";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            sb.append("[");
            while (rs.next()) {
                int no=rs.getInt("no");
                String name=rs.getString("name");
                sb.append("{\"no\":");
                sb.append(no);
                sb.append(",\"name\":\"");
                sb.append(name);
                sb.append("\"},");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String s=sb.substring(0,sb.length()-1);
        s+="]";
        System.out.println(s);
        out.print(s);
    }
}
