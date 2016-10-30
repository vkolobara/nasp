package hr.vinko.nasp.lab1;

public class AVLTree {

	private AVLNode root;

	public static int balanceFactor(AVLNode node) {
		if (node == null)
			return 0;
		return height(node.right) - height(node.left);
	}

	private static int height(AVLNode node) {
		if (node == null)
			return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	public AVLTree addElement(int element) {
		if (root == null) {
			root = new AVLNode();
			root.element = element;
			return this;
		}
		AVLNode added = addElement(element, root);
		balance(added.parent);
		return this;
	}

	public AVLTree deleteElement(int element) {
		if (root != null) {
			deleteElement(element, root);
		}
		return this;
	}

	public void deleteElement(int element, AVLNode node) {
		if (node != null) {
			if (element < node.element) {
				deleteElement(element, node.left);
			} else if (element > node.element) {
				deleteElement(element, node.right);
			} else {
				if (node.left == null || node.right == null) {
					AVLNode temp = node.left == null ? node.right : node.left;
					if (temp == null) {
						//Ovdje je list
						if (node.parent != null) {
							AVLNode parent = node.parent;
							if (node.equals(node.parent.right)) {
								node.parent.right = null;
							} else{
								node.parent.left = null;
							} 
							balance(parent);
						} else {
							root = null;
						}
						node = null;
					} else {
						//Ovdje je samo jedno dijete
						if (node.parent != null) {
							temp.parent = node.parent;
							if (node.equals(node.parent.left)) {
								node.parent.left = temp;
							} else if (node.equals(node.parent.right)){
								node.parent.right = temp;
							}
							balance(node.parent);
						} else {
							//Brisem root
							temp.parent = null;
							root = temp;
						}
						node = null;
					}
				} else {
					AVLNode temp = getMax(node.left);
					node.element = temp.element;
					deleteElement(temp.element, node.left);
				}
				
				
			}
		}	
	}

	private AVLNode getMax(AVLNode node) {
		if (node.right == null) {
			return node;
		}
		return getMax(node.right);
	}

	private void balance(AVLNode startNode) {
		if (startNode == null) return;
		
		int frParent = balanceFactor(startNode);
		if (Math.abs(frParent) == 2) {
			int frChildL = balanceFactor(startNode.left);
			int frChildR = balanceFactor(startNode.right);
			if (frParent == 2) {
				if (frChildR >= 0) {
					rotateLeft(startNode.right);
				} else {
					rotateRightLeft(startNode.right);
				}
			} else {
				if (frChildL <= 0) {
					rotateRight(startNode.left);
				} else {
					rotateLeftRight(startNode.left);
				}
			}
		} else {
			balance(startNode.parent);
		}
	}
	
//	private void balance(AVLNode startNode) {
//		if (startNode == null)
//			return;
//
//		int frParent = balanceFactor(startNode.parent);
//		if (frParent != 0) System.out.println("Parent " + startNode.parent.element + "," + frParent);
//		// if (frParent == 0) return;
//		if (Math.abs(frParent) > 1) {
//			int frChild = balanceFactor(startNode);
//			System.out.println("Child " + startNode.element + "," + frChild);
//			if (frParent == 2) {
//				if (frChild >= 0) {
//					rotateLeft(startNode);
//				} else {
//					rotateRightLeft(startNode);
//				}
//			} else {
//				if (frChild <= 0) {
//					rotateRight(startNode);
//				} else {
//					rotateLeftRight(startNode);
//				}
//			}
//		} else {
//			balance(startNode.parent);
//		}
//
//	}

	private void rotateLeft(AVLNode node) {
		AVLNode parent = node.parent;
		parent.right = node.left;
		if (parent.right != null) {
			parent.right.parent = parent;
		}
		node.left = parent;
		node.parent = parent.parent;
		parent.parent = node;
		if (node.parent == null) {
			root = node;
		}
		if (node.parent != null) {
			if (parent.equals(node.parent.left)) {
				node.parent.left = node;
			} else {
				node.parent.right = node;
			}
		}

	}

	private void rotateRight(AVLNode node) {
		AVLNode parent = node.parent;
		parent.left = node.right;
		node.right = parent;
		if (parent.left != null) {
			parent.left.parent = parent;
		}
		node.parent = parent.parent;
		parent.parent = node;
		if (node.parent == null) {
			root = node;
		}
		if (node.parent != null) {
			if (parent.equals(node.parent.left)) {
				node.parent.left = node;
			} else {
				node.parent.right = node;
			}
		}
	}

	private void rotateRightLeft(AVLNode node) {
		AVLNode rot = node.left;
		rotateRight(rot);
		rotateLeft(rot);
	}

	private void rotateLeftRight(AVLNode node) {
		AVLNode rot = node.right;
		rotateLeft(rot);
		rotateRight(rot);
	}

	private AVLNode addElement(int element, AVLNode node) {
		if (element < node.element) {
			if (node.left == null) {
				node.left = new AVLNode();
				node.left.parent = node;
				node.left.element = element;
				return node.left;
			} else {
				return addElement(element, node.left);
			}
		} else {
			if (node.right == null) {
				node.right = new AVLNode();
				node.right.parent = node;
				node.right.element = element;
				return node.right;
			} else {
				return addElement(element, node.right);
			}
		}
	}

	public AVLNode getRoot() {
		return root;
	}

	public String prettyString() {
		return root.prettyString();
	}
	
	@Override
	public String toString() {
		return root.toString();
	}

	public static class AVLNode {
		int element;
		AVLNode parent;
		AVLNode left;
		AVLNode right;

		public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
			if (right != null) {
				right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
			}
			sb.append(prefix).append(isTail ? "└── " : "┌── ").append(element).append("\n");
			if (left != null) {
				left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
			}
			return sb;
		}

		public String prettyString() {
			return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (left != null) {
				sb.append(left);
			}
			
			sb.append(element);
			
			if (right != null) { 
				sb.append(right);
			}
			return sb.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + element;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AVLNode other = (AVLNode) obj;
			if (element != other.element)
				return false;
			return true;
		}

	}

}
