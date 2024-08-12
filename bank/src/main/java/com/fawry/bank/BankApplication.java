package com.fawry.bank;

import com.fawry.bank.Repos.AccountRepo;
import com.fawry.bank.Repos.Entity.Account;
import com.fawry.bank.Repos.Entity.Address;
import com.fawry.bank.Repos.Entity.User;
import com.fawry.bank.Repos.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadUserDate(UserRepo userRepo){
		return args -> {
			User user =new User();
			user.setUser_name("mohamed");
			user.setPassword("123456");
			user.setRole("Admin");
			user.setEmail("m.shalash0@gmail.com");

			User user1 =new User();
			user1.setUser_name("tawfeek");
			user1.setPassword("password");
			user1.setRole("User");
			user1.setEmail("t.shalash0@gmail.com");

			User user2 =new User();
			user2.setUser_name("basmalla");
			user2.setPassword("123456");
			user2.setRole("Maneger");
			user2.setEmail("b.shalash0@gmail.com");

			User user3 =new User();
			user3.setUser_name("m1231");
			user3.setPassword("password");
			user3.setRole("User");
			user3.setEmail("mm.shalash0@gmail.com");

			User user4 =new User();
			user4.setUser_name("test");
			user4.setPassword("test1");
			user4.setRole("Maneger");
			user4.setEmail("tt.test@gmail.com");

			userRepo.save(user);
			userRepo.save(user1);
			userRepo.save(user2);
			userRepo.save(user3);
			userRepo.save(user4);

		};
	}

	@Bean
	public CommandLineRunner loadAccountDate(AccountRepo accountRepo,UserRepo userRepo){
		return args -> {

			User user =new User();
			user.setUser_name("mohamed");
			user.setPassword("123456");
			user.setRole("User");
			user.setEmail("m2.shalash0@gmail.com");
			userRepo.save(user);

			Account account =new Account();
			account.setAddress(new Address("eg","behira","k.h","nasser","b8"));
			account.setUser(user);
			account.setDeposit(5000);
			account.setCardNumber("123456");
			account.setPassword("123");
			account.setPhoneNumber("010955");

			accountRepo.save(account);

		};
	}
}
