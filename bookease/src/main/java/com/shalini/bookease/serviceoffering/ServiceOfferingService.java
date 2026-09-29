package com.shalini.bookease.serviceoffering;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServiceOfferingService {

    private final ServiceOfferingRepository repository;

    public ServiceOfferingService(ServiceOfferingRepository repository) {
        this.repository = repository;
    }

    public ServiceResponse create(ServiceRequest request) {
        ServiceOffering entity = new ServiceOffering(
                request.name(), request.durationMinutes(), request.price());
        ServiceOffering saved = repository.save(entity);
        return toResponse(saved);
    }

    public List<ServiceResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ServiceResponse findById(Long id) {
        ServiceOffering entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Service not found"));
        return toResponse(entity);
    }

    public ServiceResponse update(Long id, ServiceRequest request) {
        ServiceOffering entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Service not found"));
        entity.update(request.name(), request.durationMinutes(), request.price());
        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found");
        }
        repository.deleteById(id);
    }

    private ServiceResponse toResponse(ServiceOffering s) {
        return new ServiceResponse(s.getId(), s.getName(), s.getDurationMinutes(), s.getPrice());
    }
}