package com.example.sustainability.api.service;

import com.example.sustainability.api.api.ActionSummary;
import com.example.sustainability.api.api.ResourceNotFoundException;
import com.example.sustainability.api.controller.CategoryCount;
import com.example.sustainability.api.data.*;
import com.example.sustainability.api.dto.*;
import com.example.sustainability.api.repository.ItemRepository;
import com.example.sustainability.api.repository.PasswordTokenRepository;
import com.example.sustainability.api.repository.PointRepository;
import com.example.sustainability.api.repository.UserRepository;
import javax.transaction.Transactional;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Service
@Transactional
public class MarketServiceImpl implements MarketService {

    private ItemRepository itemRepository;
    private UserRepository userRepository;

    private PointRepository pointRepository;


    private PasswordTokenRepository passwordTokenRepository;

    @Autowired
    public MarketServiceImpl(ItemRepository itemRepository, UserRepository userRepository, PointRepository pointRepository, PasswordTokenRepository passwordTokenRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.pointRepository = pointRepository;
        this.passwordTokenRepository = passwordTokenRepository;
    }

    public UserResponseDto createUser(UserDto userDto) throws ResourceNotFoundException {
        if(userExistByEmail(userDto.getEmail())){
            throw new ResourceNotFoundException("user already exists"); // todo: change e
        }
        User registerUser = userRepository.save(convertDtoToUser(userDto));
        PasswordResetToken passwordToken = createAndSavePasswordToken(registerUser);
        UserResponseDto userToDto = convertUserToDto(registerUser);
        userToDto.setToken(passwordToken.getToken());
        return userToDto;
    }


    public UserResponseDto findUser(long id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .map(this::convertUserToDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public boolean userExistByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email).isPresent();
    }

    public User getUserByEmail(String email)  {
        return userRepository.findByEmail(email).get();
    }

