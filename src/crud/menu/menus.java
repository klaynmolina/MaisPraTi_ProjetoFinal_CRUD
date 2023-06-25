package crud.menu;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import crud.complementos.Formatacao;
import crud.exceptions.CadastroDuplicado;
import crud.exceptions.CadastroInvalido;
import crud.model.Aluno;
import crud.model.Usuario;
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
		String recebeControle = read.nextLine();
		
		if (recebeControle.isBlank()) {
			throw new CadastroInvalido("\nOpção inválida. Opção escolhida está em branco.");
		}
		
		int iniciarControle = Integer.parseInt(recebeControle);		
		return iniciarControle;
	}

	public static void cadastrar(Scanner read, PessoaService pessoaService, AlunoService alunoService)
			throws ParseException {
		System.out.print("\nNome: ");
		String nome = read.nextLine();

		if (nome.isBlank()) {
			throw new CadastroInvalido("\nCadastro inválido. Campo nome em branco.");
		}

		System.out.print("\nTelefone com DDD (11 digitos sem separação): ");
		String celular = read.nextLine();
		
		if(verificarCadastros(celular, pessoaService, alunoService)) {
			throw new CadastroDuplicado("\nCadastro inválido. Número informado já vinculado a outro cadastro.");
		}
		
		
		if (celular.isBlank()) {
			throw new CadastroInvalido("\nCadastro inválido. Campo celular em branco.");
		} else if (celular.length() != 11) {
			throw new CadastroInvalido("\nCadastro inválido. O número de celular informado é inválido.");
		} 

		System.out.print("\nData de nascimento (dd/MM/yyyy): ");
		String dataNascimento = read.nextLine();

		if (dataNascimento.isBlank()) {
			throw new CadastroInvalido("\nCadastro inválido. Campo data de nascimento em branco.");
		}

		System.out.print("\nDeseja informar nota final do curso? 1. Sim / 2. Não ");
		String recebeResposta = read.nextLine();
		int resposta = Integer.parseInt(recebeResposta);

		if (resposta == 1 || resposta == 2) {
			if (resposta == 1) {
				System.out.print("\nNota Final (Separado com ponto): ");
				String recebeNota = read.nextLine();
				
				if (recebeNota.isBlank()) {
					throw new CadastroInvalido("\nCadastro inválido. Campo nota em branco.");
				}
				
				Double notaFinal = Double.parseDouble(recebeNota);				
				if (notaFinal < 0 || notaFinal > 100) {
					throw new CadastroInvalido("\nNota inválida.");
				} else {
					Aluno aluno = new Aluno(nome, celular, dataNascimento, notaFinal);
					alunoService.create(aluno);
					System.out.println(aluno);
					System.out.println("\nCadastro salvo com sucesso.");
				}
			} else {
				Usuario pessoa = new Usuario(nome, celular, dataNascimento);
				pessoaService.create(pessoa);
				System.out.println(pessoa);
				System.out.println("\nCadastro salvo com sucesso.");
			}
		} else {
			System.out.println("\nOpção Inválida.");
		}
	}

	public static void todosOsCadastros(PessoaService pessoaService, AlunoService alunoService) {
		pessoaService.readAll().stream().map(a -> a + "\n").forEach(System.out::println);
		alunoService.readAll().stream().map(a -> a + "\n").forEach(System.out::println);		
	}
	
	public static boolean verificarCadastros(String telefone, PessoaService pessoaService, AlunoService alunoService) {
		List<Usuario> pessoas = pessoaService.readAll();
		List<Aluno> alunos = alunoService.readAll();
		
		for(Usuario p: pessoas) {
			if(Long.parseLong(telefone) == p.getTelefone()) {
				return true;
			}
		}
		for(Aluno a: alunos ) {
			if(Long.parseLong(telefone) == a.getTelefone()) {
				return true;
			}
		}
		return false;		
	}
	
	public static void menuAtualizar() {
		System.out.println("\nQual dado deseja atualizar?\n");
		System.out.println("1. Atualizar nome.");
		System.out.println("2. Atualizar celular com DDD (11 digitos sem separação).");
		System.out.println("3. Atualizar data de nascimento (dd/MM/yyyy).");
		System.out.println("4. Atualizar nota final (Separado com ponto).");
		System.out.println("5. Atualizar todos os dados.");
		System.out.println("6. Cancelar operação.");
		System.out.print("\nDigite a opção desejada: ");
	}

	public static void atualizar(Scanner read, PessoaService pessoaService, AlunoService alunoService,
			int escolhaAtualizar, Long atualizarTelefone) throws ParseException {
		String recebeOpcoesAtualizar = read.nextLine();
		int opcoesAtualizar = Integer.parseInt(recebeOpcoesAtualizar);
		switch (opcoesAtualizar) {
		case 1:			
			System.out.print("\nAtualizar nome: ");
			String novoNome = read.nextLine();
			
			if (novoNome.isBlank()) {
				throw new CadastroInvalido("\nCadastro inválido. Campo nome em branco.");
			}

			if (escolhaAtualizar == 1) {
				Usuario atualizarPessoa = pessoaService.readByTelefone(atualizarTelefone);
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
			System.out.print("\nAtualizar celular (11 digitos sem separação): ");
			String novoTelefone = read.nextLine();
			
			if (novoTelefone.isBlank()) {
				throw new CadastroInvalido("\nCadastro inválido. Campo celular em branco.");
			}
			
			if(verificarCadastros(novoTelefone, pessoaService, alunoService)) {
				throw new CadastroDuplicado("\nCadastro inválido. Número informado já vinculado a outro cadastro.");
			}

			try {
				Long.parseLong(novoTelefone);
			} catch (NumberFormatException e) {
				System.out.println("\nO número de celular informado é inválido.");
				break;
			}

			if (escolhaAtualizar == 1) {
				Usuario atualizarPessoa = pessoaService.readByTelefone(atualizarTelefone);
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
			System.out.print("\nAtualizar data de nascimento (dd/MM/yyyy): ");
			String novaData = read.nextLine();
			
			if (novaData.isBlank()) {
				throw new CadastroInvalido("\nCadastro inválido. Campo data de nascimento em branco.");
			}

			if (escolhaAtualizar == 1) {
				Usuario atualizarPessoa = pessoaService.readByTelefone(atualizarTelefone);
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
			System.out.print("\nAtualizar nota final (Separado com ponto): ");
			String recebeNota = read.nextLine();
			
			if (recebeNota.isBlank()) {
				throw new CadastroInvalido("\nCadastro inválido. Campo nota em branco.");
			}
			
			Double novaNota = Double.parseDouble(recebeNota);	
			if (escolhaAtualizar == 1) {
				System.out.println("\nParâmetro inexistente em cadastro tipo pessoa.");
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
			
			if (novoNome.isBlank()) {
				throw new CadastroInvalido("\nCadastro inválido. Campo nome em branco.");
			}

			System.out.print("\nAtualizar celular (11 digitos sem separação): ");
			novoTelefone = read.nextLine();
			
			if (novoTelefone.isBlank()) {
				throw new CadastroInvalido("\nCadastro inválido. Campo celular em branco.");
			}
			
			if(verificarCadastros(novoTelefone, pessoaService, alunoService)) {
				throw new CadastroDuplicado("\nCadastro inválido. Número informado já vinculado a outro cadastro.");
			}

			System.out.print("\nAtualizar data de nascimento (dd/MM/yyyy): ");
			novaData = read.nextLine();
			
			if (novaData.isBlank()) {
				throw new CadastroInvalido("\nCadastro inválido. Campo data de nascimento em branco.");
			}

			if (escolhaAtualizar == 1) {
				Usuario atualizarPessoa = pessoaService.readByTelefone(atualizarTelefone);
				atualizarPessoa.setNome(novoNome);
				atualizarPessoa.setTelefone(novoTelefone);
				atualizarPessoa.setDataNascimento(Formatacao.padraoData.parse(novaData));
				pessoaService.update(atualizarTelefone, atualizarPessoa);
				System.out.println(atualizarPessoa);
				System.out.println("\nCadastro de " + atualizarPessoa.getNome() + " atualizado com sucesso.");
			} else {
				System.out.print("\nAtualizar nota final (Separado com vírgula): ");
				recebeNota = read.nextLine();
				
				if (recebeNota.isBlank()) {
					throw new CadastroInvalido("\nCadastro inválido. Campo nota em branco.");
				}
				
				novaNota = Double.parseDouble(recebeNota);	

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
			throw new IllegalArgumentException("\nOpção Inválida: " + opcoesAtualizar);
		}

	}

	public static void deletar(Scanner read, PessoaService pessoaService, AlunoService alunoService) {
		System.out.print("1.Pessoa ou 2.Aluno? ");
		String recebeEscolhaDeletar = read.nextLine();
		int escolhaDeletar = Integer.parseInt(recebeEscolhaDeletar);

		if (escolhaDeletar == 1 || escolhaDeletar == 2) {			
			System.out.print("\nInforme o celular cadastrado: ");			
			String recebeDeletar = read.nextLine();
			
			if (recebeDeletar.isBlank()) {
				throw new CadastroInvalido("\nCampo inválido, número de celular em branco.");
			}

			Long deletar = Long.parseLong(recebeDeletar);
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
			System.out.println("\nOpção Inválida.");
		}

	}

}
