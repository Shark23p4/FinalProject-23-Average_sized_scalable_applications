@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenManager tokenManager;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.tokenManager = TokenManager.getInstance();
    }

    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        return userRepository.save(user);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        String token = UUID.randomUUID().toString();
        tokenManager.addToken(token, user.getId());
        return token;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateBiography(Long id, String biography) {
        User user = getUserById(id);
        user.setBiography(biography);
        return userRepository.save(user);
    }

    public User followUser(Long userId, Long targetId) {
        User user = getUserById(userId);
        user.getFollowing().add(targetId);
        return userRepository.save(user);
    }

    public User unfollowUser(Long userId, Long targetId) {
        User user = getUserById(userId);
        user.getFollowing().remove(targetId);
        return userRepository.save(user);
    }

    public User blockUser(Long userId, Long targetId) {
        User user = getUserById(userId);
        user.getBlockedUsers().add(targetId);
        return userRepository.save(user);
    }

    public User unblockUser(Long userId, Long targetId) {
        User user = getUserById(userId);
        user.getBlockedUsers().remove(targetId);
        return userRepository.save(user);
    }

    public User subscribeToCommunity(Long userId, String communityId) {
        User user = getUserById(userId);
        user.getSubscribedCommunities().add(communityId);
        return userRepository.save(user);
    }

    public User unsubscribeFromCommunity(Long userId, String communityId) {
        User user = getUserById(userId);
        user.getSubscribedCommunities().remove(communityId);
        return userRepository.save(user);
    }
}
