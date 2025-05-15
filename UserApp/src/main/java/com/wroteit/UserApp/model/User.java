package com.wroteit.UserApp.model;

import jakarta.persistence.*;

//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = false)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    private String biography;
//
//    @ElementCollection
//    private List<String> subscribedCommunities = new ArrayList<>();
//
//    @ElementCollection
//    private List<Long> followedUserIds = new ArrayList<>();
//
//    @ElementCollection
//    private List<Long> blockedUserIds = new ArrayList<>();
//
//    // Getters, Setters, Constructors
//}
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String biography;

    @ElementCollection
    private List<Long> following = new ArrayList<>();

    @ElementCollection
    private List<Long> blockedUsers = new ArrayList<>();

    @ElementCollection
    private List<String> subscribedCommunities = new ArrayList<>();

    protected User() {}

    private User(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.biography = "";
        this.following = new ArrayList<>();
        this.blockedUsers = new ArrayList<>();
        this.subscribedCommunities = new ArrayList<>();
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public static class Builder {
        private final String username;
        private final String password;

        public Builder(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public User build() {
            return new User(this);
        }
    }

    // Getters and setters omitted for brevity
}
