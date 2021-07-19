package kcar_operation;

import kcar_operation.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    /**
     * 상품 변경이 발생할때마다, 상품정보를 저장해 놓음
     */

    @StreamListener(KafkaProcessor.INPUT)
    public void onProductChanged(@Payload ProductChanged productChanged) {
        try {
            if (productChanged.isMe()) {
                System.out.println("##### listener : " + productChanged.toJson());
                Product product = new Product();
                product.setId(productChanged.getProductId());
                product.setStock(productChanged.getProductStock());
                product.setName(productChanged.getProductName());
                product.setPrice(productChanged.getProductPrice());
                productRepository.save(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void onProductOutOfStock(@Payload ProductOutOfStock productOutOfStock) {
        try {
            if (productOutOfStock.isMe()) {
                System.out.println("##### listener : " + productOutOfStock.toJson());
                Optional<Order> orderOptional = orderRepository.findById(productOutOfStock.getOrderId());
                Order order = orderOptional.get();
                order.setState("OrderCancelled");
                orderRepository.save(order);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
