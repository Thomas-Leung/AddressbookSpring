package com.example.AddressbookSpring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AddressbBookSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressbBookSpringApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(AddressbBookSpringApplication.class);

	@Bean
	public CommandLineRunner buddyInfoDemo(BuddyInfoRepository repository) {
		return (args) -> {

			// save a few customers
			repository.save(new BuddyInfo("Jack", "First Street", "6474351234"));
			repository.save(new BuddyInfo("Chloe", "Second Street", "6134562341"));
			repository.save(new BuddyInfo("Kim", "Toronto", "416248359"));
			repository.save(new BuddyInfo("David", "Kingston", "6134449999"));
			repository.save(new BuddyInfo("Michelle", "Ottawa", "1111111111"));

			// fetch all BuddyInfo
			log.info("BuddyInfo found with findAll():");
			log.info("-------------------------------");
			for (BuddyInfo buddyInfo : repository.findAll()) {
				log.info(buddyInfo.toString());
			}
			log.info("");

			// fetch an individual buddyInfo by ID
			BuddyInfo buddyInfo = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(buddyInfo.toString());
			log.info("");

			// fetch buddyInfo by name
			log.info("BuddyInfo found with findByName('David'):");
			log.info("--------------------------------------------");
			repository.findByName("David").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}