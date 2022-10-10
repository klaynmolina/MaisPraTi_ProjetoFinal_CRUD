package primeira.versao.model;

import java.text.ParseException;

import primeira.versao.complementos.Formatacao;

public class Aluno extends Pessoa {
	
	private Double notaFinal;

	public Aluno(String nome, String telefone, String dataNascimento, Double notaFinal) throws ParseException {
		super(nome, telefone, dataNascimento);
		this.notaFinal = notaFinal;
	}

	public Double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(Double notaFinal) {
		this.notaFinal = notaFinal;
	}
	
	@Override
	public String toString() {
		return "\nALUNO\n"+
				"\nNome: " + nome + 
				"\nTelefone: " + Formatacao.padraoTelefone(telefone) +
				"\nData de Nascimento: " + Formatacao.padraoData.format(dataNascimento) + 
				"\nData de Cadastro: " + Formatacao.padraoData.format(dataCadastro) + 
				"\nData da Última Alteracao: " + Formatacao.padraoData.format(dataUltimaAlteracao) +
				"\nNota Final: " + notaFinal;
	}
}
