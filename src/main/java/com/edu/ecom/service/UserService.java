package com.edu.ecom.service;

import com.edu.ecom.dto.ResponseDto;
import com.edu.ecom.dto.user.SignInDto;
import com.edu.ecom.dto.user.SignInResponseDto;
import com.edu.ecom.dto.user.SignupDto;
import com.edu.ecom.exceptions.AuthenticationFailException;
import com.edu.ecom.exceptions.CustomException;
import com.edu.ecom.model.AuthenticationToken;
import com.edu.ecom.model.User;
import com.edu.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;


    @Transactional
    public ResponseDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail());
        if (Objects.nonNull(user)) {
            throw new CustomException("user already present");
        }
        String encryptedpassword = signupDto.getPassword();
        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user1 = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), encryptedpassword);
        userRepository.save(user1);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user1);

        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success", "user created successfully");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        User user = userRepository.findByEmail(signInDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("username or password is not valid");
        }
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword())))
                throw new AuthenticationFailException("username or password is not valid");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        AuthenticationToken token = authenticationService.getToken(user);
        if (Objects.isNull(token))
            throw new CustomException("token is not present");
        return new SignInResponseDto("success", token.getToken());


    }
}
