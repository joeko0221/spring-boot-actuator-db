package me.joe.conf.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity(name = "t_spring_boot_actuator_db_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
public class UserInfo implements UserDetails {

  private static final long serialVersionUID = 5713799116834608239L;

  @Id
  @Column(name = "user_id", nullable = false)
  private String userId;

  @Column(name = "pwd", nullable = false)
  private String pwd;

  @Column(name = "role", nullable = false)
  private String role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    // authorities.add(new SimpleGrantedAuthority("ROLE_user"));

    // 這樣不行，一定要 ROLE_ 開頭
    // authorities.add(new SimpleGrantedAuthority(this.role));

    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role));

    return authorities;
  }

  @Override
  public String getPassword() {
    return this.pwd;
  }

  @Override
  public String getUsername() {
    return this.userId()
        .toString();
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
