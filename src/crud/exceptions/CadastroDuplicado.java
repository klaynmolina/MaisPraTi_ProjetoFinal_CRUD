package primeira.versao.exceptions;

public class CadastroDuplicado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CadastroDuplicado(String msg) {
		super(msg);
	}

}
