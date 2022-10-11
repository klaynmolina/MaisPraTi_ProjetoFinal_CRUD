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
		System.out.print("\nDigite a op��o desejada: ");
		int iniciarControle = read.nextInt();
		return iniciarControle;
	}

	public static void cadastrar(Scanner read, PessoaService pessoaService, AlunoService alunoService)
			throws ParseException {
		read.nextLine();
		System.out.print("\nNome: ");
		String nome = read.nextLine();
		System.out.print("\nTelefone com DDD (11 digitos sem separa��o): ");
		String celular = read.nextLine();
		System.out.print("\nData de nascimento (dd/MM/yyyy): ");
		String dataNascimento = read.nextLine();

		System.out.print("\nDeseja informar nota final do curso? 1. Sim / 2. N�o ");
		int resposta = read.nextInt();

		if (resposta == 1 || resposta == 2) {
			if (resposta == 1) {
				System.out.print("\nNota Final (Separado com v�rgula): ");
				Double notaFinal = read.nextDouble();
				if (notaFinal < 0 || notaFinal > 100) {
					throw new CadastroInvalido("\nNota inv�lida.");
				} else {
					Aluno aluno = new Aluno(nome, celular, dataNascimento, notaFinal);
					if (aluno.getNome().isBlank()) {
						throw new CadastroInvalido("\nCampo nome est� em branco. Cadastro inv�lido.");
					} else {
						alunoService.create(aluno);
						System.out.println(aluno);
						System.out.println("\nCadastro salvo com sucesso.");
					}
				}
			} else {
				Pessoa pessoa = new Pessoa(nome, celular, dataNascimento);
				if (pessoa.getNome().isBlank()) {
					throw new CadastroInvalido("\nCampo nome est� em branco. Cadastro inv�lido.");
				} else {
					pessoaService.create(pessoa);
					System.out.println(pessoa);
					System.out.println("\nCadastro salvo com sucesso.");
				}
			}
		} else {
			System.out.println("\nOp��o Inv�lida.");
		}
	}

	public static void todosOsCadastros(PessoaService pessoaService, AlunoService alunoService) {
		pessoaService.readAll();
		alunoService.readAll();
	}

	public static void menuAtualizar() {
		System.out.println("\nQual dado deseja atualizar?\n");
		System.out.println("1. Atualizar nome.");
		System.out.println("2. Atualizar celular com DDD (11 digitos sem separa��o).");
		System.out.println("3. Atualizar data de nascimento (dd/MM/yyyy).");
		System.out.println("4. Atualizar nota final (Separado com v�rgula).");
		System.out.println("5. Atualizar todos os dados.");
		System.out.println("6. Cancelar opera��o.");
		System.out.print("\nDigite a op��o desejada: ");
	}

	public static void atualizar(Scanner read, PessoaService pessoaService, AlunoService alunoService,
			int escolhaAtualizar, Long atualizarTelefone) throws ParseException {
		int opcoesAtualizar = read.nextInt();
		switch (opcoesAtualizar) {
		case 1:
			read.nextLine();
			System.out.print("\nAtualizar nome: ");
			String novoNome = read.nextLine();

			if (escolhaAtualizar == 1) {
				Pessoa atualizarPessoa = pessoaService.readByTelefone(atualizarTelefone);
				atualizarPessoa.setNome(novoNome);
				pessoaService.update(atualizarTelefone, atualizarPessoa);
				System.out.println(atualizarPessoa);
				System.out.println("\nCadastro de " + atualizarPessoa.getNome() + " atualizado com sucesso.");
			} else {
				Aluno atualizarAluno = alunoService.readByTelefone(atualizarTelefone);
				atualizarAluno.setNome(novoNome);
				alunoService.update(atualizarTelefone, atualizarAluno);
				System.out.println(atualizarAluno);
				System.out.println("\nCadastro de " + atualizarAluno.getNome() + " atualizado com sucesso.");
			}
			break;
		case 2:
			read.nextLine();
			System.out.print("\nAtualizar celular (11 digitos sem separa��o): ");
			String novoTelefone = read.nextLine();

			try {
				Long.parseLong(novoTelefone);
			} catch (NumberFormatException e) {
				System.out.println("\nO n�mero de celular informado � inv�lido.");
				break;
			}

			if (escolhaAtualizar == 1) {
				Pessoa atualizarPessoa = pessoaService.readByTelefone(atualizarTelefone);
				atualizarPessoa.setTelefone(novoTelefone);
				pessoaService.update(atualizarTelefone, atualizarPessoa);
				System.out.println(atualizarPessoa);
				System.out.println("\nCadastro de " + atualizarPessoa.getNome() + " atualizado com sucesso.");
			} else {
				Aluno atualizarAluno = alunoService.readByTelefone(atualizarTelefone);
				atualizarAluno.setTelefone(novoTelefone);
				alunoService.update(atualizarTelefone, atualizarAluno);
				System.out.println(atualizarAluno);
				System.out.println("\nCadastro de " + atualizarAluno.getNome() + " atualizado com sucesso.");
			}
			break;
		case 3:
			read.nextLine();
			System.out.print("\nAtualizar data de nascimento (dd/MM/yyyy): ");
			String novaData = read.nextLine();

			if (escolhaAtualizar == 1) {
				Pessoa atualizarPessoa = pessoaService.readByTelefone(atualizarTelefone);
				atualizarPessoa.setDataNascimento(Formatacao.padraoData.parse(novaData));
				pessoaService.update(atualizarTelefone, atualizarPessoa);
				System.out.println(atualizarPessoa);
				System.out.println("\nCadastro de " + atualizarPessoa.getNome() + " atualizado com sucesso.");
			} else {
				Aluno atualizarAluno = alunoService.readByTelefone(atualizarTelefone);
				atualizarAluno.setDataNascimento(Formatacao.padraoData.parse(novaData));
				alunoService.update(atualizarTelefone, atualizarAluno);
				System.out.println(atualizarAluno);
				System.out.println("\nCadastro de " + atualizarAluno.getNome() + " atualizado com sucesso.");
			}
			break;
		case 4:
			read.nextLine();
			System.out.print("\nAtualizar nota final (Separado com v�rgula): ");
			Double novaNota = read.nextDouble();

			if (escolhaAtualizar == 1) {
				System.out.println("Par�metro inexistente.");
			} else {
				Aluno atualizarAluno = alunoService.readByTelefone(atualizarTelefone);
				atualizarAluno.setNotaFinal(novaNota);
				alunoService.update(atualizarTelefone, atualizarAluno);
				System.out.println(atualizarAluno);
				System.out.println("\nCadastro de " + atualizarAluno.getNome() + " atualizado com sucesso.");
			}
			break;
		case 5:
			System.out.print("\nAtualizar nome: ");
			novoNome = read.nextLine();

			System.out.print("\nAtualizar celular (11 digitos sem separa��o): ");
			novoTelefone = read.nextLine();

			System.out.print("\nAtualizar data de nascimento (dd/MM/yyyy): ");
			novaData = read.nextLine();

			if (escolhaAtualizar == 1) {
				Pessoa atualizarPessoa = pessoaService.readByTelefone(atualizarTelefone);
				atualizarPessoa.setNome(novoNome);
				atualizarPessoa.setTelefone(novoTelefone);
				atualizarPessoa.setDataNascimento(Formatacao.padraoData.parse(novaData));
				pessoaService.update(atualizarTelefone, atualizarPessoa);
				System.out.println(atualizarPessoa);
				System.out.println("\nCadastro de " + atualizarPessoa.getNome() + " atualizado com sucesso.");
			} else {
				System.out.print("\nAtualizar nota final (Separado com v�rgula): ");
				novaNota = read.nextDouble();

				Aluno atualizarAluno = alunoService.readByTelefone(atualizarTelefone);
				atualizarAluno.setNome(novoNome);
				atualizarAluno.setTelefone(novoTelefone);
				atualizarAluno.setDataNascimento(Formatacao.padraoData.parse(novaData));
				atualizarAluno.setNotaFinal(novaNota);
				alunoService.update(atualizarTelefone, atualizarAluno);
				System.out.println(atualizarAluno);
				System.out.println("\nCadastro de " + atualizarAluno.getNome() + " atualizado com sucesso.");
			}
			break;
		case 6:
			break;
		default:
			throw new IllegalArgumentException("\nOp��o Inv�lida: " + opcoesAtualizar);
		}

	}

	public static void deletar(Scanner read, PessoaService pessoaService, AlunoService alunoService) {
		System.out.print("1.Pessoa ou 2.Aluno? ");
		int escolhaDeletar = read.nextInt();

		if (escolhaDeletar == 1 || escolhaDeletar == 2) {
			System.out.print("\nInforme o celular cadastrado: ");
			Long deletar = read.nextLong();

			if (escolhaDeletar == 1) {
				System.out.println(pessoaService.readByTelefone(deletar));
				System.out.println(
						"\nCadastro de " + pessoaService.readByTelefone(deletar).getNome() + " removido com sucesso.");
				pessoaService.remove(deletar);
			} else {
				System.out.println(alunoService.readByTelefone(deletar));
				System.out.println(
						"\nCadastro de " + alunoService.readByTelefone(deletar).getNome() + " removido com sucesso.");
				alunoService.remove(deletar);
			}
		} else {
			System.out.println("\nOp��o Inv�lida.");
		}

	}

}
