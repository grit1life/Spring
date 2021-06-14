package com.fwantastic.example2;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/com/fwantastic/example2/job2.xml"})
public class MyJobTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	private static AtomicBoolean isLaunched = new AtomicBoolean(false);
	
	private JobExecution jobExecution;
	
	@Before
	public void setUp() throws Exception{
		if(!isLaunched.getAndSet(true)) {
			jobExecution = jobLauncherTestUtils.launchJob();
		}
	}
	
	@Test
	public void testExitCode() {
		Assert.assertEquals(ExitStatus.COMPLETED.getExitCode(), jobExecution.getExitStatus().getExitCode());
	}
	
}
