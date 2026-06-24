package com.ugh.ugh.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "GameTable")
@Entity
public class Game {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "GameId")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long gameId;
	
	@NotNull
	@NotEmpty
	@Column(name = "Title")
	private String title;
	
	@NotNull
	@Min(0)
	@Max(1234)
	@Column(name = "Price")
	private float price;
	
	@NotNull
	@NotEmpty
	@Column(name = "Description", length = 1000)
	private String description;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "ReleaseDate")
	private LocalDate releaseDate;
	

	@ManyToOne
	@JoinColumn(name = "DeveloperId")
	private Developer developer;
	

	@ManyToOne
	@JoinColumn(name = "PublisherId")
	private Publisher publisher;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "GameGenreTable", joinColumns = @JoinColumn(name = "GameId"), inverseJoinColumns = @JoinColumn(name = "GenreId"))
	@ToString.Exclude
	private Collection<Genre> genres = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "game")
	@ToString.Exclude
	private Collection<Review> reviews = new ArrayList<>(); 
	
	@JsonIgnore
	@OneToMany(mappedBy = "game")
	@ToString.Exclude
	private Collection<UserGame> userGames = new ArrayList<>();
	
	public Game(String title, float price, String description, LocalDate releaseDate, Developer developer, Publisher publisher, Collection<Genre> genres) {
		setTitle(title);
		setPrice(price);
		setDescription(description);
		setReleaseDate(releaseDate);
		setPublisher(publisher);
		setDeveloper(developer);
		setGenres(genres);
	}
}
