package org.process.processorchestratormvn.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eximeebpms.bpm.engine.RuntimeService;
import org.eximeebpms.bpm.engine.TaskService;
import org.eximeebpms.bpm.engine.runtime.ProcessInstance;
import org.eximeebpms.bpm.engine.task.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
class StartProcessController {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    @PostMapping("/start")
    public String start() {

        ProcessInstance instance =
                runtimeService.startProcessInstanceByKey(
                        "process-1", "businessKey", Map.of("cifKi", "5443111123")
                );

        log.info("Starting process {}", instance.getProcessInstanceId());
        return instance.getProcessInstanceId();
    }


    record FormResultDto(String processInstanceId, boolean rejected) {
    }

    @PostMapping("/form-result")
    public void submit(
            @RequestBody FormResultDto body) {
        log.info("Getting company-data form result for {}", body.processInstanceId());
        Task task = taskService.createTaskQuery()
                .processInstanceId(body.processInstanceId())
                .singleResult();
        taskService.complete(task.getId(), Map.of("rejected", body.rejected()));
//        runtimeService.createMessageCorrelation("FORM_COMPLETED")
//                .processInstanceId(body.processInstanceId())
//                .setVariable("rejected", body.rejected())
//                .correlate();
    }
}
