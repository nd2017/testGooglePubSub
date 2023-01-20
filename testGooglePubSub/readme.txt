
[
===
eclipse hanging, stuck at 33%
cancel, did not cancel, end eclipse task in task manager
...
google: eclipse hanging creating maven-archetype-quickstart

https://forum.camunda.io/t/create-maven-project-failed-stuck-at-33/40963

solution: uncheck: run archetype generation interactively
this worked

===
]


[
===
add google pub sub dependency in pom.xml

    <dependency>
      <groupId>com.google.cloud</groupId>
      <artifactId>google-cloud-pubsub</artifactId>
      <version>1.122.2</version>
    </dependency>  
    
copied from: https://github.com/googleapis/java-pubsub/blob/main/samples/install-without-bom/pom.xml
over 50 jars added!!!!

===
]


[
===
https://stackoverflow.com/questions/1751551/maven-how-to-export-project-with-sources-and-dependencies

<project>
[...]
<build>
<plugins>

      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <version>3.5.0</version>
        <executions>
          <execution>
            <id>copy-dependencies</id>
            <phase>package</phase>
            <goals>
              <goal>copy-dependencies</goal>
            </goals>
            <configuration>
              <outputDirectory>${project.build.directory}/alternateLocation</outputDirectory>
              <overWriteReleases>false</overWriteReleases>
              <overWriteSnapshots>false</overWriteSnapshots>
              <overWriteIfNewer>true</overWriteIfNewer>
            </configuration>
          </execution>
        </executions>
      </plugin>
      
</plugins>
</build>
[...]
</project>
===
]




[
===
C:\Users\Noel\eclipse\jee-2022-09\ws_googlePubSub\noel.maven\target>java -jar noel.maven-0.0.1-SNAPSHOT.jar
no main manifest attribute, in noel.maven-0.0.1-SNAPSHOT.jar

https://stackoverflow.com/questions/9689793/cant-execute-jar-file-no-main-manifest-attribute
<build>
  <plugins>
  
    <plugin>
      <!-- Build an executable JAR -->
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <version>3.1.0</version>
      
      <configuration>
        <archive>
          <manifest>
            <addClasspath>true</addClasspath>
            <classpathPrefix>lib/</classpathPrefix>
            <mainClass>com.mypackage.MyClass</mainClass>
          </manifest>
        </archive>
      </configuration>
      
    </plugin>
    
  </plugins>
</build>
===
i changed configuration, to match my project, see below


<!-- cant-execute-jar-file-no-main-manifest-attribute - begin -->
    <plugin>

      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <version>3.1.0</version>
      
       <configuration>
        <archive>
          <manifest>
            <addClasspath>true</addClasspath>
            <classpathPrefix>lib/</classpathPrefix>
            <mainClass>com.ups.noel.maven.App</mainClass>
          </manifest>
        </archive>
      </configuration>  
      
    </plugin>
<!-- cant-execute-jar-file-no-main-manifest-attribute - end -->
===
this worked, results below
C:\Users\Noel\eclipse\jee-2022-09\ws_googlePubSub\noel.maven\target>java -jar noel.maven-0.0.1-SNAPSHOT.jar
Hello World!
]




[

https://youtu.be/XTLHd5nQTZA
how to create an executable jar file with all its dependencies using maven

below is suggested, tailor fitted to my project

<!-- begin
how to create an executable jar file with all its dependencies using maven
-->
        
   <plugin>

      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-assembly-plugin</artifactId>
      
      <executions>
      	<execution>     
      	 
      		<phase>package</phase>      
      		<goals>
      			<goal>single</goal>
      		</goals> 
      		     
      		<configuration>
        		<archive>
          			<manifest>
            			<addClasspath>true</addClasspath>
            			<classpathPrefix>lib/</classpathPrefix>
            			<mainClass>com.ups.noel.maven.App</mainClass>
          			</manifest>
        		</archive>
        
        		<descriptorRefs>
        			<descriptorRef>jar-with-dependencies</descriptorRef>
        		</descriptorRefs>
      		</configuration>
      
      	</execution>
      </executions>
      
    </plugin>
        
<!-- end
how to create an executable jar file with all its dependencies using maven
-->
        
did not work
C:\Users\Noel\eclipse\jee-2022-09\ws_googlePubSub\noel.maven\target>java -jar noel.maven-0.0.1-SNAPSHOT.jar
no main manifest attribute, in noel.maven-0.0.1-SNAPSHOT.jar

      

]


[

@ class PublisherExample, main
Exception in thread "main" java.io.IOException: The Application Default Credentials are not available. 
They are available if running in Google Compute Engine. 
Otherwise, the environment variable GOOGLE_APPLICATION_CREDENTIALS must be defined 
pointing to a file defining the credentials. 
See https://developers.google.com/accounts/docs/application-default-credentials 
for more information.

https://developers.google.com/identity/protocols/oauth2/service-account

SA service account
SA-name: test-noel-san
SA-id  : test-noel-said
email  : test-noel-said@booming-hue-374621.iam.gserviceaccount.com

===
Private key saved to your computer
C:\Users\Noel\Downloads\booming-hue-374621-065a97169056.json 
allows access to your cloud resources, 
so store it securely.
===

in run configurations, environment, set 
variable: GOOGLE_APPLICATION_CREDENTIALS
value: C:\Users\Noel\Downloads\booming-hue-374621-065a97169056.json

]



[

@ class PublisherExample, main
Exception in thread "main" java.util.concurrent.ExecutionException: 
com.google.api.gax.rpc.NotFoundException: io.grpc.StatusRuntimeException: 
NOT_FOUND: Resource not found (resource=your-topic-id).



]


[


        String projectId = "booming-hue-374621";
        String topicId = "test-noel-topic-for-pub-sub";

        //projects/booming-hue-374621/topics/test-noel-topic-for-pub-sub


@ class PublisherExample, main
Published message ID: 6788795569978509


https://console.cloud.google.com/cloudpubsub/subscription/detail/test-noel-topic-for-pub-sub-sub?project=booming-hue-374621&tab=messages

@ class PublisherExample, main
projectId: booming-hue-374621
topicId: test-noel-topic-for-pub-sub
message: test from noel lenovo laptop personal
message + strDate: test from noel lenovo laptop personal, 2023-01-20 04:22:31
Published message ID: 6789756765477830


]





