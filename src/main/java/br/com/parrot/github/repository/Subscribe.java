package br.com.parrot.github.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Subscribe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subscribe sub = new Subscribe();
		String email = "jcsouza.first@gmail.com";
		sub.writeEmailOnTxt(email);
	}

	public void writeEmailOnTxt(String email){
		try{
			String path = this.getClass().getResource("/email.txt").getPath();
			File file = new File(path);
			FileWriter fw = new FileWriter(file);
			fw.write(email + ";");
			fw.close();
		}catch (IOException e ){
			e.printStackTrace();
		}
		
	}
}
