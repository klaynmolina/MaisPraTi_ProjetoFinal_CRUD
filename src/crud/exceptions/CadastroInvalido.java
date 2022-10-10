package crud.exceptions;

public class CadastroInvalido extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CadastroInvalido(String msg) {
		super(msg);
	}

}
