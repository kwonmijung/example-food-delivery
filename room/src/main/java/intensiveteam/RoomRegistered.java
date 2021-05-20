package intensiveteam;

public class RoomRegistered extends AbstractEvent {

    private Long id;

    public RoomRegistered(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
