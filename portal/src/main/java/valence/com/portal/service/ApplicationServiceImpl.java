package valence.com.portal.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import valence.com.portal.model.Application;
import valence.com.portal.repository.ApplicationRepository;

@Service
@Data
public class ApplicationServiceImpl  implements ApplicationService {
private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application findById(Integer id) {
        return applicationRepository.findById(id).orElse(null);
    }

    @Override
    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public void deleteById(Integer id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public Application update(Application application) {
        return applicationRepository.save(application);
    }
}
