package testApiFace;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.Page;

import facebook4j.FacebookException;

public class apiFace {

/**
 * A simple Facebook4J client which
 * illustrates how to access group feeds / posts / comments.
 * 
 * @param args
 * @throws FacebookException 
 */
/*public static void main(String[] args) throws FacebookException {

    // Generate facebook instance.
    Facebook facebook = new FacebookFactory().getInstance();
    // Use default values for oauth app id.
    facebook.setOAuthAppId("", "");
    // Get an access token from: 
    // https://developers.facebook.com/tools/explorer
    // Copy and paste it below.
    String accessTokenString = "EAADCOZC2ZB0tMBAKtZAS9HQZBMmAstYRo1Uy7zuxcqo3iTdzY3fmOAFsyhooLcaYKgGeiQZALl6EZBXXHKh6jcp2PlAdFgORulwZCX9Ccucojf3ZA0Xhlw09GmlFi1ATnpZCYmNZB1YqDU2jFbSzsAS54LQ5BfKF7yafV4dAGZCAmACIgZDZD";
    AccessToken at = new AccessToken(accessTokenString);
    // Set access token.
    facebook.setOAuthAccessToken(at);

    // We're done.
    // Access group feeds.
    // You can get the group ID from:
    // https://developers.facebook.com/tools/explorer

    // Set limit to 25 feeds.
    ResponseList<Post> feeds = facebook.getHome();//facebook.getFeed();
    System.out.println("aca"+feeds.size());
        // For all 25 feeds...
        for (int i = 0; i < feeds.size(); i++) {
            // Get post.
            Post post = feeds.get(i);
            // Get (string) message.
            String message = post.getMessage();
                            // Print out the message.
            System.out.println(message);

            // Get more stuff...
            PagableList<Comment> comments = post.getComments();
            String date = post.getCreatedTime().toString();
            String name = post.getFrom().getName();
            String id = post.getId();
        }           
    }*/
	
	
	public static void main(String[] args) {
		String accessToken = "EAADCOZC2ZB0tMBAF44ZBB6ArqTUW90nI2e2tv2naLQZCpAt8ZA9i1KrpKiHFG0aThp0Xv2afTemhVt3MfykppQeUIL9TXWS28g6wGIRgdkDuWnADlDQXSpzuZC7yqjIZAHuelJcCZC2iq8VoSTycZCVKcGnN258G5zzyS8fomM6zkANxiJq3oq8CD6gxZAo6xTe3rWivyOOZBpw4FuBulpXsHuSzZAxiSZCQAfI6FNx55BVuBEjaC8aZAh9WqV";
		@SuppressWarnings("deprecation")
		FacebookClient fbClie = new DefaultFacebookClient(accessToken, Version.VERSION_2_5);
		Page page = fbClie.fetchObject("BillGates", Page.class);
	}
}