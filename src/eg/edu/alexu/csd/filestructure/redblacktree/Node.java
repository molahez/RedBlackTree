package eg.edu.alexu.csd.filestructure.redblacktree;

public class Node<T extends Comparable<T>, V> implements INode<T, V> {
	private T key;
	private V val;
	private Node<T, V> left, right;
	private Node<T, V> parent;
	private Boolean color;

	@Override
	public void setParent(INode<T, V> parent) {
		this.parent = (Node<T, V>) parent;

	}

	@Override
	public INode<T, V> getParent() {
		if (parent == null) {
			return null;
		}
		return parent;
	}

	@Override
	public void setLeftChild(INode<T, V> leftChild) {
		this.left = (Node<T, V>) leftChild;

	}

	@Override
	public INode<T, V> getLeftChild() {
		if (left == null) {
			return null;
		}
		return left;
	}

	@Override
	public void setRightChild(INode<T, V> rightChild) {

		this.right = (Node<T, V>) rightChild;
	}

	@Override
	public INode<T, V> getRightChild() {
		
		return right;
	}

	@Override
	public T getKey() {

		return key;
	}

	@Override
	public void setKey(T key) {
		this.key = key;

	}

	@Override
	public V getValue() {

		return val;
	}

	@Override
	public void setValue(V value) {
		this.val = value;
	}

	@Override
	public boolean getColor() {
		if(color == null) {
			return (Boolean) null;
		}
		
		return color;
	}

	@Override
	public void setColor(boolean color) {
		this.color = color;

	}

	@Override
	public boolean isNull() {
		if(val == null) {
			return true;
		}
		else {
			return false;
		}

	}

}
