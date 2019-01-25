import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Helper {
	static Scanner sc = new Scanner(System.in);
	/**
	 * Obtains user's integer input.
	 * @return user input if valid; throws invalid input exception if not
	 * 
	 * @author ajruth.sumandang
	 * @throws InvalidInputException if the user input a key other than 1 when prompted to input again
	 * after an invalid input
	 */
	public static int getIntInput() throws InvalidInputException{
		return getIntInput(true);
	}
	
	/**
	 * Obtains the user's integer input and converts it to an array.
	 * @return valid user integer array
	 * 
	 * @author ajruth.sumandang
	 * @throws InvalidInputException 
	 */
	public static int[] getIntArrayInput() throws InvalidInputException{
		sc = new Scanner(System.in);
		ArrayList<Integer> result = new ArrayList<Integer>();
		do{
			// acquire user input
			String input = sc.nextLine();
			String arr[] = input.split("\\s*,\\s*|\\s");
			
			try{ // try to parse the strings
				for(int i = 0; i < 20 && i < arr.length; i++){
					result.add(Integer.parseInt(arr[i]));
				}
			}catch(NumberFormatException e){ // if there is an error in parsing the string
				result.clear();
				System.out.print("Invalid input in one of the values! Enter 1 to input again. ");
				try{
					int choice = getIntInput(false);
					if(choice != 1){
						throw new InvalidInputException();
					}else{
						System.out.print("Input values again: ");
					}
				}catch(InvalidInputException e1){
					throw new InvalidInputException("Invalid integer array input.\n");
				}
			}
			
		}while(result.size() == 0);
		
		return new LinkedHashSet<Integer>(result).stream().mapToInt(Integer::valueOf).toArray();
	}
	
	/**
	 * Obtains the user's integer input and converts it to an array.
	 * Includes range that the values can only have
	 * @return valid user integer array
	 * 
	 * @author ajruth.sumandang
	 * @throws InvalidInputException 
	 */
	public static int[] getIntArrayInput(int min, int max) throws InvalidInputException{
		int arr[];
		
		do{
			arr = new int[4];
			try {
				arr = getIntArrayInput();
				for(int i: arr){
					if(i < min || i > max){
						arr = null;
						System.out.print("Number out of bounds! Enter 1 to input again. ");
						int choice = getIntInput(false);
						if(choice != 1){
							throw new InvalidInputException();
						}else{
							System.out.println("Input value(s) again: ");
						}
					}
				}
			} catch (InvalidInputException e) {
				throw new InvalidInputException("Invalid integer array inpur");
			}
		}while(arr == null);
		
		return arr;
	}
	
	/**
	 * Obtains user's integer input.
	 * @param hasLoop determines whether the system will loop if the input is invalid
	 * @return valid integer user input
	 * @throws InvalidInputException if the user input a key other than 1 when prompted to input again
	 * after an invalid input
	 */
	public static int getIntInput(boolean hasLoop) throws InvalidInputException{
		sc = new Scanner(System.in);
		int value;
		do{
			value = -1;
			String input = sc.nextLine();
			try{
				value = Integer.parseInt(input);
				if(value < 0){
					throw new NumberFormatException();
				}
			}catch(NumberFormatException e){
				if(!hasLoop){
					throw new InvalidInputException();
				}
				System.out.print("Invalid input! Enter 1 to input again: ");
				input = sc.nextLine();
				try{
					value = Integer.parseInt(input);
					if(value < 0){
						throw new InvalidInputException();
					}
				} catch(NumberFormatException e1){
					throw new InvalidInputException();
				}
			}
		}while(value < 0);
		
		return value;
	}
	
	/**
	 * Obtains user's integer input within the specified range.
	 * @param min - the minimum value allowed for the user input
	 * @param max - the maximum value allowed
	 * @return valid user integer input
	 * 
	 * @author ajruth.sumandang
	 * @throws InvalidInputException if the user input a key other than 1 when prompted to input again
	 * after an invalid input
	 */
	public static int getIntInput(int min, int max) throws InvalidInputException{
		int input = getIntInput(false);
		if(input < min || input > max){
			throw new InvalidInputException("Invalid input range.\n");
		}
		return input;
	}
	
	/**
	 * Obtains user's float input.
	 * @return valid user float input
	 * 
	 * @author ajruth.sumandang
	 * @throws InvalidInputException if the user input a key other than 1 when prompted to input again
	 * after an invalid input
	 */
	public static float getFloatInput() throws InvalidInputException{
		return getFloatInput("Input: ");
	}
	
	/**
	 * Obtains user's float input.
	 * @param msg - the message to print when the system prompts the user to input again.
	 * @return user input if valid; throws exception if not
	 * 
	 * @author ajruth.sumandang
	 * @throws InvalidInputException 
	 */
	public static float getFloatInput(String msg) throws InvalidInputException{
		sc = new Scanner(System.in);
		float value;
		do{
			value = -1;	
			String input = sc.nextLine();
			try{
				value = Float.parseFloat(input);
			}catch(NumberFormatException e){
				System.out.print("Invalid input! Enter 1 to input again. ");
				try{
					int choice = getIntInput(false);
					if(choice != 1){
						throw new InvalidInputException();
					}else{
						System.out.print(msg);
					}
				}catch(InvalidInputException e1){
					throw new InvalidInputException("Invalid float input.\n");
				}
				
			}
		}while(value < 0);
		
		return value;
	}
	
	/**
	 * Obtains the user's string input.
	 * @return string input
	 * 
	 * @author ajruth.sumandang
	 */
	public static String getStringInput(){
		String b = sc.nextLine();
		return b;
	}
	
	/**
	 * Obtains the user's date string input.
	 * @return valid user date input 
	 * 
	 * @author ajruth.sumandang
	 * @throws InvalidInputException if the user input a key other than 1 when prompted to input again
	 * after an invalid input
	 */
	public static Date getDateInput() throws InvalidInputException{
		Date date = null;
		int choice = 1;
		do{
			String strDate = sc.nextLine();
			SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
		    sdfrmt.setLenient(false);
		    try{
		        date = sdfrmt.parse(strDate); 
		    }catch(ParseException e){
		        date = null;
		    }
		    
		    if(date == null){
				System.out.print("Invalid input! Press 1 to input again. ");
				try{
					choice = Helper.getIntInput();
				}catch (InvalidInputException e){
					throw new InvalidInputException("Invalid date input.\n");
				}
				if(choice == 1){
					System.out.print("Input date: ");
				}else{
					throw new InvalidInputException("Invalid date input.\n");
				}
			}else{
				choice = 0;
			}
		}while(choice == 1);
		
	    return date;
	}
	
	/**
	 * Obtains the user's time string input.
	 * @return valid user time input
	 * 
	 * @author ajruth.sumandang
	 * @throws InvalidInputException if the user input a key other than 1 when prompted to input again
	 * after an invalid input 
	 */
	public static Time getTimeInput() throws InvalidInputException{
		Date date = null;
		Time time = new Time(0);
		int choice = 1;
		do{
			String strDate = sc.nextLine();
			SimpleDateFormat sdfrmt = new SimpleDateFormat("HH:mm");
		    sdfrmt.setLenient(false);
		    try{
		        date = sdfrmt.parse(strDate);
		    }catch(ParseException e){
		    	System.out.println(e);
		        date = null;
		    }
		    
		    if(date == null){
				System.out.print("Invalid input! Press 1 to input again. ");
				try {
					choice = Helper.getIntInput();
				} catch (InvalidInputException e) {
					throw new InvalidInputException("Invalid time input.\n");
				}
				if(choice == 1){
					System.out.print("Input time: ");
				} else {
					throw new InvalidInputException("Invalid time input.\n");
				}
			}else{
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				time.setTime(cal.getTimeInMillis());
				choice = 0;
			}
		}while(choice == 1);
		
	    return time;
	}
	
	/**
	 * Formats the date into a string of format 'MM/DD/YYYY'
	 * @param date the date to be formatted
	 * @return string format of the received date
	 * 
	 * @author ajruth.sumandang 
	 */
	public static String dateFormatter(Date date){
		SimpleDateFormat localDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    return localDateFormat.format(date);
	}
	
	public static String timeFormatter(Time time){
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
	    return localDateFormat.format(time);
	}
	/**
	 * Retrieves the latest ID of an object
	 * @param filename filename of the Meta file
	 * @return int latest id in the storage
	 * 
	 * @author mark.torres 
	 */
	public static int getLatestId() {
        String id;
        int ID;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(MovieSchedule.SCHEDMETA));
            id = br.readLine();
            ID = Integer.parseInt(id);
            return ID;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MovieScheduleTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MovieScheduleTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
}
