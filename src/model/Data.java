package model;

import java.util.Iterator;
import java.util.List;

import winterwell.jtwitter.Twitter.Status;

import com.restfb.Connection;
import com.restfb.types.Page;
import com.restfb.types.Post;

public class Data {

	private Empresa empresa;
	private int twitterGoodPoints;
	private int twitterBadPoints;
	private int facebookGoodPoints;
	private int facebookBadPoints;
	private List twitterList;
	private Page facebookPage;
	private Long numFacebookFans;
	private Connection facebookSearch;

	public Data(Empresa e1) {
		this.empresa = e1;
	}



	public Long getNumFacebookFans() {
		return numFacebookFans;
	}



	public void setNumFacebookFans(Long numFacebookFans) {
		this.numFacebookFans = numFacebookFans;
	}

	public Connection getFacebookSearch() {
		return facebookSearch;
	}

	public void setFacebookSearch(Connection facebookSearch) {
		this.facebookSearch = facebookSearch;
	}

	public List getTwitterList() {
		return twitterList;
	}


	public void setTwitterList(List twitterList) {
		this.twitterList = twitterList;
	}


	public Page getFacebookPage() {
		return facebookPage;
	}


	public void setFacebookPage(Page facebookPage) {
		this.facebookPage = facebookPage;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}



	public int getTwitterGoodPoints() {
		return twitterGoodPoints;
	}



	public void setTwitterGoodPoints(int twitterGoodPoints) {
		this.twitterGoodPoints = twitterGoodPoints;
	}



	public int getTwitterBadPoints() {
		return twitterBadPoints;
	}



	public void setTwitterBadPoints(int twitterBadPoints) {
		this.twitterBadPoints = twitterBadPoints;
	}





	public int getFacebookGoodPoints() {
		return facebookGoodPoints;
	}



	public void setFacebookGoodPoints(int facebookGoodPoints) {
		this.facebookGoodPoints = facebookGoodPoints;
	}



	public int getFacebookBadPoints() {
		return facebookBadPoints;
	}



	public void setFacebookBadPoints(int facebookBadPoints) {
		this.facebookBadPoints = facebookBadPoints;
	}



	public void processarTwitter() {
		int myid;
		int good = 0;
		int bad = 0;
		List twitterList = this.twitterList;

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.twitterList = twitterList;
  __omp_Object0.bad = bad;
  __omp_Object0.good = good;
  __omp_Object0.e1 = e1;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  twitterList = __omp_Object0.twitterList;
  bad = __omp_Object0.bad;
  good = __omp_Object0.good;
  myid = __omp_Object0.myid;
  e1 = __omp_Object0.e1;
}
// OMP PARALLEL BLOCK ENDS

		
		this.twitterGoodPoints = good;
		this.twitterBadPoints = bad;
	}

	public void processarFacebook() {
		Page facepage = this.facebookPage;
		Connection sc = this.facebookSearch;
		int myid;
		int good = 0;
		int bad = 0;

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class4 __omp_Object4 = new __omp_Class4();
  // shared variables
  __omp_Object4.sc = sc;
  __omp_Object4.facepage = facepage;
  __omp_Object4.e1 = e1;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object4);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  good  += __omp_Object4._rd_good;
  bad  += __omp_Object4._rd_bad;
  // shared variables
  myid = __omp_Object4.myid;
  sc = __omp_Object4.sc;
  facepage = __omp_Object4.facepage;
  e1 = __omp_Object4.e1;
}
// OMP PARALLEL BLOCK ENDS

		
		this.facebookGoodPoints = good;
		this.facebookBadPoints = bad;
	}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private class __omp_Class4 extends jomp.runtime.BusyTask {
  // shared variables
  int myid;
  Connection sc;
  Page facepage;
  Empresa e1;
  // firstprivate variables
  // variables to hold results of reduction
  int _rd_good;
  int _rd_bad;

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
  int good = 0;
  int bad = 0;
    // OMP USER CODE BEGINS

		{
                         { // OMP SECTIONS BLOCK BEGINS
                         // copy of firstprivate variables, initialized
                         // copy of lastprivate variables
                         // variables to hold result of reduction
                         boolean amLast=false;
                         {
                           // firstprivate variables + init
                           // [last]private variables
                           // reduction variables + init to default
                           // -------------------------------------
                           __ompName_5: while(true) {
                           switch((int)jomp.runtime.OMP.getTicket(__omp_me)) {
                           // OMP SECTION BLOCK 0 BEGINS
                             case 0: {
                           // OMP USER CODE BEGINS

				{
					long numFans = facepage.getFanCount();

					if(numFans > 5000) {
						good++;
					} 
					if(numFans < 100) {
						bad++;
					}
					// pagina com overview vazio leva negativo
					if(facepage.getCompanyOverview() == "") {
						bad--;
					}
				}
                           // OMP USER CODE ENDS
                             };  break;
                           // OMP SECTION BLOCK 0 ENDS
                           // OMP SECTION BLOCK 1 BEGINS
                             case 1: {
                           // OMP USER CODE BEGINS

				{
					List l = sc.getData();
					Iterator it = l.iterator();
					while(it.hasNext()) {
						Post p = (Post) it.next();

						if(p.getMessage().contains("ruim") || p.getMessage().contains("p\u00e9ssimo") || p.getMessage().contains("pior")) {
							bad++;
						}

						if(p.getMessage().contains("bom") || p.getMessage().contains("\u00f3timo") || p.getMessage().contains("melhor")) {
							good++;
						}
					}
				}
                           // OMP USER CODE ENDS
                           amLast = true;
                             };  break;
                           // OMP SECTION BLOCK 1 ENDS

                             default: break __ompName_5;
                           } // of switch
                           } // of while
                           // call reducer
                           jomp.runtime.OMP.resetTicket(__omp_me);
                           jomp.runtime.OMP.doBarrier(__omp_me);
                           // copy lastprivate variables out
                           if (amLast) {
                           }
                         }
                         // update lastprivate variables
                         if (amLast) {
                         }
                         // update reduction variables
                         if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                         }
                         } // OMP SECTIONS BLOCK ENDS

		}
    // OMP USER CODE ENDS
  // call reducer
  good = (int) jomp.runtime.OMP.doPlusReduce(__omp_me, good);
  bad = (int) jomp.runtime.OMP.doPlusReduce(__omp_me, bad);
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
    _rd_good = good;
    _rd_bad = bad;
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS



// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  List twitterList;
  int bad;
  int good;
  int myid;
  Empresa e1;
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
                         int _cp_good;
                         int _cp_bad;
                         boolean amLast=false;
                         {
                           // firstprivate variables + init
                           // [last]private variables
                           // reduction variables + init to default
                           int  good = 0;
                           int  bad = 0;
                           // -------------------------------------
                           jomp.runtime.LoopData __omp_WholeData2 = new jomp.runtime.LoopData();
                           jomp.runtime.LoopData __omp_ChunkData1 = new jomp.runtime.LoopData();
                           __omp_WholeData2.start = (long)( 0);
                           __omp_WholeData2.stop = (long)( twitterList.size());
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
				Status st = (Status) twitterList.get(i);

				if(st.getText().contains("#fail")) {
					good++;
				}

				if(st.getText().contains("#win")){
					bad++;
				}
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
                           _cp_good = (int) jomp.runtime.OMP.doPlusReduce(__omp_me, good);
                           _cp_bad = (int) jomp.runtime.OMP.doPlusReduce(__omp_me, bad);
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
                           good+= _cp_good;
                           bad+= _cp_bad;
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

