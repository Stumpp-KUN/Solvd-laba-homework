import exception.EmptyListException;
import exception.NotFoundElementException;
import lombok.SneakyThrows;

public class HalfLinkedList<T> {

    class Node {
        T val;
        Node next;

        Node(T val) {
            this.val=val;
        }
    }

    private Node head;
    private Node tail;
    private int length=1;

    //add's methods
    public boolean addHead(T val) {
        if (isEmpty()) {
            head=new Node(val);
            tail=head;
            return false;
        }
        Node newNode=new Node(val);
        newNode.next=head;
        head=newNode;
        length++;
        return true;
    }

    public boolean addTail(T val) {
        if (isEmpty()) {
            head=new Node(val);
            tail=head;
            return false;
        }
        Node newNode=new Node(val);
        tail.next=newNode;
        tail=newNode;
        length++;
        return true;
    }

    public boolean add( T val) {
        addTail(val);
        return true;
    }

    //drop's methods
    public boolean deleteHead() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("List is Empty");
        }
        Node temp=head;
        head=head.next;
        temp.next=null;
        if (isEmpty()) {
            tail=null;
        }
        length--;
        return true;
    }

    public boolean deleteTail() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("List is Empty");
        }
        if (head==tail) {
            head=null;
            tail=null;
            return false;
        }
        Node current=head;
        Node prev=null;

        while (current.next!=null) {
            prev=current;
            current=current.next;
        }
        prev.next=null;
        tail=prev;
        length--;
        return true;
    }

    public boolean drop(T key) throws NotFoundElementException {
        Node prev=new Node(null);
        prev.next=head;
        Node next=head.next;
        Node temp=head;
        boolean x=false;
        if (head.val==key) {
            head=head.next;
            x=true;
        }
        while (temp.next!=null) {
            if (temp.val.hashCode()==key.hashCode()) {
                prev.next=next;
                x=true;
                break;
            }
            prev=temp;
            next=temp.next;
            temp=temp.next;
        }

        if (x==false&&temp.val.hashCode()==key.hashCode()) {
            prev.next=null;
            x=true;
        }

        if (x) {
            length--;
        }
        else {
        throw new NotFoundElementException("Not fount this element");
        }
        return true;
    }

    public boolean dropById(int num){
        int lenght=getLength();
        if(lenght<num)
            return false;
        Node current = head;
        Node temp=null;
        for(int i=0;i<num;i++) {
            temp=current;
            current = current.next;
        }
        if(current==tail)
        temp.next=tail;

        tail=temp;
        this.length--;
        return true;
    }

    //get method
    public T getByNum(int num){
        Node current = head;
        for(int i=0;i<num;i++)
            current=current.next;
        return current.val;
    }

    //other methods
    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @SneakyThrows
    public String toString() {
        if(head.val==null||tail.val==null)
            throw new Exception("No head or no tail of list");
        Node current = head;
        String k="[";
        while (current != tail) {
            k+=current.val + ", ";
            current = current.next;
        }
        k+=tail.val+"]";
        return k;
    }
}
