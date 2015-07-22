package mangues.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import mangues.base.BaseServlet;
import mangues.db.DBUtil;
import mangues.model.Student;

@WebServlet(urlPatterns = { "/remove" })
public class RemoveServlet extends BaseServlet {
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
    	DBUtil.delete(student);
    	try {
			PrintWriter pw =response.getWriter();
			JSONObject json = new JSONObject();
			json.put("msg", "成功");
			pw.write(json.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
