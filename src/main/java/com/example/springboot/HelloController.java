package com.example.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
	ArrayList <Friend> friends = new ArrayList<>();

	public HelloController() {
		//Constructor
	}

	@GetMapping("/home")
	public String index() {
		return "Welcome to Spring Boot!";
	}

	@GetMapping("/friends")
	public String friends() {

		String s = "";
		for (int i = 0; i < friends.size(); i++) {
			s = s + (friends.get(i).getName()) + " " + friends.get(i).getAge() + "\n";
		}
		return s;
	}

	@DeleteMapping ("/die")
	public String die() {
		Friend f = friends.get(0);
		friends.remove(0);
		return f.getName() + " died";
	}

	@RequestMapping("/newFriend")
	public ResponseEntity newfriend(@RequestParam("friend") String friend) {
		if(friend.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 Bad Request. YOU ARE A BAD PERSON! >:(");
		}
		for(int i = 0; i < friends.size(); i++) {
			if (friends.get(i).getName().equals(friend)) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("This friend already exists (CODE 409)\n");
			}
		}
		Friend f = new Friend(friend, 18);
		friends.add(f);
		return ResponseEntity.status(HttpStatus.OK).body(friends.toString());
	}

	@PostMapping("/postFriend")
	public ResponseEntity postFriend(@RequestBody Friend friend) {
		friends.add(friend);
		return ResponseEntity.status(HttpStatus.OK).body("sucessfully added!");
	}

	@PostMapping("/postFriends")
	public ResponseEntity postFriends(@RequestBody ArrayList<Friend> newFriends) {
		for(int i =0; i < newFriends.size(); i++) {
			friends.add(newFriends.get(i));
		}
		return ResponseEntity.status(HttpStatus.OK).body("sucessfully added!");
	}

}