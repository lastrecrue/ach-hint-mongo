package ach.hin.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ach.hin.data.tasks.ScheduledTasks;

@SpringBootTest(classes = MongoApplication.class)
@RunWith(SpringRunner.class)
public class ScheduledTasksIT {

	@Autowired
	private ScheduledTasks scheduledTasks;

	@Test
	public void reportCurrentTime() {
		scheduledTasks.tailAccessLog();

	}
}
