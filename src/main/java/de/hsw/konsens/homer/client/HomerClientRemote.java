package de.hsw.konsens.homer.client;


import java.util.List;


import de.hsw.konsens.homer.service.HomerService;

public class HomerClientRemote implements HomerClient {
	private HomerService service;

	public String parse(String query) {
		return query;
	}
	
	public List<String> search(String index ,String query) {
		return null;
	}
	
	public String status() {
		return "Not implemented.";
	}

	public HomerService getService() {
		return service;
	}

	public void setService(HomerService service) {
		this.service = service;
	}

	@Override
	public String extend(String query) {
		// TODO Auto-generated method stub
		return null;
	}


}
