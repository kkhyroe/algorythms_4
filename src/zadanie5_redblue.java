import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;

abstract class RBNodePrinter
{
    abstract void nodePrint (RBNode root, RBTree tree);
    int max (int a, int b) { return Math.max(a, b); }
    int depth(RBNode node)
    {
        if (node.leftChild == null && node.rightChild == null) return 1;
        if (node.leftChild == null) return 1 + depth(node.rightChild);
        if (node.rightChild == null) return 1 + depth(node.leftChild);
        return 1 + max (depth(node.leftChild), depth(node.rightChild));
    }
}

class RBCanvasPrinter extends RBNodePrinter {

    JTree makeTree(RBNode root, Container c)
    {
        JTree jt = new JTree(translate2SwingTree(root));

        c.add(jt, BorderLayout.NORTH);
        openSubNodes(0, jt);
        return jt;

    }
    void  nodePrint(RBNode root, RBTree tree)
    {
        JFrame jf = new JFrame("Tree");

        Container c = jf.getContentPane();


        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.setLayout(new GridLayout(2, 3, 10, 10));
        jf.setSize(600, 600);
        jf.setLocationRelativeTo(null);

        JTree jt = makeTree(root, c);

        final JTextField t1 = new JTextField(100);
        t1.setFont(t1.getFont().deriveFont(Font.BOLD, 50f));

        final JButton n1 = new JButton("Add");
        n1.addActionListener(e -> {
            String global = t1.getText();
            try {
                int globalInt = Integer.parseInt(global);
                tree.insertNode(globalInt);
                Component[] cList = c.getComponents();

                for(Component comp : cList){

                    //Find the components you want to remove
                    if(comp instanceof JTree){

                        //Remove it
                        c.remove(comp);
                    }
                }
                c.revalidate();
                c.repaint();
                makeTree(root, c);
            } catch (NumberFormatException err) {
                t1.setText("Ошибка (текст)");
            }
        });

        final JTextField t2 = new JTextField(100);
        t2.setFont(t2.getFont().deriveFont(Font.BOLD, 50f));

        final JButton n2 = new JButton("Find");
        n2.addActionListener(e -> {
            String global = t2.getText();
            try {
                int globalInt = Integer.parseInt(global);
                if (tree.searchNode(globalInt) != null)
                    t2.setText("Нашел: " + tree.searchNode(globalInt).data);
                else
                    t2.setText("Не нашел");
            } catch (NumberFormatException err) {
                t2.setText("Ошибка (текст)");
            }
        });


        final JTextField t3 = new JTextField(100);
        t3.setFont(t3.getFont().deriveFont(Font.BOLD, 50f));

        final JButton n3 = new JButton("Delete");
        n3.addActionListener(e -> {
            String global = t3.getText();
            try {
                int globalInt = Integer.parseInt(global);
                tree.deleteNode(globalInt);
                Component[] cList = c.getComponents();

                for(Component comp : cList){

                    //Find the components you want to remove
                    if(comp instanceof JTree){

                        //Remove it
                        c.remove(comp);
                    }
                }
                c.revalidate();
                c.repaint();
                makeTree(root, c);
            } catch (NumberFormatException err) {
                t3.setText("Ошибка (текст)");
            }
        });


        p1.add(t1);
        p1.add(t2);
        p1.add(t3);
        p1.add(n1);
        p1.add(n2);
        p1.add(n3);
        c.add(p1);


        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setVisible(true);
    }

    void openSubNodes (int row, JTree jt) {
        TreePath tp = jt.getPathForRow(row);
        jt.expandRow(row);
        if (tp.isDescendant(jt.getPathForRow(row + 1)))
            openSubNodes(row + 1, jt);
    }

    DefaultMutableTreeNode translate2SwingTree (RBNode ast) {
        DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode("" + ast.data);
        if (ast.leftChild != null)
            dmtn.add(translate2SwingTree(ast.leftChild));
        if (ast.rightChild != null)
            dmtn.add(translate2SwingTree(ast.rightChild));
        return dmtn;
    }
}

class RBNode {
    int data;

    RBNode leftChild;
    RBNode rightChild;
    RBNode parent;

    Color color;

    public void print()
    {
        print("", this, false);
    }

