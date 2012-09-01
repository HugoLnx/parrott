package br.com.parrot.github.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Teste {
	
	/** Parse conteudo do commit para o tipo Line*/
	public static void main(String [] args){
		String teste = "@@ -16,27 +16,31 @@ class LivroCatalogo < ActiveRecord::Base\n \n   validates_associated :autores, :espiritos, :assuntos\n \n-  def self.factory(options)\n-    livro = LivroCatalogo.new\n+  def self.to_attrs(options)\n+    attrs = {}\n \n-    livro.titulo = options[:titulos].delete_at 0\n-    livro.titulos_antigos = options[:titulos]\n+    attrs[:titulo] = options[:titulos].delete_at 0\n+    attrs[:titulos_antigos] = options[:titulos]\n \n-    livro.cdd = options[:cdd]\n+    attrs[:cdd] = options[:cdd]\n+\n+    attrs[:autores] = []\n+    attrs[:espiritos] = []\n+    attrs[:assuntos] = []\n \n     options[:autores].each do |nome|\n-      livro.autores << Autor.new(nome: nome)\n+      attrs[:autores] << Autor.new(nome: nome)\n     end\n \n     options[:espiritos].each do |nome|\n-      livro.espiritos << Espirito.new(nome: nome)\n+      attrs[:espiritos] << Espirito.new(nome: nome)\n     end\n \n     options[:assuntos].each do |tipo|\n-      livro.assuntos << Assunto.new(tipo: tipo)\n+      attrs[:assuntos] << Assunto.new(tipo: tipo)\n     end\n \n-    return livro\n+    return attrs\n   end\n \n   def titulos_antigos";
		
		String [] resposta = teste.split(Pattern.quote("\n"));
		List<Line> text = new ArrayList<Line>();
		
		for ( String a : resposta ){
			if( a.startsWith("@") ){
				continue;
			}
			
			Line line = new Line();
			line.setContent(a);
			
			if ( a.startsWith("+") ){
				line.setStatus(StatusLine.ADDED);
			}else if( a.startsWith("-") ){
				line.setStatus(StatusLine.REMOVED);
			}else{
				line.setStatus(StatusLine.NOT_MODIFIED);
			}
			
			text.add(line);
		}
	}
	
}
