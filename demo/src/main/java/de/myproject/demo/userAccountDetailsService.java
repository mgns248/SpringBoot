package de.myproject.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class UserAccountDetailsService implements UserDetailsService {

    private UserAccountRepository userAccountRepo;

    public UserAccountDetailsService(UserAccountRepository userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userAccount = this.userAccountRepo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Cannot find user account"));
        return convertToUserDetails(userAccount);
    }

    private UserDetails convertToUserDetails(UserAccount user) {
        return User.builder()
                .username(user.getUsername())
                .password(user.getUsername())
                .authorities(user.getAuthorities())
                .build();
    }
}