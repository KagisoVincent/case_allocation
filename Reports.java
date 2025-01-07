/**
 * @author 220104716
 */
package acsse.csc03a3.Clients;
/**
 * main class
 */
public interface Reports {

	String getReportType();

	String getLocation();

	String getDescription();

	String getReporterName();

	void setReportType(String reportType);

	void setLocation(String location);

	void setDescription(String description);

	void setReporterName(String reporterName);
}
