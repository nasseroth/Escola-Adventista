package br.com.jetapps.app.util.zone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
// Feito para testes
public class Zones {
	//private final Log logger = LogFactory.getLog(this.getClass());
	public String getDataHoraAtual() { 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		Date date = new Date(); 
		//logger.info("Retornando a data: "+date);
		return dateFormat.format(date); 
	}
}
