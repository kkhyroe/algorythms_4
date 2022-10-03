import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;

abstract class RBNodePrinter
{
    abstract void nodePrint (RBNode root, RBTree tree);
    int max (int a, int b) { return Math.max(a, b); }
    int depth(RBNode node)
    {
        if (node.left == null && node.right == null) return 1;
        if (node.left == null) return 1 + depth(node.right);
        if (node.right == null) return 1 + depth(node.left);
        return 1 + max (depth(node.left), depth(node.right));
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
        JFrame jf = new JFrame("Red-Black Tree");
        jf.setSize(600, 600);
        jf.setLocationRelativeTo(null);

        Container c = jf.getContentPane();

        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.setLayout(new GridLayout(2, 3, 10, 10));

        JTree jt = makeTree(root, c);
        openSubNodes(0, jt);

        final JTextField t1 = new JTextField(100);
        t1.setFont(t1.getFont().deriveFont(Font.BOLD, 50f));

        final JButton n1 = new JButton("Add");
        n1.addActionListener(e -> {
            String global = t1.getText();

            try {
                int globalInt = Integer.parseInt(global);
                tree.insert(globalInt);

                Component[] cList = c.getComponents();

                for (Component comp : cList) {
                    if (comp instanceof JTree)
                        c.remove(comp);
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

                if (tree.searchTree(globalInt) != null)
                    t2.setText("Нашел: " + tree.searchTree(globalInt).data);
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

                for (Component comp : cList){
                    if (comp instanceof JTree)
                        c.remove(comp);
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
        DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode("" + ast.data + (ast.color == 1 ? " red" : " black"));

        if (ast.left != null)
            dmtn.add(translate2SwingTree(ast.left));
        if (ast.right != null)
            dmtn.add(translate2SwingTree(ast.right));

        return dmtn;
    }
}

class RBNode {
    int data;
    RBNode parent;
    RBNode left;
    RBNode right;
    int color;
}

class RBTree {
    public RBNode root;

    private RBNode TNULL;

    public RBTree() {
        TNULL = new RBNode();
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    private RBNode searchTreeHelper(RBNode node, int key) {
        if (node == TNULL || key == node.data)
            return node;

        if (key < node.data)
            return searchTreeHelper(node.left, key);

        return searchTreeHelper(node.right, key);
    }

    private void fixDelete(RBNode x) {
        RBNode s;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.right.color == 0) {
                        s.left.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.left.color == 0) {
                        s.right.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }

    private void rbTransplant(RBNode u, RBNode v){
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left){
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void deleteNodeHelper(RBNode node, int key) {
        RBNode z = TNULL;
        RBNode x, y;
        while (node != TNULL){
            if (node.data == key) {
                z = node;
            }

            if (node.data <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        int yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == 0){
            fixDelete(x);
        }
    }

    private void fixInsert(RBNode k){
        RBNode u;
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right; // uncle

                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = 0;
    }

    public RBNode searchTree(int key) {
        return searchTreeHelper(this.root, key);
    }

    public RBNode minimum(RBNode node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    // rotate left at node x
    public void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(RBNode x) {
        RBNode y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void insert(int key) {
        RBNode node = new RBNode();
        node.parent = null;
        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = 1;

        RBNode y = null;
        RBNode x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null){
            node.color = 0;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    public void deleteNode(int data) {
        deleteNodeHelper(this.root, data);
    }
}

public class zadanie5_redblue {
    public static void main(String[] args)
    {
        RBTree tree = new RBTree();
        tree.insert(100);
        tree.insert(50);
        tree.insert(200);

        RBCanvasPrinter printer = new RBCanvasPrinter();
        printer.nodePrint(tree.root, tree);
    }
}
