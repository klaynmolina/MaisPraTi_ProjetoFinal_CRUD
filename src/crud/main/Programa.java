package crud.main;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import crud.complementos.Inicializar;
import crud.exceptions.CadastroDuplicado;
import crud.exceptions.CadastroInvalido;
import crud.exceptions.CadastrosInexistentes;
import crud.exceptions.TelefoneInexistente;
import crud.menu.menus;
import crud.service.AlunoService;
import crud.service.PessoaService;

public class Programa {

	public static void main(String[] args) throws ParseException, InterruptedException {

		Scanner read = new Scanner(System.in);

		PessoaService pessoaService = new PessoaService(read);
		AlunoService alunoService = new AlunoService(read);

		// INICIALIZA��O PRONTA
		Inicializar.iniciarAluno(alunoService);
		Inicializar.iniciarPessoa(pessoaService);

		boolean controle = true;
		do {
			try {
				String recebeControle = String.valueOf(menus.menuInicial(read));
				int iniciarControle = Integer.parseInt(recebeControle);

				switch (iniciarControle) {
				case 1:
					System.out.println("\n*******************************");
					System.out.println("\nCADASTRAR\n");
					try {
						menus.cadastrar(read, pessoaService, alunoService);
					} catch (ParseException e1) {
						System.out.println("\nInforma��es inv�lidas para cria��o de cadastro.");
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
						System.out.println("\nAinda n�o existem cadastros.");
					}
					break;
				case 3:
					System.out.println("\n*******************************");
					System.out.println("\nATUALIZAR DADOS\n");
					System.out.print("1. Pessoa ou 2. Aluno? ");
					String recebeEscolhaAtualizar = read.nextLine();
					int escolhaAtualizar = Integer.parseInt(recebeEscolhaAtualizar);

					if (escolhaAtualizar == 1 || escolhaAtualizar == 2) {
						System.out.print("\nInforme o celular cadastrado: ");
						String recebeAtualizarTelefone = read.nextLine();
						
						if (recebeAtualizarTelefone.isBlank()) {
							throw new CadastroInvalido("\nCampo inv�lido, n�mero de celular em branco.");
						}

						Long atualizarTelefone = Long.parseLong(recebeAtualizarTelefone);
						try {
							if (escolhaAtualizar == 1) {
								pessoaService.readByTelefone(atualizarTelefone);
							} else {
								alunoService.readByTelefone(atualizarTelefone);
							}

							menus.menuAtualizar();
							try {								
								menus.atualizar(read, pessoaService, alunoService, escolhaAtualizar, atualizarTelefone);
							} catch (StringIndexOutOfBoundsException e) {
								System.out.println("\nO n�mero de celular informado � inv�lido.");
							} catch (ParseException e) {
								System.out.println("\nA data de nascimento informada � inv�lida.");
							} catch (InputMismatchException e) {
								System.out.println("\nNota informada � inv�lida.");
								read.next();
							}
						} catch (TelefoneInexistente e2) {
							System.out.println(e2.getMessage());
						}						
						break;
					} else {
						System.out.println("\nOp��o Inv�lida.");
					}
					break;
				case 4:
					System.out.println("\n*******************************");
					System.out.println("\nDELETAR\n");
					try {
						menus.deletar(read, pessoaService, alunoService);
					} catch (TelefoneInexistente e) {
						System.out.println(e.getMessage());
					}
					break;
				case 0:
					System.out.println("\nSISTEMA ENCERRADO.\n");
					System.out.println("*******************************");
					controle = false;
					break;
				default:
					throw new IllegalArgumentException("\nOp��o Inv�lida: " + iniciarControle);
				}

			} catch (InputMismatchException e) {
				System.out.println("\nOp��o Inv�lida.");
				read.next();
			} catch (NumberFormatException e) {
//				System.out.println("\nO n�mero de celular informado � inv�lido.");
				System.out.println("\nInser��o de dados inv�lida.");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} finally {
				Thread.sleep(1500l);
			}

		} while (controle);
	}

}
