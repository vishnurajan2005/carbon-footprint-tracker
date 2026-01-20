package com.infosys.carbon.dto;

public class LeaderboardDTO {

    private Long userId;
    private String userName;
    private Long badgeCount;

    public LeaderboardDTO(Long userId, String userName, Long badgeCount) {
        this.userId = userId;
        this.userName = userName;
        this.badgeCount = badgeCount;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getBadgeCount() {
        return badgeCount;
    }
}
