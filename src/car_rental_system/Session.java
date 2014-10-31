package car_rental_system;

import java.util.Map;
import javax.servlet.http.HttpSession;

public class Session {
	
	private static HttpSession _session;
	
	public static void initialize(HttpSession session) {
		_session = session;
		while(_session.getAttributeNames().hasMoreElements()) {
			String attr = _session.getAttributeNames().nextElement();
			Object obj = _session.getAttribute(attr);

			if(obj instanceof FlashedObject && ((FlashedObject) obj).used) { 
				_session.removeAttribute(attr);
			}
			else if(obj instanceof FlashedObject && !((FlashedObject) obj).used)
				((FlashedObject) obj).used = true;
		}
	}
	
	public static HttpSession get() {
		return _session;
	}
	
	public static void flash(String name, Object obj) {
		_session.setAttribute(name, (FlashedObject)obj);
	}
	
	public static void flashAll(Map<String, String[]> parameters) {
		for(Map.Entry<String, String[]> entry : parameters.entrySet()) {
			_session.setAttribute(entry.getKey(), entry.getValue());
		}
	}
	
}
