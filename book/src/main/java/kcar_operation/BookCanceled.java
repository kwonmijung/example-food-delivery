package kcar_operation;

public class BookCanceled extends AbstractEvent {

    //private static final String status = null;
    private Long id;
    private String status;

    public BookCanceled(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
   public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    } 



}