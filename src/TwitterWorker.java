/**
 * Created by wvitz on 08.01.2017.
 */
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Date;
import java.util.List;

public class TwitterWorker implements Worker, Runnable{

    private static final String TWITTER_CONSUMER_KEY = "pj1uo5SG2iQA7jXd7DATnI5nd";
    private static final String TWITTER_SECRET_KEY = "zLWCjRIbECvh0rrN48byGum9bx323EiL41rHZ1vT6bRM8WCQSq";
    private static final String TWITTER_ACCESS_TOKEN = "2194247881-lZ8AnMyraBvWpjqmfqxOHhaQG9I28QZtBtScNx6";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "WxI8i9AjqdS8jaHaWvaCJuuuMl0NT5nkQzi0amqKdF9iM";


    String keyword;
    Boolean isRunning;

    public TwitterWorker(String keyword){
        Thread thread = new Thread(this);
        thread.start();
        this.keyword = keyword;

    }

    public void run(){
        this.setRunning(true);

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
                .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            Query query = new Query(this.keyword);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println(tweet.getCreatedAt().toString() + " @" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }

        this.setRunning(false);
    }

    public boolean isRunning(){return this.isRunning;}

    public void setRunning(boolean run){isRunning = run;}
}
