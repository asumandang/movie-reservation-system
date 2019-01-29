import java.util.Date;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author arriane.artiaga
 *
 */

public class Reservation {
	private int reservationId;
	private Date reservationDate;
	private int customerId;
	private ArrayList<Seat> seat; 
	private int scheduleId;
	private FileReader fileReader;
	
	public Reservation(){
		setSeat(new ArrayList<Seat>());
	}
	
	public Reservation(int customerId, Date date, ArrayList<Seat> seat, int scheduleId){ 
		this.customerId = customerId;
		reservationDate = date;
		this.setSeat(seat);
		this.scheduleId = scheduleId;
		try{
			latestReservationId();
		}catch(IOException io){
			System.out.println(io);
		}
		
	}

	public ArrayList<HashMap<String, ArrayList<Seat>>> getReservedSeats(int scheduleId){
		ArrayList<HashMap<String, ArrayList<Seat>>> schedSeats = new ArrayList<>();
		HashMap<Date,ArrayList<Seat>> reservedSeats = new HashMap<>();
		
		//Zen-zan, GANBATTE!!
		
		return schedSeats;
	}
	
	public void latestReservationId()throws IOException{
		if (isEmpty()){
			reservationId = 1;
		}else{
			try{
				fileReader = new FileReader("LatestReservationId.txt");
				reservationId = fileReader.read();
				fileReader.close();
				reservationId++;
				
			}catch(FileNotFoundException fe){
				System.out.println("File not found!"); 
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("LatestReservationId.txt"));
		writer.write(reservationId);
	 
		writer.close();
	}
	
	public boolean isEmpty(){
		 File file = new File("LatestReservationId.txt");
	        if (file.length() == 0)
	            return true;
	        else
	           return false;
	}
	
	public int getReservationId(){
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public ArrayList<Seat> getSeat() {
		return seat;
	}

	public void setSeat(ArrayList<Seat> seat) {
		this.seat = seat;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId){
		this.scheduleId = scheduleId;
	}
	
}