    public void print(String prefix, RBNode n, boolean isLeft)
    {
        if (n != null) {
            System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + n.data);
            print(prefix + (isLeft ? "|   " : "    "), n.leftChild, true);
            print(prefix + (isLeft ? "|   " : "    "), n.rightChild, false);
        }
    }

    public RBNode(int data) {
        this.data = data;
    }

    public boolean isBlack() {
        return this == null || this.color == BLACK;
    }
}

class RBTree {
    public RBNode root;

    public void getTree()
    {
        RBNode localRoot = root;
        preorder(localRoot);
    }

    private void preorder(RBNode localRoot)
    {
        System.out.print(localRoot.data + " ");
        if (localRoot.leftChild != null)
            preorder(localRoot.leftChild);
        if (localRoot.rightChild != null)
            preorder(localRoot.rightChild);
    }

    private void replaceParentsChild(RBNode parent, RBNode oldChild, RBNode newChild) {
        if (parent == null)
            root = newChild;
        else if (parent.leftChild == oldChild)
            parent.leftChild = newChild;
        else if (parent.rightChild == oldChild)
            parent.rightChild = newChild;
        else
            throw new IllegalStateException("Узел не потомок своего родителя");

        if (newChild != null)
            newChild.parent = parent;
    }

    private void rotateRight(RBNode node) {
        RBNode parent = node.parent;
        RBNode leftChild = node.leftChild;

        node.leftChild = leftChild.rightChild;
        if (leftChild.rightChild != null)
            leftChild.rightChild.parent = node;

        leftChild.rightChild = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }

