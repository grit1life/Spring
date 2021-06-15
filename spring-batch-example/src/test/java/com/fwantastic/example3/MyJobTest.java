package com.fwantastic.example3;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"/com/fwantastic/example3/job3.xml"})
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
	public void testPersonsCreated() {
		List<Person> createdPersons = jdbcTemplate.query("SELECT * FROM PERSON",
				new BeanPropertyRowMapper<Person>(Person.class));
		System.out.println(createdPersons);
		Assert.assertEquals(2, createdPersons.size());
	}
}
