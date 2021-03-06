package be.odisee.travelbase.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "GEBRUIKERS")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE,force=true)
public class Gebruiker implements UserDetails{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    private final String naam;
    private final String rol;

    @Column(unique = true)
    private final String gebruikersnaam;

    private final String wachtwoord;

    @OneToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private EvaluatieFiche dummyEvaluatieFiche;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(rol));
    }

    @Override
    public String getPassword() {
        return wachtwoord;
    }
    @Override
    public String getUsername() {
        return gebruikersnaam;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}