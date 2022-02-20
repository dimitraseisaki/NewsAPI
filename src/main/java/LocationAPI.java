import services.LocationAPIService;
//� ����� ���� ������������ ��� �� ������� ��� ��������� ��� �� API, ��� �� json response ��� �������������� 
// ��� ������� ��������� ����� �� ipv6 ��� ��� ��������� ��� ���������� ���
public class LocationAPI {
public static LocationAPIService getLocationAPIService() {
	return new LocationAPIService("https://ip-geolocation.whoisxmlapi.com/", "IPV6");
}
}
