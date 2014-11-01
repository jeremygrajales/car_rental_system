package car_rental_system;
	
class FlashedObject {
	
	public boolean used = false;
	private Object value;
	
	public FlashedObject(Object obj) {
		value = obj;
	}
	
	public Object get() {
		return value;
	}
	
	public void set(Object obj) {
		value = obj;
	}
}
