package createnewhit;



import com.amazonaws.mturk.requester.Comparator;
import com.amazonaws.mturk.requester.HIT;
import com.amazonaws.mturk.requester.Locale;
import com.amazonaws.mturk.requester.QualificationRequirement;
import com.amazonaws.mturk.service.axis.RequesterService;
import com.amazonaws.mturk.service.exception.ServiceException;
import com.amazonaws.mturk.util.PropertiesClientConfig;

/**
 * * The MovieSurvey sample application creates a simple HIT using the * Amazon
 * Mechanical Turk SDK for Java. The file mturk.properties must be found in the
 * current file path.
 */
public class MovieSurvey {
	private RequesterService service;
	// Define the properties of the HIT to be created.
	private String title = "Movie Survey Example";
	private String description = "This is a survey to find out how many movies you have watched recently.";
	private int numAssignments = 100;
	private double reward = 0.05;
	  private long assignmentDurationInSeconds = 60 * 60; // 1 hour
	  private long autoApprovalDelayInSeconds = 60 * 60 * 24 * 15; // 15 days
	  private long lifetimeInSeconds = 60 * 60 * 24 * 3; // 3 days
	  private String requesterAnnotation = "sample#survey";

	/** * Constructor */
	public MovieSurvey() {
		service = new RequesterService(new PropertiesClientConfig());
	}

	/** * Create a simple survey. */
	public void createMovieSurvey() {
		try {
			// The createHIT method is called using a convenience static method
			// // RequesterService.getBasicFreeTextQuestion() that generates the
			// question
			// for the HIT.
//			HIT hit = service
//					.createHIT(
//							title,
//							description,
//							reward,
//							RequesterService
//									.getBasicFreeTextQuestion("How many movies have you seen this month?"),
//							numAssignments);
			
		      // This is an example of creating a qualification.
		      // This is a built-in qualification -- user must be based in the US
		      QualificationRequirement qualReq = new QualificationRequirement();
		      qualReq.setQualificationTypeId(RequesterService.LOCALE_QUALIFICATION_TYPE_ID);
		      qualReq.setComparator(Comparator.NotEqualTo);
		      Locale country = new Locale();
		      country.setCountry("IND");
		      qualReq.setLocaleValue(country);

		      QualificationRequirement qualReq2 = new QualificationRequirement();
		      qualReq2.setQualificationTypeId(RequesterService.APPROVAL_RATE_QUALIFICATION_TYPE_ID);
		      qualReq2.setComparator(Comparator.GreaterThanOrEqualTo);
		      qualReq2.setIntegerValue(95);
		      
		       // The create HIT method takes in an array of QualificationRequirements
		      // since a HIT can have multiple qualifications.
		      QualificationRequirement[] qualReqs = null;
		      qualReqs = new QualificationRequirement[] { qualReq, qualReq2 };
		      
		      HIT hit = service.createHIT(null, // HITTypeId 
		              title, 
		              description, "movie, film", 
		              RequesterService
						.getBasicFreeTextQuestion("How many movies have you seen this month?"),
		              reward, assignmentDurationInSeconds,
		              autoApprovalDelayInSeconds, lifetimeInSeconds,
		              numAssignments, requesterAnnotation, 
		              qualReqs,
		              null // responseGroup
		            );

		      
		      
		    //  System.out.println(hit.getQualificationRequirement()[0].getLocaleValue().getCountry());
			
			// Print out the HITId and the URL to view the HIT.
			System.out.println("Created HIT: " + hit.getHITId());
			System.out.println("HIT location: ");
			System.out.println(service.getWebsiteURL()
					+ "/mturk/preview?groupId=" + hit.getHITTypeId());
		} catch (ServiceException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	/** * Main method * * @param args */
	public static void main(String[] args) {
		// Create an instance of this class.
		MovieSurvey app = new MovieSurvey();
		// Create the new HIT.
		app.createMovieSurvey();
	}
}