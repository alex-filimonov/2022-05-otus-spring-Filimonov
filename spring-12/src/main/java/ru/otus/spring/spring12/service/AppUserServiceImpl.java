package ru.otus.spring.spring12.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.spring12.domain.AppUser;
import ru.otus.spring.spring12.repository.AppUserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AppUserServiceImpl implements UserDetailsService {
    private AppUserRepository appUserRepository;
    public AppUserServiceImpl(AppUserRepository appUserRepository){
        this.appUserRepository=appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=appUserRepository.findByLogin(username).orElseThrow(()->new UsernameNotFoundException("Login "+username+" not found"));
        List<GrantedAuthority> grantList = new ArrayList<>(Arrays.asList( new SimpleGrantedAuthority(appUser.getRole())));
        return (UserDetails) new User(appUser.getLogin(), appUser.getPassword(), grantList);
    }
}
