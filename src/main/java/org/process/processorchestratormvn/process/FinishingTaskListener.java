package org.process.processorchestratormvn.process;

import lombok.extern.slf4j.Slf4j;
import org.eximeebpms.bpm.engine.delegate.DelegateExecution;
import org.eximeebpms.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class FinishingTaskListener implements ExecutionListener {


    @Override
    public void notify(DelegateExecution execution) throws Exception {
        log.info("Finishing task {}", execution.getProcessInstanceId());
    }
}
