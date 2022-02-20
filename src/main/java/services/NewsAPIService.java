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
import model.thenewsdb.NewsResult;
//��� ����� ���� ��������� ��� �������� ��� ���������������� ��� ��� ����������� ���
public class NewsAPIService {
	//����� ����������
	private final String API_URL;
	private final String API_KEY;
	
	
	// ��������������� ����� �������� ���� constractor ��� ����� ��� URL ��� ��� API_key
	public NewsAPIService(String aPI_URL, String aPI_KEY) {
		API_URL = aPI_URL;
		API_KEY = aPI_KEY;
	}


		//���������� ���� �� ������� ���� ������ ������� �� NewsAPI, ����� ���������� �� ��������� ������������ ������� �� ��� ����������� �����������
		
		// ���� � ������� ����� � ������� get popular news by country
		//https://newsapi.org/v2/top-headlines?country=gr&apiKey=API_KEY&ipAddress=IPV6
		public   List<newsinfo> getPopularNewsForCountry(String parameter) throws NewsAPIException {
			NewsResult result = getAPIData("top-headlines", parameter,null,null,null,null, API_URL, API_KEY);
			List<newsinfo> NewsInfoList = new ArrayList<>(result.getArticles().size());
			for (Article theResult : result.getArticles()) {
				NewsInfoList.add(new newsinfo(theResult));
			}
			return NewsInfoList;
		}

		// ���� � ������� ����� � get popular news by category
	 	//https://newsapi.org/v2/top-headlines?country=gr&category=business&apiKey=API_KEY&ipAddress=IPV6
	 	//https://newsapi.org/v2/top-headlines?country=gr&category=entertainment&apiKey=API_KEY&ipAddress=IPV6
		
		public List<newsinfo> getPopularNewsForCategory(String parameter, String parameter2) throws NewsAPIException {
			NewsResult result = getAPIData("top-headlines", parameter, parameter2,null,null,null, API_URL, API_KEY);
			List<newsinfo> NewsInfoList = new ArrayList<>(result.getArticles().size());
			for (Article theResult : result.getArticles()) {
				NewsInfoList.add(new newsinfo(theResult));
			}
			return NewsInfoList;
		}
		
	
		
				 // ���� � ������� ����� � search Query for news
			 	//https://newsapi.org/v2/everything?q=Apple&apiKey=API_KEY&ipAddress=IPV6
		public List<newsinfo> searchQueryForNews(String parameter) throws NewsAPIException {
			 NewsResult result = getAPIData("everything", parameter,null,null,null,null, API_URL, API_KEY);
			 List<newsinfo> NewsInfoList = new ArrayList<>(result.getArticles().size());
			for (Article theResult : result.getArticles()) {
				NewsInfoList.add(new newsinfo(theResult));
			}
			
			return NewsInfoList;
			
		}
		
		//���� � ������� ����� � search for source
		//https://newsapi.org/v2/everything?q=Apple&language=en&sources=bbc-news&apiKey=API_KEY&ipAddress=IPV6
		public List<newsinfo> searchEverythingForSource(String parameter,String parameter2,String parameter3) throws NewsAPIException {
			 NewsResult result = getAPIData("everything", parameter,parameter2,parameter3,null,null, API_URL, API_KEY);
			 List<newsinfo> NewsInfoList = new ArrayList<>(result.getArticles().size());
			for (Article theResult : result.getArticles()) {
				NewsInfoList.add(new newsinfo(theResult));
			}
			
			return NewsInfoList;
			
		}
		
		//���� � ������� ����� search news for Language
		//https://newsapi.org/v2/everything?q=Apple&language=en&apiKey=API_KEY&ipAddress=IPV6
		//greek language is not supported according to documentation in newsapi.org&apiKey=API_KEY&ipAddress=IPV6
				public List<newsinfo> getsearchNewsForLanguage(String parameter,String parameter2) throws NewsAPIException {
					 NewsResult result = getAPIData("everything", parameter,parameter2,null,null,null, API_URL, API_KEY);
					 List<newsinfo> NewsInfoList = new ArrayList<>(result.getArticles().size());
					for (Article theResult : result.getArticles()) {
						NewsInfoList.add(new newsinfo(theResult));
					}
					
					return NewsInfoList;
					
				}
				
