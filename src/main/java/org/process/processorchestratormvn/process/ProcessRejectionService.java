package org.process.processorchestratormvn.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eximeebpms.bpm.engine.delegate.DelegateExecution;
import org.eximeebpms.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class ProcessRejectionService implements JavaDelegate {


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Rejection servicing of id={}", execution.getProcessInstanceId());
    }
}
