package de.hsw.konsens.homer.client;

import java.util.List;

import de.hsw.konsens.homer.service.HomerService;

public class HomerClientLocal implements HomerClient {
	private HomerService service;

	public HomerClientLocal(HomerService service) {
		setService(service);
	}

	public String extend(String expressionString) {
		return service.getParser().parse(expressionString);
	}
	
	public List<?> search(String query) {
		return null;
	}

	public HomerService getService() {
		return service;
	}

	public void setService(HomerService service) {
		this.service = service;
	}


}
