package flowgrammer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main implements GCMAction {
	@Override
	public String action(HttpServletRequest req, HttpServletResponse resp) {
		return "main.jsp";
	}
}
