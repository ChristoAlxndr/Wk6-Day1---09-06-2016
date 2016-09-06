package localhost.googlemapsapi.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


/* Sample class that makes simple request to Google Maps Directions
 * @author Christopher Michael Alexander
 * @since 2016-09-06
*/ 

public class RestRequest {
	
//	URL of the API to which we want to connect
	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";
	
//	Character set to use when encoding URL parameters
	protected static String charset = "UTF-8";
	
//	API key used for making requests to API
	protected static String key = "AIzaSyAI-b0OwKFzq2tHeLht0JiYzgN2kF6k_l8";
	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		
		
		try {
			
//			The origin or starting point for directions
			System.out.println("What is your origin?");
			String origin = scan.nextLine().toLowerCase();
					
//			The destination or end point for directions
			System.out.println("What is your destination?");
			String destination = scan.nextLine().toLowerCase();
			
//			The method of travel
			System.out.println("How will you be traveling? \n" +
					"driving, walking, bicycling, or transit");
			String travelMethod = scan.nextLine().toLowerCase();
			
//			Language of returnType
			System.out.println("What language would you like for the return text? \n" +
					"en = English, es = Spanish, fr = French, fil = Filipino, it = Italian, ja = Japanese, ar = Arabic");
			String language = scan.nextLine().toLowerCase();
			
//			Measurements in Standard or Metric
			System.out.println("Would you like results displayed in imperial (standard) or metric units?");
			String unitMeasure = scan.nextLine().toLowerCase();
			
//			The return type of the response json  |  xml
			System.out.println("Would you like your response in json or xml?");
			String returnType = scan.nextLine().toLowerCase();
			
//			creates the URL parameters as a string encoding them with the defined charset
			String queryString = String.format("origin=%s&destination=%s&key=%s&mode=%s&language=%s&units=%s",
					URLEncoder.encode(origin, charset),
					URLEncoder.encode(destination, charset),
					URLEncoder.encode(key, charset),
					URLEncoder.encode(travelMethod, charset),
					URLEncoder.encode(language, charset),
					URLEncoder.encode(unitMeasure, charset)
			);
			
//			creates a new URL out of the endpoint, returnType, and queryString
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");
			
//			if we did not get a 200 (success) throw an exception
			if(connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseMessage());
			}
			
//			read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
//			loop of buffer line by line until it returns null meaning there are no more lines
			while (br.readLine() != null) {
//				print out each line to the screen
				System.out.println(br.readLine());
			}
			
//			close connection to API
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scan.close();
		
	} // Main Close
	
} // Class Close

