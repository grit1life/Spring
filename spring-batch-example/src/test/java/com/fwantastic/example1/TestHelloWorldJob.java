package com.fwantastic.example1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/com/fwantastic/example1/hello_word_job.xml"})
public class TestHelloWorldJob {
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	public void testJob() throws Exception{
		final JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		//job�� ���������� �������� Ȯ���Ѵ�.
		Assert.assertEquals(ExitStatus.COMPLETED.getExitCode(), jobExecution.getExitStatus().getExitCode());
	}

}
