import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javafx.print.PaperSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/AJAX04")
public class AJAX04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        StringBuffer sb=new StringBuffer();
        String superId="";
        sb.append("[");
        String s="[]";
        try {
            conn=DBUtil.connection();
            String sql="select id from area where name=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            rs=ps.executeQuery();
            if(rs.next()){
                superId= rs.getString("id");
                sql="select * from area where super=?";
                ps= conn.prepareStatement(sql);
                ps.setString(1,superId);
                rs=ps.executeQuery();
                while (rs.next()) {
                    sb.append("{\"id\":\"");
                    sb.append(rs.getString("id"));
                    sb.append("\",");
                    sb.append("\"name\":\"");
                    sb.append(rs.getString("name"));
                    sb.append("\",");
                    sb.append("\"super\":\"");
                    sb.append(rs.getString("super"));
                    sb.append("\"},");
                }
                s=sb.substring(0,sb.length()-1);
                s+="]";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.print(s);
        System.out.println(s);
    }
}
