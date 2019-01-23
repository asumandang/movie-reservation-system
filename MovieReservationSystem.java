import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

/**
 * This class calls all the function necessary for 
 * adding movies, & creating and cancelling reservations. 
 * @author ajruth.sumandang
 * @author Rod Yap
 */
public class MovieReservationSystem {
	private ArrayList<MovieAssignment> movieAssignments = new ArrayList<MovieAssignment>();
	Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]){
		new MovieReservationSystem();
	}
	
	/**
	 * Constructor.
	 * Includes setting up the values of the system and calling the focal functions.
	 */
	public MovieReservationSystem(){
		int choice;
		setSystem();
		do{
			displayMenu();
			choice = getIntInput();
			process(choice);
		}while(choice != 0);
		sc.close();
	}
	
	/**
	 * Displays the menu that the user will choose from.
	 * @author ajruth.sumandang
	 */
	private void displayMenu(){
		System.out.println("Welcome to the Movie Reservation System!");
		System.out.println("What do you wish to do?");
		System.out.println("[1] Display Movie");
		System.out.println("[2] Add Movie");
		System.out.println("[3] Reserve Seat(s)");
		System.out.println("[4] Cancel Reservation");
		System.out.println("[0] Exit Program");
	}
	
	/**
	 * Contains the processes the system will execute.
	 * @param choice the user's choice
	 * @author ajruth.sumandang
	 */
	private void process(int choice){
		switch(choice){
			case 1: // display movies
				displayMovies();
				break;
			case 2: // add movie to cinema
				addMovieAssignment();
				break;
			case 3: // reserve seat
				break;
			case 4: // cancel reservation
				break;
			case 0: // exit program
				System.out.println("Thank you for using this program.");
			default: // invalid input
		}
	}
	
	/**
	 * Display the movies in the system sorted by date.
	 * Only display the first top ten recent movies unless desired by the user.
	 * @author ajruth.sumandang
	 */
	private void displayMovies(){
		int i = 0;
		Comparator<MovieAssignment> comp = (m1, m2) -> {return m1.getStartDate().compareTo(m2.getStartDate());};
		Collections.sort(movieAssignments, comp);
		
		System.out.println("Cinema	Start Date	End Date	Reg Price	Movie Title");
		while(movieAssignments.size() > i){
			MovieAssignment m = movieAssignments.get(i);
			
			System.out.println(m.getCinema() + "	" 
			+ dateFormatter(m.getStartDate()) + "	" 
			+ dateFormatter(m.getEndDate()) + "	"
			+ m.getMovie().getRegularPrice() + "		"
			+ m.getMovie().getMovieTitle());

			i++;
			if(i != 0 && i % 10 == 0){
				System.out.println(i + " out of " + movieAssignments.size() + " movies");
				System.out.print("Press 0 to display more. ");
				if(getIntInput() != 0){
					break;
				}
			}
		}
	}
	
	/**
	 * Adds a movie and its start date, end date, and cinema.
	 * @author ajruth.sumandang
	 */
	private void addMovieAssignment(){
		Movie movie;
		Date startDate, endDate;
		float input;
		String str;
		
		System.out.print("Enter movie title: ");
		str = getStringInput();
		System.out.print("Enter regular price: ");
		input = getIntInput();
		movie = new Movie(str, (int) input);
		System.out.print("Which cinemas? [1 - 4 only]");
		input = getIntInput();
		System.out.print("Enter start date: ");
		startDate = getDate();
		System.out.print("Enter end date: ");
		endDate = getDate();
		
		movieAssignments.add(new MovieAssignment(movie, (int) input, startDate, endDate));
	}
	
	/**
	 * Sets up the system by initializing the variables obtained from the database.
	 * @author ajruth.sumandang
	 */
	private void setSystem(){
		String a[] = {"a", "b", "c", "d"};
		for(int i = 0; i < 35; i++){
			movieAssignments.add(new MovieAssignment(new Movie(a[i % 4], 100 + i), 0, getDate()));
		}
	}
	
	/**
	 * Obtains user's integer input.
	 * AHJ: dummy function until zen finishes the input tester.
	 * @return valid integer input
	 */
	private int getIntInput(){
		int b = sc.nextInt();
		return b;
	}
	
	/**
	 * Obtains the user's string input.
	 * AHJ: dummy function until zen finishes the input tester.
	 * @return valid string input
	 */
	private String getStringInput(){
		sc.nextLine();
		String b = sc.nextLine();
		return b;
	}
	
	//AHJ: delete this if valid na ang valid-date-input function
	/**
	 * Obtains the user's date input.
	 * AHJ: dummy function until zen finishes the input tester.
	 * @return valid date input
	 */
	Date getDate(){
		int i = (int) (Math.random() * 100)  % 29 + 1;
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, i);
		return cal.getTime();
	}
	
	/**
	 * Formats the date into a string of format 'MM/DD/YYYY'
	 * @param date the date to be formatted
	 * @return string format of the received date
	 * @author ajruth.sumandang 
	 */
	String dateFormatter(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return "" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
	}
}
