package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.NotificationRepository;
import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Transactional
    public ArrayList<Notification> getNotifications(Long userId) throws IllegalArgumentException {
        //verify that user exists is valid
        if (userAccountRepository.findUserAccountById(userId) == null) throw new IllegalArgumentException("Invalid userId");

        return notificationRepository.findNotificationByUserId(userId);
    }
}
