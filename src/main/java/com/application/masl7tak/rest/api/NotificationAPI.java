package com.application.masl7tak.rest.api;


import com.application.masl7tak.dto.NotificationDTO;
import com.application.masl7tak.model.Notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping( path = "/api/")
public interface NotificationAPI {
    @GetMapping("user/notification")
    public ResponseEntity<List<NotificationDTO> > findAll(@RequestParam  Long UserId);

    @GetMapping("user/notification/{id}")
    public ResponseEntity<Notification> findById(@PathVariable Long id);

    @PostMapping("admin/notification")
    public ResponseEntity<Notification>  save(@RequestBody Notification notification);
    @PostMapping("admin/notification/all")
    public ResponseEntity<Object>  create(@RequestBody Notification notification);
    @PostMapping("admin/notification/all_business")
    public ResponseEntity<Object>  createBusiness(@RequestBody Notification notification);

    @PutMapping("admin/notification/{id}")
    public ResponseEntity<Notification>  update(@RequestBody Notification notification, @PathVariable Long id);

    @DeleteMapping("admin/notification/{id}")
    public void deleteById(@PathVariable Long id);


}
