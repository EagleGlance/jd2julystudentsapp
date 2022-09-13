package com.noirix.security;

import com.noirix.domain.Role;
import com.noirix.domain.SystemRoles;
import com.noirix.domain.User;
import com.noirix.repository.jdbctemplate.RoleRepositoryInterface;
import com.noirix.repository.user.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepositoryInterface userRepository;

    private final RoleRepositoryInterface roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            /*Find user in DB*/
            Optional<User> searchResult = userRepository.findByLogin(username);

            if (searchResult.isPresent()) {
                User user = searchResult.get();

                /*We are creating Spring Security User object*/

                return new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
//                        ["ROLE_USER", "ROLE_ADMIN"]
                        AuthorityUtils.commaSeparatedStringToAuthorityList(
                                roleRepository.findRolesByUserId(user.getId())
                                        .stream()
                                        .map(Role::getRoleName)
                                        //.map(SystemRoles::name)
                                        .collect(Collectors.joining(","))
                        )
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with login '%s'.", username));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }
}
