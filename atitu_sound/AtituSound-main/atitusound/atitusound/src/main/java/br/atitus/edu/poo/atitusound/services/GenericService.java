package br.atitus.edu.poo.atitusound.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import br.atitus.edu.poo.atitusound.entities.GenericEntity;
import br.atitus.edu.poo.atitusound.repositories.GenericRepository;

public interface GenericService <TEntidade extends GenericEntity> {
	
	GenericRepository<TEntidade> getRepository();
	
	default void validate(TEntidade entity)throws Exception {
		if (entity.getName() == null || entity.getName().isEmpty())
			throw new Exception("Campo nome inválido!");
		if (entity.getUuid() == null) {
			  if (getRepository().existsByName(entity.getName()))
	            throw new Exception("Já existe registro com esse nome");
		} else {
			if (!getRepository().existsById(entity.getUuid()))
				throw new Exception("Não existe registro com esse UUID");
			if (getRepository().existsByNameAndUuidNot(entity.getName(), entity.getUuid()))
	            throw new Exception("Já existe registro com esse nome");
		}
			
} 
			
	default TEntidade save(TEntidade entity) throws Exception {
		validate(entity);
		getRepository().save(entity);
		return entity;
		
	}
	
	default List<TEntidade> findAll() throws Exception {
		return getRepository().findAll();
	}
	
	default void validateFindByName(String name, Pageable pageable) throws Exception{
		
	}

	
	default Page<List<TEntidade>> findByNameContainingIgnoreCase(String name, Pageable pageable) throws Exception {
		validateFindByName(name, pageable);
		return getRepository().findByNameContainingIgnoreCase(name, pageable);
	}
	
	default void validateFindById(UUID uuid)throws Exception {
		
	}

	
	default Optional<TEntidade> findById(UUID uuid) throws Exception {
		validateFindById(uuid);
		
		return getRepository().findById(uuid);
	}
	
	default void validateDeleteById(UUID uuid) throws Exception{
		if(! getRepository().existsById(uuid))
			throw new Exception("Não existe registro com esse UUID");
		
	}

	
	default void deleteById(UUID uuid) throws Exception {
		validateDeleteById(uuid);
		getRepository().deleteById(uuid);
		
	}

}
