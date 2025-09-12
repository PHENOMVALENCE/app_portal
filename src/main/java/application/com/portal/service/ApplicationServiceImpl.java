package application.com.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import application.com.portal.model.Application;
import application.com.portal.model.Student;
import application.com.portal.repository.ApplicationRepository;

public class ApplicationServiceImpl implements ApplicationService {
     @Autowired
	private ApplicationRepository applicationRepository;
     
	@Override
	public Application apply(Application application, Student student) {
	     try {
	            application.setStudent(student);
	            application.setStatus("PENDING");
	            return applicationRepository.save(application);
	        } catch (Exception e) {
	            throw new RuntimeException("Error applying for field: " + e.getMessage());
	        }
	    }

	@Override
	public List<Application> getApplicationsByStudent(Student student) {
		try {
			return applicationRepository.findByStudent(student);
	} catch (Exception e) {
		throw new RuntimeException("Cant find student: " + e.getMessage());
	}
	}
	
	
	@Override
	public List<Application> getAllApplications() {
	     try {
	            return applicationRepository.findAll();
	        } catch (Exception e) {
	            throw new RuntimeException("Error retrieving all applications: " + e.getMessage());
	        }
	    }

	@Override
	public Application updateStatus(Long id, String status) {
		   try {
	            Application app = applicationRepository.findById(id)
	                    .orElseThrow(() -> new RuntimeException("Application not found"));
	            app.setStatus(status);
	            return applicationRepository.save(app);
	        } catch (Exception e) {
	            throw new RuntimeException("Error updating application status: " + e.getMessage());
	        }
	    }

}
