package com.ugh.ugh.model;

import com.ugh.ugh.enums.GameStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "UserGameTable")
@Entity
public class UserGame {
	
	@Setter(value = AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UserGameId")
	private long userGameId;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "GameStatus")
	private GameStatus gameStatus;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "GameId")
	private Game game;
	
	public UserGame(GameStatus gameStatus, User user, Game game) {
		setGameStatus(gameStatus);
		setUser(user);
		setGame(game);
	}
}