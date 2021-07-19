package kcar_operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import kcar_operation.config.kafka.KafkaProcessor;

@Service
public class PolicyHandler{
    @Autowired NotificationRepository notificationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_Notify(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;
        System.out.println("----------------------------------------------------------------------");
        System.out.println("-- listener Notify 승인완료 : " + paymentApproved.toJson() + "----------");
        System.out.println("----------------------------------------------------------------------");

        // Sample Logic //


        // Sample Logic //
        Notification notification = new Notification();
        notificationRepository.save(notification);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_Notify(@Payload PaymentCanceled paymentCanceled){

        if(!paymentCanceled.validate()) return;

        System.out.println("----------------------------------------------------------------------");
        System.out.println("-- listener Notify 승인실패 : " + paymentCanceled.toJson()+ "----------");
        System.out.println("----------------------------------------------------------------------");



        Notification notification = new Notification();
        notificationRepository.save(notification);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBookCanceled_Notify(@Payload BookCanceled bookCanceled){

        if(!bookCanceled.validate()) return;

        System.out.println("----------------------------------------------------------------------");
        System.out.println("---listener Notify : 예약 취소 " + bookCanceled.toJson() +    "----------");
        System.out.println("----------------------------------------------------------------------");
        // Sample Logic //
        Notification notification = new Notification();
        notificationRepository.save(notification);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBooked_Notify(@Payload Booked booked){

        if(!booked.validate()) return;

        System.out.println("----------------------------------------------------------------------");
        System.out.println("---listener Notify : 예약 완료 " + booked.toJson() +    "----------");
        System.out.println("----------------------------------------------------------------------");

        Notification notification = new Notification();
        notificationRepository.save(notification);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
