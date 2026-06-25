package com.ugh.ugh;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ugh.ugh.enums.GameStatus;
import com.ugh.ugh.model.Developer;
import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Genre;
import com.ugh.ugh.model.Publisher;
import com.ugh.ugh.model.Review;
import com.ugh.ugh.model.User;
import com.ugh.ugh.model.UserGame;
import com.ugh.ugh.repo.IDeveloperRepo;
import com.ugh.ugh.repo.IGameRepo;
import com.ugh.ugh.repo.IGenreRepo;
import com.ugh.ugh.repo.IPublisherRepo;
import com.ugh.ugh.repo.IReviewRepo;
import com.ugh.ugh.repo.IUserGameRepo;
import com.ugh.ugh.repo.IUserRepo;


@SpringBootApplication
public class UghApplication {

	public static void main(String[] args) {
		SpringApplication.run(UghApplication.class, args);
	}

	@Bean
	public CommandLineRunner testRepo(IGenreRepo genreRepo, IDeveloperRepo devRepo, IGameRepo gameRepo, IPublisherRepo pubRepo, IReviewRepo revRepo,
											IUserGameRepo userGameRepo, IUserRepo userRepo) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				
// ================================= GENRE =====================================				
				
				Genre action = new Genre("Action");
				Genre fps = new Genre("FPS");
				Genre coop = new Genre("Co-op");
				Genre multiplayer = new Genre("Multiplayer");
				Genre pve = new Genre("PvE");
				Genre thirdShooter = new Genre("Third-Person Shooter");
				Genre shooter = new Genre("Shooter");
				genreRepo.saveAll(Arrays.asList(action, fps, coop, multiplayer, pve, thirdShooter, shooter));
				
// ================================= DEVELOPER =====================================					
				
				Developer StarbreezeD = new Developer("OVERKILL - a Starbreeze Studio.");
				Developer ArrowheadD = new Developer("Arrowhead Game Studios");
				devRepo.saveAll(Arrays.asList(StarbreezeD, ArrowheadD));
				
// ================================= PUBLISHER =====================================					
				
				Publisher StarbreezeP = new Publisher("Starbreeze Entertainment");
				Publisher PlayStationP = new Publisher("PlayStation Publishing LLC");
				pubRepo.saveAll(Arrays.asList(StarbreezeP, PlayStationP));
				
// ================================= GAME =====================================	
				
				Game payday2 = new Game("PAYDAY2", 9.49f, "PAYDAY 2 is an action-packed, four-player co-op shooter that once again lets gamers don the masks of the original PAYDAY crew - Dallas, Hoxton, Wolf and Chains - as they descend on Washington DC for an epic crime spree.", 
											LocalDate.of(2013, 8, 13), StarbreezeD, StarbreezeP, Arrays.asList(coop, action, fps, multiplayer));
				Game helldivers2 = new Game("HELLDIVERS 2", 39.99f, "The Galaxys Last Line of Offence. Enlist in the Helldivers and join the fight for freedom across a hostile galaxy in a fast, frantic, and ferocious third-person shooter.",
											LocalDate.of(2024, 2, 8), ArrowheadD, PlayStationP, Arrays.asList(coop, multiplayer, action, pve, thirdShooter));
				gameRepo.saveAll(Arrays.asList(payday2, helldivers2));
				
// ================================= USER =====================================	
				
				User admin = new User("admin", "admin@gmail.com", "$2a$12$ULY2vzxrX4/b7SIvlq5RE.eA6N2Tn.VfHpGYrhYlHHRN6nfR.Hn6y");
				User user1 = new User("Nerdo", "nerd@gmail.com", "$2a$12$ULY2vzxrX4/b7SIvlq5RE.eA6N2Tn.VfHpGYrhYlHHRN6nfR.Hn6y");
				User user2 = new User("Zanis Antons15", "ZanAnt@gmail.com", "$2a$12$ULY2vzxrX4/b7SIvlq5RE.eA6N2Tn.VfHpGYrhYlHHRN6nfR.Hn6y");
				userRepo.saveAll(Arrays.asList(admin, user1, user2));
				
// ================================= REVIEW =====================================	
				
				Review review1 = new Review("Extremely fun co op", 4, "Very good horde shooter but alot of paid dlc, alot of cosmetics available and playstyles. Fun game to shut off the brain to.", user1, payday2);
				Review review2 = new Review("A must try", 5, "\"Go now, and should you fall, remember this: Every day is a good day to die for Democracy!\" ~Democracy Officer", user2, helldivers2);
				revRepo.saveAll(Arrays.asList(review1, review2));
				
// ================================= USERGAME =====================================	
				
				UserGame ug1 = new UserGame(GameStatus.Playing, user1, payday2);
				UserGame ug2 = new UserGame(GameStatus.Playing, user2, helldivers2);
				UserGame ug3 = new UserGame(GameStatus.Wishlist, user2, payday2);
				userGameRepo.saveAll(Arrays.asList(ug1, ug2, ug3));
				
			}	
		};
	}
	
}
