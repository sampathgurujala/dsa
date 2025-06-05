import java.util.*;
class TreeNode
{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
        this.left = left;
        this.right = right;
     }
}

public class BinarySearchTree {
    public TreeNode insert(TreeNode root, int key)
    {
        if(root==null)
            return new TreeNode(key);

        if (root.val == key)
            return root;

        if(root.val>key)
            root.left = insert(root.left,key);
        else
            root.right=insert(root.right,key);
        return root;
    }
    public TreeNode findSuccessor(TreeNode root)
    {
        TreeNode curr=root;
        while(curr!=null && curr.right!=null)
        {
            curr=curr.right;
        }
        return curr;
    }
    public TreeNode delete(TreeNode root, int key)
    {
        if (root==null)
            return null;
        if(root.val>key)
            root.left = delete(root.left,key);
        else if(root.val< key)
            root.right = delete(root.right, key);
        else {
            if (root.left == null && root.right == null)
                return null;
            else if (root.left == null && root.right != null) {
                return root.right;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else {
                TreeNode successor = findSuccessor(root.left);
                root.val = successor.val;
                root.left = delete(root.left, successor.val);
            }
        }
            return root;
    }
    public TreeNode searchBST(TreeNode root, int key)
    {
        if(root==null)
            return null;
        if(root.val==key)
            return root;
        if(root.val > key)
            return searchBST(root.left,key);
        else
            return searchBST(root.right,key);
    }
    public void inorderTraversal(TreeNode root)
    {
        if(root==null)
            return;
        inorderTraversal(root.left);
        System.out.print(root.val+"->");
        inorderTraversal(root.right);
    }
    public void levelOrderTraversal(TreeNode root)
    {
        if(root==null)
            return;
        Queue<TreeNode> q= new LinkedList();
        q.offer(root);
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i=0;i<size;i++) {
                TreeNode node = q.poll();
                System.out.print(node.val + "->");
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            System.out.println();
        }
        return;
    }
    public void preorderTraversal(TreeNode root)
    {
        if(root==null)
            return;
        System.out.print(root.val+"->");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }
    public void postorderTraversal(TreeNode root)
    {
        if(root==null)
            return;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.print(root.val+"->");
    }
public static void main(String[] args)
 {
     BinarySearchTree bst = new BinarySearchTree();
     TreeNode root = null;
     root=bst.insert(root,100);
     root=bst.insert(root,20);
     root=bst.insert(root,500);
     root=bst.insert(root,10);
     root=bst.insert(root,30);
     root=bst.insert(root,40);

     bst.inorderTraversal(root);
     System.out.println();
     bst.preorderTraversal(root);
     System.out.println();
     bst.postorderTraversal(root);
     System.out.println();
     bst.levelOrderTraversal(root);

     System.out.println("-------------------------------------");
     TreeNode node = bst.searchBST(root,40);
     if(node!=null)
         System.out.println(node.val);
    else
        System.out.println("No value Found");
     root= bst.delete(root,30);
     System.out.println("-------------After Deletion------------------------");
    bst.inorderTraversal(root);

 }
}