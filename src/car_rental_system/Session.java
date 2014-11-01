package car_rental_system;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class Session {
	
	private static HttpSession _session;
	
	public static void initialize(HttpSession session) {
		_session = session;
		Enumeration<String> names = _session.getAttributeNames();
		while(names.hasMoreElements()) {
			String attr = names.nextElement();
			Object obj = _session.getAttribute(attr);

			if(obj instanceof FlashedObject && ((FlashedObject) obj).used) { 
				_session.removeAttribute(attr);
			}
			else if(obj instanceof FlashedObject && !((FlashedObject) obj).used)
				((FlashedObject) obj).used = true;
		}
	}
	
	public static HttpSession getSession() {
		return _session;
	}
	
	public static void setAttribute(String name, Object value) {
		_session.setAttribute(name, value);
	}
	
	public static void removeAttribute(String name) {
		_session.removeAttribute(name);
	}
	
	public static Object getObject(String name, Object defaultValue) {
		Object attr = _session.getAttribute(name);
		if(attr != null) {
			if(attr instanceof FlashedObject) {
				((FlashedObject) attr).used = true;
				attr = ((FlashedObject) attr).get();
			}
			return attr;
		}
		return defaultValue;
	}
	
	public static String getString(String name, String defaultValue) {
		String attr = (String)Session.getObject(name, null);
		if(attr != null)
			return attr;
		return defaultValue;
	}
	
	public static int getInteger(String name, int defaultValue) {
		String attr = (String)Session.getObject(name, null);
		if(attr != null) {
			try {
				return Integer.parseInt(attr);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return defaultValue;
	}
	
	public static void flash(String name, Object obj) {
		_session.setAttribute(name, new FlashedObject(obj));
	}
}
