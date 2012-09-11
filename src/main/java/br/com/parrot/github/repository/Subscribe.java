package br.com.parrot.github.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Subscribe {
	public void writeEmailOnTxt(String email){
		try{
			String path = "//home//ubuntu//";
			File file = new File(path + "email.txt");
			FileWriter fw = new FileWriter(file,true);
			fw.write(email + ";");
			fw.close();
		}catch (IOException e ){
			e.printStackTrace();
		}
		
	}
}
