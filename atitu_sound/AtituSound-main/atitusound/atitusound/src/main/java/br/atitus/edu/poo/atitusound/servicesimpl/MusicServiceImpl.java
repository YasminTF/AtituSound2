package br.atitus.edu.poo.atitusound.servicesimpl;

import org.springframework.stereotype.Service;

import br.atitus.edu.poo.atitusound.entities.MusicEntity;
import br.atitus.edu.poo.atitusound.repositories.GenericRepository;
import br.atitus.edu.poo.atitusound.repositories.MusicRepository;
import br.atitus.edu.poo.atitusound.services.MusicService;

@Service
public class MusicServiceImpl implements MusicService {
	
	private final MusicRepository repository;
	public MusicServiceImpl(MusicRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public GenericRepository<MusicEntity> getRepository() {
		return repository;
	}

}
