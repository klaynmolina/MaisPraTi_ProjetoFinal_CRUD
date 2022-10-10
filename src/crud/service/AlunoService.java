package primeira.versao.service;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import primeira.versao.exceptions.CadastroDuplicado;
import primeira.versao.exceptions.CadastroInvalido;
import primeira.versao.exceptions.CadastrosInexistentes;
import primeira.versao.exceptions.IdInexistente;
import primeira.versao.model.Aluno;
import primeira.versao.repository.Repository;

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

	public Aluno readById(Long telefone) {
		Aluno buscarId = this.repository.readById(telefone);
		if (buscarId != null) {
			return buscarId;
		} else {
			throw new IdInexistente("\nCadastro inexistente.");
		}
	}

	public void readAll() {
		List<Aluno> listagem = this.repository.readAll();
		if (listagem.isEmpty() || listagem == null) {
			throw new CadastrosInexistentes("\nAinda não existem cadastros.");
		} else {
			listagem.stream().map(a -> a + "\n").forEach(System.out::println);
		}
	}

	public void update(Long telefone, Aluno aluno) {
		Aluno atualizar = this.readById(telefone);
		if (atualizar != null) {
			aluno.setDataUltimaAlteracao(new Date());
			this.repository.update(telefone, aluno);
		} else {
			throw new IdInexistente("\nCadastro inexistente.");
		}
	}

	public void remove(Long telefone) {
		Aluno remover = this.readById(telefone);
		if (remover != null) {
			this.repository.delete(telefone);
		} else {
			throw new IdInexistente("\nCadastro inexistente.");
		}
	}

}
