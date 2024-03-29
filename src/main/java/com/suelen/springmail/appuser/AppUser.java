package com.suelen.springmail.appuser;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@Entity
public class AppUser implements UserDetails {
	private static final long serialVersionUID = 1L;

	
	
	
	/*veja discoveryJPA.txt -> pasta relembrar */
	@Id()
	@SequenceGenerator(
	            name = "student_sequence",
	            sequenceName = "student_sequence",
	            allocationSize = 1
	    )
	/*
	 * em discoveryJPA.txt -> pasta relembrar
	 * pesquisar generator = "student_sequence" e  GenerationType.SEQUENCE, + SequenceGenerate Inteiro */
	@GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
    /*Digo que o tipo enumetdo é String*/ 
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;
	private Boolean locked = false;
	private Boolean enabled = false;

	
	
	
	public AppUser(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		  SimpleGrantedAuthority authority =
				  /*appUserRole.name() -> retorna o nome de como as constantes foram declarado no enum*/
	                new SimpleGrantedAuthority(appUserRole.name());
		  /*Collections.singletonList(authority) -> retorna apenas uma lista de authority*/
	        return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
