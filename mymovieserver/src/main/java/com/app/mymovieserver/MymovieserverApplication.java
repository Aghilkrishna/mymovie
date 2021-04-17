package com.app.mymovieserver;

import org.jasypt.digest.PooledStringDigester;
import org.jasypt.digest.StringDigester;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.app.mymovieserver.services.Listener;

/**
 * @author aghil
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class MymovieserverApplication {
	
	@Value("${queue.name}")
	String queueName;
	
	@Value("${stringdigester.poolsize.count}")
	int stringDigesterPoolSize;
	
	@Value("${stringdigester.iteration.count}")
	int stringDigesterIterations;
	
	@Value("${stringdigester.saltsize}")
	int stringDigesterSaltSize;

	public static void main(String[] args) {
		SpringApplication.run(MymovieserverApplication.class, args);
	}

	@Bean
	Queue queue() {
		return new Queue(queueName, true);
	}
	
	@Bean
	MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	RabbitTemplate template(ConnectionFactory connectionFactory, MessageConverter converter) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(converter);
		template.setUseDirectReplyToContainer(false);
		return template;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(Listener listener, MessageConverter converter) {
		return new MessageListenerAdapter(listener, converter);
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	@Bean
	StringDigester PasswordHash() {
		final PooledStringDigester stringDigester = new PooledStringDigester();
		stringDigester.setAlgorithm("SHA-256");
		stringDigester.setIterations(stringDigesterIterations);
		stringDigester.setSaltSizeBytes(stringDigesterSaltSize);
		stringDigester.setPoolSize(stringDigesterPoolSize);
		stringDigester.initialize();
		return stringDigester; 
	}
}
