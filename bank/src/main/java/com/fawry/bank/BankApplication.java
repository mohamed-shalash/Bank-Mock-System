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
			user.setPassword("123");
			user.setRole("Admin");

			User user1 =new User();
			user1.setUser_name("tawfeek");
			user1.setPassword("password");
			user1.setRole("USER");

			userRepo.save(user);
			userRepo.save(user1);

		};
	}

	@Bean
	public CommandLineRunner loadAccountDate(AccountRepo accountRepo,UserRepo userRepo){
		return args -> {

			User user =new User();
			user.setUser_name("mohamed");
			user.setPassword("123");
			user.setRole("Admin");
			userRepo.save(user);

			Account account =new Account();
			account.setAddress(new Address("eg","behira","k.h","nasser","b8"));
			account.setUser(user);
			account.setDeposit(5000);
			account.setCardNumber("123456");
			account.setPhoneNumber("010955");

			accountRepo.save(account);

		};
	}
}
