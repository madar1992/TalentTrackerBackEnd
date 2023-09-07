package com.example.demo.modal;

import java.util.List;

public class UserProfile {

    // Basic Details
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String alternatePhoneNumber;

    // X-Class Details
    private String xSchoolName;
    private String xBoard;
    private String xPercentage;
    private String xYearOfPassing;
    private String xCity;
    private String xState;
    private String xPincode;

    // Intermediate Details
    private String iCollegeName;
    private String iBoard;
    private String iProgram;
    private String iPercentage;
    private String iYearOfPassing;
    private String iCity;
    private String iState;

    // Engineering Details
    private String eCollegeName;
    private String eBoard;
    private String eProgram;
    private String ePercentage;
    private String eYearOfPassing;
    private String eCity;
    private String eState;

    // Skills
    private List<String> skills;

    // Experience Details
    private List<Experience> experienceDetails;

    public static class Experience {
        private String company;
        private String position;
        private String startDate;
        private String endDate;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAlternatePhoneNumber() {
		return alternatePhoneNumber;
	}

	public void setAlternatePhoneNumber(String alternatePhoneNumber) {
		this.alternatePhoneNumber = alternatePhoneNumber;
	}

	public String getxSchoolName() {
		return xSchoolName;
	}

	public void setxSchoolName(String xSchoolName) {
		this.xSchoolName = xSchoolName;
	}

	public String getxBoard() {
		return xBoard;
	}

	public void setxBoard(String xBoard) {
		this.xBoard = xBoard;
	}

	public String getxPercentage() {
		return xPercentage;
	}

	public void setxPercentage(String xPercentage) {
		this.xPercentage = xPercentage;
	}

	public String getxYearOfPassing() {
		return xYearOfPassing;
	}

	public void setxYearOfPassing(String xYearOfPassing) {
		this.xYearOfPassing = xYearOfPassing;
	}

	public String getxCity() {
		return xCity;
	}

	public void setxCity(String xCity) {
		this.xCity = xCity;
	}

	public String getxState() {
		return xState;
	}

	public void setxState(String xState) {
		this.xState = xState;
	}

	public String getxPincode() {
		return xPincode;
	}

	public void setxPincode(String xPincode) {
		this.xPincode = xPincode;
	}

	public String getiCollegeName() {
		return iCollegeName;
	}

	public void setiCollegeName(String iCollegeName) {
		this.iCollegeName = iCollegeName;
	}

	public String getiBoard() {
		return iBoard;
	}

	public void setiBoard(String iBoard) {
		this.iBoard = iBoard;
	}

	public String getiProgram() {
		return iProgram;
	}

	public void setiProgram(String iProgram) {
		this.iProgram = iProgram;
	}

	public String getiPercentage() {
		return iPercentage;
	}

	public void setiPercentage(String iPercentage) {
		this.iPercentage = iPercentage;
	}

	public String getiYearOfPassing() {
		return iYearOfPassing;
	}

	public void setiYearOfPassing(String iYearOfPassing) {
		this.iYearOfPassing = iYearOfPassing;
	}

	public String getiCity() {
		return iCity;
	}

	public void setiCity(String iCity) {
		this.iCity = iCity;
	}

	public String getiState() {
		return iState;
	}

	public void setiState(String iState) {
		this.iState = iState;
	}

	public String geteCollegeName() {
		return eCollegeName;
	}

	public void seteCollegeName(String eCollegeName) {
		this.eCollegeName = eCollegeName;
	}

	public String geteBoard() {
		return eBoard;
	}

	public void seteBoard(String eBoard) {
		this.eBoard = eBoard;
	}

	public String geteProgram() {
		return eProgram;
	}

	public void seteProgram(String eProgram) {
		this.eProgram = eProgram;
	}

	public String getePercentage() {
		return ePercentage;
	}

	public void setePercentage(String ePercentage) {
		this.ePercentage = ePercentage;
	}

	public String geteYearOfPassing() {
		return eYearOfPassing;
	}

	public void seteYearOfPassing(String eYearOfPassing) {
		this.eYearOfPassing = eYearOfPassing;
	}

	public String geteCity() {
		return eCity;
	}

	public void seteCity(String eCity) {
		this.eCity = eCity;
	}

	public String geteState() {
		return eState;
	}

	public void seteState(String eState) {
		this.eState = eState;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<Experience> getExperienceDetails() {
		return experienceDetails;
	}

	public void setExperienceDetails(List<Experience> experienceDetails) {
		this.experienceDetails = experienceDetails;
	}

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters and setters...

    // Basic Details getters and setters...
    // X-Class Details getters and setters...
    // Intermediate Details getters and setters...
    // Engineering Details getters and setters...
    // Skills getters and setters...
    // Experience Details getters and setters...
}

