package model;

import java.util.Iterator;
import java.util.List;

import winterwell.jtwitter.Twitter.Status;

import jomp.runtime.OMP;

import com.restfb.types.Page;

public class Data {

	private Empresa empresa;
	private int goodPoints;
	private int badPoints;
	private List twitterList;
	private Page facebookPage;

	public Data(Empresa e1) {
		this.empresa = e1;
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


	public int getGoodPoints() {
		return goodPoints;
	}


	public void setGoodPoints(int goodPoints) {
		this.goodPoints = goodPoints;
	}


	public int getBadPoints() {
		return badPoints;
	}


	public void setBadPoints(int badPoints) {
		this.badPoints = badPoints;
	}


	public void processarTwitter() {
		int myid;
		int good = 0;
		int bad = 0;

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
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
  bad = __omp_Object0.bad;
  good = __omp_Object0.good;
  myid = __omp_Object0.myid;
  e1 = __omp_Object0.e1;
}
// OMP PARALLEL BLOCK ENDS


		this.goodPoints = good;
		this.badPoints = bad;
	}

	public void processarFacebook() {

	}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
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
                         boolean amLast=false;
                         {
                           // firstprivate variables + init
                           // [last]private variables
                           // reduction variables + init to default
                           // -------------------------------------
                           jomp.runtime.LoopData __omp_WholeData2 = new jomp.runtime.LoopData();
                           jomp.runtime.LoopData __omp_ChunkData1 = new jomp.runtime.LoopData();
                           __omp_WholeData2.start = (long)( 0);
                           __omp_WholeData2.stop = (long)( this.twitterList.size());
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
				Status st = (Status) this.twitterList.get(i);
				System.out.println(st.getText());

				if(st.getText().contains("#fail")) {
					bad++;
				}

				if(st.getText().contains("#win")){
					good++;
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

