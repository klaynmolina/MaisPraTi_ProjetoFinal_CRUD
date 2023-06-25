package crud.complementos;

import java.text.ParseException;

import crud.model.Aluno;
import crud.model.Usuario;
import crud.service.AlunoService;
import crud.service.PessoaService;

public class Inicializar {
	
	public static PessoaService iniciarPessoa(PessoaService pessoaService) throws ParseException {
		Usuario pessoa1 = new Usuario("Benício Nunes", "21981531834", "06/03/1987");
		Usuario pessoa2 = new Usuario("Davi Duarte", "21987883715", "02/03/1990");
		Usuario pessoa3 = new Usuario("Bianca Aragão", "21979313607", "27/10/1992");
		Usuario pessoa4 = new Usuario("Lavínia Viana", "21988887315", "19/05/1970");
		pessoaService.create(pessoa1);
		pessoaService.create(pessoa2);
		pessoaService.create(pessoa3);
		pessoaService.create(pessoa4);
		return pessoaService;
	}
	
	public static AlunoService iniciarAluno(AlunoService alunoService) throws ParseException {
		Aluno aluno1 = new Aluno("João Felipe Moreira", "21979463931", "14/03/1973", 9.5);
		Aluno aluno2 = new Aluno("Yasmin Costa", "21974709723", "14/05/1997", 7.3);
		Aluno aluno3 = new Aluno("Lorenzo Ribeiro", "21981614204", "07/02/1982", 8.7);
		Aluno aluno4 = new Aluno("Emilly Pereira", "21988135788", "05/09/1997", 5.9);
		alunoService.create(aluno1);
		alunoService.create(aluno2);
		alunoService.create(aluno3);
		alunoService.create(aluno4);
		return alunoService;
	}

}
