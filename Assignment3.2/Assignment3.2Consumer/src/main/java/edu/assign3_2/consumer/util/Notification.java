package edu.assign3_2.consumer.util;

import java.util.ArrayList;
import java.util.List;

public class Notification {

	private MailService mailService;
	private List<String> subscribers;
	
	public Notification() {
		mailService = new MailService("projectmail200@gmail.com", "parola+123");
		
		subscribers = new ArrayList<String>();
		subscribers.add("projectmail200@gmail.com");
		subscribers.add("alexdivri94@gmail.com");
	}
	
	public void notifySubscribers(String dvd) {
		for (String sub : subscribers) {
			mailService.sendMail(sub, "A new DVD was added", dvd);
		}
	}
}
