package kcar_operation;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import kcar_operation.config.kafka.KafkaProcessor;

@Service
public class PolicyHandler{



    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
