import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class ImperialMeasurement {
	
	public static void main(String[] args) throws Exception
	{
		new ImperialMeasurement().run();
	}
	
	HashSet<String> bad = new HashSet<String>();
	BufferedReader file;
	StringTokenizer st;
	
	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = "th in ft yd ch fur mi lea".split(" ");
		String[] strs2 = "thou inch foot yard chain furlong mile league".split(" ");
		HashMap<String,Integer> index = new HashMap<String,Integer>();
		for(int i = 0;i<strs.length;i++) {
			index.put(strs[i], i);
			index.put(strs2[i], i);
		}
		double[] conversions = new double[] {1,1000,12000,36000,792000,7920000,63360000,190080000};
		String[] line = file.readLine().split(" ");
		int a = Integer.parseInt(line[0]);
		int index1 = index.get(line[1]);
		int index2 = index.get(line[3]);
		System.out.println(a * conversions[index1] / conversions[index2]);
	}	
	
}
