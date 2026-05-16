package org.process.processorchestratormvn.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eximeebpms.bpm.engine.delegate.DelegateExecution;
import org.eximeebpms.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateFormDelegate implements JavaDelegate {

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public void execute(DelegateExecution execution) {
        String processInstanceId =
                execution.getProcessInstanceId();
        log.info("Prepering company data form for {}", processInstanceId);
        String formUrl =
                "http://localhost:8080/company-data-form/"
                        + processInstanceId;
        execution.setVariable("formUrl", formUrl);
        sendCreateCompanyDataProcess(processInstanceId);
    }

    private void sendCreateCompanyDataProcess(String processId) {

        String url = "http://localhost:8080/company-data-process-start";

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = Map.of("processId", processId);

        HttpEntity<Map<String, String>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(
                url,
                request,
                Void.class
        );

    }
}
