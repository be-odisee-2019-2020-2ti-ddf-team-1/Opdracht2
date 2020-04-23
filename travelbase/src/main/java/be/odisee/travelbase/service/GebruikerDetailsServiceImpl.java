package be.odisee.travelbase.service;

import be.odisee.travelbase.dao.GebruikerRepository;
import be.odisee.travelbase.domain.Gebruiker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Primary // if there would be any confusion with InMemory implementation, this one should be chosen
@Service
public class GebruikerDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Override
    public UserDetails loadUserByUsername(String gebruikersnaam) throws UsernameNotFoundException {
        Gebruiker gebruiker = gebruikerRepository.findByGebruikersnaam(gebruikersnaam);
        if (gebruiker != null) return gebruiker;
        throw new UsernameNotFoundException("Gebruiker "+gebruikersnaam+" is niet gevonden!");
    }

}
