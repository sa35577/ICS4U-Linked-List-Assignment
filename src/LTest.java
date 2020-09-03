//LTest.java
//class that tests the LList
public class LTest {
    public static void main(String[] args) {
        LList nums = new LList();
        //testing purposes

        nums.sortedInsert(5);
        System.out.println(nums);
        nums.sortedInsert(10);
        System.out.println(nums);
        nums.sortedInsert(1);
        System.out.println(nums);
        nums.push(15);
        nums.push(15);
        nums.push(78);
        nums.enqueue(78);
        nums.enqueue(15);
        System.out.println(nums);
        nums.reverse();
        System.out.println(nums);
        nums.push(78);
        System.out.println(nums);
        nums.enqueue(45);
        System.out.println(nums);
        nums.push(15);
        System.out.println(nums);
        nums.delete(78);
        System.out.println(nums);
        LList coolList = nums.clone();
        coolList.push(5);
        System.out.println(coolList);

    }
}
//LNode.java
//class holding the properties of each node
class LNode {
    private int val; //the value at the LNode
    private LNode next,prev; //storing the locations of the next and previous nodes
    //constructor
    public LNode(LNode p,int v, LNode n) {
        prev = p;
        val = v;
        next = n;
    }
    public int getVal() { return val; } //getting value at LNode
    public void setVal(int v) { val = v;} //setting the value at LNode to the integer passed in
    public LNode getNextNode() { return next; } //getting the LNode's next node
    public void setNextNode(LNode n) { next = n; } //setting the LNode's next node to the LNode passed in
    public LNode getPrevNode() {return prev;} //getting the LNode's previous node
    public void setPrevNode(LNode p) {prev = p;} //setting the LNode's previous node to the LNode passed in
}
