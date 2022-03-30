package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
    ArrayList<Notification> findNotificationByUserId(Long userId);
    void deleteById(Long id);
    void deleteByListing(Listing listing);

}
