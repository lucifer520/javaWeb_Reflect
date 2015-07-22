package mangues.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mangues.base.BaseServlet;
import mangues.db.DBUtil;
import mangues.model.Student;

@WebServlet(urlPatterns = { "/add" })
public class AddServlet extends BaseServlet {
    private Student student;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	protected void manguesPost(HttpServletRequest request,
			HttpServletResponse response) {
		DBUtil.insert(student);
		try {
			response.sendRedirect("query");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/*try {
			PrintWriter pw =response.getWriter();
			pw.write(new String("成功"));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

}
