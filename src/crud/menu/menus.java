package crud.menu;

import java.text.ParseException;
import java.util.Scanner;

import crud.complementos.Formatacao;
import crud.exceptions.CadastroInvalido;
import crud.model.Aluno;
import crud.model.Pessoa;
import crud.service.AlunoService;
import crud.service.PessoaService;

public class menus {

	public static int menuInicial(Scanner read) {
		System.out.println("\n*******************************");
		System.out.println("\nSISTEMA DE PESSOAS & ALUNOS\n");
		System.out.println("1. Cadastrar.");
		System.out.println("2. Buscar todos os cadastros.");
		System.out.println("3. Atualizar dados.");
		System.out.println("4. Deletar.");
		System.out.println("0. Encerrar sistema.");
		System.out.print("\nDigite a opção desejada: ");
		int iniciarControle = read.nextInt();
		return iniciarControle;
	}

	public static void cadastrar(Scanner read, PessoaService pessoaService, AlunoService alunoService)
			throws ParseException {
		read.nextLine();
		System.out.print("\nNome: ");
		String nome = read.nextLine();
		System.out.print("\nTelefone com DDD (11 digitos): ");
		String telefone = read.nextLine();
		System.out.print("\nData de nascimento (dd/MM/yyyy): ");
		String dataNascimento = read.nextLine();

		System.out.print("\nDeseja informar nota final do curso? 1. Sim / 2. Não ");
		int resposta = read.nextInt();

		if (resposta == 1 || resposta == 2) {
			if (resposta == 1) {
				System.out.print("\nNota Final (Separação com vírgula): ");
				Double notaFinal = read.nextDouble();

				Aluno aluno = new Aluno(nome, telefone, dataNascimento, notaFinal);
				if (aluno.getNome().isBlank()) {
					throw new CadastroInvalido("\nCampo nome está em branco. Cadastro inválido.");
				} else {
					alunoService.create(aluno);
					System.out.println(aluno);
					System.out.println("\nCadastro salvo com sucesso.");
				}
			} else {
				Pessoa pessoa = new Pessoa(nome, telefone, dataNascimento);
				if (pessoa.getNome().isBlank()) {
					throw new CadastroInvalido("\nCampo nome está em branco. Cadastro inválido.");
				} else {
					pessoaService.create(pessoa);
					System.out.println(pessoa);
					System.out.println("\nCadastro salvo com sucesso.");
				}
			}
		} else {
			System.out.println("\nOpção Inválida.");
		}
	}

	public static void todosOsCadastros(PessoaService pessoaService, AlunoService alunoService) {
		pessoaService.readAll();
		alunoService.readAll();
	}

	public static void menuAtualizar() {
		System.out.println("\nQual dado deseja atualizar?\n");
		System.out.println("1. Atualizar nome.");
		System.out.println("2. Atualizar telefone.");
		System.out.println("3. Atualizar data de nascimento.");
		System.out.println("4. Atualizar nota final.");
		System.out.println("5. Atualizar todos os dados.");
		System.out.println("6. Cancelar operação.");
		System.out.print("\nDigite a opção desejada: ");
	}

