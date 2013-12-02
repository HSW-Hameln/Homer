package de.hsw.konsens.homer.client;

import java.util.List;

import de.hsw.konsens.homer.service.HomerService;

public interface HomerClient {

	public String extend(String query);
	
	public List<?> search(String query);

	public HomerService getService();

	public void setService(HomerService service);

}
