package car_rental_system;

import java.util.ArrayList;

public class Errors {
	
	private ArrayList<String> _errors;
	
	public Errors() {
		this(new String[]{});
	}
	
	public Errors(String[] errors) {
		_errors = new ArrayList<String>();
		this.add(errors);
	}
	
	public void add(String error) {
		if(error != null)
			add(new String[]{error});
	}

	public void add(String[] errors) {
		if(errors != null) {
			for(String error : errors) {
				if(!error.isEmpty() && !_errors.contains(error))
					_errors.add(error);
			}	
		}
	}
	
	public ArrayList<String> getErrors() {
		return _errors;
	}
	
	public boolean isEmpty() {
		return _errors.isEmpty();
	}
	
	public boolean contains(String name) {
		return _errors.contains(name);
	}
}
