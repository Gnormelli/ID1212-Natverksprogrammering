package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.DataAccess.ProfilePictureRepository;
import com.ID1212.ID1212_Project_Intelij.DataAccess.RoleRepository;
import com.ID1212.ID1212_Project_Intelij.DataAccess.UserRepository;
import com.ID1212.ID1212_Project_Intelij.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    // Instead of contructor userRepository is autowired
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfilePictureRepository profilePictureRepository;

    @Autowired
    RoleRepository roleRepository;

    /**
     * Service to handle requests for finding usernames
     * @param username simple string
     * @return User object extended with userDetails from repository
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Username not found");
        }
        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(user.getUserRole().getRoleName());
        authority.add(simpleGrantedAuthority);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authority);
    }

    public Object createUser(User user){

        Optional<User> fromDatabase = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));

        if(fromDatabase.isPresent()){
                return null;

        }else
            user.setUserRole(roleRepository.findRoleById(0L));
            userRepository.save(user);
            User userWithID = userRepository.findByUsername(user.getUsername());
            return userWithID;
    }


    public String updateUserProfilePicture(String username, Long profilePictureId){
        User user = userRepository.findByUsername(username);
        user.setProfilePicture(profilePictureRepository.findProfilePictureById(profilePictureId));
        userRepository.save(user);
        return "Done";
    }

    public User canWeLogIn(String username, String password) {

        Optional<User> fromDatabase = Optional.ofNullable(userRepository.findByUsername(username));
        if(fromDatabase.isPresent()){
            User usr = userRepository.findByUsername(username);

            if(usr.getPassword().equals(password)){
                return usr;
            }else{
                return null;
            }


        }else
            return null;
    }

    public boolean DoesUserExsist(String username) {

        Optional<User> fromDatabase = Optional.ofNullable(userRepository.findByUsername(username));
        if(fromDatabase.isPresent()){
           return true;

        }else
            return false;
    }
}
