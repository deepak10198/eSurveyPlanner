/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp.service;

import com.esp.dao.DAO;
import com.esp.entity.UserMaster;
import com.esp.entity.UserRoles;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rakesh.K
 */
@Service(value = "CustomUserDetailsService")
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("UserMasterDAO")
    DAO userMasterDAO;

    @Autowired
    @Qualifier("UserRolesDAO")
    DAO userRolesDAO;

    @Autowired
    @Qualifier("UserRolesService")
    GenericService userRolesService;

    @Autowired
    @Qualifier("UserMasterService")
    GenericService userMasterService;

    static final Logger logger = Logger.getLogger(CustomUserDetailsService.class.getName());

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        logger.info(">>loadUserByUsername method start");
        logger.info(">>requested user :" + login);

        UserMaster domainUser = (UserMaster) userMasterDAO.find(UserMaster.class, "loginId", login);
        logger.info("domain user login id :" + domainUser.getLoginId());
        logger.info("domain User Id :" + domainUser.getId());
        UserRoles userRoles = (UserRoles) userRolesDAO.find(UserRoles.class, "userId.id", domainUser.getId());
        logger.info("user roles role id :" + userRoles.getRoleId().getId());

        if (domainUser == null) {
            logger.info("domain user null");
            return null;
        }

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        User user = new User(
                domainUser.getLoginId(),
                domainUser.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(userRoles.getRoleId().getId())
        );

        if (user != null) {

            return user;
        }
        return null;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(BigDecimal role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }

    public List<String> getRoles(BigDecimal role) {

        List<String> roles = new ArrayList<String>();

        if (role.intValue() == 1) {
            roles.add("ROLE_GUEST");
            roles.add("ROLE_ADMIN");
            logger.info("User Role is  :ROLE_ADMIN");
        } else if (role.intValue() == 2) {
            roles.add("ROLE_GUEST");
            logger.info("User role is :ROLE_GUEST");
        }
        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
