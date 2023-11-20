package br.atitus.edu.poo.atitusound.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.atitus.edu.poo.atitusound.dtos.PlaylistDTO;
import br.atitus.edu.poo.atitusound.entities.PlaylistEntity;
import br.atitus.edu.poo.atitusound.services.GenericService;
import br.atitus.edu.poo.atitusound.services.PlaylistService;

@RestController
@RequestMapping("/playlist")
public class PlaylistController extends GenericController<PlaylistEntity, PlaylistDTO> {
	
	private final PlaylistService service;


	public PlaylistController(PlaylistService service) {
		super();
		this.service = service;
	}

	@Override
	protected GenericService<PlaylistEntity> getserService() {
		return service;
	}

	@Override
	protected PlaylistEntity convertDto2Entity(PlaylistDTO dto) {
		PlaylistEntity playlist = new PlaylistEntity();
		playlist.setName(dto.getName());
		playlist.setPublic_share(dto.getPublic_share());		
		return playlist;
	}
	

}
