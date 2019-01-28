package models;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Movie {
	private String movieTitle;
	private float regularPrice;
	private int movieId;
	private FileReader fileReader;
	
	public Movie(String title, float price){
		movieTitle = title;
		regularPrice = price;
		try{
			latestMovieId();
		}catch(IOException io){
			System.out.println(io);
		}
	}
	
	public void latestMovieId() throws IOException{
		if (isEmpty()){
			movieId = 1;
		}else{
			try{
				fileReader = new FileReader("LatestMovie.txt");
				movieId = fileReader.read();
				fileReader.close();
				movieId++;
				
			}catch(FileNotFoundException fe){
				System.out.println("File not found!"); 
			}
		}
	}
	
	public boolean isEmpty(){
		 File file = new File("LatestMovieId.txt");
	        if (file.length() == 0)
	            return true;
	        else
	           return false;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public float getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(float regularPrice) {
		this.regularPrice = regularPrice;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

}
