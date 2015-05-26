package BST;

public class BinarySearchTree {
	private class Node{
		private int key;
		private Node left, right;
		public Node(int x){
			key = x;
			left = right = null;
		}
	}
	
	private Node root;
	
	public boolean exists(int x){
		return exists(root, x);
	}

	private boolean exists(Node n, int x){
		if (n == null) return false;
		if (x > n.key) return exists(n.right, x);
		if (x < n.key) return exists(n.left, x);
		return true;
	}
	
	public int size(){
		return size(root);
	}
	private int size(Node n){
		if (n == null) return 0;
		return 1 + size(n.left) + size(n.left);
	}
	
	public int min(){ 	//return minimum value in an non-empty tree
						//note that if root == null we get an error, so
		if (root == null) return Integer.MIN_VALUE;
		return min(root);
	}
	private int min(Node n){
		if (n.left == null) return n.key;
		return min(n.left);
	} 
	
	public int max(){
		if (root == null) return Integer.MAX_VALUE;
		return max(root);
	}
	private int max(Node n){
		if (n.right == null) return n.key;
		return max(n.right);
	}
	
	public void deleteMin(){
		if (root != null) root = deleteMin(root);
	}
	private Node deleteMin(Node n){
		if (n.left == null) // base case: if the root node
							// has not the left subtree,
							// then n has to be erased
			return n.right;
		n.left = deleteMin(n.left); // the minimum is in the tree
		return n;
	}
	
	public void preorderPrint(){
		if (root == null){
			System.out.println("The tree is empty");
		}
		else {
			System.out.println("Preorder printing...");
			preorderPrint(root, 0);
			System.out.println("- End of the tree -");
		}
	}
	private void preorderPrint(Node n, int level){
		for(int i=0; i<level; i++)
			System.out.print(" ");
			if (n == null){
				System.out.println("X");
				return;
			}
		System.out.println(n.key);
		preorderPrint(n.left, level++);
		preorderPrint(n.right, level++);
	}
	
	public void inorderPrint(){
		if ( root == null){
			System.out.println("The tree is empty");
		} else {
			System.out.println("Inorder printing ...");
			inorderPrint(root, 0);
			System.out.println("- End of the tree -");
		}
	}
	private void inorderPrint(Node n, int level){
		if (n == null){ 
			for(int i=0; i<level; i++) System.out.print("\t");
				System.out.println("X");
				return;
		}
		inorderPrint(n.left, level++); // traverse the left subtree
		for(int i=0; i<level; i++) 
			System.out.print("\t");
		System.out.println(n.key);
		inorderPrint(n.right, level++); // traverse the right subtree
	}
	
	public void printAsString(){
		// print a string corresponding to the tree pointed by root
			printAsString(root);
		}
	private void printAsString(Node n){
		if (n == null) {
			System.out.print("null");
			return;
		}
		System.out.print("(" + n.key + ",");
		printAsString(n.left);
		System.out.print(",");
		printAsString(n.right);
		System.out.print(")");
	}
	
	
	public int rank(int key){		// Rank = n of elements lower than the key
		return rank(root, key);
	}
	private int rank(Node g, int key){
		if (g == null) return 0;
		if (g.key == key) return size(g.left);
		if (g.key < key ) return 1 + size(g.left) + rank(g.right,key);
		else return rank(g.left, key);
	}

	public int predecessor(int x){
		return predecessor(root, x);
	}
	private int predecessor(Node n, int x){
		if (n == null) return x;
		if (n.key >= x) return predecessor(n.left, x);
		int r = predecessor(n.right, x); 
		if (r < x ) return r;
		else return n.key;
	}
	
	public Node insert(Node n, int x) {
		// if x does not occur in the BST
		// rooted at g
		// insert a new node
		if (n == null)
			return new Node(x);
		if (x < n.key)
			n.left = insert(n.left, x);
		else if (x > n.key)
			n.right = insert(n.right, x);
		return n;
	}

	public Node delete(Node n, int x){
		if (n == null) return null; // there is nothing to delete
		if (n.key == x){ // the root node g has to be erased
		if ( n.left == null ) return n.right;
		if ( n.right == null ) return n.left;
		n.key = min(n.right);
		n.right = deleteMin(n.right); //update the right tree
		return n;
		}
		if (x < n.key) n.left = delete(n.left, x);
		else n.right = delete(n.right, x);
		return n;
		}
}
