package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.domain.UrgencyStatus;

public class ToDoEntryDto {
    private String title;
    private String description;
    private int duration;
    private UrgencyStatus urgencyStatus;
    private GreenSpaceDto greenSpace;

    public ToDoEntryDto(String title, String description, int duration, UrgencyStatus urgencyStatus, GreenSpaceDto greenSpace) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.urgencyStatus = urgencyStatus;
        this.greenSpace = greenSpace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public GreenSpaceDto getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpaceDto greenSpace) {
        this.greenSpace = greenSpace;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UrgencyStatus getUrgencyStatus() {
        return urgencyStatus;
    }

    public void setUrgencyStatus(UrgencyStatus urgencyStatus) {
        this.urgencyStatus = urgencyStatus;
    }
}
