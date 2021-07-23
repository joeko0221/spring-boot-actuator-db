package me.joe.conf.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import me.joe.conf.entity.UserInfo;
import me.joe.conf.repository.UserInfoRepository;

@Service
public class AuthService implements UserDetailsService {

  @Autowired
  private UserInfoRepository userInfoRepository;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

    // 查詢 UserInfo
    UserInfo userInfo = userInfoRepository.getByUserId(userId);

    if (userInfo == null) throw new UsernameNotFoundException("email not found");

    return userInfo;
  }

}
