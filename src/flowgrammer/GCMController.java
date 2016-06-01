package flowgrammer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GCMController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private HashMap<String, Object> mActionMap;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String propsString = config.getInitParameter("config");
		Properties props = new Properties();
		FileInputStream fis = null;
		try {
			ServletContext context = this.getServletContext();
			String realPath = context.getRealPath(propsString);
			fis = new FileInputStream(realPath);
			props.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Iterator<Object> keys = props.keySet().iterator();
		mActionMap = new HashMap<String, Object>();
		
		while (keys.hasNext()) {
			String cmd = keys.next().toString();
			String className = props.getProperty(cmd);
			try {
				Class<?> cmdClass = Class.forName(className);
				Object cmdInstance = cmdClass.newInstance();
				mActionMap.put(cmd, cmdInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String nextPage = "/WEB-INF/views/noaddress.jsp";
		
		String type = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
		GCMAction action = (GCMAction) mActionMap.get(type);
		if (action != null) {
			nextPage = action.action(req, resp);
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher(nextPage );
		dispatcher.forward(req, resp);
	}
}
