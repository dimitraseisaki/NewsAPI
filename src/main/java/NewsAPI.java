import services.NewsAPIService;
//��������������� ��� ��� ������������ ��� NewsAPIService
//� ����� ���� ������������ ��� �� ������� ��� ��������� ��� �� API, ��� �� json response ��� �������������� 
//����� �� API_KEY ���
public class NewsAPI {
	public static NewsAPIService getNewsAPIService() {
		return new NewsAPIService ("https://newsapi.org/","API_KEY");
	}
}
