package api.payload;

//import restAssuredAPI.Pojo_PostRequest.User.Address;

public class User {
	
	  private String user_first_name;
	    private String user_last_name;
	    private Long user_contact_number;
	    private String user_email_id;
	    private Address userAddress;
	    public String scenario_name;
	    public int expectedStatus;
	    //int user_id;
	    //int userStatus=0;

		/*
		 * public int getUserStatus() { return userStatus; }
		 * 
		 * public void setUserStatus(int userStatus) { this.userStatus = userStatus; }
		 */

		/*
		 * public int getUser_id() { return user_id; }
		 * 
		 * public void setUser_id(int user_id) { this.user_id = user_id; }
		 */

	    public User()
	    {
	    	
	    }
		public int getExpectedStatus() {
			return expectedStatus;
		}

		public void setExpectedStatus(int expectedStatus) {
			this.expectedStatus = expectedStatus;
		}

		public String getScenario() {
			return scenario_name;
		}

		public void setScenario(String scenario) {
			this.scenario_name = scenario;
		}

		// Getters
	    public String getUser_first_name() {
	        return user_first_name;
	    }

	    public String getUser_last_name() {
	        return user_last_name;
	    }

	    public Long getUser_contact_number() {
	        return user_contact_number;
	    }

	    public String getUser_email_id() {
	        return user_email_id;
	    }

	    public Address getUserAddress() {
	        return userAddress;
	    }

	    // Setters (added)
		
		  public void setUser_first_name(String userFirstName) { this.user_first_name =
		  userFirstName; }
		  
		  public void setUser_last_name(String userLastName) { this.user_last_name =
		  userLastName; }
		 
	    public void setUser_contact_number(Long userContactNumber) {
	        this.user_contact_number = userContactNumber;
	    }

	    public void setUser_email_id(String userEmailId) {
	        this.user_email_id = userEmailId;
	    }

	    public void setUserAddress(Address userAddress) {
	        this.userAddress = userAddress;
	    }

	    // Optional constructor (you can add other constructors as needed)
	    public User(String userFirstName, String userLastName, Long userContactNumber, String userEmailId, Address userAddress) {
	        this.user_first_name = userFirstName;
	        this.user_last_name = userLastName;
	        this.user_contact_number = userContactNumber;
	        this.user_email_id = userEmailId;
	        this.userAddress = userAddress;
	    }
	    
	    
	    public static class Address {
	    	 public Address()
	    	 {
	    		 
	    	 }

	        private String plotNumber;
	        private String street;
	        private String state;
	        private String country;
	        private Long zipCode;


	        // Getters
	        public String getPlotNumber() {
	            return plotNumber;
	        }

	        public String getStreet() {
	            return street;
	        }

	        public String getState() {
	            return state;
	        }

	        public String getCountry() {
	            return country;
	        }

	        public Long getZipCode() {
	            return zipCode;
	        }


	        // Setters
	        public void setPlotNumber(String plotNumber) {
	            this.plotNumber = plotNumber;
	        }

	        public void setStreet(String street) {
	            this.street = street;
	        }

	        public void setState(String state) {
	            this.state = state;
	        }

	        public void setCountry(String country) {
	            this.country = country;
	        }

	        public void setZipCode(Long zipCode) {
	            this.zipCode = zipCode;
	        }


	        // Optional constructor (you can add other constructors as needed)
	        public Address(String plotNumber, String street, String state, String country, Long zipCode) {
	            this.plotNumber = plotNumber;
	            this.street = street;
	            this.state = state;
	            this.country = country;
	            this.zipCode = zipCode;
	        }
	    }
	}