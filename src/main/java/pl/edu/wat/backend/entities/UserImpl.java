package pl.edu.wat.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@NoArgsConstructor
public class UserImpl implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "myUser", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<FiszkaSet> myFiszkaSets = new HashSet<>();

    public UserImpl(String username,
                    String email,
                    String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserImpl{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void addFiszkaSet(FiszkaSet fiszkaSet) {
        myFiszkaSets.add(fiszkaSet);
        fiszkaSet.setMyUser(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserImpl userImpl = (UserImpl) o;
        return Objects.equals(id, userImpl.id);
    }
}
