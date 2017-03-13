package ru.mow.domain.tests.data;

public class Message {

	public String subject;
	public String email;
	public String messageText;

	public Message(String email) {
		this.email = email;
	}

	public Message(String subject, String email) {
		this.subject = subject;
		this.email = email;
	}

	public Message(String subject, String email, String messageText) {
		this.subject = subject;
		this.email = email;
		this.messageText = messageText;
	}

}
