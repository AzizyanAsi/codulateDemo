package com.example.demo.service.impl;

import com.example.demo.converter.PreferenceConverter;
import com.example.demo.converter.dataTransferObjects.PreferenceRequest;
import com.example.demo.domain.User;
import com.example.demo.domain.UserPreferences;
import com.example.demo.exception.PreferencesNotFoundException;
import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PreferenceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PreferenceServiceImpl implements PreferenceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreferenceServiceImpl.class);

    private final PreferenceRepository preferenceRepository;
    private final PreferenceConverter preferenceConverter;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserPreferences create(PreferenceRequest request, Long userId) {
        LOGGER.debug("Requested to create preferences for user : {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Not found user by this id: {}", userId));
        UserPreferences converted = preferenceConverter.convertToEntity(request);
        converted.setUser(user);
        preferenceRepository.save(converted);
        userRepository.save(user);
        return preferenceRepository.save(converted);
    }

    @Override
    @Transactional
    public UserPreferences update(Long id, PreferenceRequest request) {

        LOGGER.info("Requested to update a preferences with id {}", id);
        final UserPreferences pref = this.preferenceRepository.findById(id)
                .orElseThrow(() -> new PreferencesNotFoundException("No preferences found by this id {}", id));
        pref.setIsEmailReceived(request.getIsEmailReceived());
        pref.setIsSmsReceived(request.getIsSmsReceived());
        return preferenceRepository.save(pref);
    }

    @Override
    @Transactional(readOnly = true)
    public UserPreferences findById(Long id) {
        LOGGER.debug("In findById requested to get the preferences with  id: {}", id);
        return this.preferenceRepository.findById(id)
                .orElseThrow(() -> new PreferencesNotFoundException("Not found preferences by this id: {}", id));
    }

    @Override
    public boolean deleteById(Long id) {
        LOGGER.info("Requested to delete a role with id {}", id);
        if (!preferenceRepository.existsById(id)) {
            throw new RoleNotFoundException("No role found by this id", id);
        }
        preferenceRepository.deleteById(id);
        LOGGER.info(" Preferences with id {} successfully deleted", id);
        return true;
    }
}
