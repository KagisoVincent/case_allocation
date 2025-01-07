package acsse.csc03a3.Clients;
/*
 * victim clients 
 */
public class Victim implements Reports {
    private String caseType;
    private String location;
    private String description;
    private String userName;

    public Victim(String userName, String caseType, String location, String description) {
        this.caseType = caseType;
        this.location = location;
        this.description = description;
        this.userName = userName;
    }

	@Override
	public String getReportType() {
		// TODO Auto-generated method stub
		return caseType;
	}

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public String getReporterName() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public void setReportType(String reportType) {
		// TODO Auto-generated method stub
		this.caseType=reportType;
	}

	@Override
	public void setLocation(String location) {
		// TODO Auto-generated method stub
		this.location=location;
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		this.description = description;
	}

	@Override
	public void setReporterName(String reporterName) {
		// TODO Auto-generated method stub
		this.userName = userName;
		
	}


    // Getters and setters
    // ...
	
	
	
	
}