class TreeNode
{
    int data;
    TreeNode leftChild;
    TreeNode rightChild;

    public void displayNode()
    {
        System.out.print('{');
        System.out.print(data);
        System.out.print("} ");
    }
}

class Tree
{
    private TreeNode root;

    public void  insert(int id)
    {
        TreeNode newNode = new TreeNode();
        newNode.data = id;

        if (root == null)
            root = newNode;
        else {
           TreeNode current = root;
           TreeNode parent;

           while (true) {
               parent = current;
               if (id < current.data) {
                   current = current.leftChild;

                   if (current == null) {
                       parent.leftChild = newNode;
                       return;
                   }
               } else {
                   current = current.rightChild;

                   if (current == null) {
                       parent.rightChild = newNode;
                       return;
                   }
               }
           }
        }
    }

    public TreeNode find(int key)
    {
        TreeNode current = root;

        while (current.data != key) {
            if (key < current.data)
                current = current.leftChild;
            else
                current = current.rightChild;
            if (current == null)
                return null;
        }

        return  current;
    }

    public TreeNode delete(int key) {
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;

        while (current.data != key) {
            parent = current;

            if (key < current.data) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }

            if (current == null)
                return null;
        }

        if (current.leftChild == null && current.rightChild == null)
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        else if (current.rightChild == null)
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.rightChild;
        else if (current.leftChild == null)
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        else {
            TreeNode successor = current.rightChild;
            if (successor.leftChild == null) {
                if (isLeftChild) {
                    parent.leftChild = successor;
                    successor.leftChild = current.leftChild;
                } else {
                    parent.rightChild = successor;
                    successor.leftChild = current.leftChild;
                }
            } else {
                TreeNode successorParent = successor;

                while (successor.leftChild != null) {
                    successorParent = successor;
                    successor = successor.leftChild;
                }

                successorParent.leftChild = successor.rightChild;
                successor.rightChild = current.rightChild;
                parent.rightChild = successor;
                successor.leftChild = current.leftChild;
            }
        }
        return current;
    }
}

public class zadanie5 {

}