				//���� � ������� ����� � ������� � search query for date of publication
			//https://newsapi.org/v2/everything?q=Apple&language=en&sources=bbc-news&from=2022-01-25&to=2022-01-31&apiKey=API_KEY&ipAddress=IPV6
					public List<newsinfo> getsearchQueryForDateofPublication(String parameter,String parameter2,String parameter3,String parameter4,String parameter5) throws NewsAPIException {
					 NewsResult result = getAPIData("everything", parameter,parameter2,parameter3,parameter4,parameter5, API_URL, API_KEY);
					 List<newsinfo> NewsInfoList = new ArrayList<>(result.getArticles().size());
					  for (Article theResult : result.getArticles()) {
						NewsInfoList.add(new newsinfo(theResult));
							}
							
							return NewsInfoList;
							
						}
	
	
	//get APIData ������� ��� ����� ����� ���� �� Postman
	//� ������� ���� �� ��������� �� �ewsResult 
public NewsResult getAPIData(String apiFunction, String parameter,String parameter2,String parameter3,String parameter4,String parameter5, String API_URL, String API_KEY) throws NewsAPIException {
    	
    	try {
    		//���� build �� URI 
    		//�������� �� ���������� segments
    		//����������� ��� apiFuction ����� ��� ������������ ������� ��� ����������� top-headlines ��� everything
    		//���� addParameter �� �PI KEY �� ����� �������� �� ��������� ����� ��� URI ��� Postman
			final URIBuilder uriBuilder= new URIBuilder(API_URL).setPathSegments("v2", apiFunction).addParameter("apiKey", API_KEY);
			
			
			//���������� ����������
			if (parameter !=null && !parameter.isEmpty()) {
			   switch (apiFunction) {
			   case "top-headlines":
				   if(parameter !=null) {
				   uriBuilder.addParameter("country", parameter);
				   }
				   if(parameter2 !=null)
				   {
					   uriBuilder.addParameter("category", parameter2);
					 }
				  
				//   uriBuilder.addParameter("sources=bbc-news", parameter5);
				  break;
			   case "everything":
				   if(parameter !=null) {
				   uriBuilder.addParameter("q", parameter);
				   }
				   if(parameter !=null) {
				   uriBuilder.addParameter("language", parameter2);
				   }
				  if(parameter !=null) {
					  uriBuilder.addParameter("sources", parameter3);
				   }
				  if(parameter !=null) {
					  uriBuilder.addParameter("from", parameter4);
				   }
				  if(parameter !=null) {
					  uriBuilder.addParameter("to", parameter5);
				   }
				//   uriBuilder.addParameter("sources=bbc-news", parameter);
				  break;
			   }
				   
			}
			
			final URI uri = uriBuilder.build();
			
			
			//���� �� URI ���� ������ http
			final HttpGet getRequest = new HttpGet(uri);
			final CloseableHttpClient httpclient = HttpClients.createDefault();
			//���������� ������ send ��� Postman
			try (CloseableHttpResponse response = httpclient.execute(getRequest)) {
				final HttpEntity entity = response.getEntity();
				final ObjectMapper mapper = new ObjectMapper();
				
				//������ �� �� status ����� ��
				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					ErrorResponse errorResponse = mapper.readValue(entity.getContent(), ErrorResponse.class);
					if (errorResponse.getStatus() != null)
						throw new NewsAPIException("Error occurred on API call: " + errorResponse.getStatus());
				}
				
				
				//�� ����� ok ���������� ��� NewsResult
				return mapper.readValue(entity.getContent(), NewsResult.class);
			} catch (IOException e) {
				throw new NewsAPIException("Error requesting data from the TheNewsAPIDB API.", e);
			}
		
			
			//����������� �� ���� ��� Exception
		} catch (URISyntaxException e) {
			throw new NewsAPIException("Unable to create request URI.", e);
			
		}
    	
    	
    }
}
