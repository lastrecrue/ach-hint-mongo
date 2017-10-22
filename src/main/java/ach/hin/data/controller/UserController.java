package ach.hin.data.controller;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ach.hin.data.repository.CarRepository;
import ach.hin.data.repository.FriendsRepository;
import ach.hin.data.repository.ModelRepository;
import ach.hin.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private FriendsRepository friendsRepository;

	@Autowired
	private ModelRepository modelRepository;

	@RequestMapping(value = "all", method = RequestMethod.GET)
	public @ResponseBody Collection<?> all(@RequestParam(value = "number", required = true) Integer number) {
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
		case 4:
			log.debug("modelRepository");
			return modelRepository.findAll();
		default:
			break;
		}
		return null;
	}

	@RequestMapping(value = "byId", method = RequestMethod.GET)
	public @ResponseBody Serializable byId(@RequestParam(value = "number", required = true) Integer number,
			@RequestParam(value = "id", required = true) String id) {
		switch (number) {
		case 1:
			log.debug("userRepository");
			return userRepository.findOne(id).getFriends();
		case 2:
			log.debug("carRepository");
			return carRepository.findOne(id);
		case 3:
			log.debug("friendsRepository");
			return friendsRepository.findOne(id);
		case 4:
			log.debug("modelRepository");
			return modelRepository.findOne(id);
		default:
			break;
		}
		return null;
	}

}
