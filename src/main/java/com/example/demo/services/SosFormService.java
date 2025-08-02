package com.example.demo.services;

import com.example.demo.entities.SosForm;
import com.example.demo.repositories.SosFormRepository;
import com.example.demo.respomces.ActivationTooSoonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SosFormService {
    private final SosFormRepository sosFormRepository;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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

    public void deactivate(UUID userId, String code) {
        Optional<SosForm> existingForm = sosFormRepository.findByUserId(userId);
        existingForm.ifPresent(existing -> existing.setIsReady(false));
    }

    public void activate(UUID userId, Instant activateAt) {
        Optional<SosForm> existingForm = sosFormRepository.findByUserId(userId);
        if (existingForm.isPresent()){
            SosForm form = existingForm.get();
            Instant lastActivation = form.getLastActivation();
            if ((lastActivation == null) ||
                    lastActivation.isBefore(Instant.now().minus(24, ChronoUnit.HOURS))) {
                long delayMillis = Duration.between(Instant.now(), activateAt).toMillis();
                existingForm.get().setIsReady(true);
                sosFormRepository.save(existingForm.get());
                // Отложенное выполнение. Подразумеваю, что с фронта может прилететь время,
                // которое даёт разницу как минимум 1 минута
                scheduler.schedule(() -> {
                    Optional<SosForm> existingForm_ = sosFormRepository.findByUserId(userId);
                    if(existingForm_.isPresent()){
                        if(existingForm_.get().getIsReady()){
                            // TODO отправка сообщения человеку
                            System.out.println("activated");
                            existingForm_.get().setIsReady(false);
                            existingForm_.get().setLastActivation(Instant.now());
                            sosFormRepository.save(existingForm_.get());
                        }
                    }
                }, delayMillis, TimeUnit.MILLISECONDS);
            } else {
                throw new ActivationTooSoonException("Форма может быть активирована только один раз в 24 часа.");
            }
        }
        else {
            throw new ActivationTooSoonException("нет заполнена форма ..");
        }
    }
}
