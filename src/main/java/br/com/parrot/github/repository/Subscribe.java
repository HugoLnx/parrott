package br.com.parrot.github.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Subscribe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Subscribe sub = new Subscribe();
		String email = "jcsouza.first@gmail.com";
		sub.writeEmailOnTxt(email);
	}

	public void writeEmailOnTxt(String email){
		try{
			//String path = this.getClass().getResource("/email.txt").getPath();
			String path = "//home//parrot//";
			System.out.println(path);
			File file = new File(path + "email.txt");
			FileWriter fw = new FileWriter(file,true);
			fw.write(email + ";");
			fw.close();
		}catch (IOException e ){
			e.printStackTrace();
		}
		
	}
}
