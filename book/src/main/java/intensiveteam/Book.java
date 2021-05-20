package intensiveteam;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Book_table")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @PostPersist
    public void onPostPersist(){
        {
        
            intensiveteam.external.Payment payment = new intensiveteam.external.Payment();
            payment.setBookId(getId());
            payment.setRoomId(getRoomId());
            payment.setGuestId(getGuestId());
            payment.setPrice(getPrice());
            payment.setHostId(getHostId());
            payment.setStartDate(getStartdate());
            payment.setEndDate(getEndDate());
            payment.setStatus("PayApproved");

            // mappings goes here
            try {
                 Application.applicationContext.getBean(intensiveteam.external.PaymentService.class)
                    .pay(payment);
            }catch(Exception e) {
                throw new RuntimeException("결제서비스 호출 실패입니다.");
            }
        }
        
        // 결제까지 완료되면 최종적으로 예약 완료 이벤트 발생
        Booked booked = new Booked();
        BeanUtils.copyProperties(this, booked);
        booked.setStatus("Booked");
        booked.publishAfterCommit();
        Booked booked = new Booked();
        BeanUtils.copyProperties(this, booked);
        booked.publishAfterCommit();

    }

    @PostRemove
    public void onPostRemove(){
        BookCanceled bookCanceled = new BookCanceled();
        BeanUtils.copyProperties(this, bookCanceled);
        bookCanceled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
