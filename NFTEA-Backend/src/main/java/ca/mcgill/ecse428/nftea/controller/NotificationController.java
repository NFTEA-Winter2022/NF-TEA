package ca.mcgill.ecse428.nftea.controller;

import ca.mcgill.ecse428.nftea.dao.NotificationRepository;
import ca.mcgill.ecse428.nftea.dto.NotificationDto;
import ca.mcgill.ecse428.nftea.model.Notification;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.NotificationService;
import ca.mcgill.ecse428.nftea.utils.DtoUtils;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationsService;

    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping(value = {"/notification", "/notification/"})
    public ResponseEntity getListings(@RequestParam Long userId) {
        ArrayList<Notification> notifications = new ArrayList<Notification>();
        ArrayList<NotificationDto> notificationsDto = new ArrayList<NotificationDto>();

        try {
            notifications = notificationsService.getNotifications(userId);
            for (Notification notification : notifications) {
                notificationsDto.add(DtoUtils.convertToDto(notification));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(notificationsDto, HttpStatus.OK);
    }

    @DeleteMapping(value = {"/notification", "/notification/"})
    public ResponseEntity<String> deleteNotification(@RequestParam Long id) {
        try {
            notificationRepository.deleteById(id);
        } catch (Exception msg) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg.getMessage());
        }
        return new ResponseEntity<String>("Notification no longer exists.", HttpStatus.OK);
    }


    @PutMapping(value = {"/notification/createLISTING_SALE", "/notification/createLISTING_SALE/"})
    public ResponseEntity createNotificationLISTING_SALE(@RequestParam Long userId, @RequestParam Long tradeOfferId, @RequestParam Long listingId ) {
        Notification notification;
        try {
            notification = notificationsService.createNotificationListingSale(userId, tradeOfferId, listingId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(convertDto(notification), HttpStatus.OK);
    }

    @PutMapping(value = {"/notification/createTRADE_OFFER", "/notification/createTRADE_OFFER/"})
    public ResponseEntity createNotificationTRADE_OFFER(@RequestParam Long userId, @RequestParam Long tradeOfferId, @RequestParam Long listingId ) {
        Notification notification;
        try {
            notification = notificationsService.createNotificationTRADE_OFFER(userId, tradeOfferId, listingId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(convertDto(notification), HttpStatus.OK);
    }

    private NotificationDto convertDto(Notification notification) {
        return new NotificationDto(notification.getId(), notification.getType(), notification.getUser(), notification.getTradeOffer(), notification.getListing());
    }


}
