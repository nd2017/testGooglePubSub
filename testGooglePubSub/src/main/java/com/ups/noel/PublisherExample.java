package com.ups.noel;

/**
 * Hello world!
 *
 */


import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class PublisherExample 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "@ class PublisherExample, main" );
        
        Properties prop = new Properties();
        
        String projectId = "booming-hue-374621";
        String topicId = "test-noel-topic-for-pub-sub";
        //projects/booming-hue-374621/topics/test-noel-topic-for-pub-sub
        
        String message = "";
        
        try (
        	InputStream input = 
        			new FileInputStream("pubsub.properties")
        	//PublisherExample.class.getClassLoader().getResourceAsStream("/pubsub.properties")
        	
        	)
        {
        	prop.load(input);
        	
        	projectId = prop.getProperty("projectId");
        	topicId = prop.getProperty("topicId");
        	message = prop.getProperty("message");
        	
        	System.out.println("projectId: " + projectId);
        	System.out.println("topicId: " + topicId);
        	System.out.println("message: " + message);
        	
        	Date date = Calendar.getInstance().getTime();  
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        	String strDate = dateFormat.format(date);  
        	
        	message = message + ", " + strDate;
        	System.out.println("message + strDate: " + message);
        	
        }
        

        publisherExample(projectId, topicId, message);
        
    }
    
    
    
    public static void publisherExample(String projectId, String topicId, String message)
    	      throws IOException, ExecutionException, InterruptedException {
    	    TopicName topicName = TopicName.of(projectId, topicId);

    	    Publisher publisher = null;
    	    try {
    	      // Create a publisher instance with default settings bound to the topic
    	      publisher = Publisher.newBuilder(topicName).build();

    	      //String message = "test from noel lenovo laptop personal";
    	      ByteString data = ByteString.copyFromUtf8(message);
    	      PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

    	      // Once published, returns a server-assigned message id (unique within the topic)
    	      ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
    	      String messageId = messageIdFuture.get();
    	      System.out.println("Published message ID: " + messageId);
    	    } finally {
    	      if (publisher != null) {
    	        // When finished with the publisher, shutdown to free up resources.
    	        publisher.shutdown();
    	        publisher.awaitTermination(1, TimeUnit.MINUTES);
    	      }
    	    }
    	  }
    
    
}
