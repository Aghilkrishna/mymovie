package com.app.mymovieserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.app.mymovieserver.dtos.BaseDto;
import com.app.mymovieserver.exceptions.ErrorCodeDescription;
import com.app.mymovieserver.util.RandomUtil;

/**
 * @author aghil
 *
 */
public class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	@Value("${queue.name}")
	String queueName = "mymovieq";

	@Autowired
	RabbitTemplate mq;

	@Autowired
	RandomUtil randomUtil;

	public Object sendtoMQ(Object request, final String serviceMethodName, final String serviceName) {
		log.info("Start BaseController sendtoMQ method");

		try {
			log.info("About to send a message to queue [" + queueName + "]");
			Object obj = mq.convertSendAndReceive(queueName, request, new MessagePostProcessor() {
				@Override
				public Message postProcessMessage(Message message) throws AmqpException {

					String trackId = randomUtil.getTrackId();
					log.info("Generated track id [" + trackId + "]");

					message.getMessageProperties().setHeader("ServiceMethodName", serviceMethodName);
					message.getMessageProperties().setHeader("ServiceName", serviceName);
					message.getMessageProperties().setHeader("TrackId", trackId);
					MDC.put("TrackId", trackId);

					SecurityContext context = SecurityContextHolder.getContext();
					if (context != null) {
						Authentication authentication = context.getAuthentication();
						if (authentication != null && authentication.getPrincipal() != null
								&& authentication.getPrincipal() instanceof Integer) {
							Integer sid = (Integer) authentication.getPrincipal();
							message.getMessageProperties().setHeader("SID", sid);
						}
					}
					return message;
				}
			});
			if (obj == null) {
				log.error("Queue timed out .... for queue name[" + queueName + "]");
				return new BaseDto(ErrorCodeDescription.ERROR_SERVICE_UNAVAILABLE.getErrorCode());
			} else {
				log.info("Send message to queue [" + queueName + "] is successfull");
			}

			log.info("End BaseController sendtoMQ method");
			return obj;
		} catch (Exception exception) {
			log.error("Exception in BaseController :", exception);
			log.info("End BaseController sendtoMQ method");
			return new BaseDto(ErrorCodeDescription.ERROR_SERVICE_UNAVAILABLE.getErrorCode());
		}
	}
}
