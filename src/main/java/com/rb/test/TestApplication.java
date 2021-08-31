package com.rb.test;

import com.rb.test.Producer.KafkaProducer1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rb.test"})
public class TestApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(TestApplication.class, args);

/*		File file = new File("C:\\Users\\HP\\Documents\\GamingTestData.txt");
		Scanner scanner = new Scanner(file);
		String payload = null;
		KafkaProducer1 kafkaProducer1 = new KafkaProducer1();
		try{
			while(scanner.hasNextLine()){
				payload = scanner.nextLine();
				System.out.println(payload);
				if (payload != null){
					if(payload.charAt(payload.length() - 1) == ',')
						payload = payload.substring(0,payload.length() - 1);
					System.out.println(payload);
					kafkaProducer1.sendMessage("battle-topic", payload);
				}
			}
		}catch (Exception ex){
			System.out.println(ex.getMessage());
		}*/
	}
}
