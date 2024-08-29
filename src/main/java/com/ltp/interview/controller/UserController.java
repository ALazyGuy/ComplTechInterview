package com.ltp.interview.controller;

import com.ltp.interview.model.dto.DeleteMultipleUsersRequestDto;
import com.ltp.interview.model.dto.LoadAllUsersWebsocketDto;
import com.ltp.interview.model.dto.UserInfoDto;
import com.ltp.interview.model.dto.UserUpdateRequestDto;
import com.ltp.interview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SimpMessagingTemplate template;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserInfoDto> allUsers() {
        final LoadAllUsersWebsocketDto wsResponse = userService.getWebsocketResponseOnLoadUsers();
        template.convertAndSend("/interview/user", wsResponse);
        return userService.getAllUsersInfo();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @DeleteMapping(value = "/many", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteManyUsers(@RequestBody DeleteMultipleUsersRequestDto deleteMultipleUsersRequestDto) {
        userService.deleteUsers(deleteMultipleUsersRequestDto.getUserIds());
    }

    @PatchMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoDto> updateUser(@RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        final Optional<UserInfoDto> userInfoDto = userService.updateCurrentUser(userUpdateRequestDto);
        return userInfoDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(202).build());
    }

}
