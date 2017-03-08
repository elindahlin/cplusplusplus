package javabeat.net.springboot.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javabeat.net.springboot.domain.Forecast;
import javabeat.net.springboot.gateway.SmhiGateway;

@Service
@Validated
public class DataServiceImpl implements DataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataServiceImpl.class);
	private final SmhiGateway smhiGateway;
	
	@Inject
    public DataServiceImpl(final SmhiGateway smhiGateway) {
		this.smhiGateway = smhiGateway;
    }
	
	@Override
	public Forecast getData() {	
		LOGGER.info("Getting forecast for fixed point (59,18)");
		Forecast forecast = smhiGateway.getForecast(59, 18);
		return forecast;
	}
}
