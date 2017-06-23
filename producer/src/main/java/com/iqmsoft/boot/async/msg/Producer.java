package com.iqmsoft.boot.async.msg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static com.iqmsoft.boot.async.msg.ProducerConfig.FIBO_CALCULATOR_EXCHANGE_NAME;
import static com.iqmsoft.boot.async.msg.ProducerConfig.FIBO_CALCULATOR_ROUTING_KEY_NAME;

import java.util.Date;
import java.util.Random;


@Component
@Slf4j
public class Producer {

    private AsyncRabbitTemplate asyncRabbitTemplate;

    public Producer(AsyncRabbitTemplate asyncRabbitTemplate) {
        this.asyncRabbitTemplate = asyncRabbitTemplate;
    }

    @Scheduled(fixedDelay = 2000L)
    public void send() {
    	
        int number = new Random().nextInt(46);

        FiboCalcRequest request = new FiboCalcRequest(number);

        AsyncRabbitTemplate.RabbitConverterFuture<FiboCalcResponse> future =
                asyncRabbitTemplate.convertSendAndReceive(FIBO_CALCULATOR_EXCHANGE_NAME, FIBO_CALCULATOR_ROUTING_KEY_NAME, request);
        
        log.info("Sending --> Thread: '{}' calc fibonacci for number '{}'", Thread.currentThread().getName(), number);

        future.addCallback(new ListenableFutureCallback<FiboCalcResponse>() {
            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onSuccess(FiboCalcResponse result) {
                log.info("Sent Successfully --> Thread: '{}' result: '{}'", Thread.currentThread().getName(), result);
            }
        });

    }

}
