package com.app.mymovieserver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.app.mymovieserver.security.ApplicationFilter;

/**
 * @author aghil
 *
 */
@SpringBootApplication
@ImportResource("classpath:security.xml" )
public class MymovieserviceApplication {

	@Value("${queue.name}")
	String queueName ;

	@Value("${request.timeout}")
	String requestTimeout;

	public static void main(String[] args) {
		SpringApplication.run(MymovieserviceApplication.class, args);
	}

	@Bean
	MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	RabbitTemplate template(ConnectionFactory connectionFactory, MessageConverter converter) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setReplyTimeout(new Long(requestTimeout));
		template.setUseDirectReplyToContainer(false);
		template.setMessageConverter(converter);
		return template;
	}

	@Bean
	Queue queue() {
		return new Queue(queueName, true);
	}

	@Bean
	public FilterRegistrationBean authorizationFilter(){
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
		filterRegBean.setFilter(new ApplicationFilter());
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");
		filterRegBean.setUrlPatterns(urlPatterns);
		return filterRegBean;
	}

}
