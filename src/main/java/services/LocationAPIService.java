package services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.NewsAPIException;
import model.newsinfo;
import model.thenewsdb.Article;
import model.thenewsdb.ErrorResponse;
import model.thenewsdb.Location;
import model.thenewsdb.LocationResult;
//import newsapi.LocationAPI;
import model.thenewsdb.NewsResult;

public class LocationAPIService {
	//����� ���������� ��� ���� ��� API_KEY ��� ����������� ���� �ewsAPIService ����� �� IPV6 ��� ��� ��������� ��� �/� ���
	private final String API_URL;
	private final String IPV6;
	
	// ��������������� ����� �������� ���� constractor ��� ����� ��� URL ��� ��� IPV6
	public LocationAPIService(String aPI_URL, String iPV6) {
		API_URL = aPI_URL;
		IPV6 = iPV6;
	
}
	
	//���������� � ������� � ����� ������� ����� ���������� �� ��������� ��� ��������� ���
	//https://ip-geolocation.whoisxmlapi.com/api/v1?apiKey=API_KEY&ipAddress=IPV6
	public LocationResult searchUserLocation(String parameter) throws NewsAPIException {
		LocationResult result = getAPIData("v1",parameter, API_URL, IPV6); 
		return result;
	} 

	
	//get APIData ������� ��� ����� ����� ���� �� Postman
		//� ������� ���� �� ��������� �� LocationResult
private LocationResult getAPIData(String apiFunction, String parameter, String API_URL, String IPV6) throws NewsAPIException {
	
	try {
	final URIBuilder uriBuilder= new URIBuilder(API_URL).setPathSegments("api", apiFunction).addParameter("ipAddress", IPV6);
	if (parameter!=null && !parameter.isEmpty()) {
		   switch (apiFunction) {
		  case "v1" :
			  if(parameter!=null){
			  uriBuilder.addParameter("apiKey", parameter);	
			  }
		  break;
		  
		   }
		   
	}
	final URI uri = uriBuilder.build();
	
	
	//���� �� URI ���� ������ http
	final HttpGet getRequest = new HttpGet(uri);
	final CloseableHttpClient httpclient = HttpClients.createDefault();
	try (CloseableHttpResponse response = httpclient.execute(getRequest)) {
		final HttpEntity entity = response.getEntity();
		final ObjectMapper mapper = new ObjectMapper();
		
		//������ �� �� status ����� ��
		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
		ErrorResponse errorResponse = mapper.readValue(entity.getContent(), ErrorResponse.class);
		if (errorResponse.getStatus() != null)
				throw new NewsAPIException("Error occurred on API call: " + errorResponse.getStatus());
		}
		
		//�� ����� ok ���������� ��� LocationResult
		return mapper.readValue(entity.getContent(), LocationResult.class);
	} catch (IOException e) {
		throw new NewsAPIException("Error requesting data from the NewsAPI.", e);
	}
	
} catch (URISyntaxException e) {
	throw new NewsAPIException("Unable to create request URI.", e);
	
}
}

}