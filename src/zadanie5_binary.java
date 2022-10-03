import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;

abstract class NodePrinter
{
    abstract void nodeprint (TreeNode root, Tree tree);
    int max (int a, int b) { return Math.max(a, b); }
    int depth (TreeNode node)
    {
        if (node.leftChild == null && node.rightChild == null) return 1;
        if (node.leftChild == null) return 1 + depth(node.rightChild);
        if (node.rightChild == null) return 1 + depth(node.leftChild);
        return 1 + max (depth(node.leftChild), depth(node.rightChild));
    }
}

class TreeCanvas extends JPanel {
    private TreeNode root;
    private NodePrinter np;

    public TreeCanvas(TreeNode root, NodePrinter np) {
        this.root = root;
        this.np = np;
        d = np.depth(root);
        rows = (2 * d);
        cols = 2 << d;
    }

    private int d;
    private  int rows;
    private int cols;

    public void paint(Graphics g) {
        Dimension dim = getSize();
        int xf = dim.width;
        int yf = dim.height;
        int fontsize = (xf + yf) / 2;
        g.setFont(g.getFont().deriveFont(fontsize * 1.5f));
        xyPrint(root, dim.width/2, dim.width/2, 1, xf, yf, g);
    }

    void  xyPrint(TreeNode node, int x, int dx, int y, int xf, int yf, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3.0f));

        g.drawString("" + node.data, x - xf, (y + 1) * yf);
        g.setColor(Color.BLACK);
        if (node.leftChild != null) {
            g.drawLine(x - (dx / 2) + xf, (y + 2) * yf, x, (y + 1) * yf);
            xyPrint(node.leftChild, x - dx / 2, dx / 2, y + 2, xf, yf, g);
        }
        if (node.rightChild != null) {
            g.drawLine (x + xf, (y + 1) * yf, x + (dx / 2), (y + 2) * yf); // line down
            xyPrint (node.rightChild, x + dx / 2, dx / 2, y + 2, xf, yf, g);
        }
    }
}

class CanvasPrinter extends NodePrinter {

    JTree makeTree(TreeNode root, Container c)
    {
        JTree jt = new JTree(translate2SwingTree(root));

        c.add(jt, BorderLayout.NORTH);
        openSubnodes(0, jt);
        return jt;

    }
    void  nodeprint(TreeNode root, Tree tree)
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
                tree.insert(globalInt);
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
                if (tree.find(globalInt) != null)
                    t2.setText("Нашел: " + tree.find(globalInt).data);
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
                tree.delete(globalInt);
                Component[] cList = c.getComponents();

                for(Component comp : cList){

                    if(comp instanceof JTree){

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

    void openSubnodes (int row, JTree jt) {
        TreePath tp = jt.getPathForRow(row);
        jt.expandRow(row);
        if (tp.isDescendant(jt.getPathForRow(row + 1)))
            openSubnodes(row + 1, jt);
    }

    DefaultMutableTreeNode translate2SwingTree (TreeNode ast) {
        DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode("" + ast.data);
        if (ast.leftChild != null)
            dmtn.add(translate2SwingTree(ast.leftChild));
        if (ast.rightChild != null)
            dmtn.add(translate2SwingTree(ast.rightChild));
        return dmtn;
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

public class zadanie5_binary {
    public static void main(String[] args)
    {
        Tree tree = new Tree();
        for(int i = 25; i<=10; i+=5){
            tree.insert(i);
            tree.insert(i-25);
        }

        CanvasPrinter printer = new CanvasPrinter();
        printer.nodeprint(tree.root, tree);
    }
}
