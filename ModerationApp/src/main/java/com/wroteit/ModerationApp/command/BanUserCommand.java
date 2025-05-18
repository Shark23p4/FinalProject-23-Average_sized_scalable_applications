package com.wroteit.ModerationApp.command;

public class BanUserCommand implements ModerationCommand {

    private final Long userId;
    private final Long communityId;

    public BanUserCommand(Long userId, Long communityId) {
        this.userId = userId;
        this.communityId = communityId;
    }

    @Override
    public void execute() {
        System.out.println("User " + userId + " banned from community " + communityId);
    }
}
