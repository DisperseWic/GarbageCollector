package com.magister.garbagecollector.configuration;

import java.util.Arrays;

import com.magister.garbagecollector.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import com.magister.garbagecollector.entities.Application;
import com.magister.garbagecollector.entities.jpa.ApplicationRepository;
import com.magister.garbagecollector.services.UserService;


@Configuration
public class InitialDataConfiguration {
	
	@Bean
	CommandLineRunner init(UserService userService, 
			ApplicationRepository applicationRepository) {
		return new CommandLineRunner() {
			
			@Override
			@Transactional
			public void run(String... args) throws Exception {

				User user = userService.createUser(new User("user", "user", true));
				User admin = userService.createUser(new User("admin", "admin", true), Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
				User sergey = userService.createUser(new User("sergey", "sergey", true));
				User sarah = userService.createUser(new User("sarah", "sarah", false), null);
				
				String address1 = "Россия, Пермь, ул. Ленина, 12а";
				String address2 = "Россия, Добрянка, пр. Майский, 144";
				String address3 = "Россия, Пермь, ул. Революции, 13";

				String tip1 = "Бытовые отходы";
				String tip2 = "Строительный мусор";
				String tip3 = "Металлом";

				String comment1 = "Труднодоступная территория";
				String comment2 = "Необходим кран-борт";
				String comment3 = "Радиоактивный хлам";

				
				Application app1 = new Application(user, address1, 0.99, comment1, tip1);
				Application app2 = new Application(user, address2, 14.37, comment2, tip2);
				Application app3 = new Application(sergey, address3, 72.18, comment3, tip3);
				applicationRepository.saveAll(Arrays.asList(app1, app2, app3));

			}
		};
	}
}
