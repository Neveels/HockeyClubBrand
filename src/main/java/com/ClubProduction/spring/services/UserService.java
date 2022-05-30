package com.ClubProduction.spring.services;

import com.ClubProduction.spring.models.Role;
import com.ClubProduction.spring.models.User;
import com.ClubProduction.spring.payload.request.LoginRequest;
import com.ClubProduction.spring.payload.request.SignupRequest;
import com.ClubProduction.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
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

    public boolean checkActive(LoginRequest loginRequest){
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        if(!user.get().getActive()) return false;
        else return true;
    }

    public void addUser(SignupRequest signUpRequest){
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhoneNumber());

        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        //Generate activation code
        user.setActivateCode(UUID.randomUUID().toString());
        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello %s! \n" +
                            "Welcome to Dinamo-Minsk. Please, visit next link: http://localhost:8086/activate/%s",
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
        return true;
    }

    public boolean checkActivatedCode(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        if(user.get().getActivateCode() != null){
            return true;
        }
        else return false;
    }

    public void changeRole(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user.isAdmin()){
            user.getRoles().clear();
            user.getRoles().add(Role.ROLE_USER);
        }else{
            user.getRoles().clear();
            user.getRoles().add(Role.ROLE_ADMIN);
        }

        userRepository.save(user);
//        if(user.get().isAdmin()){
//            user.get().setRoles(Collections.singleton(ERole.ROLE_USER));
//        }else user.get().setRoles(Collections.singleton(ERole.ROLE_ADMIN));
//        userRepository.save();
    }
}
