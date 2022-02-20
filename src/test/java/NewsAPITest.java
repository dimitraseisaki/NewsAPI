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
//σε αυτή την κλάση μπορούμε να εκτελέσουμε test τον μεθόδων που δημιουργήσαμε στη κλάση NewsAPIService
public class NewsAPITest {

	@Before
	public void setUp() throws Exception {
	}
	
	//δημιουργούμε TEST όπου καλούμε τις μεθόδους από το ΝewsAPIService μια προς μια 
	
	@Test
	//τεστ για τα νέα της χώρας την οποία επιλέγει ο χρήστης
	public void testSearchAPI1() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.getPopularNewsForCountry("gr");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);	}
	
	@Test
	//τεστ ανάλογα με την χώρα και την κατηγορία που επιλέγει ο χρήστης
	public void testSearchAPI2() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.getPopularNewsForCategory("gr","business");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}
	
	@Test
	//τεστ ανάλογα το query που επιλέγει ο χρήστης
	public void testSearchAPI3() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.searchQueryForNews("Apple");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}
	
	@Test
	//τεστ ανάλογα με τo query την γλώσσα και την πηγή των νέων
	public void testSearchAPI4() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.searchEverythingForSource("Apple","en","bbc-news");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}
	
	@Test
	//τεστ ανάλογα με τo query την γλώσσα
	public void testSearchAPI5() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.getsearchNewsForLanguage("Apple","en");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);	
		
	}
	
	@Test
	//τεστ ανάλογα με τo query την γλώσσα και την πηγή των νέων και την ημερομηνία δημοσίευση 
	public void testSearchAPI6() throws NewsAPIException {
		final NewsAPIService newsSearchService=NewsAPI.getNewsAPIService();
		final List<newsinfo> results = newsSearchService.getsearchQueryForDateofPublication("Apple","en","bbc-news","2022-01-25","2022-01-31");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);	
		
	}

		 @Test
		//βρίσκει αυτόμαρα top-headlines news από την περιοχή του χρήστη ανάλογα με το  IP address που έχει
	public void testSearchAPI() throws NewsAPIException {
		 final LocationAPIService newsSearchService1= LocationAPI.getLocationAPIService();
		 //βαζεις το api_key
		 final LocationResult results2 = newsSearchService1.searchUserLocation("at_8aqFoTPTYnhz1lPOI5yfygioNPURy");
		 String country = Location.getCountry();
		 System.out.println("Your country is: " + country);
	     final NewsAPIService newsSearchService= NewsAPI.getNewsAPIService();
		 final List<newsinfo> results= newsSearchService.getPopularNewsForCountry(country);
		 Assert.assertFalse(results.isEmpty());
		 results.forEach(System.out::println);
		}



}
