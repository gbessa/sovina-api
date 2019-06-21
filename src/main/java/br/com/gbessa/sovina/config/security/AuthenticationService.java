package br.com.gbessa.sovina.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.gbessa.sovina.models.User;
import br.com.gbessa.sovina.repositories.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(username);
		if (!user.isPresent()) throw new UsernameNotFoundException("Dados Inválidos");
		return user.get(); 
	}

}
