package org.process.processorchestratormvn.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eximeebpms.bpm.engine.RuntimeService;
import org.eximeebpms.bpm.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class StartProcessController {

    private final RuntimeService runtimeService;

    @PostMapping("/start")
    public String start() {

        ProcessInstance instance =
                runtimeService.startProcessInstanceByKey(
                        "process-1"
                );
        log.info("Starting process {}", instance.getProcessInstanceId());
        return instance.getProcessInstanceId();
    }


    record FormResultDto(String processInstanceId, boolean approved) {
    }

    @PostMapping("/form-result")
    public void submit(
            @RequestBody FormResultDto body) {
        log.info("Getting company-data form result for {}", body.processInstanceId());
        runtimeService.createMessageCorrelation("FORM_COMPLETED")
                .processInstanceId(body.processInstanceId())
                .setVariable("approved", body.approved())
                .correlate();
    }
}
