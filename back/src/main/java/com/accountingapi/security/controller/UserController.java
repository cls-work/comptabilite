package com.accountingapi.security.controller;

import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.dto.LangEditDto;
import com.accountingapi.security.exception.AppException;
import com.accountingapi.security.model.Role;
import com.accountingapi.security.model.User;
import com.accountingapi.security.payload.ApiResponse;
import com.accountingapi.security.payload.SignUpRequest;
import com.accountingapi.security.repository.RoleRepository;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.service.HistoricalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private HistoricalService historicalService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

       @PreAuthorize("hasRole('ROLE_ADMIN')")
     @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long id){
        return userRepository.getById(id);
    }

        @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userId}")
    @Transactional
    public void deleteUserById (@PathVariable("userId") Long userId){
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<?> editUser(@CurrentUser UserPrincipal currentUser, @PathVariable Long userId, @Valid @RequestBody SignUpRequest signUpRequest){

        User oldUser = userRepository.getOne(userId);
        if(oldUser==null){
            return new ResponseEntity(new ApiResponse(false, "User not found !"),
                    HttpStatus.BAD_REQUEST);
        }

        oldUser.setUsername(signUpRequest.getUsername());
        oldUser.setName(signUpRequest.getName());
        oldUser.setEmail(signUpRequest.getEmail());
        oldUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Role userRole = roleRepository.findById(Long.parseLong(signUpRequest.getRoleId()))
                .orElseThrow(() -> new AppException("User Role not set."));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        oldUser.setRoles(userRoles);

        userRepository.save(oldUser);

        return new ResponseEntity(new ApiResponse(true, "lang successfully saved. "),
                HttpStatus.ACCEPTED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/lang/{userId}")
    public ResponseEntity<?> editLang(@PathVariable Long userId,@Valid @RequestBody LangEditDto langEditDto){

        User oldUser = userRepository.getOne(userId);
        if(oldUser==null){
            return new ResponseEntity(new ApiResponse(false, "User not found !"),
                    HttpStatus.BAD_REQUEST);
        }
        oldUser.setLang(langEditDto.getLang());

        userRepository.save(oldUser);
        return new ResponseEntity(new ApiResponse(true, "lang successfully saved. "),
                HttpStatus.ACCEPTED);

    }


  /* @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }*/

    /*@GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long pollCount = pollRepository.countByCreatedBy(user.getId());
        long voteCount = voteRepository.countByUserId(user.getId());

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), pollCount, voteCount);

        return userProfile;
    }*/

    /*@GetMapping("/users/{username}/polls")
    public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
                                                         @CurrentUser UserPrincipal currentUser,
                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsCreatedBy(username, currentUser, page, size);
    }*/


   /* @GetMapping("/users/{username}/votes")
    public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
                                                       @CurrentUser UserPrincipal currentUser,
                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsVotedBy(username, currentUser, page, size);
    }*/

}