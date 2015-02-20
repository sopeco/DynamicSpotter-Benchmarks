package org.spotter.benchmark.app.problems.jms;

public class Main {
public static void main(String[] args)  {
	System.out.println(ESTReceiver.class.getName());
	
	ESTSender.getInstance().sendMessage(1000);
	while(true){
		try {
			Thread.sleep(1000);
			ESTSender.getInstance().sendMessage(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			break;
		}
	}
//	for (int i = 0; i<20; i++){
//		Thread t = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				while(true){
//					long start = System.currentTimeMillis();
//					ESTSender.getInstance().sendMessage(1000);
//					System.out.println(System.currentTimeMillis() - start);
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						break;
//					}
//				}
//				
//			}
//		});
//		t.start();
//	}
	
	
}
}
