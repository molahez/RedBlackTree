package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
		if (key == null) {
			throw new RuntimeErrorException(null);

		}

		if (key.compareTo(firstKey()) < 0 || key.compareTo(lastKey()) > 0) {
			return null;
		} else {
			tree.search(key);
			Node<T, V> x = tree.reqn;
			if (x != null && x.getKey() != null) {

				Map.Entry<T, V> entry = new AbstractMap.SimpleEntry<T, V>(x.getKey(), x.getValue());
				return entry;
			} else {
				Set<T> list = new LinkedHashSet<T>(keySet());
				for (T i : list) {
					if (key.compareTo(i) < 0) {
						tree.search(i);
						Node<T, V> z = tree.reqn;
						Map.Entry<T, V> entry1 = new AbstractMap.SimpleEntry<T, V>(z.getKey(), z.getValue());
						return entry1;

					}
				}

			}
		}
		return null;

	}

	@Override
	public T ceilingKey(T key) {
		if (key == null) {
			throw new RuntimeErrorException(null);

		}

		if (key.compareTo(firstKey()) < 0 || key.compareTo(lastKey()) > 0) {
			return null;
		} else {
			tree.search(key);
			Node<T, V> x = tree.reqn;
			if (x != null && x.getKey() != null) {

				return x.getKey();
			} else {
				Set<T> list = new LinkedHashSet<T>(keySet());
				for (T i : list) {
					if (key.compareTo(i) < 0) {
						tree.search(i);
						Node<T, V> z = tree.reqn;
						return z.getKey();

					}
				}

			}
		}
		return null;

	}

	@Override
	public void clear() {
		tree.clear();
		size = 0;

	}

	@Override
	public boolean containsKey(T key) {
		if (key == null) {
			throw new RuntimeErrorException(null);

		}
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
		if (tree.isEmpty()) {
			throw new RuntimeErrorException(null);

		}
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

		if (key.compareTo(firstKey()) < 0 || key.compareTo(lastKey()) > 0) {
			return null;
		} else {
			tree.search(key);
			Node<T, V> x = tree.reqn;
			if (x != null && x.getKey() != null) {

				Map.Entry<T, V> entry = new AbstractMap.SimpleEntry<T, V>(x.getKey(), x.getValue());
				return entry;
			} else {
				Set<T> list = new LinkedHashSet<T>(keySet());
				ArrayList<T> list1 = new ArrayList<T>(list);
				Collections.reverse(list1);

				for (T i : list1) {
					if (key.compareTo(i) > 0) {
						tree.search(i);
						Node<T, V> z = tree.reqn;
						Map.Entry<T, V> entry1 = new AbstractMap.SimpleEntry<T, V>(z.getKey(), z.getValue());
						return entry1;

					}
				}

			}
		}
		return null;
	}

	@Override
	public T floorKey(T key) {
		if (key == null) {
			throw new RuntimeErrorException(null);

		}

		if (key.compareTo(firstKey()) < 0 || key.compareTo(lastKey()) > 0) {
			return null;
		} else {
			tree.search(key);
			Node<T, V> x = tree.reqn;
			if (x != null && x.getKey() != null) {

				return x.getKey();
			} else {
				Set<T> list = new LinkedHashSet<T>(keySet());
				ArrayList<T> list1 = new ArrayList<T>(list);
				Collections.reverse(list1);

				for (T i : list1) {
					if (key.compareTo(i) > 0) {
						tree.search(i);
						Node<T, V> z = tree.reqn;
						return z.getKey();

					}
				}

			}
		}
		return null;
	}

	@Override
	public V get(T key) {
		if (key == null) {
			throw new RuntimeErrorException(null);

		}
		return (V) tree.search(key);
	}

	@Override
	public ArrayList<Map.Entry<T, V>> headMap(T toKey) {
		if (toKey == null) {
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
		if (toKey == null) {
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

		return (ArrayList<Entry<T, V>>) sett;
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
		if (tree.isEmpty()) {
			return null;
		}
		Node<T, V> x = (Node<T, V>) tree.treemin((Node<T, V>) tree.getRoot());

		if (x.getKey() != null) {
			Map.Entry<T, V> entry = new AbstractMap.SimpleEntry<T, V>(x.getKey(), x.getValue());
			remove(x.getKey());
			return entry;
		} else {
			return null;
		}
	}

	@Override
	public Map.Entry<T, V> pollLastEntry() {
		if (tree.isEmpty()) {
			return null;
		}
		Node<T, V> x = (Node<T, V>) tree.treemax((Node<T, V>) tree.getRoot());

		if (x.getKey() != null) {

			Map.Entry<T, V> entry = new AbstractMap.SimpleEntry<T, V>(x.getKey(), x.getValue());
			remove(x.getKey());
			return entry;
		} else {
			return null;
		}
	}

	@Override
	public void put(T key, V value) {
		if (key == null || value == null) {
			throw new RuntimeErrorException(null);

		}

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
		size--;
		return tree.delete(key);

	}

	@Override
	public int size() {
		return tree.size;
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
