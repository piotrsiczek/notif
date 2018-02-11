package com.spiczek.notif.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class NotifServerApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(NotifServerApp.class);
		app.addListeners(new ApplicationPidFileWriter());
		app.run(args);
	}
}
