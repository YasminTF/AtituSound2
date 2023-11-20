package br.atitus.edu.poo.atitusound.controllers;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.atitus.edu.poo.atitusound.dtos.ArtistDTO;
import br.atitus.edu.poo.atitusound.entities.ArtistEntity;
import br.atitus.edu.poo.atitusound.services.ArtistService;
import br.atitus.edu.poo.atitusound.services.GenericService;


@RestController
@RequestMapping("/artists")
public class ArtistController extends GenericController<ArtistEntity, ArtistDTO> {
	
	private final ArtistService service ;
	public ArtistController(ArtistService service) {
		super();
		this.service = service;
	}

	protected ArtistEntity  convertDto2Entity(ArtistDTO dto) {
		ArtistEntity entity = new ArtistEntity();
		entity.setName(dto.getName());
		entity.setImage(dto.toString());
		return entity;
		
	}

	@Override
	protected GenericService<ArtistEntity> getserService() {		
		return service;
	}
		
}