    public User convertDtoToUser(UserDto userDto) {
        return new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getOrg(),
               userDto.getPassword(), UUID.randomUUID().toString());
    }

    public UserResponseDto convertUserToDto(User user) {
        if (user == null) {
            return new UserResponseDto(true, "User not found");
        }

        return new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getOrg());
    }

    public ItemResponseDto createItem(ItemDto itemDto, byte[] imageData) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findByEmail(itemDto.getUserToken());
        if (user.isPresent()) {
            Item item = convertDtoToItem(itemDto, user.get().getId());
            item.setImage(imageData);
            item.setUser(user.get());
            Item createdItem = itemRepository.save(item);
            return convertItemToDto(createdItem);
        } else {
            throw new ResourceNotFoundException("User does not exist");
        }
    }

    private ItemResponseDto convertItemToDto(Item item) {
        byte[] encodedImage = Base64.getEncoder().encode(item.getImage());

        User user = item.getUser();
        String email = user.getEmail();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();


        return new ItemResponseDto(email, item.getId(), firstName, lastName, item.getDescription(), item.getItemName(), item.getType(),
                item.getExchanged(), new String(encodedImage));
    }

    public Item convertDtoToItem(ItemDto dto, Long id) {
        return new Item(id, dto.getDescription(), dto.getItemName(), dto.getType());
    }

    public List<ItemResponseDto> findItemsByUser(Long userId) throws ResourceNotFoundException {
        if (userRepository.findById(userId).isPresent()) {

            return itemRepository.findAllByUserId(userId).stream().map(this::convertItemToDto).toList();
        } else {
            throw new ResourceNotFoundException("User does not exist");
        }
    }


    public List<ItemResponseDto> getAllItems() {
        return itemRepository.findAll().stream().map(this::convertItemToDto).toList();
    }

    public List<ItemResponseDto> getAllItemsByCategory(String type) {
        return itemRepository.findAllByType(type).stream().map(this::convertItemToDto).toList();
    }

    public String recordPoints(RecordPointsRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(IllegalArgumentException::new);

        List<ActionType> todayUserTasks = pointRepository.findAllByUserIdAndEntryDate(user.getId(), LocalDate.now())
                .stream()
                .map(it-> it.getActionType())
                .toList();

        List<UserTask> userTasks = dto.getList().stream()
                .filter(i-> !todayUserTasks.contains(i.getActionType()))
                .map(request -> {
                    return new UserTask(user,
                           Instant.now(),
                           request.getPointTotal(),
                           ActionType.valueOf(request.getActionType()),
                           LocalDate.now());
        }).toList();

        if (userTasks.isEmpty()) return "tasks already recorded";


        List<UserTask> userTasks1 = pointRepository.saveAll(userTasks);

        return "success";
    }

    public HashMap<String, ActionSummary> findUserDailyActionsSummary(String userID) {
        Optional<User> user = userRepository.findByEmail(userID);

        try {
            user.orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new RuntimeException("User not found",e);
        }

        HashMap<String, ActionSummary> taskMap = new HashMap<>();
        for (ActionType type :ActionType.values()) {
                taskMap.put(type.getValue(), new ActionSummary());
        }

        List<UserTask> todayUserTasks = pointRepository.findAllByUserIdAndEntryDate(user.get().getId(), LocalDate.now());

        todayUserTasks.forEach(task ->{
            ActionSummary actionSummary = taskMap.get(task.getActionType().getValue());
            actionSummary.setRecorded(true);
            actionSummary.setPoints(task.getPoints());
        });

        return taskMap;
    }

    public List<LeaderboardDto> calculateLeaderboard() {
        List<User> allUsers = userRepository.findAll();
        List<UserTask> all = pointRepository.findAll();

        Map<User, Integer> aggMap = all.stream().collect(groupingBy(UserTask::getUser, summingInt(UserTask::getPoints)));

        allUsers.removeAll(new HashSet<>(aggMap.keySet()));

        allUsers.forEach(user -> aggMap.put(user, 0));

        return mapUserForLeaderboard(aggMap);
    }
    private List<LeaderboardDto> mapUserForLeaderboard(Map<User, Integer> aggMap) {

        List<LeaderboardDto> list = new ArrayList<>(aggMap.entrySet().stream().map(set -> {
            User user = set.getKey();
            return new LeaderboardDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), set.getValue());
        }).toList());

        list.sort((o1, o2) -> (int) (o2.getPoints() - o1.getPoints()));

    return list;

    }

    public int findPointsByUser(long userId) throws ResourceNotFoundException {
        if (userRepository.findById(userId).isPresent()) {

            List<UserTask> all = pointRepository.findAllById(List.of(userId));

            AtomicInteger count = new AtomicInteger();

            all.forEach(userTask -> count.addAndGet(userTask.getPoints()));

            return count.get();
        } else {
            throw new ResourceNotFoundException("User does not exist");
        }
    }

    public void createPasswordResetTokenForUser(User user) {
        PasswordResetToken myToken = createAndSavePasswordToken(user);

        sendEmail(myToken);

    }

    private PasswordResetToken createAndSavePasswordToken(User user) {
        String token = UUID.randomUUID().toString(); //todo: 5 digit code
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
        return myToken;
    }


    public String validatePasswordResetToken(String token) {
        PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

    public void changeUserByPasswordResetToken(NewPasswordDto newPasswordDto) {
        PasswordResetToken passToken = passwordTokenRepository.findByToken(newPasswordDto.getToken());
        User user = passToken.getUser();


        if(!newPasswordDto.getMatchingPassword().equals(newPasswordDto.getPassword())) {
            throw new RuntimeException("reset passwords do not match");
        }

        user.setUserToken(UUID.randomUUID().toString());
        userRepository.save(user);
    }


    public void sendEmail(PasswordResetToken myToken) {
        User user = myToken.getUser();
        Email email = EmailBuilder.startingBlank()
                .from("Admin", "isaacewumi@gmail.com")
                .to(user.getFirstName(), user.getEmail())
                .withSubject("Your code to sign to reset password")
                .withPlainText("To verify it's you, enter the following code: " + myToken.getToken())
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "isaacewumi@gmail.com", "cvtrzecwhisurtwy")
                        .buildMailer();

        mailer.sendMail(email);
    }

    public String login(LoginDto dto) {
        User userByEmail = getUserByEmail(dto.getEmail());
        boolean result = userByEmail.getPassword().equals(dto.getPassword());
        return result? userByEmail.getEmail(): "";
    }

    public List<CategoryCount> getAllItemsByCategoryCount() {
        List<Item> all = itemRepository.findAll();

        Map<String, List<Item>> collect = all.stream().collect(groupingBy(Item::getType));

        List<CategoryCount> categoryCounts = collect.entrySet().stream().map(set -> new CategoryCount(set.getKey(), set.getValue().size())).toList();

        return categoryCounts;

    }

    public ItemResponseDto findItemById(long itemId) {
         return convertItemToDto(itemRepository.getReferenceById(itemId));
    }
}