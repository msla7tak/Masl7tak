package com.application.masl7tak.rest.api;


import com.application.masl7tak.dto.RegionDTO;
import com.application.masl7tak.dto.SuccessDTO;
import com.application.masl7tak.model.Faq;
import com.application.masl7tak.model.Region;
import jakarta.persistence.Column;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequestMapping( path = "/api/")
public interface FaqAPI {
    @GetMapping("public/faq")
    public ResponseEntity<Object> findAll();
    @GetMapping("public/faq/support")
    public ResponseEntity<Object> findAllSupport();

    @GetMapping("public/faq/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id);

    @PostMapping("user/faq")
    public ResponseEntity<Object>  save(@ModelAttribute Faq faq);
    @PostMapping("user/points")
    public ResponseEntity<Object>  points(@RequestParam Long user_id);
    @PostMapping("user/faq/support")

    public ResponseEntity<Object> support(@RequestParam(name = "name", required = false) String name,
                                                    @RequestParam(name = "email", required = false) String email,
                                                    @RequestParam(name = "question_en", required = false) String question_en,
                                                    @RequestParam(name = "status", required = false) int status);


    @PutMapping("user/faq/{id}")
    public ResponseEntity<Object>  update(@RequestBody Faq faq, @PathVariable Long id);

    @DeleteMapping("admin/faq/{id}")
    public void deleteById(@PathVariable Long id);


}
