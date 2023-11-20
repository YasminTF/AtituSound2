package br.atitus.edu.poo.atitusound.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.atitus.edu.poo.atitusound.dtos.MusicDTO;
import br.atitus.edu.poo.atitusound.entities.ArtistEntity;
import br.atitus.edu.poo.atitusound.entities.MusicEntity;
import br.atitus.edu.poo.atitusound.services.GenericService;
import br.atitus.edu.poo.atitusound.services.MusicService;

@RestController
@RequestMapping("/musics")
public class MusicController extends GenericController<MusicEntity, MusicDTO>{
    
	private final MusicService service;	
	
	public MusicController(MusicService service) {
		super();
		this.service = service;
	}

	@Override
	protected GenericService<MusicEntity> getserService() {
		return service;
	}

	@Override
	protected MusicEntity convertDto2Entity(MusicDTO dto) {
		MusicEntity entidade = new MusicEntity();
		entidade.setName(dto.getName());
		entidade.setDuration(dto.getDuration());
		entidade.setUrl(dto.getUrl());
		
		ArtistEntity entidadeArtista = new ArtistEntity();
		entidadeArtista.setUuid(dto.getArtist().getUuid());
		entidade.setArtist(entidadeArtista);
		
		return entidade;
	}

}
