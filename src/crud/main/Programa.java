package crud.main;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import crud.complementos.Inicializar;
import crud.exceptions.CadastroDuplicado;
import crud.exceptions.CadastroInvalido;
import crud.exceptions.CadastrosInexistentes;
import crud.exceptions.IdInexistente;
import crud.menu.menus;
import crud.service.AlunoService;
import crud.service.PessoaService;

public class Programa {

	public static void main(String[] args) throws ParseException, InterruptedException {

		Scanner read = new Scanner(System.in);

		PessoaService pessoaService = new PessoaService(read);
		AlunoService alunoService = new AlunoService(read);

		// INICIALIZAÇÃO PRONTA
		//Inicializar.iniciarAluno(alunoService);
		//Inicializar.iniciarPessoa(pessoaService);

		boolean controle = true;
		do {
			try {
				int iniciarControle = menus.menuInicial(read);

				switch (iniciarControle) {
				case 1:
					System.out.println("\n*******************************");
					System.out.println("\nCADASTRAR\n");
					try {
						menus.cadastrar(read, pessoaService, alunoService);
					} catch (ParseException e1) {
						System.out.println("\nInformações inválidas para criação de cadastro.");
					} catch (CadastroDuplicado e1) {
						System.out.println(e1.getMessage());
					} catch (CadastroInvalido e1) {
						System.out.println(e1.getMessage());
					}
					break;
				case 2:
					System.out.println("\n*******************************");
					System.out.println("\nTODOS OS CADASTROS\n");
					try {
						menus.todosOsCadastros(pessoaService, alunoService);
					} catch (CadastrosInexistentes e1) {
						System.out.println(e1.getMessage());
					} catch (NullPointerException e1) {
						System.out.println("\nAinda não existem cadastros.");
					}
					break;
				case 3:
					System.out.println("\n*******************************");
					System.out.println("\nATUALIZAR DADOS\n");
					System.out.print("1. Pessoa ou 2. Aluno? ");
					int escolhaAtualizar = read.nextInt();

					System.out.print("\nInforme o telefone cadastrado: ");
					Long atualizarId = read.nextLong();

					try {
						if (escolhaAtualizar == 1) {
							if (pessoaService.readById(atualizarId) == null) {
								throw new IdInexistente("\nCadastro inexistente.");
							}
						} else {
							if (alunoService.readById(atualizarId) == null) {
								throw new IdInexistente("\nCadastro inexistente.");
							}
						}

						menus.menuAtualizar();
						try {
							menus.atualizar(read, pessoaService, alunoService, escolhaAtualizar, atualizarId);
						} catch (StringIndexOutOfBoundsException e) {
							System.out.println("\nO número de telefone informado é inválido.");
						} catch (ParseException e) {
							System.out.println("\nA data de nascimento informada é inválida.");
						} catch (InputMismatchException e) {
							System.out.println("\nNota informada é inválida.");
							read.next();
						}
					} catch (IdInexistente e2) {
						System.out.println(e2.getMessage());
					}
					break;
				case 4:
					System.out.println("\n*******************************");
					System.out.println("\nDELETAR\n");
					try {
						menus.deletar(read, pessoaService, alunoService);
					} catch (IdInexistente e) {
						System.out.println(e.getMessage());
					}
					break;
				case 0:
					System.out.println("\nSISTEMA ENCERRADO.\n");
					System.out.println("*******************************");
					controle = false;
					break;
				default:
					throw new IllegalArgumentException("\nOpção Inválida: " + iniciarControle);
				}

			} catch (InputMismatchException e) {
				System.out.println("\nOpção escolhida é Inválida.");
				read.next();
			} catch (NumberFormatException e) {
				System.out.println("\nO número de telefone informado é inválido. Tente novamente.");
			} catch (RuntimeException e) {
				System.out.println("\nUm erro desconhecido ocorreu.");
				System.out.println(e.getMessage());
			} finally {
				Thread.sleep(1500l);
			}

		} while (controle);
	}

}
