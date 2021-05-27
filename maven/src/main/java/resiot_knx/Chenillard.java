package resiot_knx;

import java.util.concurrent.TimeUnit;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.KNXFormatException;
import tuwien.auto.calimero.KNXTimeoutException;
import tuwien.auto.calimero.link.KNXLinkClosedException;
import tuwien.auto.calimero.process.ProcessCommunicator;

import java.lang.Math;

public class Chenillard {
	
	private long v;
	private ProcessCommunicator pc;
	private boolean running;
	private Thread t;
	private String motif;
    int max = 4;
    int min = 1;

	
	public Chenillard(ProcessCommunicator processComm, long vitesse) {
		assert vitesse > 500;
		
		this.pc = processComm;
		this.v = vitesse;
		this.motif = "normal";
	}
	
	/**
	 * crÃ©Ã© une instance de thread
	 * @return nouvelle instance de thread
	 */
	private Thread thread() {
		return new Thread() {
			public void run() {
				switch(motif) {
				/*eteind tout */
				case "normal": for(int i=1;i<4;i++) {
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
				break;
			
				
				case "random" :  {
					for(int a=1;a<4;a++) {
						try {
							pc.write(new GroupAddress("0/0/"+a), false);
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
					
				    int range = max - min + 1;


					while(running) {
						/*lance le chenillard*/
						try {
						    // generate random numbers within 1 to 10
						    int rand = (int)(Math.random() * range) + min;
							pc.write(new GroupAddress("0/0/"+rand), true); //allume la lampe i
							TimeUnit.MILLISECONDS.sleep(v);
							pc.write(new GroupAddress("0/0/"+rand), false);
						} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}
				break;
				
				case "phare" : 	
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
						
						
						while(running) {	
							/*lance le chenillard*/
							try {
								pc.write(new GroupAddress("0/0/1"), true);
								pc.write(new GroupAddress("0/0/2"), true);
								pc.write(new GroupAddress("0/0/3"), true);
								pc.write(new GroupAddress("0/0/4"), true);
								
								TimeUnit.MILLISECONDS.sleep(v);
								
								pc.write(new GroupAddress("0/0/1"), false);
								pc.write(new GroupAddress("0/0/2"), false);
								pc.write(new GroupAddress("0/0/3"), false);
								pc.write(new GroupAddress("0/0/4"), false);
								
								TimeUnit.MILLISECONDS.sleep(v);
								
								
							} catch (KNXTimeoutException | KNXLinkClosedException | KNXFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						} 
						break;
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
		this.motif = "normal";
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
	 * allumage aléatoire des lampes
	 */
	public void random() {
		/*allumage aléatoire des lammpes*/
		this.running = true;
		this.motif = "random";		
		this.t = thread();
		this.t.start();
	}
	
	public void phare() {
		/*allumage aléatoire des lammpes*/
		this.running = true;
		this.motif = "phare";		
		this.t = thread();
		this.t.start();
	}
	
		
	
	
	/**
	 * accelere la vitesse du chenillard
	 * @param pourcent: pourcentage d'acceleration par rapport aï¿½ la vitesse de depart
	 */
	void accelere() {
		if(v > 600) {	
			this.v = v - 100;
		}
		else {
			System.out.println("La vitesse est dï¿½ja trop ï¿½levï¿½e.");
		}
		
	}
	
	/**
	 * ralenti de pourcent pourcent de la vitesse
	 * @param pourcent: pourcentage de ralentissement par rapport aï¿½ la vitesse de depart
	 */
	void ralenti() {
		this.v = v +100;
	}

	public ProcessCommunicator getPc() {
		return pc;
	}
	
	
	
}
