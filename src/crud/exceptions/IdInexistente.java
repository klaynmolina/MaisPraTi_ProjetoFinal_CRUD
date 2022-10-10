package crud.exceptions;

public class IdInexistente extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IdInexistente(String msg) {
		super(msg);
	}

}
