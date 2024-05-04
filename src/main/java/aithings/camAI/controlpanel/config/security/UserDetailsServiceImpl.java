/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aithings.camAI.controlpanel.config.security;

import aithings.camAI.controlpanel.entity.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import aithings.camAI.controlpanel.dto.SubFunctionDTO;
import aithings.camAI.controlpanel.exception.UsernameNotActiveException;
import aithings.camAI.controlpanel.repository.FunctionRepository;
import aithings.camAI.controlpanel.repository.RoleRepository;
import aithings.camAI.controlpanel.utils.constant.EActiveStatus;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final RoleRepository roleRepository;

    private final FunctionRepository functionRepository;

    private static final String ROLE_ID = "DEVICE_PROCESS";

    public UserDetailsServiceImpl(RoleRepository roleRepository, FunctionRepository functionRepository) {
        this.roleRepository = roleRepository;
        this.functionRepository = functionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String serial) throws UsernameNotFoundException, UsernameNotActiveException {

        SAUserEntity user = new SAUserEntity();
        user.setId(serial);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<String> authorities = new ArrayList<>();
        List<String> functions = new ArrayList<>();
        List<SubFunctionDTO> subFunctions = new ArrayList<>();
                SARoleEntity role = roleRepository.findSARoleByIdAndStatus(ROLE_ID, EActiveStatus.ACTIVE.getValue()).orElse(null);
                if (role != null) {
                    for (String func : role.getFunctions()) {
                        SAFunctionEntity function = functionRepository.getFunctionByCode(func);
                        if (function != null) {
                            if (!functions.contains(function.getId())) {
                                functions.add(function.getId());
                            }
                            for (SubFunctionDTO sub : function.getSubFunctions()) {
                                sub.getAuthorities().forEach(authority -> {
                                    if (func.equals(authority.getId())) {
                                        functions.add(authority.getId());
                                        if (!functions.contains(sub.getId())) {
                                            functions.add(sub.getId());
                                        }
                                        /*Add sub func*/
                                        if (!subFunctions.contains(sub)) {
                                            subFunctions.add(sub);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            authorities = functions;
            if (!authorities.isEmpty()) {
                authorities.forEach(authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority)));
            }
        return new CustomerUserDetails(user, grantedAuthorities, subFunctions);
    }

}
