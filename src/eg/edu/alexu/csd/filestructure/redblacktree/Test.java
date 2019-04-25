package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;

public class Test {

	public static void main(String[] args) {
		RedBlackTree redBlackTree  = new RedBlackTree();
		Random r = new Random();
		for (int i = 0; i < 100; ++i) {
			int key = r.nextInt(1000);
			redBlackTree.insert(key, "toto" + key);
		}
		System.out.println(redBlackTree.getRoot().getKey());
		
		
		
	}
}
