package com.ugh.ugh.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "GenreTable")
@Entity
public class Genre {

	@Setter(value = AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GenreId")
	private long genreId;
	
	@NotNull
	@NotEmpty
	@Column(name = "Name")
	private String name;
	
	@ManyToMany(mappedBy = "genres")
	@ToString.Exclude
	private Collection<Game> games = new ArrayList<>();
	
	public Genre(String name) {
		setName(name);
	}
}
