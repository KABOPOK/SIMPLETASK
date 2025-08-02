package com.example.demo.services;

import com.example.demo.entities.SosForm;
import com.example.demo.repositories.SosFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SosFormService {
    private final SosFormRepository sosFormRepository;

    public void save(SosForm sosForm) {
        Optional<SosForm> existingForm = sosFormRepository.findByUserId(sosForm.getUserId());
        if (existingForm.isPresent()) {
            SosForm existing = existingForm.get();
            existing.setComment(sosForm.getComment());
            existing.setAddress(sosForm.getAddress());
            existing.setDateTime(sosForm.getDateTime());
            existing.setTelegramContact(sosForm.getTelegramContact());
            existing.setDeactivationCode(sosForm.getDeactivationCode());
            sosFormRepository.save(existing);
        } else {
            sosFormRepository.save(sosForm);
        }
    }
}
