This project contains the methods that the application should call and the second contains the code with the application, which is running on a console.

In the first project there is the LocationAPI class where I define two variables one for the URL and one for IPV6. From the get method : https://ip-geolocation.whoisxmlapi.com/api/v1?apiKey=YOUR_API_KEY&ipAddress=your_ip_address.

The user have to log in to : https: //ip-geolocation.whoisxmlapi.com to receive his api_key. After receiving it, the user replaces API_KEY with his API_KEY and uses IPV6 as ipAddress to locate the user's location.

There is also the NewsAPI class I define two variables one for the URL and one for API_KEY. From the get method: https://newsapi.org/v2/everything?apiKey=API_KEY the user have to log in to newsapi.org/v2/ to receive their api_key. Upon receipt he replaces API_KEY with its API_KEY.

In NewsAPITest I create tests that I can perform. There are two tests in terms of top-headlines which are the top news by country and by category. Then there are four tests for everything, this is the search for new ones by query, by source, by language and by date Finally Test Location where it refers to the user's location.
