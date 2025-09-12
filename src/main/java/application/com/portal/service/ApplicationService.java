package application.com.portal.service;

import java.util.List;

import application.com.portal.model.Application;
import application.com.portal.model.Student;

public interface ApplicationService {
	
	public Application apply(Application application, Student student);
	public List<Application> getApplicationsByStudent(Student student);
	public List<Application> getAllApplications();
	 public Application updateStatus(Long id, String status);

}
