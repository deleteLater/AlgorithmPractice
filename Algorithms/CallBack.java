package per.Algotithms;

public interface CallBack{
	void excute(Object... objects);
}

class Local implements CallBack,Runnable{
	public Remote remote;
	public String message;
	
	public Local() {
		
	}
	public Local(Remote remote,String message) {
		this.remote = remote;
		this.message = message;
	}
	public void sendMessage() {
		System.out.println(Thread.currentThread().getName() + " currentThread!");
		Thread thread = new Thread(this,"Local");
		thread.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		remote.excutedMessage(message, this);
	}
	@Override
	public void excute(Object... objects) {
		// TODO Auto-generated method stub
		System.out.println(objects[0]);
		System.out.println(Thread.currentThread().getName() + " currentThread!");
		System.out.println("DONE!");
	}
}

class Remote {
	public void excutedMessage(String msg,CallBack cb) {
		for(int i = 0; i < 1000000000; i++) {
			;
		}
		System.out.println("excuteMessage: "+msg);
		msg = msg + "msg Changed!";
		cb.excute(msg);//call back
	}
}

/*
 * TestCode
 * public static void main(String args[]){
 * 		Local local = new Local(new Remote(),"Hello,World!");
		local.sendMessage();
		System.out.println("gogogo");
 * }
 * 
 */
