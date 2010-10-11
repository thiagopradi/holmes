package model;

import java.util.List;

public class Empresa {
	private String name;
	private String twitterAccount;
	private String facebookPage;
	private Data data;
	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public Empresa(String name, String twitterAccount, String facebookPage,
			List<String> outrosNomes) {
		super();
		this.name = name;
		this.twitterAccount = twitterAccount;
		this.facebookPage = facebookPage;
		this.outrosNomes = outrosNomes;
	}
	public Empresa() {
		// TODO Auto-generated constructor stub
	}
	private List<String> outrosNomes;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTwitterAccount() {
		return twitterAccount;
	}
	public void setTwitterAccount(String twitterAccount) {
		this.twitterAccount = twitterAccount;
	}
	public String getFacebookPage() {
		return facebookPage;
	}
	public void setFacebookPage(String facebookPage) {
		this.facebookPage = facebookPage;
	}
	public List<String> getOutrosNomes() {
		return outrosNomes;
	}
	public void setOutrosNomes(List<String> outrosNomes) {
		this.outrosNomes = outrosNomes;
	}
	
	
	
	
}
