package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import jomp.runtime.OMP;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookException;
import com.restfb.types.Page;


public class MainClass {

	public static void main(String args[]) throws FacebookException {
		OMP.setNumThreads(2);
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
		FacebookClient publicOnlyFacebookClient = new DefaultFacebookClient();

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.publicOnlyFacebookClient = publicOnlyFacebookClient;
  __omp_Object0.facebookClient = facebookClient;
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
  publicOnlyFacebookClient = __omp_Object0.publicOnlyFacebookClient;
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

	}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private static class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  FacebookClient publicOnlyFacebookClient;
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
  // reduction variables, init to default
    // OMP USER CODE BEGINS
 
		{
                         { // OMP FOR BLOCK BEGINS
                         // copy of firstprivate variables, initialized
                         // copy of lastprivate variables
                         // variables to hold result of reduction
                         boolean amLast=false;
                         {
                           // firstprivate variables + init
                           // [last]private variables
                           // reduction variables + init to default
                           // -------------------------------------
                           jomp.runtime.LoopData __omp_WholeData2 = new jomp.runtime.LoopData();
                           jomp.runtime.LoopData __omp_ChunkData1 = new jomp.runtime.LoopData();
                           __omp_WholeData2.start = (long)( 0);
                           __omp_WholeData2.stop = (long)( arr.size());
                           __omp_WholeData2.step = (long)(1);
                           jomp.runtime.OMP.setChunkStatic(__omp_WholeData2);
                           while(!__omp_ChunkData1.isLast && jomp.runtime.OMP.getLoopStatic(__omp_me, __omp_WholeData2, __omp_ChunkData1)) {
                           for(;;) {
                             if(__omp_WholeData2.step > 0) {
                                if(__omp_ChunkData1.stop > __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start >= __omp_WholeData2.stop) break;
                             } else {
                                if(__omp_ChunkData1.stop < __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start > __omp_WholeData2.stop) break;
                             }
                             for(int i = (int)__omp_ChunkData1.start; i < __omp_ChunkData1.stop; i += __omp_ChunkData1.step) {
                               // OMP USER CODE BEGINS
 {
				Page page = facebookClient.fetchObject(((Empresa) arr.get(i)).getFacebookPage(), Page.class);
				System.out.println(page.getName());
			}
                               // OMP USER CODE ENDS
                               if (i == (__omp_WholeData2.stop-1)) amLast = true;
                             } // of for 
                             if(__omp_ChunkData1.startStep == 0)
                               break;
                             __omp_ChunkData1.start += __omp_ChunkData1.startStep;
                             __omp_ChunkData1.stop += __omp_ChunkData1.startStep;
                           } // of for(;;)
                           } // of while
                           // call reducer
                           jomp.runtime.OMP.doBarrier(__omp_me);
                           // copy lastprivate variables out
                           if (amLast) {
                           }
                         }
                         // set global from lastprivate variables
                         if (amLast) {
                         }
                         // set global from reduction variables
                         if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                         }
                         } // OMP FOR BLOCK ENDS

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

