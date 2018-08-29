package NetConect.Message;
import java.io.IOException;

//import NetConect.Message.*;
public interface MessageCoder
{
	byte [] toWire(loginMessage msg)throws IOException ;
	//throws IOException;
	loginMessage fromWirte(byte[] input)throws IOException;//throws IOException;
	//void write(int b) throws IOException;
}
