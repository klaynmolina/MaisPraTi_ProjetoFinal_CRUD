package crud.service;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import crud.exceptions.CadastroDuplicado;
import crud.exceptions.CadastroInvalido;
import crud.exceptions.CadastrosInexistentes;
import crud.exceptions.TelefoneInexistente;
import crud.model.Pessoa;
import crud.repository.Repository;

public class PessoaService {

	Scanner read;
	Repository<Pessoa> repository = new Repository<>();

	public PessoaService(Scanner read) {
		this.read = read;
	}
	
	public void create(Pessoa pessoa) {
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

	public Pessoa readByTelefone(Long telefone) {
		Pessoa buscarId = this.repository.readById(telefone);
		if (buscarId != null) {
			return buscarId;
		} else {
			throw new TelefoneInexistente("\nCadastro inexistente.");
		}
	}

	public List<Pessoa> readAll() {
		List<Pessoa> listagem = this.repository.readAll();
		if (listagem.isEmpty() || listagem == null) {
			throw new CadastrosInexistentes("\nAinda não existem cadastros.");
		} else {
			return listagem;
		}
	}
	
	public void update(Long telefone, Pessoa pessoa) {
		Pessoa atualizar = this.readByTelefone(telefone);
		if (atualizar != null) {
			pessoa.setDataUltimaAlteracao(new Date());
			this.repository.update(telefone, pessoa);
		} else {
			throw new TelefoneInexistente("\nCadastro inexistente.");
		}
	}

	public void remove(Long telefone) {
		Pessoa remover = this.readByTelefone(telefone);
		if (remover != null) {
			this.repository.delete(telefone);
		} else {
			throw new TelefoneInexistente("\nCadastro inexistente.");
		}
	}

}
