import java.io.IOException;
import java.util.Scanner;

public class Howl {

	public static void main(String[] args) throws IOException
	{
		new Howl().run();		
	}

	public void run() throws IOException
	{
		Scanner file = new Scanner(System.in);
		String line = file.nextLine();
		System.out.println(line+"O");
	}

}
