package com.example.demo.converter;

import com.example.demo.converter.dataTransferObjects.PreferenceRequest;
import com.example.demo.converter.dataTransferObjects.PreferenceResponse;

import com.example.demo.domain.UserPreferences;

import java.util.List;

public interface PreferenceConverter {
    UserPreferences convertToEntity(PreferenceRequest request);

    PreferenceResponse convertToDTO(UserPreferences preferences);

    List<PreferenceResponse> bulkConvertToDTO(List<UserPreferences> preferences);

    List<UserPreferences> bulkConvertToEntity(List<PreferenceRequest> request);
}
