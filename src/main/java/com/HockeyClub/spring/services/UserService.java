package com.HockeyClub.spring.services;

import com.HockeyClub.spring.models.ERole;
import com.HockeyClub.spring.models.User;
import com.HockeyClub.spring.payload.request.SignupRequest;
import com.HockeyClub.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    PasswordEncoder encoder;

    public void addUser(SignupRequest signUpRequest){
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhoneNumber());

        user.setActive(true);
        user.getRoles().add(ERole.ROLE_ADMIN);
        //Generate activation code
        user.setActivateCode(UUID.randomUUID().toString());
        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello %s! \n" +
                            "Welcome to Dinamo Minsk. Please, visit next link: http://localhost/8086/activate/%s",
                    user.getUsername(),
                    user.getActivateCode()
            );
            mailSender.send(user.getEmail(),"Activation code", message);
        }

        userRepository.save(user);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivateCode(code);
        if(user == null){
            return false;
        }
        //null because user confirmed email
        user.setActivateCode(null);
        userRepository.save(user);
        return false;
    }
}
