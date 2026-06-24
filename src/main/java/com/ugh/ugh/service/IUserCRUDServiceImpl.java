package com.ugh.ugh.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ugh.ugh.repo.IUserRepo;


@Service
public class IUserCRUDServiceImpl implements UserDetailsService{
    private final IUserRepo iUserRepo;


    public IUserCRUDServiceImpl(IUserRepo iUserRepo) {
        this.iUserRepo = iUserRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iUserRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
