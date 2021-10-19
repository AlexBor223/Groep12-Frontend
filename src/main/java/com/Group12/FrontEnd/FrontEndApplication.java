package com.Group12.FrontEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;

@SpringBootApplication
public class FrontEndApplication {

	public static void main(String[] args) {

		Application.launch(WindowController.class, args);
	}

}
