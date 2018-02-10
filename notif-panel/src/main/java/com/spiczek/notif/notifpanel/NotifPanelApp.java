package com.spiczek.notif.notifpanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

@SpringBootApplication
public class NotifPanelApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(NotifPanelApp.class);
		app.addListeners(new ApplicationPidFileWriter());
		app.run(args);
	}
}
