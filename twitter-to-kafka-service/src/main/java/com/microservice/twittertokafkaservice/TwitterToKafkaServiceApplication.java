package com.microservice.twittertokafkaservice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.JSON;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.Problem;
import com.twitter.clientlib.model.ResourceUnauthorizedProblem;
import com.twitter.clientlib.model.StreamingTweet;
import com.google.gson.reflect.TypeToken;
import com.microservice.twittertokafkaservice.producer.implementations.TwitterProducer;
import com.microservice.twittertokafkaservice.runner.implementations.TwitterStreamRunner;

// import twitter4j.*;
// @PropertySource("classpath:kafka.properties")
@ComponentScan(basePackages = {"com.microservice.twittertokafkaservice"})
@SpringBootApplication
public class TwitterToKafkaServiceApplication {
	// private TwitterToKafkaStatusListener twitterToKafkaStatusListener;
	// TwitterStreamRunner twitterStreamRunner;
    static TwitterProducer twitterProducer;
	public static void main(String[] args) {
		SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
		
		// TwitterStreamRunner runner= 
		TwitterApi apiInstance = new TwitterApi();
        // TwitterCredentialsOAuth2 credentials = new TwitterCredentialsOAuth2("UePp0Rkl2kk23BuLFURj9ngl6",
        //         "bJdtb12ftyWg8p6bWsI5tKeqxdW7nlhZigyNzT5THcL0xsG4WD",
        //         "1276082951202078721-tuEBoPXHIlcocCypJorHqBbilBs7li",
        //         "AAAAAAAAAAAAAAAAAAAAAPtvdAEAAAAATKM6avwJtQFPcFL6ulJHhGgUe1Y%3Doj1pDS9Cjl3YXLqFe3MgHns5WQuSIcW6s8tHGNkzG8zF0Ik3Lf");
		TwitterCredentialsBearer cres=new TwitterCredentialsBearer("AAAAAAAAAAAAAAAAAAAAAPtvdAEAAAAAJzAjSGgmsRaOWz%2B83TSegBkep1E%3DXcDBX43877CQXGcHT81heLBp1ycVVAgsxwsJwtbK2dkVNjT9i6");	
        apiInstance.setTwitterCredentials(cres);


        try {
            // findTweetById
            // InputStream result = apiInstance.tweets().searchStream("20", null, tweetFields, null, null,null, null);
            Set<String> expansions = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of fields to expand.
    Set<String> tweetFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Tweet fields to display.
    Set<String> userFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of User fields to display.
    Set<String> mediaFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Media fields to display.
    Set<String> placeFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Place fields to display.
    Set<String> pollFields = new HashSet<>(Arrays.asList()); // Set<String> | A comma separated list of Poll fields to display.
    Integer backfillMinutes = 0; // Integer | The number of minutes of backfill requested
            InputStream result =apiInstance.tweets().searchStream(expansions, tweetFields, userFields, mediaFields, placeFields, pollFields, backfillMinutes);
                                
            BufferedReader reader = new BufferedReader(new InputStreamReader(result));
            
            String line=reader.readLine();

            while(true){
                if(line.isBlank()){
                    continue;
                }
               

                // Object jsonObject =JSON.getGson().fromJson(line,StreamingTweet.class );
                System.out.println(line );

                twitterProducer.send(StreamingTweet.fromJson(line));
                // System.out.println(line);
                line=reader.readLine();
            }
    
        } catch (ApiException e) {
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
        catch(IOException e){
            System.err.println("Status code: " + e.getMessage());

        }
	}

	private static void extracted(Problem e) {
        System.out.println(e.toString());
        if (e instanceof ResourceUnauthorizedProblem) {
            System.out.println(((ResourceUnauthorizedProblem) e).getTitle() + " "
                    + ((ResourceUnauthorizedProblem) e).getDetail());
        }
    }
	public TwitterToKafkaServiceApplication(TwitterProducer twitterProducer){
		// this.twitterStreamRunner=streamRunner;
        this.twitterProducer=twitterProducer;
	}

	// @Override
	public void run(String... args) throws Exception {
		
		// this.twitterStreamRunner.run();
		
	}

	

}
