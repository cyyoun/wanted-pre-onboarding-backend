package wanted.recruitment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wanted.recruitment.domain.User;
import wanted.recruitment.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public String add(@RequestBody User user) {
        userService.save(user);
        return user.getUserName() + " 님 환영합니다 🙂";
    }
}
