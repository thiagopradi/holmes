package model;

import java.util.ArrayList;

public class MainClass {
	public static void main(String args[]) {
		ArrayList<Empresa> arr = new ArrayList<Empresa>();
		
		Empresa e = new Empresa();
		
		e.setName("Submarino");
		e.setTwitterAccount("@novosubmarino");
		e.setFacebookPage("http://www.facebook.com/pages/Radar-Submarino/133481940001233");
		ArrayList<String> a = new ArrayList<String>();
		a.add("@SubViagens");
		a.add("@cartaosubmarino");
		e.setOutrosNomes(a);
		arr.add(e);
		
		e = new Empresa();
		e.setName("Americanas");
		e.setTwitterAccount("@americanascom");
		e.setFacebookPage("http://www.facebook.com/pages/Lojas-Americanas-SA/125497644154412");
		a = new ArrayList<String>();
		a.add("#lojasamericanas");
		a.add("Lojas Americanas");
		a.add("americanas");
		e.setOutrosNomes(a);

		
		arr.add(e);
	}	
}
