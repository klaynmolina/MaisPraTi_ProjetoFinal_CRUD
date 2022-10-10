package primeira.versao.complementos;

import java.text.SimpleDateFormat;

public class Formatacao {

	public static SimpleDateFormat padraoData = new SimpleDateFormat("dd/MM/yyyy");
	
	public static String padraoTelefone (String telefone) {
		telefone  = "(" + telefone.substring(0,2) + ") " + telefone.substring(2, 7) + "-" + telefone.substring(7,11);
		return telefone;
	}

}
