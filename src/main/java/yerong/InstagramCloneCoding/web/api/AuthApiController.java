package yerong.InstagramCloneCoding.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yerong.InstagramCloneCoding.service.AuthService;
import yerong.InstagramCloneCoding.web.dto.auth.UsernameAvailabilityResponseDto;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthApiController {

    private final AuthService authService;

    @GetMapping("/auth/checkUsernameAvailability")
    public ResponseEntity<UsernameAvailabilityResponseDto> checkUsernameAvailability(@RequestParam String username) {
        boolean available = authService.isUsernameAvailable(username);

        UsernameAvailabilityResponseDto response = new UsernameAvailabilityResponseDto(available);
        return ResponseEntity.ok(response);
    }
}
