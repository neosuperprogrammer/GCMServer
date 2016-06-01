package flowgrammer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import flowgrammer.DAO.GCMDao;
import flowgrammer.model.PushId;

public class GCMPusher implements GCMAction{
	private Sender sender;
	private Message msg;
	
	public static void sendPush() {
		Sender sender = new Sender(API_KEY);  //구글 코드에서 발급받은 서버 키
		Message msg = new Message.Builder()
		.addData("q", "push_id")  //데이터 추가
		.addData("push_id", "test")  //데이터 추가
		.build();

		//푸시 전송. 파라미터는 푸시 내용, 보낼 단말의 id, 마지막은 잘 모르겠음 
		Result result = null;
		try {
			List<PushId> pushIds = GCMDao.getPushId();
			PushId pushId = pushIds.get(0);
			result = sender.send(msg, pushId.pushId(), 5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("error : " + e.getMessage());
		}

		//결과 처리
		if(result.getMessageId() != null) {
			//푸시 전송 성공
			System.out.print("send gsm push success");
		}
		else {
			String error = result.getErrorCodeName();   //에러 내용 받기

			System.out.print("send gsm push error : " + error);
			//에러 처리
			//		      if(Constants.ERROR_INTERNAL_SERVER_ERROR.equlas(error)) {
			//		         //구글 푸시 서버 에러
			//		      }
			//		      else if() {}

		}
	}


	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) {
		sender = new Sender(API_KEY);							//푸시 보내는 객체 생성
		Message.Builder builder = new Message.Builder();	//푸시 메시지 만드는 객체
	
		Map<String, String[]> map = request.getParameterMap();	//파라미터 맵을 받아옴
		Iterator<String> iterator = map.keySet().iterator();				//맵의 모든 키를 받아옴
		while(iterator.hasNext()) {
			String key = iterator.next();									//파라미터의 키
			String values[] = map.get(key);								//파라미터 값
			String value = "";												//푸시 데이터 값
			if(values != null) {
				for(String v : values)
					value += v+",";											//배열을 a,b,c,d 형태의 문자열로 만듬
				value = value.substring(0, value.length()-1);
			}
			
			try {
				String msg = new String(value.getBytes("UTF-8"), "UTF-8");
				builder.addData(key, msg);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		msg = builder.build();												//푸시 메시지 생성
		try {
			List<PushId> pushIds = GCMDao.getPushId();
			PushId pushId = pushIds.get(0);
			Result result = sender.send(msg, pushId.pushId(), 5);					//푸시 전송
			String error = result.getErrorCodeName();					//에러코드
			String msgId = result.getMessageId();						//푸시 메시지 id
			
			request.setAttribute("error", error);							//에러메시지 저장
			request.setAttribute("msgId", msgId);						//푸시 메시지 id 저장
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/WEB-INF/views/gcmResult.jsp";
	}
}
