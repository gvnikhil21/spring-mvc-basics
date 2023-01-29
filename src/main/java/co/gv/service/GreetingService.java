package co.gv.service;

public class GreetingService {

	public String getMessage(String name) {
		if (name == null) {
			name = "WEB_MVC";
		}
		return "Hello " + name;
	}
}
