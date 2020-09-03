/*
LList.java
Sat Arora
Simulation of a doubly linked list. This was done without the use of built-in data
structures, and includes multiple methods.
Push: Add element to front of list.
Pop/Dequeue: Remove element from front of list.
Enqueue: Add element to back of list.
Delete (2): Delete the element from the list (one taking integer value, other taking LNode reference).
DeleteAt: Deleting at specified index.
SortedInsert: Assuming the list is in ascending order from tail to head, it adds a new
node into the position that maintains the order.
RemoveDuplicates: Removes any duplicates of values.
Reverse: Reverses the order of the nodes in the list.
Clone: Returns a copy of the list.
 */
public class LList {
    private LNode head,tail; //the head and tail of the doubly-linked list
    //constructor
    public LList() {
        head = null;
        tail = null;
    }
    //toString method prints the LList nicely
    @Override
    public String toString() {
        String ans = "[";
        LNode tmp = head;
        while (tmp != null) {
            ans += Integer.toString(tmp.getVal()) + ", ";
            tmp = tmp.getNextNode();
        }
        if (head != null) {
            ans = ans.substring(0,ans.length() - 2) + "]";
        }
        return ans;
    }
    //Q1
    //push method adds to the first place (becoming the new head)
    public void push(int n) {
        LNode tmp = new LNode(null,n,head); //temporary LNode which will become the new head
        if (head != null) //if there is something in the LList
            head.setPrevNode(tmp); //setting the head's previous node to the tmp, eventually the new head
        head = tmp; //the new head is the first element, which is tmp
        if (tail == null) tail = tmp; //if there was nothing in the list to start, now the head and tail are both tmp
    }
    //pop method removes from the front of list
    public int pop() {
        if (head == null) return -1; //if nothing is in the list, return -1 (value saying there is nothing in the list)
        LNode del = head.getNextNode(); //del LNode which will be the new head
        int value = head.getVal(); //storing the value of the current head (which will be deleted)
        if (del == null) { //if there is no next node (list will be empty after)
            //nothing is in the list, head and tail are both null
            tail = null;
            head = null;
        }
        else {
            head = del; //head is the 2nd element, becoming the first
            head.setPrevNode(null); //making the 2nd element the first
        }
        return value; //returning the value of the original head
    }

