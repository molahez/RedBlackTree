package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import org.junit.Assert;

public class Test {

	public static void main(String[] args) {
		RedBlackTree rr = new RedBlackTree();

		Random r = new Random();
		HashSet<Integer> list = new HashSet<Integer>();
		for (int i = 0; i < 100000; ++i) {
			int key = r.nextInt(10000);
			list.add(key);
			rr.insert(key, "soso" + key);
		}
		System.out.println(rr.size);
		for (Integer elem : list) {
			rr.delete(elem);
		}
		System.out.println(rr.size);
		System.out.println(rr.getRoot().getKey());

	}
}
