package com.example.demo.converter.impl;

import com.example.demo.converter.PreferenceConverter;
import com.example.demo.converter.dataTransferObjects.PreferenceRequest;
import com.example.demo.converter.dataTransferObjects.PreferenceResponse;
import com.example.demo.domain.UserPreferences;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PreferenceConverterImpl implements PreferenceConverter {


    @Override
    public UserPreferences convertToEntity(PreferenceRequest request) {
        if (request == null) {
            return null;
        }
        UserPreferences entity = new UserPreferences();
        entity.setIsEmailReceived(request.getIsEmailReceived());
        entity.setIsSmsReceived(request.getIsSmsReceived());
        return entity;
    }

    @Override
    public PreferenceResponse convertToDTO(UserPreferences preferences) {
        PreferenceResponse resp = new PreferenceResponse();
        resp.setId(preferences.getId());
        resp.setIsEmailReceived(preferences.getIsEmailReceived());
        resp.setIsSmsReceived(preferences.getIsSmsReceived());
        return resp;
    }

    @Override
    public List<PreferenceResponse> bulkConvertToDTO(List<UserPreferences> preferences) {
        return preferences.stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserPreferences> bulkConvertToEntity(List<PreferenceRequest> request) {
        return request.stream().map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
