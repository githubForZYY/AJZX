import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AJAX02")
public class AJAX02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer sb=new StringBuffer();
        sb.append("<tr>");
        sb.append("        <td>1</td>");
        sb.append("        <td>张三</td>");
        sb.append("    </tr>");
        sb.append("    <tr>");
        sb.append("        <td>2</td>");
        sb.append("        <td>李四</td>");
        sb.append("    </tr>");
        sb.append("    <tr>");
        sb.append("        <td>3</td>");
        sb.append("        <td>王五</td>");
        sb.append("    </tr>");
        out.print(sb);
    }
}
