package com.example.activiti.service.security;

import com.example.activiti.dao.ActivitiUserRepository;
import com.example.activiti.model.ActivitiUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ActivitiUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ActivitiUserRepository activitiUserRepository;

    public ActivitiUserDetailsService(ActivitiUserRepository activitiUserRepository) {
        this.activitiUserRepository = activitiUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ActivitiUser activitiUser = activitiUserRepository
                .findActivitiUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(activitiUser.getUsername(),
                activitiUser.getPassword(),
                AuthorityUtils.createAuthorityList(activitiUser.getRole()));
    }
}
