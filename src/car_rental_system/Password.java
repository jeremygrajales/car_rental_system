package car_rental_system;

public class Password {
	private String password;
	
	public Password(String password) {
		this.password = Helper.cleanString(password);
	}
	
	public String[] getHashTuple() {
		String salt = Helper.getNewSalt();
		String hash = Helper.hash(password, salt);
		String[] hashTuple = {hash, salt};
		return hashTuple;
	}
}
