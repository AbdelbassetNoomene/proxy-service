package com.proxy.service.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BourseGateway {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/names")
	public Collection<Societe> listSocietes(@RequestParam(defaultValue="0")int page,
			@RequestParam(defaultValue="30")int size){
		ParameterizedTypeReference<Resources<Societe>> responseType=
				new ParameterizedTypeReference<Resources<Societe>>() {}; 
		return restTemplate.exchange("http://societe-service/societes?page="+page+"&size="+size,HttpMethod.GET,
				null, responseType).getBody().getContent();
	}

}
class Societe {
	private Long id;
	private String nomSociete;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the nomSociete
	 */
	public String getNomSociete() {
		return nomSociete;
	}
	/**
	 * @param nomSociete the nomSociete to set
	 */
	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}
	
}
