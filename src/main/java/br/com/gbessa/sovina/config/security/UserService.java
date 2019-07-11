package br.com.gbessa.sovina.config.security;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.gbessa.sovina.models.User;

public class UserService {

    public static User authenticated() {
		@SuppressWarnings("unchecked")
		Optional<User> user = (Optional<User>) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
	    return user.orElse(null); 	    
    }
	
}
