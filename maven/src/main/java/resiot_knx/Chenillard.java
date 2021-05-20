package resiot_knx;

import java.util.concurrent.TimeUnit;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXFormatException;
import tuwien.auto.calimero.KNXTimeoutException;
import tuwien.auto.calimero.link.KNXLinkClosedException;
import tuwien.auto.calimero.process.ProcessCommunicator;

public class Chenillard {
	
	private long v;
	private ProcessCommunicator pc;
	private boolean running;
	private Thread t;
	
	public Chenillard(ProcessCommunicator processComm, long vitesse) {
		assert vitesse > 500;
		
		this.pc = processComm;
		this.v = vitesse;
	}
	
	/**
	 * créé une instance de thread
	 * @return nouvelle instance de thread
	 */
	private Thread thread() {
		return new Thread() {
			public void run() {
				/*eteind tout */
				for(int i=1;i<4;i++) {
					try {
						pc.write(new GroupAddress("0/0/"+i), false);
					} catch (KNXTimeoutException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (KNXLinkClosedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (KNXFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				int i=1;
				while(running) {
					/*lance le chenillard*/
					try {
						int lpre = (i%4)+1;
						i++;
						int lpost = (i%4)+1;
						pc.write(new GroupAddress("0/0/"+i), false); //eteind la lampe i
						i++;
						if(i>4) i=1;
						pc.write(new GroupAddress("0/0/"+i), true); //allume la lampe i
						TimeUnit.MILLISECONDS.sleep(v);
					} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};

	}
	
	/**
	 * demarre le chenillard
	 */
	public void demarrer() {
		/*lance le chenillard*/
		this.running = true;
		this.t = thread();
		this.t.start();
	}
	
	/**
	 * eteind le chenillard
	 */
	public void eteindre() {
		this.running = false;
		try {
			pc.write(new GroupAddress("0/0/1"), false);
			pc.write(new GroupAddress("0/0/2"), false);
			pc.write(new GroupAddress("0/0/3"), false);
			pc.write(new GroupAddress("0/0/4"), false);
		} catch (KNXTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KNXLinkClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KNXFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	
	/**
	 * accelere la vitesse du chenillard
	 * @param pourcent: pourcentage d'acceleration par rapport a� la vitesse de depart
	 */
	void accelere() {
		if(v > 600) {	
			this.v = v - 100;
		}
		else {
			System.out.println("La vitesse est d�ja trop �lev�e.");
		}
		
	}
	
	/**
	 * ralenti de pourcent pourcent de la vitesse
	 * @param pourcent: pourcentage de ralentissement par rapport a� la vitesse de depart
	 */
	void ralenti() {
		this.v = v +100;
	}

	public ProcessCommunicator getPc() {
		return pc;
	}
	
	
	
}
