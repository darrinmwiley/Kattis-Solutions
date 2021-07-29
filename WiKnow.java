import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class WiKnow {
	
	static int mxn = 400000;
	static int n;
	
	static class SegTree {
		int[] tree;
		
		SegTree() {
			tree = new int[4*mxn];
			Arrays.fill(tree, mxn);
		}
		
		void change(int i, int lo, int hi, int j, int x) {
			if (lo>hi || hi<j || lo>j) return;
			if (lo==hi && lo == j)
				tree[i] = x;
			else {
				int mid = (lo+hi)/2;
				change(i<<1, lo, mid, j, x);
				change(i<<1|1, mid+1, hi, j, x);
				tree[i] = Math.min(tree[i<<1], tree[i<<1|1]);
			}
		}
		
		int query(int i, int lo, int hi, int l, int r) {
			if (lo>hi || lo>r || hi<l) return mxn;
			if (l <= lo && hi <= r) 
				return tree[i];
			else {
				int mid = (lo+hi)/2;
				return Math.min(query(i<<1, lo, mid, l, r), 
						query(i<<1|1, mid+1, hi, l, r));
			}
		}
	}
	
	static class BIT {
		int[] tree;
		BIT() {
			tree = new int[mxn];
			Arrays.fill(tree, mxn);
		}
		void mod(int i, int x) {
			for (i = mxn - 1 - i; i < mxn; i|=i+1) tree[i] = Math.min(tree[i], x);
		}
		int query(int i) {
			int ans = mxn;
			for (i = mxn - 1 - i; i>=0; i=(i&(i+1))-1)
				ans = Math.min(ans, tree[i]);
			return ans;
		}
	}
	
	static class TwoPQ {
		PriorityQueue<Integer> a, b;
		public TwoPQ() {
			a = new PriorityQueue<Integer>(Collections.reverseOrder());
			b = new PriorityQueue<Integer>(Collections.reverseOrder());
		}
		void update() {
			while (!a.isEmpty() && !b.isEmpty() && a.peek() == b.peek()) {
				a.poll(); b.poll();
			}
		}
		void push(int x) {
			a.add(x); update();
		}
		void poll(int x) {
			if (x != -1) { b.add(x); update(); }
		}
		int poll() {
			if (a.isEmpty()) return -1;
			int x = a.poll(); poll(x);
			return x;
		}
		int peek() {
			update();
			return a.isEmpty() ? -1 : a.peek();
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(input.readLine());
		int[] arr = new int[n], prev = new int[n], nxt = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(input.readLine())-1;
		
		int[] save = new int[n];
		for (int i = 0; i < n; i++) save[i] = -1;
		for (int i = 0; i < n; i++) {
			prev[i] = save[arr[i]]; save[arr[i]] = i;
		}
		for (int i = 0; i < n; i++) save[i] = n;
		for (int i = n-1; i >= 0; i--) {
			nxt[i] = save[arr[i]]; save[arr[i]] = i;
		}
		
//		TwoPQ ds = new TwoPQ();
//		BIT ds = new BIT();
		SegTree ds = new SegTree();
		int aa = -1, bb = -1;
		for (int i = 0; i < n; i++) {
			if (prev[i] != -1) {
				int x = ds.query(1, 0, n-1, prev[i]+1, i);
				if (x < mxn) if (arr[i]<aa || aa==-1 || (arr[i] == aa && x < bb)) {
					aa = arr[i]; bb = x;
				}
				ds.change(1, 0, n-1, prev[i], mxn);
			}
			if (nxt[i] != n) {
				ds.change(1, 0, n-1, i, arr[i]);
			}
		}
		if (aa==-1) System.out.println(-1);
		else System.out.println((aa+1) + " " + (bb+1));
		
	}
}
