import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exception.NewsAPIException;
import model.newsinfo;
import model.thenewsdb.Location;
import model.thenewsdb.LocationResult;
import services.LocationAPIService;
import services.NewsAPIService;
//�� ���� ��� ����� �������� �� ����������� test ��� ������� ��� ������������� ��� ����� NewsAPIService
public class NewsAPITest {

	@Before
	public void setUp() throws Exception {
	}
	
	//������������ TEST ���� ������� ��� �������� ��� �� �ewsAPIService ��� ���� ��� 
	
	@Test
	//���� ��� �� ��� ��� ����� ��� ����� �������� � �������
	public void testSearchAPI1() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.getPopularNewsForCountry("gr");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);	}
	
	@Test
	//���� ������� �� ��� ���� ��� ��� ��������� ��� �������� � �������
	public void testSearchAPI2() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.getPopularNewsForCategory("gr","business");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}
	
	@Test
	//���� ������� �� query ��� �������� � �������
	public void testSearchAPI3() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.searchQueryForNews("Apple");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}
	
	@Test
	//���� ������� �� �o query ��� ������ ��� ��� ���� ��� ����
	public void testSearchAPI4() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.searchEverythingForSource("Apple","en","bbc-news");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}
	
	@Test
	//���� ������� �� �o query ��� ������
	public void testSearchAPI5() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.getsearchNewsForLanguage("Apple","en");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);	
		
	}
	
	@Test
	//���� ������� �� �o query ��� ������ ��� ��� ���� ��� ���� ��� ��� ���������� ���������� 
	public void testSearchAPI6() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.getsearchQueryForDateofPublication("Apple","en","bbc-news","2022-01-25","2022-01-31");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);	
		
	}

		 @Test
		//������� �������� top-headlines news ��� ��� ������� ��� ������ ������� �� ��  IP address ��� ����
	public void testSearchAPI() throws NewsAPIException {
		 final LocationAPIService newsSearchService1= LocationAPI.getLocationAPIService();
		 //������ �� api_key
		 final LocationResult results2 = newsSearchService1.searchUserLocation("API_KEY");
		 String country = Location.getCountry();
		 System.out.println("Your country is: " + country);
	     final NewsAPIService newsSearchService= NewsAPI.getNewsAPIService();
		 final List<newsinfo> results= newsSearchService.getPopularNewsForCountry(country);
		 Assert.assertFalse(results.isEmpty());
		 results.forEach(System.out::println);
		}



}
