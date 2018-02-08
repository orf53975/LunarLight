package lunarlight.ServingThreads;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class serveConnThread implements Callable<Void>{ //In constructor we pass socket that we got from listenSock.accept()
    private Socket conn;
    public serveConnThread(Socket c){
        this.conn = c;
    }

    @Override
    public Void call(){//right now we just send basic string
        try {
            conn.setTcpNoDelay(true);
            conn.setSoTimeout(150000);
            OutputStream out = this.conn.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
            writer.write("Work in progress"+"\n");
            writer.flush();
        }
        catch(java.io.IOException ex){
            System.out.println(ex);
        }
        finally {
            if (conn!=null){
                try {
                    conn.close();
                }
                catch (java.io.IOException ex){
                    System.out.println("For some reason i cant close the connection");
                }
            }
        }
        return null;
    }
}
