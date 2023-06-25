package crud.service;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import crud.exceptions.CadastroDuplicado;
import crud.exceptions.CadastroInvalido;
import crud.exceptions.CadastrosInexistentes;
import crud.exceptions.TelefoneInexistente;
import crud.model.Usuario;
import crud.repository.Repository;

public class PessoaService {

	Scanner read;
	Repository<Usuario> repository = new Repository<>();

	public PessoaService(Scanner read) {
		this.read = read;
	}
	
	public void create(Usuario pessoa) {
		if (pessoa != null) {	
			if (repository.readAll().contains(pessoa)) {
				throw new CadastroDuplicado("\nCadastro já existente.");
			} else {
				this.repository.create(pessoa);
			}
		} else {
			throw new CadastroInvalido("\nCadastro não encontrado.");
		}
	}

	public Usuario readByTelefone(Long telefone) {
		Usuario buscarId = this.repository.readById(telefone);
		if (buscarId != null) {
			return buscarId;
		} else {
			throw new TelefoneInexistente("\nCadastro inexistente.");
		}
	}

	public List<Usuario> readAll() {
		List<Usuario> listagem = this.repository.readAll();
		if (listagem.isEmpty() || listagem == null) {
			throw new CadastrosInexistentes("\nAinda não existem cadastros.");
		} else {
			return listagem;
		}
	}
	
	public void update(Long telefone, Usuario pessoa) {
		Usuario atualizar = this.readByTelefone(telefone);
		if (atualizar != null) {
			pessoa.setDataUltimaAlteracao(new Date());
			this.repository.update(telefone, pessoa);
		} else {
			throw new TelefoneInexistente("\nCadastro inexistente.");
		}
	}

	public void remove(Long telefone) {
		Usuario remover = this.readByTelefone(telefone);
		if (remover != null) {
			this.repository.delete(telefone);
		} else {
			throw new TelefoneInexistente("\nCadastro inexistente.");
		}
	}

}
