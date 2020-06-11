package mate.academy.cinemaproject.dto;

public class TicketRequestDto {
    private Long movieSessionId;
    private Long userId;

    public TicketRequestDto() {
    }

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
