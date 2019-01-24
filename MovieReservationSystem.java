import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * This class calls all the function necessary for 
 * adding movies, & creating and cancelling reservations. 
 * @author ajruth.sumandang
 * @author Rod Yap
 */
public class MovieReservationSystem {
	private ArrayList<MovieSchedule> movieSchedules = new ArrayList<MovieSchedule>();
	
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
			choice = Helper.getIntInput();
			process(choice);
		}while(choice != 0);
	}
	
	/**
	 * Displays the menu that the user will choose from.
	 * @author ajruth.sumandang
	 */
	private void displayMenu(){
		System.out.println("________________________________________");
		System.out.println("Welcome to the Movie Reservation System!");
		System.out.println("What do you wish to do?");
		System.out.println("[1] Display Movie");
		System.out.println("[2] Add Movie");
		System.out.println("[3] Reserve Seat(s)");
		System.out.println("[4] Cancel Reservation");
		System.out.println("[0] Exit Program");
		System.out.print("Choice: ");
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
				addMovieSchedule();
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
		Comparator<MovieSchedule> comp = (m1, m2) -> {return m1.getStartDate().compareTo(m2.getStartDate());};
		Collections.sort(movieSchedules, comp);
		if(movieSchedules.size() == 0){
			System.out.println("No scheduled movies yet in the system. Please add movies.");
			return;
		}
		System.out.println("____________________________________________________________________");
		System.out.println("Cinema	Start Date	End Date	Reg Price	Movie Title");
		while(movieSchedules.size() > i){
			MovieSchedule m = movieSchedules.get(i);
			
			System.out.println(m.getCinema() + "	" 
			+ Helper.dateFormatter(m.getStartDate()) + "	" 
			+ Helper.dateFormatter(m.getEndDate()) + "	"
			+ m.getMovie().getRegularPrice() + "		"
			+ m.getMovie().getMovieTitle());

			i++;
			if(i != 0 && i % 10 == 0){
				System.out.println(i + " out of " + movieSchedules.size() + " movies");
				System.out.print("Press 0 to display more. ");
				if(Helper.getIntInput() != 0){
					break;
				}
			}
		}
	}
	
	/**
	 * Adds a movie and its start date, end date, and cinema.
	 * @author ajruth.sumandang
	 */
	private void addMovieSchedule(){
		Movie movie;
		Date startDate, endDate;
		int choice = 0;
		float input;
		String str;
		
		System.out.print("Enter movie title: ");
		str = Helper.getStringInput();
		
		do {
			System.out.print("Enter regular price: ");
			input = Helper.getFloatInput();
			if(input < 0){
				System.out.print("Invalid input! Input 1 to input again: ");
				choice = Helper.getIntInput();
				if(choice != 1){
					return;
				}
			}
		} while(input < 0);

		movie = new Movie(str, (int) input);
		do {
			System.out.println("Enter cinema(s) to show the movie [1 - 4 only]");
			System.out.print("If more than one cinema, separate the numbers by comma: ");
			input = Helper.getIntInput(1, 4);
			if(input < 0){
				System.out.print("Invalid input! Input 1 to input again: ");
				choice = Helper.getIntInput();
				if(choice != 1){
					return;
				}
			}
		} while(input < 0);
		
		do {
			System.out.print("Enter start date: ");
			startDate = Helper.getDateInput();
		} while(startDate == null);
		
		do {
			System.out.print("Enter end date: ");
			endDate = Helper.getDateInput();
		} while(endDate == null);
		
		if(startDate.compareTo(endDate) > 0){
			System.out.println("End date is less than start date. Changing movie end date to its start date...");
			endDate = startDate;
		}
		

		movieSchedules.add(new MovieSchedule(movie, (int) input, startDate, endDate));
	}
	
	/**
	 * Sets up the system by initializing the variables obtained from the database.
	 * @author ajruth.sumandang
	 */
	private void setSystem(){
//		String a[] = {"a", "b", "c", "d"};
//		for(int i = 0; i < 35; i++){
//			movieSchedules.add(new MovieSchedule(new Movie(a[i % 4], 100 + i), 0, Helper.getDate()));
//		}
	}
}
