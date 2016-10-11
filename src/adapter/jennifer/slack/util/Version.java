package adapter.jennifer.slack.util;

/**
 * Get Extension Version
 *
 */
public class Version {

	public String getVersion(){
		return "1.0";
	}
	
	public static void main(String[] args) {
		System.out.format("Slack Adapter Version %s\n", new Version().getVersion());
	}
}
