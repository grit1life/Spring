package com.fwantastic.example2;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class CounterIncrementerTasklet implements Tasklet{

	private static final String MY_COUNTER_KEY = "MY.COUNTER";
	private static final int DEFAULT_VALUE = 0;
	
	private Integer counter;
	
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
		final ExecutionContext jobExecutionContext =chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
		if(counter == null) {
			counter = jobExecutionContext.getInt(MY_COUNTER_KEY, DEFAULT_VALUE);
		}
		System.out.println("д╚©Нем: "+ counter);
		
		jobExecutionContext.put(MY_COUNTER_KEY, counter+1);
		
		return RepeatStatus.FINISHED;
	}
	
	public void setCounter(final Integer counter) {
		this.counter = counter;
	}

}
