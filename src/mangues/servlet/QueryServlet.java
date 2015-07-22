package mangues.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import mangues.base.BaseServlet;
import mangues.db.DBUtil;
import mangues.model.Student;
@WebServlet(urlPatterns = { "/query" })
public class QueryServlet extends BaseServlet {

	@Override	
	protected void manguesPost(HttpServletRequest request,
			HttpServletResponse response) {
		String sql = "select * from Student";
    	List<Student> list = DBUtil.getList(sql);
    	
    	/******************ajax方式获取****************/
    	/*String string = JSON.toJSONString(list);
    	try {
			PrintWriter pw =response.getWriter();
			pw.write(string);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
    	/******************jsp请求获取数据*******************/
    	request.setAttribute("students", list);
    	 try {
			request.getRequestDispatcher( "index.jsp").forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
	}

}
