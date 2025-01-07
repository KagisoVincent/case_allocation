/*
 * @author 220104716
 */
package acsse.csc03a3.Clients;
import java.io.Serializable;
/*
 * Sation class for the station clients(polices) , to track the victim cases n respond to them
 * this class implements the main class called Reports 
 */
public class Station implements Reports, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String policeName;
    private String station;
    private String reply;
    private Victim victim;

    public Station(String policeName, String station, String reply) {
        this.policeName = policeName;
        this.station = station;
        this.reply = reply;
    }

    public Station(String policeName, String station, String reply, Victim victim) {
        this.policeName = policeName;
        this.station = station;
        this.reply = reply;
        this.victim = victim;
    }

	@Override
	public String getReportType() {
		// TODO Auto-generated method stub
		return station;
	}

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return reply;
	}

	@Override
	public String getReporterName() {
		// TODO Auto-generated method stub
		return policeName;
	}

	@Override
	public void setReportType(String reportType) {
		// TODO Auto-generated method stub
		this.station=reportType;
		
	}

	public Victim getVictim() {
		return victim;
	}

	public void setVictim(Victim victim) {
		this.victim = victim;
	}

	@Override
	public void setLocation(String location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		this.reply=description;
	}

	@Override
	public void setReporterName(String reporterName) {
		// TODO Auto-generated method stub
		this.policeName=reporterName;
	}

}

