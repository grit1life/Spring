package com.fwantastic.example4;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"/com/fwantastic/example4/job4.xml"})
public class MyJobTest {
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
	
	@Test
	public void testOutputFileCreated() {
		File file = new File("output/com/fwantastic/example4/output.csv");
		Assert.assertTrue(file.exists());
	}
}
