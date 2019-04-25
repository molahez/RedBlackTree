package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;

public class RedBlackTree<T extends Comparable<T>, V> implements IRedBlackTree<T, V> {

	private Node<T, V> root = null;
	private Node<T, V> reqn = null;

	public INode<T, V> getRoot() {
		if (root == null) {

			return null;
		}
		return root;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void clear() {
		root = null;

	}

	@Override
	public V search(T key) {

		if (key == null) {
			throw new RuntimeErrorException(null);
		}

		Node<T, V> temp = root;
		if (root == null) {
			return null;
		}
		while (true) {
			if (key.compareTo(temp.getKey()) < 0) {
				if (!temp.getLeftChild().isNull()) {
					temp = (Node<T, V>) temp.getLeftChild();
				} else {
					return null;
				}
			}

			else if (key.compareTo(temp.getKey()) > 0) {
				if (temp.getRightChild() != null) {
					temp = (Node<T, V>) temp.getRightChild();
				} else {
					return null;
				}
			} else if (key.compareTo(temp.getKey()) == 0) {
				reqn = temp;
				return temp.getValue();

			}

		}
	}

	@Override
	public boolean contains(T key) {
		if (key == null) {
			throw new RuntimeErrorException(null);
		}
		Node<T, V> temp = root;
		if (root == null) {
			return false;
		}
		while (true) {
			if (key.compareTo(temp.getKey()) < 0) {
				if (!temp.getLeftChild().isNull()) {
					temp = (Node<T, V>) temp.getLeftChild();
				} else {
					return false;
				}
			}

			else if (key.compareTo(temp.getKey()) > 0) {
				if (temp.getRightChild() != null) {
					temp = (Node<T, V>) temp.getRightChild();
				} else {
					return false;
				}
			} else if (key.compareTo(temp.getKey()) == 0) {

				return true;

			}

		}
	}

	@Override
	public void insert(T key, V value) {

		if (key == null || value == null) {
			throw new RuntimeErrorException(null);
		}
		Node<T, V> temp = root;
		// here we set node
		Node<T, V> inserted = new Node<T, V>();
		inserted.setKey(key);
		inserted.setValue(value);

		if (root == null) {

			root = inserted;
			inserted.setColor(Node.BLACK);
			inserted.setParent(null);

		} else {
			inserted.setColor(Node.RED);
			while (true) {
				if (key.compareTo((T) temp.getKey()) < 0) {
					if (temp.getLeftChild() == null) {
						temp.setLeftChild(inserted);
						inserted.setParent(temp);
						break;
					} else {
						temp = (Node<T, V>) temp.getLeftChild();
					}
				} else if (key.compareTo((T) temp.getKey()) > 0) {
					if (temp.getRightChild() == null) {
						temp.setRightChild(inserted);
						inserted.setParent(temp);
						break;
					} else {
						temp = (Node<T, V>) temp.getRightChild();
					}

				} else {
					temp.setValue(value);
					break;
				}

			}
			fixTree(inserted);

		}
	}

	private void fixTree(Node<T, V> node) {

		while (node.getParent() != null && node.getParent().getColor() == Node.RED) {
			Node<T, V> uncle = null;
			if (node.getParent() == node.getParent().getParent().getLeftChild()) {
				uncle = (Node<T, V>) node.getParent().getParent().getRightChild();

				if (uncle != null && uncle.getColor() == Node.RED) {
					node.getParent().setColor(Node.BLACK);
					uncle.setColor(Node.BLACK);
					node.getParent().getParent().setColor(Node.RED);
					node = (Node<T, V>) node.getParent().getParent();
					continue;
				}
				if (node == node.getParent().getRightChild()) {
					node = (Node<T, V>) node.getParent();
					rotateLeft(node);
				}
				node.getParent().setColor(Node.BLACK);
				node.getParent().getParent().setColor(Node.RED);
				rotateRight((Node<T, V>) node.getParent().getParent());

			} else {
				uncle = (Node<T, V>) node.getParent().getParent().getLeftChild();
				if (uncle != null && uncle.getColor() == Node.RED) {
					node.getParent().setColor(Node.BLACK);
					uncle.setColor(Node.BLACK);
					node.getParent().getParent().setColor(Node.RED);
					node = (Node<T, V>) node.getParent().getParent();
					continue;
				}
				if (node == node.getParent().getLeftChild()) {

					node = (Node<T, V>) node.getParent();
					rotateRight(node);
				}
				node.getParent().setColor(Node.BLACK);
				node.getParent().getParent().setColor(Node.RED);
				rotateLeft((Node<T, V>) node.getParent().getParent());

			}
		}
		root.setColor(Node.BLACK);
	}

	private void rotateLeft(Node<T, V> node) {
		Node<T, V> y = (Node<T, V>) node.getRightChild();
		node.setRightChild(y.getLeftChild());
		if (y.getLeftChild() != null) {
			y.getLeftChild().setParent(node);
		}
		y.setParent(node.getParent());
		if (node.getParent() == null) {
			root = y;
		} else if (node == node.getParent().getLeftChild()) {
			node.getParent().setLeftChild(y);
		} else {
			node.getParent().setRightChild(y);
		}
		y.setLeftChild(node);
		node.setParent(y);

	}

	private void rotateRight(Node<T, V> node) {
		Node<T, V> x = (Node<T, V>) node.getLeftChild();
		node.setLeftChild(x.getRightChild());
		if (x.getRightChild() != null) {
			x.getRightChild().setParent(node);
		}
		x.setParent(node.getParent());
		if (node.getParent() == null) {
			root = x;
		} else if (node == node.getParent().getRightChild()) {
			node.getParent().setRightChild(x);
		} else {
			node.getParent().setLeftChild(x);
		}
		x.setRightChild(node);
		node.setParent(x);

	}

	@Override
	public boolean delete(T key) {
		if (search(key) == null) {
			return false;
		}
		Node<T, V> z = reqn;

		Node<T, V> x;
		Node<T, V> y = z;

		boolean y_original = y.getColor();

		if (z.getLeftChild() == null) {
			x = (Node<T, V>) z.getRightChild();
			transplant(z, (Node<T, V>) z.getRightChild());

		} else if (z.getRightChild() == null) {
			x = (Node<T, V>) z.getLeftChild();
			transplant(z, (Node<T, V>) z.getLeftChild());

		} else {
			y = (Node<T, V>) treemin((Node<T, V>) z.getRightChild());
			y_original = y.getColor();
			x = (Node<T, V>) y.getRightChild();
			if (x != null && y.getParent() == z) {

				x.setParent(z);

			} else {
				transplant(y, (Node<T, V>) y.getRightChild());
				y.setRightChild(z.getRightChild());
				if (y.getRightChild() != null) {
					y.getRightChild().setParent(y);
				}

			}
			transplant(z, y);
			y.setLeftChild(z.getLeftChild());
			if (y.getLeftChild() != null) {
				y.getLeftChild().setParent(y);
			}

			y.setColor(z.getColor());
		}
		if (y_original == Node.BLACK) {
			deleteFixup(x);

		}

		return true;
	}

	private void transplant(Node<T, V> target, Node<T, V> targwith) {
		if (target.getParent() == null) {
			root = targwith;
		} else if (target == target.getParent().getLeftChild()) {
			target.getParent().setLeftChild(targwith);

		} else {
			target.getParent().setRightChild(targwith);
		}
		if (targwith == null) {

		} else {
			targwith.setParent(target.getParent());
		}

	}

	private INode<T, V> treemin(Node<T, V> subtree) {
		while (subtree.getLeftChild() != null) {
			subtree = (Node<T, V>) subtree.getLeftChild();
		}
		return subtree;

	}

	private void deleteFixup(Node<T, V> x) {
		while (x != null && x != root && x.getColor() == Node.BLACK) {
			if (x == x.getParent().getLeftChild()) {
				Node<T, V> w = (Node<T, V>) x.getParent().getRightChild();

				if (w.getColor() == Node.RED) {
					w.setColor(Node.BLACK);
					x.getParent().setColor(Node.RED);
					rotateLeft((Node<T, V>) x.getParent());
					w = (Node<T, V>) x.getParent().getRightChild();
				}
				if (w.getLeftChild().getColor() == Node.BLACK && w.getRightChild().getColor() == Node.BLACK) {
					w.setColor(Node.RED);
					x = (Node<T, V>) x.getParent();
					continue;

				} else if (w.getRightChild().getColor() == Node.BLACK) {
					w.getLeftChild().setColor(Node.BLACK);
					w.setColor(Node.RED);
					rotateRight(w);
					w = (Node<T, V>) x.getParent().getRightChild();

				}
				if (w.getRightChild().getColor() == Node.RED) {
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(Node.BLACK);
					w.getRightChild().setColor(Node.BLACK);
					rotateLeft((Node<T, V>) x.getParent());
					x = root;

				}
			} else {
				Node<T, V> w = (Node<T, V>) x.getParent().getLeftChild();

				if (w.getColor() == Node.RED) {
					w.setColor(Node.BLACK);
					x.getParent().setColor(Node.RED);
					rotateRight((Node<T, V>) x.getParent());
					w = (Node<T, V>) x.getParent().getLeftChild();
				}
				if (w.getLeftChild().getColor() == Node.BLACK && w.getRightChild().getColor() == Node.BLACK) {
					w.setColor(Node.RED);
					x = (Node<T, V>) x.getParent();
					continue;

				} else if (w.getLeftChild().getColor() == Node.BLACK) {
					w.getRightChild().setColor(Node.BLACK);
					w.setColor(Node.RED);
					rotateLeft(w);
					w = (Node<T, V>) x.getParent().getLeftChild();

				}
				if (w.getLeftChild().getColor() == Node.RED) {
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(Node.BLACK);
					w.getLeftChild().setColor(Node.BLACK);
					rotateRight((Node<T, V>) x.getParent());
					x = root;

				}

			}

		}
		if (x != null) {
			x.setColor(Node.BLACK);
		}

	}

}
