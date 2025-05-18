package com.wroteit.ModerationApp.command;

public class DeletePostCommand implements ModerationCommand {

    private final String entityType;
    private final Long entityId;

    public DeleteContentCommand(String entityType, Long entityId) {
        this.entityType = entityType;
        this.entityId = entityId;
    }

    @Override
    public void execute() {
        System.out.println("Deleted " + entityType + " with ID: " + entityId);
    }
}
