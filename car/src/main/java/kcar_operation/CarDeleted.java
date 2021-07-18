package kcar_operation;

public class CarDeleted extends AbstractEvent {

    private Long id;

    public CarDeleted(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
