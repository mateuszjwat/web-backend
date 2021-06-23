package pl.edu.wat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.backend.JWT.JwtUtils;
import pl.edu.wat.backend.dtos.JwtResponse;
import pl.edu.wat.backend.dtos.LoginForm;
import pl.edu.wat.backend.dtos.SignUpForm;
import pl.edu.wat.backend.entities.UserImpl;
import pl.edu.wat.backend.services.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping(path = "api/auth")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    public static UserImpl me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserImpl) authentication.getPrincipal();
    }

    @PostMapping("/signInTest")
    public ResponseEntity<?> signInTest(HttpServletResponse response) throws Exception {
        //test token
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("Mariam", "123"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginForm loginRequest) {

        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpForm signUpRequest) {
        // Create new userImpl's account

        UserImpl userImpl = new UserImpl(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        try {
            userService.addNewUser(userImpl);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body("lol");
        }

        return ResponseEntity.ok("UserImpl registered successfully!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserImpl userImpl = (UserImpl) authentication.getPrincipal();
        userService.deleteStudent(userImpl.getId());

        return ResponseEntity.ok("UserImpl deleted successfully!");
    }
}
