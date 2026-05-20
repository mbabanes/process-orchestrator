package org.process.processorchestratormvn.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eximeebpms.bpm.engine.delegate.DelegateTask;
import org.eximeebpms.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class ProcessTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("Listing eventName={} for id={}", delegateTask.getEventName(), delegateTask.getProcessInstanceId());
    }
}
