package crud.service;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import crud.exceptions.CadastroDuplicado;
import crud.exceptions.CadastroInvalido;
import crud.exceptions.CadastrosInexistentes;
import crud.exceptions.TelefoneInexistente;
import crud.model.Aluno;
import crud.repository.Repository;

public class AlunoService {

	Scanner read;
	Repository<Aluno> repository = new Repository<>();

	public AlunoService(Scanner read) {
		this.read = read;
	}

	public void create(Aluno aluno) {
		if (aluno != null) {
			if (repository.readAll().contains(aluno)) {
				throw new CadastroDuplicado("\nCadastro já existente.");
			} else {
				this.repository.create(aluno);
			}
		} else {
			throw new CadastroInvalido("\nCadastro não encontrado.");
		}
	}

	public Aluno readByTelefone(Long telefone) {
		Aluno buscarId = this.repository.readById(telefone);
		if (buscarId != null) {
			return buscarId;
		} else {
			throw new TelefoneInexistente("\nCadastro inexistente.");
		}
	}

	public List<Aluno> readAll() {
		List<Aluno> listagem = this.repository.readAll();
		if (listagem.isEmpty() || listagem == null) {
			throw new CadastrosInexistentes("\nAinda não existem cadastros.");
		} else {
			return listagem;
		}
	}

	public void update(Long telefone, Aluno aluno) {
		Aluno atualizar = this.readByTelefone(telefone);
		if (atualizar != null) {
			aluno.setDataUltimaAlteracao(new Date());
			this.repository.update(telefone, aluno);
		} else {
			throw new TelefoneInexistente("\nCadastro inexistente.");
		}
	}

	public void remove(Long telefone) {
		Aluno remover = this.readByTelefone(telefone);
		if (remover != null) {
			this.repository.delete(telefone);
		} else {
			throw new TelefoneInexistente("\nCadastro inexistente.");
		}
	}

}
