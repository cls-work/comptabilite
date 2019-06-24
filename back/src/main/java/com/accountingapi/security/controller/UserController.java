package com.accountingapi.security.controller;

import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.dto.UserUpdateDto;
import com.accountingapi.security.exception.AppException;
import com.accountingapi.security.model.Role;
import com.accountingapi.security.model.User;
import com.accountingapi.security.repository.RoleRepository;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.service.HistoricalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
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

    @GetMapping("")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long id){
        return userRepository.getById(id);
    }

    @DeleteMapping("/{userId}")
    @Transactional
    public void deleteUserById (@PathVariable("userId") Long userId){
        userRepository.deleteById(userId);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{userId}")
    public User editUser(@CurrentUser UserPrincipal currentUser, @PathVariable Long userId, @Valid @RequestBody UserUpdateDto userUpdateDto){

        User oldUser = userRepository.getById(userId);
        oldUser.setUsername(userUpdateDto.getUsername());
        oldUser.setName(userUpdateDto.getName());
        oldUser.setEmail(userUpdateDto.getEmail());
        oldUser.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        Role userRole = roleRepository.findById(userUpdateDto.getId())
                .orElseThrow(() -> new AppException("User Role not set."));

        oldUser.setRoles(Collections.singleton(userRole));

        return userRepository.save(oldUser);
    }


    @GetMapping("/roles")

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
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