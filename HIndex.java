import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.TreeMap;

public class HIndex {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(input.readLine());
		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>(Collections.reverseOrder());
		for (int i = 0; i < n; i++) {
			int c = Integer.parseInt(input.readLine());
			if (map.containsKey(c))
				map.put(c, map.get(c)+1);
			else map.put(c, 1);
		}
		long cnt = 0;
		for (int c : map.keySet()) {
			if (cnt>c) {
				System.out.println(cnt);
				return;
			}
			cnt += map.get(c);
			if (cnt>=c) {
				System.out.println(c);
				return;
			}
		}
		System.out.println(cnt);
	}
}