    //Q2
    //enqueue method, adding element to the back of the LList
    public void enqueue(int n) {
        LNode tmp = new LNode(tail,n,null); //temporary LNode which will become the new tail
        if (tail != null) //if there is something in the LList
            tail.setNextNode(tmp); //setting the tail's next node to the tmp, eventually the new tail
        tail = tmp; //setting tail to the new last element, tmp
        if (head == null) head = tmp; //if there was no element in the LList, the head is also now tmp


    }
    //dequeue method, the exact same as pop
    public int dequeue() {
        return pop();
    }
    //Q3
    //delete method taking in an integer to delete, deletes first occurence of the integer
    public void delete(int x) {
        LNode tmp = head; //temporary LNode which starts from the head, will increment for searching for the desired value
        while (tmp != null) { //going until the end of the LList
            if (tmp.getVal() == x) { //checking if the value at the LNode is the desired value
                delete(tmp); //deleting the LNode holding the value we want to delete
                return; //the element was deleted, so there is no sense in continuing to search for the value
            }
            tmp = tmp.getNextNode(); //moving on to the next node
        }
    }
    //delete method taking in LNode to delete, and deletes it
    public void delete(LNode del) {
        if (del == null) return; //if the element is not in the LList, don't do anything
        if (head == null && tail == null) return; //if the LList is empty, don't do anything
        LNode back = del.getPrevNode(), front = del.getNextNode(); //back and front are the LNodes around the one to delete, eventually being consecutive
        if (head == del && tail == del) { //if the LNode to delete is the only element in the LList
            //LList is empty, so the head and tail are null
            head = null;
            tail = null;
        }
        else if (back == null) { //if the previous node of the desired one to delete is null
            head = head.getNextNode(); //the head is going to be deleted, so the new head is the 2nd element
            head.setPrevNode(null); //setting the head to be the new first element
        }
        else if (front == null) { //if the next node of the desired one to delete is null
            tail = tail.getPrevNode();///the tail is going to be deleted, so the new head is the 2nd last element
            tail.setNextNode(null); //setting the tail to be the new last element
        }
        else {
            //joining the nodes around the desired one to delete, ultimately removing the node to delete
            back.setNextNode(front);
            front.setPrevNode(back);
        }
    }
    //deleteAt deletes at specificed index
    public void deleteAt(int idx) {
        int cur = 0; //keeping track of index
        LNode tmp = head; //tmp LNode starts from head, will go down towards the tail as the index increases
        while (cur != idx) { //going until the desired index is reached
            tmp = tmp.getNextNode(); //moving tmp to the next position
            ++cur; //incrementing the index
        }
        delete(tmp); //deleting the LNode at the desired index
    }
    //Q4
    //method that assumes the LList is in sorted order, and inserts it to remain in order
    public void sortedInsert(int n) {
        LNode tmp = tail,newNode; //tmp LNode will increment from tail to head for comparing, newNode will be the inserted node
        if (tail == null || n >= head.getVal()) { //if the list is empty or the inserted value is greater than the max in the LList, the head value
            push(n); //pushing to the front
            return; //already inserted into LList, should not continue in method
        }
        if (n <= tail.getVal()) { //if the value to insert is less than (or equal to) the minimum value in the LList (the tail)
            enqueue(n); //pushing to the back of the LList
            return; //already inserted into LList, should not continue in method
        }
        while (tmp != null) { //keeps going until reaching the end of the LList
            if (tmp.getVal() > n) { //if the value at the current LNode is greater than the value to insert
                newNode = new LNode(tmp,n,tmp.getNextNode()); //LNode positioned in between the current node and the one next to it
                //setting the previous and next LNodes to be around the newNode
                tmp.getNextNode().setPrevNode(newNode);
                tmp.setNextNode(newNode);
                break;//inserted once already, now must leave the program
            }
            tmp = tmp.getPrevNode(); //tmp LNode moves back one
        }
    }
    //Q5
    //method that removes all duplicates
    public void removeDuplicates() {
        for (LNode cur = head; cur != null; cur = cur.getNextNode()) { //LNode going from head to tail
            for (LNode cmp = cur.getNextNode(); cmp != null; cmp = cmp.getNextNode()) { //LNode searching from cur to the tail
                if (cur.getVal() == cmp.getVal()) { //checking if the values are the same (at different positions)
                    delete(cmp); //deleting the value at the LNode (the one closer to the tail)
                }
            }
        }
    }
    //Q6
    //method that reverses the LList
    public void reverse() {
        LNode front = head,back=tail; //LNodes that will sweep from the head towards the middle, and the tail towards the middle
        int val; //variable that is used temporarily each time for switching
        if (front == null && tail == null) return; //if there is nothing in the LList, there is no sense in reversing the LList
        if (front == tail) return; //if there is one element in the LList, there is also no sense in reversing
        while (front.getPrevNode() != back && front != back) { //keeps going until the back is the same place as the front or the back has passed the front
            val = front.getVal(); //temporary val used to store the value at front to switch
            front.setVal(back.getVal()); //setting the front's value to the back's current value
            back.setVal(val); //setting the back's value to the old front's value (val)
            front = front.getNextNode(); //making front one step closer to the tail of the LList
            back = back.getPrevNode(); //making back one step closer to the head of the LList
        }
    }
    //Q7
    //method that returns an LList with the same values in the same order
    public LList clone() {
        LList nList = new LList(); //creating a new LList
        for (LNode cur = tail; cur != null; cur = cur.getPrevNode()) { //looping thru each element from the tail to head
            nList.push(cur.getVal()); //pushing the values to the front of the LList, as we loop from back to front
        }
        return nList; //returning the new LList
    }
}