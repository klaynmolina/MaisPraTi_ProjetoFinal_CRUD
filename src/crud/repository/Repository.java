package crud.repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import crud.model.Usuario;


public class Repository<T extends Usuario> {

	Map<Long, T> BandoDeDados;

	public Repository() {
		this.BandoDeDados = new TreeMap<>();
	}

	public void create(T t) {
			this.BandoDeDados.put(t.getTelefone(), t);	
	}	

	public T readById(Long chave) {
		return this.BandoDeDados.get(chave);
	}
		
	public List<T> readAll() {
		return this.BandoDeDados.values().stream().collect(Collectors.toList());
	}

	public void update(Long chave,  T t) {
		this.BandoDeDados.replace(chave, t);			
	}
	
	public void delete(Long chave) {
		this.BandoDeDados.remove(chave);
	}
}
