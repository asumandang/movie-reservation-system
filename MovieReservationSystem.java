import java.sql.Time;
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
	 * 
	 * @author ajruth.sumandang
	 */
	public MovieReservationSystem(){
		int choice = -1;
		setSystem();
		do{
			displayMenu();
			try {
				choice = Helper.getIntInput(false);
			} catch (InvalidInputException e) {
				System.out.println(e);
			}
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
	 * @param choice - the user's choice
	 * @author ajruth.sumandang
	 */
	private void process(int choice){
		switch(choice){
			case 1: // display movies
				displayMovies();
				break;
			case 2: // add movie to cinema
				try {
					addMovieSchedule();
				} catch (InvalidInputException e) {
					System.out.println(e);
				}
				break;
			case 3: // reserve seat
				break;
			case 4: // cancel reservation
				break;
			case 0: // exit program
				System.out.println("Thank you for using this program.");
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
		System.out.println("Cinema	Start Date	End Date	Time	Reg Price	Movie Title");
		while(movieSchedules.size() > i){
			MovieSchedule m = movieSchedules.get(i);
			
			System.out.println(m.getCinema() + "	" 
			+ Helper.dateFormatter(m.getStartDate()) + "	" 
			+ Helper.dateFormatter(m.getEndDate()) + "	"
			+ Helper.timeFormatter(m.getTime()) + "	"
			+ m.getMovie().getRegularPrice() + "		"
			+ m.getMovie().getMovieTitle());

			i++;
			if(i != 0 && i % 10 == 0){
				System.out.println(i + " out of " + movieSchedules.size() + " movies");
				System.out.print("Press 0 to display more. ");
				try {
					if(Helper.getIntInput() != 0){
						break;
					}
				} catch (InvalidInputException e) {
					return;
				}
			}
		}
	}
	
	/**
	 * Adds a movie and its information such as price, cinema, and schedule.
	 * @author ajruth.sumandang
	 * @throws InvalidInputException if the user input an invalid value and chooses not to proceed
	 * when prompted
	 */
	private void addMovieSchedule() throws InvalidInputException{
		Movie movie;
		Date startDate, endDate;
		Time time = new Time(0);
		float input = 0;
		int cinema[];
		String str;
		String errMsg = "Unable to create movie due to an incorrect movie information input";
		
		// edit movie information
		System.out.print("Enter movie title: ");
		str = Helper.getStringInput();
		System.out.print("Enter regular price: ");
		input = Helper.getFloatInput("Enter regular price:");
		movie = editMovieInformation(new Movie(str, input));
		if(movie == null){
			return;
		}
		
		try{
			// input cinemas where the movies will be placed
			System.out.println("Enter cinema(s) to show the movie [1 - 4 only]");
			System.out.print("If more than one cinema, separate the numbers by comma: ");
			cinema = Helper.getIntArrayInput(1, 4);
			
			int i = 0;
			// adds the movie information per cinema
			while(i < cinema.length){
				System.out.println("--Movie Schedule for Cinema " + cinema[i] + "--");
				
				System.out.print("Enter start date (MM/DD/YYYY): ");
				startDate = Helper.getDateInput();
				
				System.out.print("Enter end date (MM/DD/YYYY): ");
				endDate = Helper.getDateInput();
				
				if(startDate.compareTo(endDate) > 0){
					System.out.println("End date is less than start date. Changing movie end date to its start date...");
					endDate = startDate;
				}
				
//				System.out.print("Enter start time (HH:MM): ");
//				time = Helper.getTimeInput();
				showMovieScheduleSummary(new MovieSchedule(movie, cinema[i], startDate, endDate, time));
				movieSchedules.add(new MovieSchedule(movie, cinema[i], startDate, endDate, time));
				i++;
			}
			
		}catch(InvalidInputException e){ 
			// if any of the input is invalid and the user opted not to input again,
			// the system prompts the user that the movie was not created due to an invalid  input.
			throw new InvalidInputException(errMsg);
		}
	}
	
	/**
	 * Holds the function necessary for editing movie information.
	 * Includes the user's interface and edits the received movie information
	 * @param movie - the movie to be edited
	 * @return the edited movie; null if the user chose not to create the movie and exit to main menu.
	 * 
	 * @author ajruth.sumandang
	 */
	private Movie editMovieInformation(Movie movie){
		int choice;
		do{
			showMovieInformationMenu(movie);
			try {
				choice = Helper.getIntInput(false);
			} catch (InvalidInputException e) {
				choice = -1;
			}
			movie = editMovieProcess(movie, choice);
		} while (choice != 0 && choice != 3);
		
		return movie;
	}
	
	/**
	 * Displays the information (such as title and price) of the received movie.
	 * @param movie - movie whose information is to be displayed
	 * 
	 * @author ajruth.sumandang
	 */
	private void showMovieInformationMenu(Movie movie){
		System.out.println("______________________________________");
		System.out.println("Below is the movie to be created. Input choice if you want to apply changes,");
		System.out.println("proceed to scheduling, or terminate adding the movie.");
		System.out.println("[Movie Information]");
		System.out.println("[1] Title: " + movie.getMovieTitle());
		System.out.println("[2] Price: " + movie.getRegularPrice());
		System.out.println("[3] No changes. Proceed to scheduling.");
		System.out.println("[0] Do not create movie & exit to main menu.");
		System.out.print("Choice: ");
	}
	
	/**
	 * Edits the movie based on the user's input.
	 * @param movie - the movie to be edited
	 * @param choice - user choice
	 * @return edited movie
	 */
	private Movie editMovieProcess(Movie movie, int choice){
		String title;
		Float price;
		switch(choice){
			case 1:
				// if the movie title is to be edited
				System.out.print("Input new movie title: ");
				title = Helper.getStringInput();
				movie.setMovieTitle(title);
				break;
			case 2: 
				// if the movie's price is to be edited
				System.out.print("Input new price: ");
				try {
					price = Helper.getFloatInput("Input new price: ");
					movie.setRegularPrice(price);
				} catch (InvalidInputException e) {
					movie = null;
				}
				break;
			case 3:
				// if the user chose not to edit the schedule
				System.out.println("Proceeding to the movie schedule...");
				break;
			case 0:
				// if user chose not to create the movie and exit to main menu
				movie = null;
				System.out.println("Exiting to main menu...");
				break;
			default:
				// invalid inputs
				System.out.println("Invalid input!");
		}
		return movie;
	}
	
	/**
	 * Displays the movie schedule information.
	 * @param movieSched - the movie schedule whose information is to be displayed
	 * 
	 * @author ajruth.sumandang
	 */
	private void showMovieScheduleSummary(MovieSchedule movieSched){
		System.out.println("[Movie Schedule Summary for Cinema " + movieSched.getCinema() +  "]");
		System.out.println("[1] Start date: " + Helper.dateFormatter(movieSched.getStartDate()));
		System.out.println("[2] End date: " + Helper.dateFormatter(movieSched.getEndDate()));
		System.out.println("_______________________________");
	}
	
	/**
	 * Sets up the system by initializing the variables obtained from the database.
	 * 
	 * @author ajruth.sumandang
	 */
	private void setSystem(){
	}
}
