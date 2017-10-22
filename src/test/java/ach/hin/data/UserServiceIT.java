package ach.hin.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ach.hin.data.entity.Car;
import ach.hin.data.entity.Model;
import ach.hin.data.entity.User;
import ach.hin.data.repository.CarRepository;
import ach.hin.data.repository.FriendsRepository;
import ach.hin.data.repository.ModelRepository;
import ach.hin.data.repository.UserRepository;
import ach.hin.data.service.UserService;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class UserServiceIT {

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

		User user1 = new User("name1");
		User user2 = new User("name2");
		User user3 = new User("name3");
		User user4 = new User("name4");

		Model model1 = new Model("model1");
		Model model2 = new Model("model2");
		Model model3 = new Model("model3");
		Car car1 = new Car("reg1", model1);
		Car car2 = new Car("reg2", model1);
		Car car3 = new Car("reg3", model1);
		Car car4 = new Car("reg4", model2);
		Car car5 = new Car("reg4", model3);
		Car car6 = new Car("reg5", model3);
		Car car7 = new Car("reg6", model3);
		
		model1= modelRepository.save(model1);
		model2= modelRepository.save(model2);
		model3= modelRepository.save(model3);

		car1 = carRepository.save(car1);
		car2 = carRepository.save(car2);
		car3 = carRepository.save(car3);
		car4 = carRepository.save(car4);
		car5 = carRepository.save(car5);
		car6 = carRepository.save(car6);
		car7 = carRepository.save(car7);

		user1 = userRepository.save(user1);
		user2 = userRepository.save(user2);
		user3 = userRepository.save(user3);
		user4 = userRepository.save(user4);
		//
		user1 = userService.addFriend(user1, user2);
		user1 = userService.addFriend(user1, user3);
		user2 = userService.addFriend(user2, user1);

		user1 = userService.addCarsToUser(user1, car1);
		user1 = userService.addCarsToUser(user1, car2);
		user1 = userService.addCarsToUser(user1, car3);
		user1 = userService.addCarsToUser(user1, car4);
		user2 = userService.addCarsToUser(user2, car5);
		user2 = userService.addCarsToUser(user2, car6);
		user4 = userService.addCarsToUser(user4, car7);

	}

}
