import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FallingSnow{
    
    Node root;
    boolean needsRotation = false;
    
    public void go() throws NumberFormatException, IOException {
        BigInteger m = new BigInteger("1000000009");
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(file.readLine());
        PriorityQueue<Long> start = new PriorityQueue();
        PriorityQueue<Long> end = new PriorityQueue();
        for(int i = 0;i<N;i++)
        {
            StringTokenizer st = new StringTokenizer(file.readLine());
            start.add(Long.parseLong(st.nextToken()));
            end.add(Long.parseLong(st.nextToken())+1);
        }
        LinkedList<Height> list = new LinkedList();
        int ht = 0;
        if(N==0)
        {
            System.out.println("shovel time!");
            return;
        }
        long min = smallNext(start,end);
        list.add(new Height(0,min));
        while(!end.isEmpty())
        {
            Long st = start.peek();
            Long en = end.peek();
            if(st!=null&&st<en){
                while(start.peek()!=null&&start.peek()==st)
                {
                    ht++;
                    start.poll();
                }
                long next = smallNext(start,end);
                if(list.getLast().height==ht)
                    list.getLast().length+=next-st;
                else
                    list.add(new Height(ht,next-st));
            }else if(st==null||en<st){
                while(end.peek()!=null&&end.peek()==en)
                {
                    ht--;
                    end.poll();
                }
                long next = smallNext(start,end);
                if(list.getLast().height==ht)
                    list.getLast().length+=next-en;
                else
                    list.add(new Height(ht,next-en));
            }else{
                end.poll();
                start.poll();
                long next = smallNext(start,end);
                if(list.getLast().height==ht)
                    list.getLast().length+=next-en;
                else
                    list.add(new Height(ht,next-en));
            }
        }
        long[] less = new long[list.size()];
        root = new Node(list.getFirst().clone());
        less[0] = 0;
        long[] size = new long[list.size()];
        size[0] = list.getFirst().length;
        Iterator<Height> it = list.iterator();
        it.next();
        int pos = 1;
        while(it.hasNext())
        {
            Height next = it.next();
            size[pos] = next.length;
            Node add = new Node(next.clone());
            less[pos++] = root.addAndCountLess(add);
            if(needsRotation)
                adjustAfterInsertion(add);
        }
        long[] more = new long[list.size()];
        root = new Node(list.getLast().clone());
        more[more.length-1] = 0;
        pos = more.length-2;
        it = list.descendingIterator();
        it.next();
        while(it.hasNext())
        {
            Node add = new Node(it.next().clone());
            more[pos--] = root.addAndCountMore(add);
            if(needsRotation)
                adjustAfterInsertion(add);
        }
        boolean zero = true;
        BigInteger ans = new BigInteger("0");
        for(int i = 0;i<less.length;i++)
        {
            if(more[i]!=0&&less[i]!=0&&size[i]!=0)
                zero = false;
            ans = ans.add(new BigInteger(""+less[i]).multiply(new BigInteger(""+more[i]).multiply(new BigInteger(""+size[i]))));
            ans = ans.mod(m);
        }
        if(zero)
        {
            System.out.println("shovel time!");
        }else
        {
            System.out.println(ans.intValue()+"");
        }
    }
    
    public boolean isRed(Node n)
    {
        return n!=null && !n.black;
    }
    
    public boolean isBlack(Node n)
    {
        return !isRed(n);
    }
    
    public void setColor(Node n, Color c)
    {
        if(n==null)
            return;
        if(c == Color.black)
            n.black = true;
        else
            n.black = false;
    }
    
    public Node parentOf(Node n)
    {
        if(n==null)
            return null;
        return n.parent;
    }
    
    public Node grandparentOf(Node n)
    {
        if(n == null|| parentOf(n)==null)
            return null;
        return parentOf(parentOf(n));
    }
    
    public Node siblingOf(Node n)
    {
        if(n==null||n.parent==null)
            return null;
        if(n==n.parent.left)
            return n.parent.right;
        return n.parent.left;
    }
    
    public Node leftOf(Node n)
    {
        if(n==null)
            return null;
        return n.left;
    }
    
    public Node rightOf(Node n)
    {
        if(n==null)
            return null;
        return n.right;
    }
    
    public void adjustAfterInsertion(Node n) {
        needsRotation = false;
        // Step 1: color the node red
        n.black = false;

        // Step 2: Correct double red problems, if they exist
        if (n != null && n != root && isRed(parentOf(n))) {

            // Step 2a (simplest): Recolor, and move up to see if more work
            // needed
            if (isRed(siblingOf(parentOf(n)))) {
                setColor(parentOf(n), Color.black);
                setColor(siblingOf(parentOf(n)), Color.black);
                setColor(grandparentOf(n), Color.red);
                adjustAfterInsertion(grandparentOf(n));
            }

            // Step 2b: Restructure for a parent who is the left child of the
            // grandparent. This will require a single right rotation if n is
            // also
            // a left child, or a left-right rotation otherwise.
            else if (parentOf(n) == leftOf(grandparentOf(n))) {
                if (n == rightOf(parentOf(n))) {
                    rotateLeft(n = parentOf(n));
                }
                setColor(parentOf(n), Color.black);
                setColor(grandparentOf(n), Color.red);
                rotateRight(grandparentOf(n));
            }

            // Step 2c: Restructure for a parent who is the right child of the
            // grandparent. This will require a single left rotation if n is
            // also
            // a right child, or a right-left rotation otherwise.
            else if (parentOf(n) == rightOf(grandparentOf(n))) {
                if (n == leftOf(parentOf(n))) {
                    rotateRight(n = parentOf(n));
                }
                setColor(parentOf(n), Color.black);
                setColor(grandparentOf(n), Color.red);
                rotateLeft(grandparentOf(n));
            }
        }

        // Step 3: Color the root black
        setColor((Node) root, Color.black);
    }
    
    /**
     * Rotates left around the given node.
     */
    protected void rotateLeft(Node n) {
        if (n.getRight() == null) {
            return;
        }
        Node oldRight = n.getRight();
        n.count-=oldRight.data.length;
        if(oldRight.getRight()!=null)
            n.count-=oldRight.getRight().count;
        oldRight.count+=n.data.length;
        if(n.getLeft()!=null)
            oldRight.count+=n.getLeft().count;
        n.setRight(oldRight.getLeft());
        if (n.getParent() == null) {
            root = oldRight;
        } else if (n.getParent().getLeft() == n) {
            n.getParent().setLeft(oldRight);
        } else {
            n.getParent().setRight(oldRight);
        }
        oldRight.setLeft(n);
    }

    /**
     * Rotates right around the given node.
     */
    protected void rotateRight(Node n) {
        if (n.getLeft() == null) {
            return;
        }
        Node oldLeft = n.getLeft();
        oldLeft.count = n.count;
        n.count-=oldLeft.data.length;
        if(oldLeft.getLeft()!=null)
            n.count-=oldLeft.getLeft().count;
        n.setLeft(oldLeft.getRight());
        if (n.getParent() == null) {
            root = oldLeft;
        } else if (n.getParent().getLeft() == n) {
            n.getParent().setLeft(oldLeft);
        } else {
            n.getParent().setRight(oldLeft);
        }
        oldLeft.setRight(n);
    }
     
    public long smallNext(PriorityQueue<Long> a, PriorityQueue<Long> b)
    {
        if(a.peek()==null&&b.peek()==null)
            return 1_000_000_000_000_000_001l;
        else if(a.peek()==null)
            return b.peek();
        return Math.min(a.peek(),b.peek());
    }
    
    private class Height{
        
        public long height,length;
        
        public Height(long height, long length)
        {
            this.height = height;
            this.length = length;
        }
        
        public Height clone(){
            return new Height(height,length);
        }
    }
    
    private class Node{
        
        public Height data;
        public Node right;
        public Node left;
        public Node parent;
        public boolean black = true;
        long count;
        public Node(Height data)
        {
            this.data = data;
            count = data.length;
        }
        
        public Node getParent()
        {
            return parent;
        }
        
        public Node getLeft()
        {
            return left;
        }
        
        public Node getRight()
        {
            return right;
        }
        
        public long addAndCountLess(Node node)
        {
            if(data.height==node.data.height)
            {
                count+=node.data.length;
                data.length+=node.data.length;
                if(left!=null)
                    return left.count();
                return 0;
            }else if(data.height>node.data.height)
            {
                if(left==null)
                {
                    count+=node.data.length;
                    node.parent = this;
                    left = node;
                    needsRotation = true;
                    return 0;
                }
                count+=node.data.length;
                return left.addAndCountLess(node);
            }
            long ret = data.length;
            if(left!=null)
            {
                ret+=left.count();
            }
            if(right==null)
            {
                count+=node.data.length;
                needsRotation = true;
                node.parent = this;
                right = node;
                return ret;
            }
            long ans = ret+right.addAndCountLess(node);
            count+=node.data.length;
            return ans;
        }
        
        public long addAndCountMore(Node node)
        {
            if(data.height==node.data.height)
            {
                count+=node.data.length;
                data.length+=node.data.length;
                if(right!=null){
                    return right.count();
                }
                return 0;
            }else if(data.height>node.data.height)
            {
                long ret = data.length;
                if(right!=null)
                {
                    ret+=right.count();
                }
                if(left==null)
                {
                    count+=node.data.length;
                    needsRotation = true;
                    node.parent = this;
                    left = node;
                    return ret;
                }
                long ans = ret+left.addAndCountMore(node);
                count+=node.data.length;
                return ans;
            }
            if(right==null)
            {
                count+=node.data.length;
                needsRotation = true;
                node.parent = this;
                right = node;
                return 0;
            }
            count+=node.data.length;
            return right.addAndCountMore(node);
        }
        
        public void setLeft(Node child)
        {
            if(left!=null)
            {
                left.parent = null;
            }
            if(child!=null)
            {
                child.removeFromParent();
                child.parent = this;
            }
            left = child;
        }
        
        public void setRight(Node child)
        {
            if (right != null) {
                right.parent = null;
            }
            if (child != null) {
                child.removeFromParent();
                child.parent = this;
            }
            right = child;
        }
        
        public void removeFromParent() {
            if (parent != null) {
                if (parent.left == this) {
                    parent.left = null;
                } else if (parent.right == this) {
                    parent.right = null;
                }
                this.parent = null;
            }
        }
        
        public Long count()
        {
            return count;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        new FallingSnow().go();
    }
}
/*
6
1 2
2 3
3 4
4 5
6 7
9 10

ans = 3

4
1 10
2 9
4 7
5 6

ans = 32
1629586
*/