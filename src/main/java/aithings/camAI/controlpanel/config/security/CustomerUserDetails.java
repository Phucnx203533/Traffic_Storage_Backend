package aithings.camAI.controlpanel.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import aithings.camAI.controlpanel.dto.SubFunctionDTO;
import aithings.camAI.controlpanel.entity.SAUserEntity;
import aithings.camAI.controlpanel.utils.constant.EActiveStatus;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CustomerUserDetails implements UserDetails {
    private SAUserEntity user;
    private final List<GrantedAuthority> authorities;

    @Getter
    private List<SubFunctionDTO> subFunctions;

    public CustomerUserDetails(SAUserEntity user, List<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public CustomerUserDetails(SAUserEntity user,List<GrantedAuthority> authorities, List<SubFunctionDTO> subFunctions) {
        this.user = user;
        this.authorities = authorities;
        this.subFunctions = subFunctions;
    }

    public void setUser(SAUserEntity user) {
        this.user = user;
    }

    public SAUserEntity getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Objects.equals(this.user.getStatus(), EActiveStatus.ACTIVE.getValue());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.user.getStatus(), EActiveStatus.ACTIVE.getValue());
    }

    public String getFullName(){
        return user.getFullName();
    }

    public Date getLastLogin(){
        return user.getLastLogin();
    }
}
