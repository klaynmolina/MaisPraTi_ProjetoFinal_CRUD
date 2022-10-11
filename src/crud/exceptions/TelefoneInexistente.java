package crud.exceptions;

public class TelefoneInexistente extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TelefoneInexistente(String msg) {
		super(msg);
	}

}
