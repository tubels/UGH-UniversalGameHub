package com.ugh.ugh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "ReviewTable")
@Entity
public class Review {
	
	@Setter(value = AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ReviewId")
	private long reviewId;
	
	@NotNull
	@NotEmpty
	@Column(name = "Title")
	private String title;
	
	@Min(1)
	@Max(5)
	@Column(name = "Rating")
	private int rating;
	
	@NotNull
	@NotEmpty
	@Column(name = "Description")
	private String description;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "GameId")
	private Game game;
	
	public Review(String title, int rating, String description, User user, Game game) {
		setTitle(title);
		setRating(rating);
		setDescription(description);
		setUser(user);
		setGame(game);
	}
}