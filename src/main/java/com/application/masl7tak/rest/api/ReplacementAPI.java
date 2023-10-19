package com.application.masl7tak.rest.api;

import com.application.masl7tak.model.Replacement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
public interface ReplacementAPI {
    @GetMapping("public/replacement")
    public ResponseEntity <List<Replacement>> findReplacement();


    @GetMapping("public/replacement/{id}")
    public ResponseEntity<Replacement> findById(@PathVariable Long id);

    @PostMapping("admin/replacement")
    public ResponseEntity<Object> save(@RequestBody Replacement replacement);

    @PutMapping("admin/replacement/{id}")
    public ResponseEntity<Object> update(@RequestBody Replacement replacement, @PathVariable Long id);

    @DeleteMapping("admin/replacement/{id}")
    public void deleteById(@PathVariable Long id);
}
