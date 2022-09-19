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

    public void delete(int id)
    {

    }
}

public class zadanie5 {

}
