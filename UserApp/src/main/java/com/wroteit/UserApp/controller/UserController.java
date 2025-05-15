@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRequest req) {
        User user = new User.Builder(req.getUsername(), req.getPassword()).build();
        if (req.getBiography() != null) {
            user.setBiography(req.getBiography());
        }
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest req) {
        String token = userService.login(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/biography")
    public ResponseEntity<User> updateBiography(@PathVariable Long id, @RequestBody String bio) {
        return ResponseEntity.ok(userService.updateBiography(id, bio));
    }

    @PostMapping("/{id}/follow/{targetId}")
    public ResponseEntity<User> followUser(@PathVariable Long id, @PathVariable Long targetId) {
        return ResponseEntity.ok(userService.followUser(id, targetId));
    }

    @DeleteMapping("/{id}/unfollow/{targetId}")
    public ResponseEntity<User> unfollowUser(@PathVariable Long id, @PathVariable Long targetId) {
        return ResponseEntity.ok(userService.unfollowUser(id, targetId));
    }

    @PostMapping("/{id}/block/{targetId}")
    public ResponseEntity<User> blockUser(@PathVariable Long id, @PathVariable Long targetId) {
        return ResponseEntity.ok(userService.blockUser(id, targetId));
    }

    @DeleteMapping("/{id}/unblock/{targetId}")
    public ResponseEntity<User> unblockUser(@PathVariable Long id, @PathVariable Long targetId) {
        return ResponseEntity.ok(userService.unblockUser(id, targetId));
    }

    @PostMapping("/{id}/subscribe/{communityId}")
    public ResponseEntity<User> subscribeToCommunity(@PathVariable Long id, @PathVariable String communityId) {
        return ResponseEntity.ok(userService.subscribeToCommunity(id, communityId));
    }

    @DeleteMapping("/{id}/unsubscribe/{communityId}")
    public ResponseEntity<User> unsubscribeFromCommunity(@PathVariable Long id, @PathVariable String communityId) {
        return ResponseEntity.ok(userService.unsubscribeFromCommunity(id, communityId));
    }
}
