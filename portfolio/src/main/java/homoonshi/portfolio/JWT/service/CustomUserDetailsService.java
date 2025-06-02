package homoonshi.portfolio.JWT.service;

import homoonshi.portfolio.JWT.entity.CustomUserDetails;
import homoonshi.portfolio.JWT.entity.UserEntity;
import homoonshi.portfolio.JWT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userData = userRepository.findByUsername(username);

        if(userData!=null){
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