	public static void atualizar(Scanner read, PessoaService pessoaService, AlunoService alunoService,
			int escolhaAtualizar, Long atualizarId) throws ParseException {
		int opcoesAtualizar = read.nextInt();
		switch (opcoesAtualizar) {
		case 1:
			read.nextLine();
			System.out.print("\nAtualizar nome: ");
			String novoNome = read.nextLine();

			if (escolhaAtualizar == 1) {
				Pessoa atualizarPessoa = pessoaService.readById(atualizarId);
				atualizarPessoa.setNome(novoNome);
				pessoaService.update(atualizarId, atualizarPessoa);
				System.out.println(atualizarPessoa);
				System.out.println("\nCadastro de " + atualizarPessoa.getNome() + " atualizado com sucesso.");
			} else {
				Aluno atualizarAluno = alunoService.readById(atualizarId);
				atualizarAluno.setNome(novoNome);
				alunoService.update(atualizarId, atualizarAluno);
				System.out.println(atualizarAluno);
				System.out.println("\nCadastro de " + atualizarAluno.getNome() + " atualizado com sucesso.");
			}
			break;
		case 2:
			read.nextLine();
			System.out.print("\nAtualizar telefone: ");
			String novoTelefone = read.nextLine();

			if (escolhaAtualizar == 1) {
				Pessoa atualizarPessoa = pessoaService.readById(atualizarId);
				atualizarPessoa.setTelefone(novoTelefone);
				pessoaService.update(atualizarId, atualizarPessoa);
				System.out.println(atualizarPessoa);
				System.out.println("\nCadastro de " + atualizarPessoa.getNome() + " atualizado com sucesso.");
			} else {
				Aluno atualizarAluno = alunoService.readById(atualizarId);
				atualizarAluno.setTelefone(novoTelefone);
				alunoService.update(atualizarId, atualizarAluno);
				System.out.println(atualizarAluno);
				System.out.println("\nCadastro de " + atualizarAluno.getNome() + " atualizado com sucesso.");
			}
			break;
		case 3:
			read.nextLine();
			System.out.print("\nAtualizar data de nascimento: ");
			String novaData = read.nextLine();

			if (escolhaAtualizar == 1) {
				Pessoa atualizarPessoa = pessoaService.readById(atualizarId);
				atualizarPessoa.setDataNascimento(Formatacao.padraoData.parse(novaData));
				pessoaService.update(atualizarId, atualizarPessoa);
				System.out.println(atualizarPessoa);
				System.out.println("\nCadastro de " + atualizarPessoa.getNome() + " atualizado com sucesso.");
			} else {
				Aluno atualizarAluno = alunoService.readById(atualizarId);
				atualizarAluno.setDataNascimento(Formatacao.padraoData.parse(novaData));
				alunoService.update(atualizarId, atualizarAluno);
				System.out.println(atualizarAluno);
				System.out.println("\nCadastro de " + atualizarAluno.getNome() + " atualizado com sucesso.");
			}
			break;
		case 4:
			read.nextLine();
			System.out.print("\nAtualizar nota final: ");
			Double novaNota = read.nextDouble();

			if (escolhaAtualizar == 1) {
				System.out.println("Parâmetro inexistente.");
			} else {
				Aluno atualizarAluno = alunoService.readById(atualizarId);
				atualizarAluno.setNotaFinal(novaNota);
				alunoService.update(atualizarId, atualizarAluno);
				System.out.println(atualizarAluno);
				System.out.println("\nCadastro de " + atualizarAluno.getNome() + " atualizado com sucesso.");
			}
			break;
		case 5:
			System.out.print("\nAtualizar nome: ");
			novoNome = read.nextLine();

			System.out.print("\nAtualizar telefone: ");
			novoTelefone = read.nextLine();

			System.out.print("\nAtualizar data de nascimento: ");
			novaData = read.nextLine();

			if (escolhaAtualizar == 1) {
				Pessoa atualizarPessoa = pessoaService.readById(atualizarId);
				atualizarPessoa.setNome(novoNome);
				atualizarPessoa.setTelefone(novoTelefone);
				atualizarPessoa.setDataNascimento(Formatacao.padraoData.parse(novaData));
				pessoaService.update(atualizarId, atualizarPessoa);
				System.out.println(atualizarPessoa);
				System.out.println("\nCadastro de " + atualizarPessoa.getNome() + " atualizado com sucesso.");
			} else {
				System.out.print("\nAtualizar nota final: ");
				novaNota = read.nextDouble();

				Aluno atualizarAluno = alunoService.readById(atualizarId);
				atualizarAluno.setNome(novoNome);
				atualizarAluno.setTelefone(novoTelefone);
				atualizarAluno.setDataNascimento(Formatacao.padraoData.parse(novaData));
				atualizarAluno.setNotaFinal(novaNota);
				alunoService.update(atualizarId, atualizarAluno);
				System.out.println(atualizarAluno);
				System.out.println("\nCadastro de " + atualizarAluno.getNome() + " atualizado com sucesso.");
			}
			break;
		case 6:
			break;
		default:
			throw new IllegalArgumentException("\nOpção Inválida: " + opcoesAtualizar);
		}

	}

	public static void deletar(Scanner read, PessoaService pessoaService, AlunoService alunoService) {
		System.out.print("1.Pessoa ou 2.Aluno? ");
		int escolhaDeletar = read.nextInt();

		System.out.print("\nInforme o telefone cadastrado: ");
		Long deletar = read.nextLong();

		if (escolhaDeletar == 1) {
			System.out.println(pessoaService.readById(deletar));
			System.out.println("\nCadastro de " + pessoaService.readById(deletar).getNome() + " removido com sucesso.");
			pessoaService.remove(deletar);
		} else {
			System.out.println(alunoService.readById(deletar));
			System.out.println("\nCadastro de " + alunoService.readById(deletar).getNome() + " removido com sucesso.");
			alunoService.remove(deletar);
		}

	}

}
