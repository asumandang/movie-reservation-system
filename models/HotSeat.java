import models.Seat;

/**
 * 
 * @author arriane.artiaga
 *
 */
public class HotSeat extends Seat{
	
	/**
	 * Increases the price to be paid by 20% of the regular price
	 * @param price - the seat price
	 * @param code - the seat code
	 */
	public HotSeat(float price, String code){
		super((float)(price+price*.2), code);
	}
}
