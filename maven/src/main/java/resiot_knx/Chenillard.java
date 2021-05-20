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
				/*éteind tout */
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
						pc.write(new GroupAddress("0/0/"+i), false); //éteind la lampe i
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
	 * démarre le chenillard
	 */
	public void demarrer() {
		/*lance le chenillard*/
		this.running = true;
		this.t = thread();
		this.t.start();
	}
	
	/**
	 * éteind le chenillard
	 */
	public void eteindre() {
		this.running = false;
	}
	
	/**
	 * accélère la vitesse du chenillard
	 * @param pourcent: pourcentage d'accélération par rapport à la vitesse de départ
	 */
	void accelere(int pourcent) {
		this.v = pourcent;
		this.running = true;
		this.t = thread();
		this.t.start();
		
	}
	
	/**
	 * ralenti de pourcent pourcent de la vitesse
	 * @param pourcent: pourcentage de ralentissement par rapport à la vitesse de départ
	 */
	void ralenti(int pourcent) {
		this.v = pourcent;
		this.running = true;
		this.t = thread();
		this.t.start();
		
	}

	public ProcessCommunicator getPc() {
		return pc;
	}
	
	
	
}
