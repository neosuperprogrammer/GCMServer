package flowgrammer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GCMAction {
	public static final String API_KEY = "AIzaSyAmIMGdKpVRCFQbD7sOvboujMQW8oZQaBI";
//	public static final String regId = "APA91bESXM0WH5qo3P22aInKi7WiR1FH_TzHlURw4oCAKfXOuETTPl-JqBN5PbsFjPSql2_34eOodFeDJ5P5pAIfhQAJh-joA1Gg_rvxz-_GyebF5SllkcjWMRIPAGFHPclXK_y8wzqH";
	
	public String action(HttpServletRequest req, HttpServletResponse resp);
}
