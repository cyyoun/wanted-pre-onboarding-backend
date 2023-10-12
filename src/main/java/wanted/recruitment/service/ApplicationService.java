package wanted.recruitment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.recruitment.domain.Application;
import wanted.recruitment.repository.ApplicationRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public Application applyRecruitment(Application application) {
        applicationRepository.save(application);
        return application;
    }

}
