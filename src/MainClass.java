import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;

import model.Data;
import model.Empresa;

import jomp.runtime.OMP;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookException;
import com.restfb.Parameter;
import com.restfb.types.Page;
import com.restfb.types.Post;


public class MainClass {

	public static void main(String args[]) throws FacebookException {
		ArrayList arr = new ArrayList();
		
		Empresa e = new Empresa();
		
		e.setName("Submarino");
		e.setTwitterAccount("@novosubmarino");
		e.setFacebookPage("133481940001233");
		ArrayList a = new ArrayList();
		a.add("@SubViagens");
		a.add("@cartaosubmarino");
		e.setOutrosNomes(a);
		arr.add(e);
		
		e = new Empresa();
		e.setName("Americanas");
		e.setTwitterAccount("@americanascom");
		e.setFacebookPage("125497644154412");
		a = new ArrayList();
		a.add("#lojasamericanas");
		a.add("Lojas Americanas");
		a.add("americanas");
		e.setOutrosNomes(a);

		arr.add(e);

		// o arquivo encontra-se no mesmo diret\u00f3rio //da aplica\u00e7\u00e3o
		File file = new File("keys.properties");	
		Properties props = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			//l\u00ea os dados que est\u00e3o no arquivo
			props.load(fis);  
			fis.close();
		}
		catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

		
		FacebookClient facebookClient = new DefaultFacebookClient(props.getProperty("facebook_key"));
		Twitter twClient = new Twitter();
		int myid;
		OMP.setNumThreads(arr.size());

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.twClient = twClient;
  __omp_Object0.facebookClient = facebookClient;
  IOException ex = null;
__omp_Object0.ex = ex;
  __omp_Object0.fis = fis;
  __omp_Object0.props = props;
  __omp_Object0.file = file;
  __omp_Object0.a = a;
  __omp_Object0.e = e;
  __omp_Object0.arr = arr;
  __omp_Object0.args = args;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  twClient = __omp_Object0.twClient;
  facebookClient = __omp_Object0.facebookClient;
  ex = __omp_Object0.ex;
  fis = __omp_Object0.fis;
  props = __omp_Object0.props;
  file = __omp_Object0.file;
  a = __omp_Object0.a;
  e = __omp_Object0.e;
  arr = __omp_Object0.arr;
  args = __omp_Object0.args;
}
// OMP PARALLEL BLOCK ENDS

		
		
		
		
		Iterator it = arr.iterator();  
		while (it.hasNext()) {
			Empresa e2 = (Empresa) it.next();
			e2.getData().processarTwitter();
			e2.getData().processarFacebook();
			
			System.out.println("Empresa: " + e2.getName());
			System.out.println("Pontos bons no facebook: " + e2.getData().getFacebookGoodPoints());
			System.out.println("Pontos bons no twitter: " + e2.getData().getTwitterGoodPoints());
			System.out.println("Pontos ruims no facebook: " + e2.getData().getFacebookBadPoints());
			System.out.println("Pontos ruims no twitter: " + e2.getData().getTwitterBadPoints());
		}  
	}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  Twitter twClient;
  FacebookClient facebookClient;
  IOException ex;
  FileInputStream fis;
  Properties props;
  File file;
  ArrayList a;
  Empresa e;
  ArrayList arr;
  String [ ] args;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  int myid;
  // reduction variables, init to default
    // OMP USER CODE BEGINS

		{
			myid = OMP.getThreadNum();
			Empresa e1 = (Empresa) arr.get(myid);
			Data d = new Data(e1);
			e1.setData(d);
			Page page = facebookClient.fetchObject(e1.getFacebookPage(), Page.class);
			Connection publicSearch = facebookClient.fetchConnection("search", Post.class, Parameter.with("q", page.getName()), Parameter.with("type", "post"));
			List list = twClient.search(e1.getTwitterAccount());
			d.setTwitterList(list);
			d.setFacebookPage(page);	
			d.setFacebookSearch(publicSearch);
		}
    // OMP USER CODE ENDS
  // call reducer
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}

