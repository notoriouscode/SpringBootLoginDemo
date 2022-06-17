package com.stock.dto;

public class StockUserDtoResponse {
	private String username;
	private String firstName;
	private String lastName;
	private String jwtToken;

	public StockUserDtoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockUserDtoResponse(String username, String firstName, String lastName, String jwtToken) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jwtToken = jwtToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", jwtToken="
				+ jwtToken + "]";
	}

}
