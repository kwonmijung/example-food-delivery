package kcar_operation;

public class CarRegistered extends AbstractEvent {

    private Long id;

    public CarRegistered(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
