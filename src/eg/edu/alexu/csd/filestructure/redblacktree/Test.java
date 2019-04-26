package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Assert;

public class Test {

	public static void main(String[] args) {
		ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
				for (int i = 0; i < 2; ++i) {

			
			treemap.put(i, "soso" + i);
		}

	
		System.out.println(treemap.floorKey(0));

	}
}
