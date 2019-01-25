
enum Cinema {
	CINEMA_MAX(4);
	private final int number;
	private Cinema(int number){
		this.number = number;
	}
	
	public int getNumber(){
		return number;
	}
}
