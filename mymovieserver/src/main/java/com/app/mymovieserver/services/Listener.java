package com.app.mymovieserver.services;

import java.lang.reflect.Method;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.app.mymovieserver.dtos.BaseDto;
import com.app.mymovieserver.exceptions.ErrorCodeDescription;


/**
 * @author aghil
 *
 */
@Service
public class Listener implements MessageListener, ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(Listener.class);

	@Autowired
	MessageConverter json;

	@Autowired
	RabbitTemplate q;

	ApplicationContext ctx;

	@Override
	public void onMessage(Message message) {
		try {
			log.info("Start Listener onMessage method");

			Object request = json.fromMessage(message);

			String trackId = message.getMessageProperties().getHeaders()
					.get("TrackId").toString();
			MDC.put("TrackId", trackId);
			Object service = ctx.getBean(message.getMessageProperties()
					.getHeaders().get("ServiceName").toString());
			Object response = null;
			try {
				Method method = service.getClass().getMethod(
						message.getMessageProperties().getHeaders()
						.get("ServiceMethodName").toString(),
						request.getClass());
				response = method.invoke(service, request);
				((BaseDto) response).setTrackId(trackId);

			} catch (Exception e) {
				BaseDto baseDto = new BaseDto();
				log.error("Exception" , e);
				baseDto.setStatusCode(ErrorCodeDescription.ERROR_GENERIC
						.getErrorCode());
				baseDto.setTrackId(trackId);
				response = baseDto;
			}

			q.convertAndSend(message.getMessageProperties().getReplyTo(),
					response);
			log.info("Sent response successfully to the queue" + response);

		} catch (Exception e) {
			log.error("Error", e);
		}
		log.info("End Listener onMessage method");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ctx = applicationContext;
	}

}
