package crud.exceptions;

public class CadastrosInexistentes extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CadastrosInexistentes(String msg) {
		super(msg);
	}

}
