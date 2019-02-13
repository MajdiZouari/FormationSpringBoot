package com.project.projetdatauser.services;

import com.project.projetdatauser.model.User;
import com.project.projetdatauser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String SHA256PwdEncoding(String pwd){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pwd.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void createUser(User user){
        String encodedPwdSha256 = SHA256PwdEncoding(user.getPwd());
        user.setPwd(encodedPwdSha256);
        userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.deleteById(user.getId());
        userRepository.save(user);
    }

    public User getUserById(String id){
        return userRepository.findOneById(id);
    }

    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }

    public User authentificate(String login, String pwd){
        return userRepository.findByLoginAndPwd(login,SHA256PwdEncoding(pwd));
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}
