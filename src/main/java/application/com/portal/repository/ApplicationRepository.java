package application.com.portal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import application.com.portal.model.*;

@Repository

public class ApplicationRepository extends JpaRepository<Application, Long> {
	
    List<Application> findByStudent(User student);
}
