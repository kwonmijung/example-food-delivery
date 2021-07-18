package kcar_operation;

import kcar_operation.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GuestManagementPageViewHandler {


    @Autowired
    private GuestManagementPageRepository guestManagementPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBooked_then_CREATE_1 (@Payload Booked booked) {
        try {

            if (!booked.validate()) return;

            // view 객체 생성
            GuestManagementPage guestManagementPage = new GuestManagementPage();
            // view 객체에 이벤트의 Value 를 set 함
            guestManagementPage.setBookId(booked.getId());
//            guestManagementPage.set(booked.get());
//            guestManagementPage.set(booked.get());
            // view 레파지 토리에 save
            guestManagementPageRepository.save(guestManagementPage);
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookCanceled_then_UPDATE_1(@Payload BookCanceled bookCanceled) {
        try {
            if (!bookCanceled.validate()) return;
                // view 객체 조회
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBookCanceled_then_DELETE_1(@Payload BookCanceled bookCanceled) {
        try {
            if (!bookCanceled.validate()) return;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}