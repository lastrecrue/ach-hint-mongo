package ach.hin.data.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ach.hin.data.entity.User;
import ach.hin.data.repository.CarRepository;
import ach.hin.data.repository.FriendsRepository;
import ach.hin.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/hello-world")
@Slf4j
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private FriendsRepository friendsRepository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Collection<?> sayHello(@RequestParam(value = "number", required = false) Integer number) {
		switch (number) {
		case 1:
			log.debug("userRepository");
			return userRepository.findAll();
		case 2:
			log.debug("carRepository");
			return carRepository.findAll();
		case 3:
			log.debug("friendsRepository");
			return friendsRepository.findAll();
		default:
			break;
		}
		return null;
	}

}
