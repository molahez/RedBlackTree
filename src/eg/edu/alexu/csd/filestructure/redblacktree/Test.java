package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Assert;

public class Test {

	public static void main(String[] args) {
		ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
		
			ArrayList<Integer> list = new ArrayList<Integer>();
			Random r = new Random();
			for (int i = 0; i < 3; ++i) {
				int key = r.nextInt(1000000);
				list.add(key);
				treemap.put(key, "soso" + key);
			}
			Collections.shuffle(list);
			String key = "soso" + list.get(r.nextInt(list.size()));
			System.out.println(treemap.containsValue(key));
		

	}
}
