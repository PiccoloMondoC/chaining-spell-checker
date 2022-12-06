public class FirstHashNode {    
    FirstHashNode next;
    FirstHashNode prev;
    String data;
    public Object key;
    
    /* Constructor */
    public FirstHashNode(String val) {
        data = val;
        next = null;
        prev = null;
    }
/*
    public String displayList() {
        FirstChainingListNode current = head;
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
