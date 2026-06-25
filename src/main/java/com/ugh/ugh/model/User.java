package com.ugh.ugh.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
@Table(name = "UserTable")
@Entity
public class User implements UserDetails {

	@Setter(value = AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UserId")
	private long userId;
	
	@NotNull
	@NotEmpty
	@Column(name = "Username")
	private String username;

	@NotNull
	@NotEmpty
	@Column(name = "Password")
	private String password;
	
	@NotNull
	@NotEmpty
	@Email
	@Column(name = "Email")
	private String email;
	
	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private Collection<Review> reviews = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private Collection<UserGame> userGames = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
	
	public User(String username, String email, String password) {
		setUsername(username);
		setEmail(email);
		setPassword(password);
	}
	
	public User(String username, String password) {
		setUsername(username);
		setEmail(null);
		setPassword(password);
	}
}