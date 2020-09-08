package com.hm2.ums.services;

import com.google.common.collect.Lists;
import com.hm2.common.beans.ResponseData;
import com.hm2.common.exceptions.InvalidRequestException;
import com.hm2.common.exceptions.ProcessException;
import com.hm2.common.persistencecustom.persistence.service.BaseService;
import com.hm2.ums.critrria.UserCriteria;
import com.hm2.ums.entities.User;
import com.hm2.ums.repositories.user.UserRepository;
import com.hm2.ums.vo.EmailVo;
import com.hm2.ums.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@org.springframework.transaction.annotation.Transactional
@Service
public class UserSerivce extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(UserSerivce.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    public UserVo save(UserVo userVo) {
        User user = userVo.getUser();
        //==> Update User
        if (user.getId() != null && user.getId() != 0) {
            Optional<User> userOptional = userRepository.findById(user.getId());
            if (userOptional.isPresent()) {
                User userUpdate = userOptional.get();
                userUpdate.setUsername(user.getUsername());
                user = userUpdate;
            }
        } else {
            //==> Create User
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setUserCode(userVo.getUser().getUserCode());
        }

        user.setAccessMenu(userVo.getUser().getAccessMenu());
        user.setTel(userVo.getUser().getTel());
        user.setNickname(userVo.getUser().getNickname());

        user = userRepository.save(user);

        userVo.setUser(user);
        return userVo;
    }

    public User findByUserId(long userId) {
        return userRepository.findById(userId).get();
    }

    public List<User> getUserAll() {
        List<User> users = userRepository.findAllByOrderByCreatedDateDesc();
        ArrayList<User> userList = new ArrayList<User>();
        if (!users.toString().isEmpty())
            userList = Lists.newArrayList(users);
        return userList;
    }

    public ResponseData<List<User>> searchUser(UserCriteria criteria) {
        return userRepository.searchUser(criteria);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        logger.info("Method deleteUser id: {} success", userId);
    }

    @Transactional
    public void changePassword(UserVo userVo) throws ProcessException {
        User user = userRepository.findByUsername(userVo.getTel());
        if (user == null)
            throw new ProcessException("Invalid User", "ไม่พบผู้ใช้งาน");

        user.setPassword(new BCryptPasswordEncoder().encode(userVo.getPassword()));
        user.setChangePasswordDate(new Date());
        user.setForgotPasswordKey(null);
        userRepository.save(user);
    }

    public boolean isUserExistsByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) return true;
        return false;
    }

    public boolean isUserExistsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) return true;
        return false;
    }

    public String generatePassword() {
        int streamSize = 11;
        int randomNumberOrigin = 33;
        int randomNumberBound = 122;
        return new Random().ints(streamSize, randomNumberOrigin, randomNumberBound).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public void forgotPassword(EmailVo vo) throws InvalidRequestException {

        //==> Add forgot password key
        String key = generatePassword();
//        User user = userRepository.findByEmail(vo.getEmail());
//        if (user != null) {
////            user.setForgotPasswordKey(key);
//            userRepository.save(user);
//        } else {
//            throw new InvalidRequestException("User not found for email!", "ไม่พบผู้ใช้งาน สำหรับอีเมลนี้");
//        }

        //==> Send Email
        SimpleMailMessage message = new SimpleMailMessage();
        String from = "developer@revotive.com";
        String to = vo.getEmail();
        String subject = "Forget Password";
        String body = "http://localhost:3000/#/changePassword?key=" + key;
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public User findForgotPasswordKey(String key) {
        return null;
//        return  userRepository.findByForgotPasswordKey(key);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String generateUserCode() {
        String prefix = "D";
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return prefix + generatedString;
    }

    public User findByUser(String username) {
        return userRepository.findByUsername(username);
    }
}
