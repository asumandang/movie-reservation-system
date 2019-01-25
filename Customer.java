 /**
  * 
  * @author arriane.artiaga
  *
  */
public class Customer {
		private int customerId;
		private String lastName;
		private String firstName;
		private String middleName;
		private String email;
		
		/*
		 * 
		 */
		public Customer(String lastName, String firstName, String middleName, String email){
			this.lastName = lastName;
			this.firstName = firstName;
			this.middleName = middleName;
			this.email = email;
		}
		
		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

}
