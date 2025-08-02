package com.example.demo.controllers;
import com.example.demo.entities.SosForm;
import com.example.demo.mappers.SosFormMapper;
import com.example.demo.repositories.SosFormRepository;
import com.example.demo.services.SosFormService;
import generated.com.example.demo.api.FormApi;
import generated.com.example.demo.api.model.ActivateFormRequest;
import generated.com.example.demo.api.model.DeactivateFormRequest;
import generated.com.example.demo.api.model.SosFormDTO;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SosFormController implements FormApi {
    private final SosFormService sosFormService;
    private final SosFormMapper sosFormMapper;

    @Override
    public void activateForm(ActivateFormRequest activateFormRequest) {
        sosFormService.activate(activateFormRequest.getUserId(),activateFormRequest.getActivateAt());
    }

    @Override
    public void createSosForm(SosFormDTO sosFormDTO) {
        SosForm sosForm = sosFormMapper.map(sosFormDTO);
        sosFormService.save(sosForm);
    }

    @Override
    public void deactivateForm(DeactivateFormRequest deactivateFormRequest) {
        sosFormService.deactivate(deactivateFormRequest.getUserId(),deactivateFormRequest.getCode());
    }


}
