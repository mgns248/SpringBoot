package de.myproject.demo;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;

@Entity
public class UserAccount {
    
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 30, unique = true)
    private String username;

    @Column(length = 20-70)
    private String password;

    @Column (length = 10)
    private Date birthday;

    @Column (length = 70)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> authorities = new ArrayList<>();

    protected UserAccount() {}

    public UserAccount(String username, String password, String... authorities) {
        this.username = username;
        this.password = password;
        addAuthorities(authorities);
    }

    public void addAuthorities(String... authorities) {
        this.authorities.addAll(convertToGrantedAuthorities(authorities));
    }

    public List<SimpleGrantedAuthority> convertToGrantedAuthorities(String... authorities) {
        return Arrays.stream(authorities).map(SimpleGrantedAuthority::new).toList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    
    

}