    private void rotateLeft(RBNode node) {
        RBNode parent = node.parent;
        RBNode rightChild = node.rightChild;

        node.leftChild = rightChild.leftChild;
        if (rightChild.leftChild != null)
            rightChild.leftChild.parent = node;

        rightChild.leftChild = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);
    }

    public RBNode searchNode(int key) {
        RBNode node = root;

        while(node != null) {
            if (key == node.data)
                return node;
            else if (key < node.data)
                node = node.leftChild;
            else
                node = node.rightChild;
        }

        return node;
    }

    private RBNode getUncle(RBNode parent) {
        RBNode grandparent = parent.parent;
        if (grandparent.leftChild == parent) {
            return grandparent.rightChild;
        } else if (grandparent.rightChild == parent) {
            return grandparent.leftChild;
        } else {
            throw new IllegalStateException("Родитель не является потомком своего родителя");
        }
    }

    private void fixedRBPropertiesAfterInsert(RBNode node) {
        RBNode parent = node.parent;

        if (parent == null) {
            return;
        }

        if (parent.color == BLACK)
            return;

        RBNode grandparent = parent.parent;

        if (grandparent == null) {
            parent.color = BLACK;
            return;
        }

        RBNode uncle = getUncle(parent);

        if (uncle != null && uncle.color == RED) {
            parent.color = BLACK;
            grandparent.color = RED;
            uncle.color = BLACK;

            fixedRBPropertiesAfterInsert(grandparent);
        } else if (parent == grandparent.leftChild) {
            if (node == parent.rightChild) {
                rotateLeft(parent);

                parent = node;
            }

            rotateRight(grandparent);

            parent.color = BLACK;
            grandparent.color = RED;
        } else {
            if (node == parent.leftChild) {
                rotateRight(parent);

                parent = node;
            }

            rotateLeft(grandparent);

            parent.color = BLACK;
            grandparent.color = RED;
        }
    }

    public RBNode insertNode(int key) {
        RBNode node = root;
        RBNode parent = null;

        while(node != null) {
            parent = node;

            if (key < node.data)
                node = node.leftChild;
            else if (key > node.data)
                node = node.rightChild;
            else
                throw new IllegalArgumentException("В дереве уже есть ключ " + key);
        }

        RBNode newNode = new RBNode(key);
        newNode.color = RED;

        if (parent == null)
            root = newNode;
        else if (key < parent.data)
            parent.leftChild = newNode;
        else
            parent.rightChild = newNode;

        newNode.parent = parent;

        fixedRBPropertiesAfterInsert(newNode);

        return newNode;
    }

    private static class LeafNode extends RBNode {
        private LeafNode() {
            super(0);
            this.color = BLACK;
        }
    }

    private RBNode deleteNodeWithZeroOrOneChild(RBNode node) {
        if (node.leftChild != null) {
            replaceParentsChild(node.parent, node, node.leftChild);
            return node.leftChild;
        } else if (node.rightChild != null) {
            replaceParentsChild(node.parent, node, node.rightChild);
            return node.rightChild;
        } else {
            RBNode newChild = node.color == BLACK ? new LeafNode() : null;
            replaceParentsChild(node.parent, node, newChild);
            return newChild;
        }
    }

    private void handleRedSibling(RBNode node, RBNode sibling) {
        sibling.color = BLACK;
        node.parent.color = RED;

        if (node == node.parent.leftChild)
            rotateLeft(node.parent);
        else
            rotateRight(node.parent);
    }

    private void handleBlackSiblingWithAtLeastOneRedChild(RBNode node, RBNode sibling) {
        boolean nodeIsLeftChild = node == node.parent.leftChild;

        if (nodeIsLeftChild && sibling.rightChild.isBlack()) {
            sibling.leftChild.color = BLACK;
            sibling.color = RED;
            rotateRight(sibling);
            sibling = node.parent.rightChild;
        } else if (!nodeIsLeftChild && sibling.leftChild.isBlack()) {
            sibling.rightChild.color = BLACK;
            sibling.color = RED;
            rotateLeft(sibling);
            sibling = node.parent.leftChild;
        }

        sibling.color = node.parent.color;
        node.parent.color = BLACK;
        if (nodeIsLeftChild) {
            sibling.rightChild.color = BLACK;
            rotateLeft(node.parent);
        } else {
            sibling.leftChild.color = BLACK;
            rotateRight(node.parent);
        }
    }

    private RBNode getSibling(RBNode node) {
        RBNode parent = node.parent;
        if (node == parent.leftChild) {
            return parent.rightChild;
        } else if (node == parent.rightChild) {
            return parent.leftChild;
        } else {
            throw new IllegalStateException("Родитель не потомок своего прародителя");
        }
    }

    private void fixedRBPropertiesAfterDelete(RBNode node) {
        if (node == root) {
            node.color = BLACK;
            return;
        }

        RBNode sibling = getSibling(node);

        if (sibling.color == RED) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node);
        }

        if (sibling.leftChild.isBlack() && sibling.rightChild.isBlack()) {
            sibling.color = RED;

            if (node.parent.color == RED) {
                node.parent.color = BLACK;
            }

            else {
                fixedRBPropertiesAfterDelete(node.parent);
            }
        }

        else {
            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
        }
    }

    private RBNode findMinimum(RBNode node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }

        return node;
    }

    public RBNode deleteNode(int key) {
        RBNode node = root;

        while (node != null && node.data != key) {
            if (key < node.data)
                node = node.leftChild;
            else
                node = node.rightChild;
        }

        if (node == null)
            return null;

        RBNode movedUpNode;
        Color deletedNodeColor;

        if (node.leftChild == null || node.rightChild == null) {
            movedUpNode = deleteNodeWithZeroOrOneChild(node);
            deletedNodeColor = node.color;
        } else {
            RBNode inOrderSuccessor = findMinimum(node.rightChild);

            node.data = inOrderSuccessor.data;

            movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
            deletedNodeColor = inOrderSuccessor.color;
        }

        if (deletedNodeColor == BLACK) {
            fixedRBPropertiesAfterDelete(movedUpNode);

            if (movedUpNode.getClass() == LeafNode.class)
                replaceParentsChild(movedUpNode.parent, movedUpNode, null);
        }

        return node;
    }
}

public class zadanie5_redblue {
    public static void main(String[] args)
    {
        RBTree tree = new RBTree();
        tree.insertNode(50);
        tree.insertNode(100);
        tree.insertNode(0);
//        tree.insertNode(2);
//        tree.insertNode(4);
//        tree.insertNode(10);

        tree.root.print();
        System.out.println(tree.root.rightChild.color);
//        tree.insertNode(1);
//        System.out.println(tree.root.leftChild.data);

        RBCanvasPrinter printer = new RBCanvasPrinter();
        printer.nodePrint(tree.root, tree);
    }
}
