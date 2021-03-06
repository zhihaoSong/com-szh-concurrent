package com.szh.algorithm.tree;


import java.util.*;

/**
 * 前序遍历+中序遍历 重构二叉树
 */
public class Solution {
    public TreeNode reConstructBinaryTree1(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0)
            return null;
        return reConstructBin(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    TreeNode reConstructBin(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn)
            return null;
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = 0; i < in.length; i++) {
            if (in[i] == root.val) {
                root.left = reConstructBin(pre, startPre + 1, i + startPre - startIn, in, startIn, i - 1);
                root.right = reConstructBin(pre, i + startPre - startIn + 1, endPre, in, i + 1, endIn);
            }
        }
        return root;
    }


    public TreeNode reConstructBinaryTree3(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode node = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) {
                node.left = reConstructBinaryTree3(Arrays.copyOfRange(pre, 1, i + 1),
                        Arrays.copyOfRange(in, 0, i));
                node.right = reConstructBinaryTree3(Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return node;
    }
    //输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0)
            return null;
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (root.val == in[i]) {
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1),
                        Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return root;
    }


    public void prePrint(TreeNode root) {
        System.out.print(root.val + "\t");
        if (root.left != null)
            prePrint(root.left);
        if (root.right != null)
            prePrint(root.right);
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if (root == null) return null;
        ArrayList<Integer> result = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode index = queue.poll();
            result.add(index.val);
            if (index.left != null) {
                queue.add(index.left);
            }
            if (index.right != null)
                queue.add(index.right);
        }
        return result;
    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0)
            return false;
        return judgeBST(sequence, 0, sequence.length - 1);
    }

    public boolean judgeBST(int[] sequence, int left, int right) {
        if (right <= left) return true;
        int i = left;
        for (; i < right; i++) {
            if (sequence[i] > sequence[right])
                break;
        }
        for (int j = i; j < right; j++) {
            if (sequence[j] < sequence[right])
                return false;
        }
        return judgeBST(sequence, left, i - 1) && judgeBST(sequence, i, right - 1);
    }

    private ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> tempPath = new ArrayList<Integer>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) return null;
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tempPath = new ArrayList<Integer>();
        AddFindPath(paths, tempPath, root, target);
        return paths;
    }

    public void AddFindPath(
            ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> tempPath, TreeNode root, int target) {
        if (root == null) return;
        tempPath.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null)
            paths.add(new ArrayList<Integer>(tempPath));
        AddFindPath(paths, tempPath, root.left, target);
        AddFindPath(paths, tempPath, root.right, target);
        tempPath.remove(tempPath.size() - 1);
    }

    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0)
            return -1;
        char[] charArr = str.toCharArray();
        int len = charArr.length;
        int[] temp = new int['z' + 1];

        for (int i = 0; i < len; i++) {
            temp[(int) charArr[i]]++;
        }
        for (int i = 0; i < len; i++) {
            if (temp[(int) charArr[i]] == 1)
                return i;
        }
        return -1;
    }

    public int TreeDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(TreeDepth(root.left) - TreeDepth(root.right)) > 1)
            return false;
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length < 2) return;
        int flag = 1;
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result ^= array[i];
        }
        while ((result & flag) == 0)
            flag = flag << 1;
        for (int i = 0; i < array.length; i++) {
            if ((flag & array[i]) == 0)
                num1[0] ^= array[i];
            else
                num2[0] ^= array[i];
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) return pNode;
        if (pNode.right != null) {
            TreeLinkNode rightNode = pNode.right;
            while (rightNode.left != null) {
                rightNode = rightNode.left;
            }
            return rightNode;
        }
        while (pNode.next != null) {
            if (pNode.next.left == pNode) return pNode.next;
            pNode = pNode.next;
        }
        return null;
    }

    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    boolean isSymmetrical(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left != null && right != null)
            return left.val == right.val && isSymmetrical(left.left, right.right)
                    && isSymmetrical(left.right, right.left);
        return false;
    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (pRoot == null)
            return res;
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.add(pRoot);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                ArrayList<Integer> aList1 = new ArrayList<Integer>();
                while (!stack1.isEmpty()) {
                    TreeNode note = stack1.pop();
                    aList1.add(note.val);
                    if (note.left != null) stack2.add(note.left);
                    if (note.right != null) stack2.add(note.right);
                }
                res.add(aList1);
            } else {
                ArrayList<Integer> aList2 = new ArrayList<Integer>();
                while (!stack2.isEmpty()) {
                    TreeNode note = stack2.pop();
                    aList2.add(note.val);
                    if (note.right != null) stack1.add(note.right);
                    if (note.left != null) stack1.add(note.left);
                }
                res.add(aList2);
            }
        }
        return res;
    }

    ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (pRoot == null) return result;
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(pRoot);
        int lineLen = 1, count = 0;
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            lineLen--;
            tmp.add(current.val);
            if (current.left != null) {
                queue.add(current.left);
                count++;
            }
            if (current.right != null) {
                queue.add(current.right);
                count++;
            }
            if (lineLen == 0) {
                result.add(tmp);
                tmp = new ArrayList<Integer>();
                lineLen = count;
                count = 0;
            }
        }
        return result;
    }

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        if (pRootOfTree.left == null && pRootOfTree.right == null) return pRootOfTree;
        TreeNode left = Convert(pRootOfTree.left);
        TreeNode temp = left;
        while (temp != null && temp.right != null)
            temp = temp.right;
        if (left != null) {
            pRootOfTree.left = temp;
            temp.right = pRootOfTree;
        }
        TreeNode right = Convert(pRootOfTree.right);
        if (right != null) {
            pRootOfTree.right = right;
            right.left = pRootOfTree;
        }
        return left == null ? pRootOfTree : left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode aaa = solution.reConstructBinaryTree
                (
                        new int[]{1, 2, 4, 7, 3, 5, 6, 8},
                        new int[]{4, 7, 2, 1, 5, 3, 8, 6}
                );
        solution.prePrint(aaa);

        TreeNode bbb = solution.reConstructBinaryTree1(
                new int[]{1, 2, 4, 7, 3, 5, 6, 8},
                new int[]{4, 7, 2, 1, 5, 3, 8, 6}
        );
        solution.prePrint(bbb);
        System.out.println();
        TreeNode root = new TreeNode(8);
        TreeNode root1 = new TreeNode(6);
        TreeNode root2 = new TreeNode(10);
        TreeNode root3 = new TreeNode(5);
        TreeNode root4 = new TreeNode(7);
        TreeNode root5 = new TreeNode(9);
        TreeNode root6 = new TreeNode(11);
        root.left = root1;
        root.right = root2;
        root1.left = root3;
        root1.right = root4;
        root2.left = root5;
        root2.right = root6;
        ArrayList<Integer> aaabbb = solution.PrintFromTopToBottom(root);
        for (int i = 0; i < aaabbb.size(); i++) {
            System.out.println(aaabbb.get(i));
        }
        int[] aa = new int[1];
        int[] bb = new int[1];
        solution.FindNumsAppearOnce(new int[]{2, 4, 3, 6, 3, 2, 5, 5}, aa, bb);
        System.out.println(aa[0] + "\t" + bb[0]);
        System.out.println();

        ArrayList<ArrayList<Integer>> print = solution.Print2(root);
        System.out.println(print);
    }
}

