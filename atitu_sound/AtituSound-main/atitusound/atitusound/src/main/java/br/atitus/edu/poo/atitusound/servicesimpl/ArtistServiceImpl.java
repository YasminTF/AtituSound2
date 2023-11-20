package br.atitus.edu.poo.atitusound.servicesimpl;

import org.springframework.stereotype.Service;

import br.atitus.edu.poo.atitusound.entities.ArtistEntity;
import br.atitus.edu.poo.atitusound.repositories.ArtistRepository;
import br.atitus.edu.poo.atitusound.repositories.GenericRepository;
import br.atitus.edu.poo.atitusound.services.ArtistService;

@Service
public class ArtistServiceImpl implements ArtistService {
	
	private final ArtistRepository repository;	
	
	public ArtistServiceImpl(ArtistRepository repository) {
		super();
		this.repository = repository;
		
	}

	@Override
	public GenericRepository<ArtistEntity> getRepository() {		
		return repository;
	}
			
}
