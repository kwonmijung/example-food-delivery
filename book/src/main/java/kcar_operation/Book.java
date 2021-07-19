package kcar_operation;

import java.util.Date;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;


@Entity
@Table(name="Book_table")
public class Book {

   // static Log log = LogFactory.getLog(Book.class.getName());

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long carId;
    private Integer price;
    private Long guestId;
    private String startDate;
    private String endDate;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        // System.out.println("--------------------"+startDate);
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //private static Logger logger = LoggerFactory.getLogger(Book.class);



    @PostPersist
    public void onPostPersist(){
        {

            kcar_operation.external.Payment payment = new kcar_operation.external.Payment();
            payment.setBookId(getId());
            payment.setCarId(getCarId());
            payment.setGuestId(getGuestId());
            payment.setPrice(getPrice());
            payment.setStartDate(getStartDate());
            payment.setEndDate(getEndDate());
            payment.setStatus("PayApproved");

            // log.info("Exiting the program");

            // mappings goes here
            try {
               //  System.out.println("\n\n##### listener  : " + payment.toJson() + "\n\n");


                 BookApplication.applicationContext.getBean(kcar_operation.external.PaymentService.class)
                    .pay(payment);
            }catch(Exception e) {
                throw new RuntimeException("-------- 결제서비스 호출 실패-----------");
            }
        }

        // 결제까지 완료되면 최종적으로 예약 완료 이벤트 발생
        Booked booked = new Booked();
        BeanUtils.copyProperties(this, booked);
        booked.setStatus("Booked");
        booked.publishAfterCommit();
        System.out.println("\n\n##### listener  : " + booked.toJson() + "\n\n");
        System.out.println("==================================================================");
        System.out.println("================== Booked========================================");
        System.out.println("==================================================================");

    }

    @PostRemove
    public void onPostRemove(){
        BookCanceled bookCanceled = new BookCanceled();
        BeanUtils.copyProperties(this, bookCanceled);
        bookCanceled.setStatus("Canceled");
        bookCanceled.publishAfterCommit();
    }


}
