package com.wroteit.ModerationApp.command;

import com.wroteit.ModerationApp.model.Moderator;
import com.wroteit.ModerationApp.repository.ModeratorRepository;

public class AssignModeratorCommand implements ModerationCommand {

    private final ModeratorRepository moderatorRepository;
    private final Long userId;
    private final Long communityId;

    public AssignModeratorCommand(ModeratorRepository moderatorRepository, Long userId, Long communityId) {
        this.moderatorRepository = moderatorRepository;
        this.userId = userId;
        this.communityId = communityId;
    }

    @Override
    public void execute() {
        if (moderatorRepository.findByUserIdAndCommunityId(userId, communityId).isEmpty()) {
            Moderator moderator = new Moderator(userId, communityId);
            moderatorRepository.save(moderator);
        }
    }
}
