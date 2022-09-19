import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class TreeViewer extends JFrame
{
    JLabel label1;

    TreeViewer()
    {
        label1 = new JLabel();
        label1.setText("welcome");
        getContentPane().add(label1);
    }
}

class TreeNode
{
    int data;
    TreeNode leftChild;
    TreeNode rightChild;

    TreeNode(Integer data) {
        this.data = data;
    }

    public void print()
    {
        print("", this, false);
    }

    public void print(String prefix, TreeNode n, boolean isLeft)
    {
        if (n != null) {
            System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + n.data);
            print(prefix + (isLeft ? "|   " : "    "), n.leftChild, true);
            print(prefix + (isLeft ? "|   " : "    "), n.rightChild, false);
        }
    }

    public void displayNode()
    {
        System.out.print('{');
        System.out.print(data);
        System.out.print("} ");
    }
}

class Tree
{
    public TreeNode root;

    public void  insert(int id)
    {
        TreeNode newNode = new TreeNode(id);

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

    public TreeNode delete(int key)
    {
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

    public void getTree()
    {
        TreeNode localRoot = root;
        preorder(localRoot);
    }

    private void preorder(TreeNode localRoot)
    {
        System.out.print(localRoot.data + " ");
        if (localRoot.leftChild != null)
            preorder(localRoot.leftChild);
        if (localRoot.rightChild != null)
            preorder(localRoot.rightChild);
    }
}

public class zadanie5 {
    public static void main(String[] args)
    {
        Tree tree = new Tree();
        for(int i = 25; i<=50; i+=5){
            tree.insert(i);
            tree.insert(i-25);
        }
//        tree.delete(42);
        tree.insert(-1);
        tree.insert(-10);
        tree.insert(-5);
//        tree.insert(-20);
//        tree.insert(17);
//        tree.getTree();
//
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(bos));
        tree.root.print();
//        System.out.println("example");
//        JOptionPane.showMessageDialog(null, "Captured: " + '\n' + bos);
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//        frame.add(bos);
//        frame.setSize(200, 200);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
    }
}
