import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * Filename: BTView.java
 * <p>
 * Description: JavaFX pane used for displaying the BST in the scene.
 * <p>
 * Course: CSC 364-001
 * @author Will St. Onge
 */
public class BTView extends Pane {
    private BST<Integer> tree = new BST<>();
    private double radius = 15; // Tree node radius
    private double vGap = 50; // Gap between successive levels in the tree
    private ArrayList<BST.TreeNode<Integer>> path;

	BTView(BST<Integer> tree) {
        this.tree = tree;
        path = new ArrayList<BST.TreeNode<Integer>>();
        setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
        this.getChildren().add(new Text(20, 20, msg));
    }
    
    public void setPath(ArrayList<BST.TreeNode<Integer>> path) {
    	this.path = path;
    }

    public ArrayList<BST.TreeNode<Integer>> getPath() {
		return path;
	}
    
    public void displayTree() {
        this.getChildren().clear(); // Clear the pane
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayTree(tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4, this.path);
        }
    }

    /** Display a subtree rooted at position (x, y) */
    private void displayTree(BST.TreeNode<Integer> root,
                             double x, double y, double hGap, ArrayList<BST.TreeNode<Integer>> path) {
        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayTree(root.left, x - hGap, y + vGap, hGap / 2, path);
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayTree(root.right, x + hGap, y + vGap, hGap / 2, path);
        }

        // Display a node
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        for(BST.TreeNode<Integer> i : path)
        	if(root.equals(i))
        		circle.setFill(Color.ORANGE);
        circle.setStroke(Color.BLACK);
        this.getChildren().addAll(circle,
                new Text(x - 4, y + 4, root.element.toString()));
    }
}
