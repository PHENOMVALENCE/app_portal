package valence.com.portal.service;

import valence.com.portal.model.Application;

public interface ApplicationService {
    Application findById(Integer id);
    Application save(Application application);
    void deleteById(Integer id);
    Application update(Application application);
}
