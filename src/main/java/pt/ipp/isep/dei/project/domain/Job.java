package pt.ipp.isep.dei.project.domain;

import java.util.regex.Pattern;

public class Job {
    private String title;

    public Job(String title) {
        setTitle(title);
    }

    public void setTitle(String title) {
        if (!verifyFilled(title) || !verifySpecialCharacters(title)) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("The job title is invalid!");
        }
    }

    public String getTitle() {
        return title;
    }

    private boolean verifySpecialCharacters(String title) {
        String regex = "^[a-zA-Z0-9 ]+$";
        return Pattern.matches(regex, title);
    }

    private boolean verifyFilled(String title) {
        return title.trim().isEmpty();
    }
}
