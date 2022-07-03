package com.example.demo.service;

import com.example.demo.converter.dataTransferObjects.PreferenceRequest;
import com.example.demo.domain.UserPreferences;


public interface PreferenceService {

    UserPreferences create(PreferenceRequest request, Long userId);

    UserPreferences update(Long id, PreferenceRequest request);

    UserPreferences findById(Long id);

    boolean deleteById(Long id);
}
