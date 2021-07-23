package me.joe.conf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.joe.conf.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

  UserInfo getByUserId(String userId);

}
