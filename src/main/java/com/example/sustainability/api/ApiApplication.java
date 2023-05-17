package com.example.sustainability.api;

import com.example.sustainability.api.data.ActionType;
import com.example.sustainability.api.dto.*;
import com.example.sustainability.api.service.MarketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

	}

}

	@Component
	class ReconTool implements CommandLineRunner {

		@Autowired
		private MarketServiceImpl myService;

		@Override
		public void run(String...args) throws Exception {
			myService.createUser(new UserDto("Olabisi", "Ewumi", "org", "bisi@demo.com", "bisi", "bisi"));
			myService.createUser(new UserDto("Jeff", "Johnshon", "Permanent Mission of Mexico", "jake@mail.com", "fajglaksj", "fajglaksj"));
			myService.createUser(new UserDto("Alice", "Smith", "ABC Company", "alice@mail.com", "fajglaksj", "fajglaksj"));
			myService.createUser(new UserDto("Jak", "Smith", "ABC Company", "jak@mddsail.com", "jak", "jak"));
			myService.createUser(new UserDto("Bob", "Johnson", "XYZ Corporation", "bob@mail.com", "fajglaksj", "fajglaksj"));
			myService.createUser(new UserDto("Charlie", "Brown", "Acme Inc.", "charlie@mail.com", "fajglaksj", "fajglaksj"));
			myService.createUser(new UserDto("David", "Lee", "Global Industries", "david@mail.com", "fajglaksj", "fajglaksj"));
			myService.createUser(new UserDto("Emily", "Davis", "ABC Company", "emily@mail.com", "fajglaksj", "fajglaksj"));
			myService.createUser(new UserDto("Frank", "Wilson", "XYZ Corporation", "frank@mail.com", "fajglaksj", "fajglaksj"));
			myService.createUser(new UserDto("Grace", "Taylor", "Acme Inc.", "grace@mail.com", "fajglaksj", "fajglaksj"));
			myService.createUser(new UserDto("Henry", "Martin", "Global Industries", "henry@mail.com", "fajglaksj", "fajglaksj"));
//			myService.createUser(new UserDto("Jeff", "Johnshon", "Permanent Mission of Mexico", "jake@mail.com", "fajglaksj", "fajglaksj"));
			UserResponseDto myServiceUser = myService.createUser(new UserDto("Isabella", "Clark", "Permanent Mission of Mexico", "isabella@mail.com", "fajglaksj", "fajglaksj"));
			myService.login(new LoginDto("bisi@demo.com", "bisi"));
			byte[] data = getData();
			myService.createItem(new ItemDto("jake@mail.com",      "This is a vintage stationary pen.", "Pen", "MATERIAL", false), data);
			myService.createItem(new ItemDto("alice@mail.com",      "This is a vintage stationary pen.", "Pen", "MATERIAL", false), data);
			myService.createItem(new ItemDto("bob@mail.com",     "This is a leather-bound notebook.", "Notebook", "MATERIAL", false), data);
			myService.createItem(new ItemDto("charlie@mail.com",       "This is a wireless mouse.", "Mouse", "ELECTRONIC", false), data);
			myService.createItem(new ItemDto("david@mail.com",       "This is a set of noise-cancelling headphones.", "Headphones", "ELECTRONIC", false), data);
			myService.createItem(new ItemDto("emily@mail.com",       "This is a wooden desk organizer.", "Desk Organizer", "MATERIAL", false), data);
			myService.createItem(new ItemDto("frank@mail.com",       "This is a metal water bottle.", "Water Bottle", "MATERIAL", false), data);
			myService.createItem(new ItemDto("grace@mail.com",       "This is a glass paperweight.", "Paperweight", "MATERIAL", false), data);
			myService.createItem(new ItemDto("henry@mail.com",       "This is a set of colored pencils.", "Colored Pencils", "MATERIAL", false), data);
			myService.createItem(new ItemDto("isabella@mail.com",      "This is a stapler with a built-in staple remover.", "Stapler", "MATERIAL", false), data);
			myService.createItem(new ItemDto("isabella@mail.com",      "This is a wireless keyboard.", "Keyboard", "ELECTRONIC", false), data);

			myService.createItem(new ItemDto("alice@mail.com", "des", "itemName", "BOOKS", false), data);
			List<ItemResponseDto> itemsByUser = myService.findItemsByUser(1L);
			System.out.println("f;k;lsk;d");
//			System.out.println(myService.recordPoints(new RecordPointsRequestDto("b", List.of(new PointsDto(ActionType.LIGHTS.getValue(), 100), new PointsDto(ActionType.COMPUTER.getValue(), 100)))));
			System.out.println(myService.recordPoints(new RecordPointsRequestDto("jake@mail.com", List.of(new PointsDto(ActionType.LIGHTS.getValue(), 100), new PointsDto(ActionType.COMPUTER.getValue(), 100)))));

			myService.recordPoints(new RecordPointsRequestDto("jake@mail.com", List.of(new PointsDto(ActionType.LIGHTS.getValue(), 100), new PointsDto(ActionType.COMPUTER.getValue(), 100))));
			myService.recordPoints(new RecordPointsRequestDto("alice@mail.com", List.of(new PointsDto(ActionType.LIGHTS.getValue(), 50), new PointsDto(ActionType.COMPUTER.getValue(), 50))));
			myService.recordPoints(new RecordPointsRequestDto("bob@mail.com", List.of(new PointsDto(ActionType.LIGHTS.getValue(), 75), new PointsDto(ActionType.COMPUTER.getValue(), 75))));
			myService.recordPoints(new RecordPointsRequestDto("charlie@mail.com", List.of(new PointsDto(ActionType.WINDOW.getValue(), 100), new PointsDto(ActionType.COMPUTER.getValue(), 100))));
			myService.recordPoints(new RecordPointsRequestDto("david@mail.com", List.of(new PointsDto(ActionType.LIGHTS.getValue(), 25), new PointsDto(ActionType.WINDOW.getValue(), 25), new PointsDto(ActionType.COMPUTER.getValue(), 50))));
			myService.recordPoints(new RecordPointsRequestDto("emily@mail.com", List.of(new PointsDto(ActionType.COMPUTER.getValue(), 75), new PointsDto(ActionType.WINDOW.getValue(), 75))));
			myService.recordPoints(new RecordPointsRequestDto("frank@mail.com", List.of(new PointsDto(ActionType.LIGHTS.getValue(), 50), new PointsDto(ActionType.COMPUTER.getValue(), 50))));
			myService.recordPoints(new RecordPointsRequestDto("grace@mail.com", List.of(new PointsDto(ActionType.WINDOW.getValue(), 25), new PointsDto(ActionType.COMPUTER.getValue(), 25), new PointsDto(ActionType.LIGHTS.getValue(), 50))));
			myService.recordPoints(new RecordPointsRequestDto("henry@mail.com", List.of(new PointsDto(ActionType.LIGHTS.getValue(), 90), new PointsDto(ActionType.COMPUTER.getValue(), 90))));
			myService.recordPoints(new RecordPointsRequestDto("isabella@mail.com", List.of(new PointsDto(ActionType.WINDOW.getValue(), 100), new PointsDto(ActionType.COMPUTER.getValue(), 100))));
			System.out.println(myService.getAllItems().toString());
			System.out.println(myService.getUserByEmail("bisi@demo.com").toString());
		}

		private byte[] getData() throws IOException {
			InputStream is = getClass().getResourceAsStream("/phone.jpeg");
			try {
				assert is != null;
				return is.readAllBytes();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

