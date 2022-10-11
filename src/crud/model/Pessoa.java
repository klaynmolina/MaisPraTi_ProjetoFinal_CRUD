package crud.model;

import java.text.ParseException;
import java.util.Date;

import crud.complementos.Formatacao;

public class Pessoa { 
	
	protected String nome;
	protected String telefone;
	protected Date dataNascimento;
	protected Date dataCadastro;
	protected Date dataUltimaAlteracao;	
	
	
	public Pessoa(String nome, String telefone, String dataNascimento) throws ParseException {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = Formatacao.padraoData.parse(dataNascimento);
		this.dataCadastro = new Date();
		this.dataUltimaAlteracao = new Date();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getTelefone() {
		return Long.parseLong(telefone);
	}

	public void setTelefone(String telefone) {		
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nPESSOA\n"+
				"\nNome: " + nome + 
				"\nCelular: " + Formatacao.padraoTelefone(telefone) +
				"\nData de Nascimento: " + Formatacao.padraoData.format(dataNascimento) + 
				"\nData de Cadastro: " + Formatacao.padraoData.format(dataCadastro) + 
				"\nData da Última Alteracao: " + Formatacao.padraoData.format(dataUltimaAlteracao);
	}
	
}
