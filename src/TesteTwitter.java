import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;

public class TesteTwitter {

	public static void main(String[] args) {
		Twitter twClient = new Twitter();
		List<Status> list = twClient.search("tchandy");
		for (Status status : list) {
			System.out.println(status);
		}

	}

}
