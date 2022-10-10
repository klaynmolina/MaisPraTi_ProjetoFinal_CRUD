package primeira.versao.complementos;

import java.text.ParseException;

import primeira.versao.model.Aluno;
import primeira.versao.model.Pessoa;
import primeira.versao.service.AlunoService;
import primeira.versao.service.PessoaService;

public class Inicializar {
	
	public static PessoaService iniciarPessoa(PessoaService pessoaService) throws ParseException {
		Pessoa pessoa1 = new Pessoa("Benício Nunes", "21981531834", "06/03/1987");
		Pessoa pessoa2 = new Pessoa("Davi Duarte", "21987883715", "02/03/1990");
		Pessoa pessoa3 = new Pessoa("Bianca Aragão", "21979313607", "27/10/1992");
		Pessoa pessoa4 = new Pessoa("Lavínia Viana", "21988887315", "19/05/1970");
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
