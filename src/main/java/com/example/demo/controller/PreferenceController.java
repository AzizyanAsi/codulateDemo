package com.example.demo.controller;

import com.example.demo.converter.PreferenceConverter;
import com.example.demo.converter.dataTransferObjects.PreferenceRequest;
import com.example.demo.converter.dataTransferObjects.PreferenceResponse;
import com.example.demo.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/preferences")
public class PreferenceController {

    private final PreferenceService preferenceService;
    private final PreferenceConverter preferenceConverter;

    @PostMapping("/create")
    public ResponseEntity<PreferenceResponse> createUserPreferences(@RequestBody @Valid PreferenceRequest request,
                                                                    @RequestParam Long userId) {
        return ResponseEntity.ok(preferenceConverter.convertToDTO(preferenceService.create(request, userId)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<PreferenceResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid PreferenceRequest request) {
        return ResponseEntity.ok(preferenceConverter.convertToDTO(preferenceService.update(id, request)));

    }

    @GetMapping("/search/{id}")
    public ResponseEntity<PreferenceResponse> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(preferenceConverter.convertToDTO(preferenceService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(preferenceService.deleteById(id));
    }

}

