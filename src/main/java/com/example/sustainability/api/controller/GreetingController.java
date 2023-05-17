package com.example.sustainability.api.controller;

import com.example.sustainability.api.api.ActionSummary;
import com.example.sustainability.api.api.ResourceNotFoundException;
import com.example.sustainability.api.data.User;
import com.example.sustainability.api.dto.*;
import com.example.sustainability.api.service.MarketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class GreetingController {

	private MarketServiceImpl marketService;

	@Autowired
	public GreetingController(MarketServiceImpl marketService) {
		this.marketService = marketService;
	}

	@RequestMapping(path = "/item/save", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ItemResponseDto> saveItem(@RequestPart ItemDto data, @RequestPart MultipartFile image)  throws ResourceNotFoundException {
		ItemResponseDto responseItem;
		try {
			responseItem = marketService.createItem(data, image.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Error with image data", e);
		}

		return new ResponseEntity<>(responseItem, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/item", method = GET)
	public ResponseEntity<List<ItemResponseDto>> retrieveItems() {
		return new ResponseEntity<>(marketService.getAllItems(), HttpStatus.OK);

	}

	@GetMapping("/item/type")
	public ResponseEntity<List<ItemResponseDto>> retrieveItemsByCategory(@RequestParam("category") String category)
	{
		return new ResponseEntity<>(marketService.getAllItemsByCategory(category), HttpStatus.OK);
	}

	@GetMapping("/item/type/count")
	public ResponseEntity<List<CategoryCount>> retrieveItemsByCategoryCount()
	{
		return new ResponseEntity<>(marketService.getAllItemsByCategoryCount(), HttpStatus.OK);
	}

	@GetMapping("/item/{id}")
	public ResponseEntity<ItemResponseDto> retrieveItemById(@PathVariable("id") long itemId) throws ResourceNotFoundException {
		ItemResponseDto itemsByUser = marketService.findItemById(itemId);
		return new ResponseEntity<>(itemsByUser, HttpStatus.OK);
	}

	@GetMapping("/items/{id}")
	public ResponseEntity<List<ItemResponseDto>> retrieveItemsByUser(@PathVariable("id") long userId) throws ResourceNotFoundException {
		List<ItemResponseDto> itemsByUser = marketService.findItemsByUser(userId);
		return new ResponseEntity<>(marketService.findItemsByUser(userId), HttpStatus.OK);
	}



	//----------------------------------------------------------------
	@RequestMapping(path = "/user/create", method = POST)
	public ResponseEntity<String> createUser(@RequestBody UserDto dto) throws ResourceNotFoundException {

		UserResponseDto responseDto = marketService.createUser(dto);
		return new ResponseEntity<>(responseDto.getEmail(), HttpStatus.CREATED);
	}

	@RequestMapping(path = "/user/login", method = POST)
	public ResponseEntity<String> logingUser(@RequestBody LoginDto dto) throws ResourceNotFoundException {

		if(!marketService.userExistByEmail(dto.getEmail())){
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}else {
			String responseDto = marketService.login(dto);
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		}
	}


	@PostMapping("/user/resetPassword")
	public String resetPassword(@RequestParam("email") String userEmail) {

		User user = marketService.getUserByEmail(userEmail);

		if (user == null) {
			throw new RuntimeException("user does not exist");
		}
		marketService.createPasswordResetTokenForUser(user);
		return "success";
	}

	@GetMapping("/user/changePassword")
	public String showChangePasswordPage(@RequestParam("token") String token) {
		String result = marketService.validatePasswordResetToken(token);
		String message;
		if(result != null) {
			message = "ok";
		} else {
			message = "fail";
		}
		return message;
	}

	@PostMapping("/user/savePassword")
	public ResponseEntity<String> savePassword(NewPasswordDto newPasswordDto) {

		String result = marketService.validatePasswordResetToken(newPasswordDto.getToken());

		if(result == null) {
			throw new RuntimeException("invalid token");
		}

		marketService.changeUserByPasswordResetToken(newPasswordDto);

		return new ResponseEntity<>("password changed", HttpStatus.OK);
	}



	/////-------------------------------------
	@RequestMapping(path = "/user/points", method = POST)
	public ResponseEntity<String> recordPoints(@RequestBody RecordPointsRequestDto dto) {

		String responseDto = marketService.recordPoints(dto);
		return new ResponseEntity<>("responseDto", HttpStatus.CREATED);
	}

	@RequestMapping(path = "/user/point/{id}", method = GET)
	public ResponseEntity<HashMap<String, ActionSummary>> findUserDailyActionsSummary(@PathVariable("id") String userId) {

		;
		return new ResponseEntity<>(marketService.findUserDailyActionsSummary(userId), HttpStatus.CREATED);
	}

//	@RequestMapping(path = "/user/point", method = GET)
//	public ResponseEntity<Boolean> checkRecordPointsExist(@RequestBody PointsDto dto) {
//
////		Boolean response = marketService.checkRecordPointsExist(dto);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

	@RequestMapping(path = "/user/points/total", method = GET)
	public ResponseEntity<List<LeaderboardDto>> getRecordPointsLeaderBoard() {

		List<LeaderboardDto> leaderboardDto = marketService.calculateLeaderboard();
		return new ResponseEntity<>(leaderboardDto, HttpStatus.OK);
	}

	@GetMapping("/user/points/{id}")
	public ResponseEntity<Integer> getPointsByUser(@PathVariable("id") long userId) throws ResourceNotFoundException {
		int pointsByUser = marketService.findPointsByUser(userId);
		return new ResponseEntity<>(pointsByUser, HttpStatus.OK);
	}

}
