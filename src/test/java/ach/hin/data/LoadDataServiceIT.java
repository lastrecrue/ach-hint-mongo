package ach.hin.data;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.junit4.SpringRunner;

import ach.hin.data.entity.Car;
import ach.hin.data.entity.Model;
import ach.hin.data.entity.User;
import ach.hin.data.repository.CarRepository;
import ach.hin.data.repository.FriendsRepository;
import ach.hin.data.repository.ModelRepository;
import ach.hin.data.repository.UserRepository;
import ach.hin.data.service.UserService;

@SpringBootTest(classes = MongoApplication.class)
@RunWith(SpringRunner.class)
public class LoadDataServiceIT {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FriendsRepository friendsRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ModelRepository modelRepository;

	@Before
	public void initBase() {
		userRepository.deleteAll();
		friendsRepository.deleteAll();
		carRepository.deleteAll();
		modelRepository.deleteAll();
	}

	@Test
	public void addRelationsTest() {

		int nextInt = new Random().nextInt(20);
		for (int i = 0; i < nextInt; i++) {
			Model model1 = new Model("model" + i);
			modelRepository.save(model1);
		}
		nextInt = new Random().nextInt(200);
		long count = modelRepository.count();
		for (int i = 0; i < nextInt; i++) {
			Model randomEntity = getRandomEntity(count, modelRepository);
			Car car1 = new Car("reg" + i, randomEntity);
			carRepository.save(car1);
		}

		nextInt = new Random().nextInt(100);
		for (int i = 0; i < nextInt; i++) {
			User user1 = new User("name" + i);
			userRepository.save(user1);
		}

		List<User> findAll = userRepository.findAll();
		count = findAll.size();
		for (User user : findAll) {
			nextInt = new Random().nextInt(20);
			for (int i = 0; i < nextInt; i++) {
				User randomEntity = getRandomEntity(count, userRepository);
				if (user.getFriends() == null || !user.getFriends().getUsers().contains(randomEntity)) {
					userService.addFriend(user, randomEntity);
				}
			}

			nextInt = new Random().nextInt(5);
			count = modelRepository.count();
			for (int i = 0; i < nextInt; i++) {
				Car randomEntity = getRandomEntity(count, carRepository);
				if (!user.getCars().contains(randomEntity)) {
					userService.addCarsToUser(user, randomEntity);
				}
			}
		}

	}

	private <T extends Serializable, R extends MongoRepository<T, String>> T getRandomEntity(long count, R r) {
		int idx = new Random().nextInt((int) count);
		Page<T> questionPage = r.findAll(new PageRequest(idx, 1));
		T q = null;
		if (questionPage.hasContent()) {
			q = questionPage.getContent().get(0);
		}
		return q;
	}

}
