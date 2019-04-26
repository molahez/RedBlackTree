package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;

import java.util.Set;

public class TreeMap<T extends Comparable<T>, V> implements ITreeMap<T, V> {

	RedBlackTree<T, V> tree = new RedBlackTree<T, V>();
	int size = 0;

	@Override
	public Map.Entry<T, V> ceilingEntry(T key) {
		tree.search(key);
		Node<T, V> x = tree.reqn;

		if (x.getKey() != null) {
			Map.Entry<T, V> entry = new AbstractMap.SimpleEntry<T, V>(x.getKey(), x.getValue());

			return entry;
		} else {
			return null;
		}
	}

	@Override
	public T ceilingKey(T key) {
		tree.search(key);
		Node<T, V> x = tree.reqn;

		if (x.getKey() != null) {
			return x.getKey();
		} else {
			return null;
		}

	}

	@Override
	public void clear() {
		tree.clear();
		size = 0;

	}

	@Override
	public boolean containsKey(T key) {
		// TODO Auto-generated method stub
		return tree.contains(key);

	}

	@Override
	public boolean containsValue(V value) {
		if (value == null) {
			throw new RuntimeErrorException(null);

		}
		return tree.postorder((Node<T, V>) tree.getRoot(), value);
	}

	@Override
	public Set<Map.Entry<T, V>> entrySet() {
		List<Map.Entry<T, V>> set = tree.inorder((Node<T, V>) tree.getRoot());
		Set<Map.Entry<T, V>> sett = new LinkedHashSet<Map.Entry<T, V>>(set);

		return sett;
	}

	@Override
	public Map.Entry<T, V> firstEntry() {
		if (tree.isEmpty()) {
			return null;
		}
		Node<T, V> x = (Node<T, V>) tree.treemin((Node<T, V>) tree.getRoot());

		if (x.getKey() != null) {
			Map.Entry<T, V> entry = new AbstractMap.SimpleEntry<T, V>(x.getKey(), x.getValue());

			return entry;
		} else {
			return null;
		}
	}

	@Override
	public T firstKey() {
		if (tree.isEmpty()) {
			return null;
		}
		Node<T, V> x = (Node<T, V>) tree.treemin((Node<T, V>) tree.getRoot());

		if (x.getKey() != null) {

			return x.getKey();
		} else {
			return null;
		}
	}

	@Override
	public Map.Entry<T, V> floorEntry(T key) {
		if (key == null) {
			throw new RuntimeErrorException(null);
		}
		tree.search(key);
		Node<T, V> x = tree.reqn;
		if (x.getKey() != null) {
			Map.Entry<T, V> entry = new AbstractMap.SimpleEntry<T, V>(x.getKey(), x.getValue());

			return entry;
		} else {

			return null;
		}
	}

	@Override
	public T floorKey(T key) {
		tree.search(key);
		Node<T, V> x = tree.reqn;
		if (x.getKey() != null) {
			return x.getKey();
		} else {

			return null;
		}
	}

	@Override
	public V get(T key) {
		// TODO Auto-generated method stub
		return (V) tree.search(key);
	}

	@Override
	public ArrayList<Map.Entry<T, V>> headMap(T toKey) {
		if(toKey == null) {
			throw new RuntimeErrorException(null);

		}
		List<Map.Entry<T, V>> set = tree.inorder((Node<T, V>) tree.getRoot());
		List<Map.Entry<T, V>> sett = new ArrayList<Map.Entry<T, V>>();

		for (int i = 0; i < set.size(); i++) {
			if (set.get(i).getKey().compareTo(toKey) < 0) {
				sett.add(set.get(i));

			}
		}

		return (ArrayList<Entry<T, V>>) sett;
	}

	@Override
	public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive) {
		if(toKey == null) {
			throw new RuntimeErrorException(null);

		}
		List<Map.Entry<T, V>> set = tree.inorder((Node<T, V>) tree.getRoot());
		List<Map.Entry<T, V>> sett = new ArrayList<Map.Entry<T, V>>();
		
		if (inclusive) {
			for (int i = 0; i < set.size(); i++) {
				if (set.get(i).getKey().compareTo(toKey) <= 0) {
					sett.add(set.get(i));

				}
			}
		} else {
			for (int i = 0; i < set.size(); i++) {
				if (set.get(i).getKey().compareTo(toKey) < 0) {
					sett.add(set.get(i));

				}
			}
		}

		return  (ArrayList<Entry<T, V>>) sett;
	}

	@Override
	public Set<T> keySet() {
		Set<T> list = new LinkedHashSet<T>();
		Set<Map.Entry<T, V>> ss = entrySet();
		for (Map.Entry<T, V> i : ss) {
			list.add((T) i.getKey());
		}

		return (Set<T>) list;
	}

	@Override
	public Map.Entry<T, V> lastEntry() {

		if (tree.isEmpty()) {
			return null;
		}
		Node<T, V> x = (Node<T, V>) tree.treemax((Node<T, V>) tree.getRoot());

		if (x.getKey() != null) {
			Map.Entry<T, V> entry = new AbstractMap.SimpleEntry<T, V>(x.getKey(), x.getValue());

			return entry;
		} else {
			return null;
		}
	}

	@Override
	public T lastKey() {
		if (tree.isEmpty()) {
			return null;
		}
		Node<T, V> x = (Node<T, V>) tree.treemax((Node<T, V>) tree.getRoot());

		if (x.getKey() != null) {

			return x.getKey();
		} else {
			return null;
		}
	}

	@Override
	public Map.Entry<T, V> pollFirstEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map.Entry<T, V> pollLastEntry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(T key, V value) {

		tree.insert(key, value);
		size++;
	}

	@Override
	public void putAll(Map<T, V> map) {
		if (map == null) {
			throw new RuntimeErrorException(null);
		}
		for (Entry<T, V> entry : map.entrySet()) {
			tree.insert(entry.getKey(), entry.getValue());
			size++;
		}

	}

	@Override
	public boolean remove(T key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Collection<V> values() {
		List<V> list = new ArrayList<>();
		Set<Map.Entry<T, V>> ss = entrySet();
		for (Map.Entry<T, V> i : ss) {
			list.add(i.getValue());
		}

		return list;
	}

}
