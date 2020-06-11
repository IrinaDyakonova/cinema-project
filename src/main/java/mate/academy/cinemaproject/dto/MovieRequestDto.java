package mate.academy.cinemaproject.dto;

import java.time.LocalDateTime;

public class MovieRequestDto {
    private String title;
    private String description;


    public MovieRequestDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
