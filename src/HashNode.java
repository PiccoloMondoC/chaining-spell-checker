public class HashNode {    
    HashNode next;
    HashNode prev;
    String data;
    public Object key;
    
    /* Constructor */
    public HashNode(String val) {
        data = val;
        next = null;
        prev = null;
    }
/*
    public String displayList() {
        ChainingListNode current = head;
		if(current!=null){
		String output=current.display();
		while (current != null) {
			current = current.next;
			if(current!=null)
			output = output+"->"+current.display();
		}
		return output;
		}
		return null;
    }*/
}
