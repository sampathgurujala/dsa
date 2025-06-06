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
public class IterativeTraversals {
    public TreeNode findInorderPredecessor(TreeNode root,TreeNode parent)
    {
        TreeNode curr= root;
        while(curr!=null && curr.right!=null && curr.right!=parent)
        {
            curr= curr.right;
        }
        return curr;
    }
    //can be used for values that lies in range n1,n2
    public List<Integer> morrisInorderTraversal(TreeNode root)      //T:O(N)    S:O(1)
    {
        TreeNode curr=root;
        List<Integer> res=new ArrayList<>();
        while(curr!=null)
        {
            if(curr.left==null)
            {
                res.add(curr.val);
                curr=curr.right;
            }
            else
            {
                TreeNode predecessor = findInorderPredecessor(curr.left,curr);
                if(predecessor.right==null)
                {
                    predecessor.right = curr;
                    curr=curr.left;
                }
                else
                {
                    predecessor.right = null;
                    res.add(curr.val);
                    curr=curr.right;
                }
            }
        }
        return res;
    }

    public List<Integer> preorderIterative(TreeNode root)   //T: O(h) S:O(h)  where in worst case, h can be |N|
    {
        Stack<TreeNode> st= new Stack<>();
        List<Integer> res= new ArrayList<>();
        if(root== null)
            return res;
        st.push(root);
        while(!st.isEmpty())
        {
            TreeNode top =st.pop();
            res.add(top.val);
            if(top.right!=null)
                st.push(top.right);
            if(top.left!=null)
                st.push(top.left);

        }
        return res;
    }
    public List<Integer> postorderIterative(TreeNode root)
    {
        Stack<TreeNode> s1= new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        List<Integer> res= new ArrayList<>();
        if(root== null)
            return res;
        s1.push(root);
        while(!s1.isEmpty())
        {
            TreeNode top =s1.pop();
            s2.push(top.val);
            if(top.left!=null)
                s1.push(top.left);
            if(top.right!=null)
                s1.push(top.right);
        }
        while(!s2.isEmpty())
        {
            res.add(s2.pop());
        }
        return res;
    }
    public TreeNode inorderToBSTConstruction(int[] arr, int low, int high)
    {
        if(low>high)
            return null;
        int mid = low + (high-low)/2;

        TreeNode root = new TreeNode(arr[mid]);
        root.left = inorderToBSTConstruction(arr, low,mid-1);
        root.right = inorderToBSTConstruction(arr, mid+1,high);
        return root;
    }
    public static void main(String[] args)
    {
        IterativeTraversals bst = new IterativeTraversals();
        TreeNode root = null;
        root=bst.insert(root,100);
        root=bst.insert(root,20);
        root=bst.insert(root,500);
        root=bst.insert(root,10);
        root=bst.insert(root,30);
        root=bst.insert(root,40);

        List<Integer> res=null;
        res= bst.morrisInorderTraversal(root);
        for(int x: res)
        {
            System.out.print(x+"->");
        }
        System.out.println();
        res= bst.preorderIterative(root);
        for(int x: res)
        {
            System.out.print(x+"->");
        }
        System.out.println();
        res = bst.postorderIterative(root);
        for(int x: res)
        {
            System.out.print(x+"->");
        }

    }
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
}