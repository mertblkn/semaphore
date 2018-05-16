import java.util.concurrent.Semaphore;
public class test2
{
	static Semaphore semaphore = new Semaphore(1);
	
	static class SingleLane extends Thread
	{
		String aracNo = "";
		String yon = "";
		int saga = 0;
		int sola = 0;
		SingleLane(Arac arac)
		{
			this.aracNo = arac.aracNoAl();
			this.yon = arac.yonAl();
		}
		
		public void run()
		{
			if(yon == "A dan B ye") {
				saga ++; sola=0;
			} else { sola++; saga=0;}
			if(saga<5 || sola<5) {
				System.out.println(aracNo + yon + " = karşıya geçmek için bekliyor");
				try {
					semaphore.acquire();
				} catch (InterruptedException e) {}
				System.out.println(aracNo + ". karşıya geçiyor");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				finally {
					semaphore.release();
				System.out.println(aracNo + ". karşıya geçti");
				}
			}
		}
	}

	public static class Arac {

		private String aracNo;
		private String yon;

		public Arac(int i)
	    {
	    	if (i%2==0) {
	        	yon = " A dan B ye";
	        } else {
	        	yon = " B den A ya";
	        }
	        this.aracNo = "Traktör "+ i + "";
	    }

		public String aracNoAl()
	    {
	        return aracNo;
	    }

	    public String yonAl()
	    {
	        return yon;
	    }

	}
	
	public static void main(String[] args) 
	{
		for (int i=1;i<10 ;i++) {
			Arac arac = new Arac(i);
			SingleLane t1 = new SingleLane(arac);
			t1.start();
		}
		
	}

}