
public class assignment5 {
    public static void main(String[] args) throws InterruptedException {
        ChatUser u1 = new ChatUser("Bob");
        ChatUser u2 = new ChatUser("Alice");
        u1.setPriority(Thread.MAX_PRIORITY);
        u2.setPriority(Thread.MIN_PRIORITY);
        u1.start();
        u2.start();
        System.out.println("Is alice alive : " +u2.isAlive());
        Thread.sleep(500);
        u1.Pausechat();
        System.out.println("bob paused ");
        u2.join();
        u1.Resumechat();
        System.out.println("bob resumed ");
        Thread.sleep(500);
        u1.Stopchat();
        u1.join();
        Thread.sleep(50);
        System.out.println("alice after join "+u2.isAlive());
        System.out.println("Chat ended  ");
    }
}
class ChatUser extends Thread{
    private volatile boolean paused = false;
    private volatile boolean running = true;
    ChatUser(String name){
        super(name);
    }
    @Override
    public void run(){
        int i = 1;
        while(running&&i<=5){
            if(!paused){System.out.println(getName()+ " says : Message " + i);
                i++;
                try{
                    Thread.sleep(500);}
                catch (Exception e){System.out.println(e.getMessage());}
            }
        }System.out.println(getName()+ " Finished Chatting");
    }
    public void Pausechat(){paused = true;}
    public void Stopchat(){running = false;}
    public void Resumechat(){paused = false;}
}