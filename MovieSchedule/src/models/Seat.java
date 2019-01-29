package models;


/**
 * 
 * @author arriane.artiaga
 *
 */
public class Seat{
	private float price;
	private String code;
	
	/**
	 * 
	 * @param price
	 * @param code
	 */
	public Seat(float price, String code){
		this.price = price;
		this.code = code;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
