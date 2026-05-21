package org.process.processorchestratormvn.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eximeebpms.bpm.engine.TaskService;
import org.eximeebpms.bpm.engine.delegate.DelegateTask;
import org.eximeebpms.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
class UserTaskListener implements TaskListener {

    private final TaskService taskService;

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("Listing eventName={} for id={}", delegateTask.getEventName(), delegateTask.getProcessInstanceId());
        taskService.complete(delegateTask.getId(), Map.of("rejected", true));
    }
}
