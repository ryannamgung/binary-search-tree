//Name: Ryan Namgung
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.*;
/**
 * An implementation of a sorted map using a binary search tree.
 * TODO: Finish implementation
 */
public class TreeMap<K,V> extends AbstractSortedMap<K,V> {
  private Node<K,V> root;
  private int size;
  /** Constructs an empty map using the natural ordering of keys. */
  public TreeMap() {
    super();                  // the AbstractSortedMap constructor
    root = new Node(null, null, null, null, null);
    size = 0;

  }

  /**
   * Constructs an empty map using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the map
   */
  public class Node<K,V> extends MapEntry<K,V> {
    private Node<K,V> parent;
    private Node<K,V> left;
    private Node<K,V> right;
  public Node(Node<K,V> par, Node<K,V> l, Node<K,V> r,K key, V value){
    super(key,value);// calls the default constructor of its parent class
    parent = par;
    left = l;
    right = r;
  }
    public Node getleft() {
      return left;
    }
    public Node getright(){// Node because we are returning the node
      return right;
    }
    public Node getparent(){
      return parent;
    }
    public void setleft(Node<K,V> node){
      left = node;
    }
    public void setright(Node<K,V> node){
      right = node;
    }
    public void setparent(Node<K,V> node){
      parent = node;
    }
    public boolean isExternal(){
      return right == null && left == null;
    }
  }
  private Node<K,V> findNode(K key){
  	checkKey(key);
    Node <K,V> current = root;

    while (current.isExternal() == false){
    int comp = compare(current.getKey(), key);
      if(comp == 0){
       return current;
    }
      else if(comp < 0){
        current = current.getleft();
      }
      else if(comp > 0){
        current = current.getright();
      }
    } 
    return current;
  }
  public TreeMap(Comparator<K> comp) {
    super(comp);              // the AbstractSortedMap constructor
    root = new Node(null, null, null, null, null);
  }

  /**
   * Returns the number of entries in the map.
   * @return number of entries in the map
   */
  @Override

  public int size() {
    return size;
  }

  /**
   * Returns the value associated with the specified key,
   * or null if no such entry exists.
   * @param key  the key whose associated value is to be returned
   * @return the associated value, or null if no such entry exists
   */
  @Override
  public V get(K key) throws IllegalArgumentException {

    checkKey(key);
      // check the key
      //if node returned by findnode is correct key
    Node<K,V> match = findNode(key);
    if(match.getKey() == key){
      return match.getValue();
    }
    else{
      return null;
    }
  }
    //return findNode.get(value);
    // return findNodeKey.getValue(); in the book apparently

  /**
   * Associates the given value with the given key. If an entry with
   * the key was already in the map, this replaced the previous value
   * with the new one and returns the old value. Otherwise, a new
   * entry is added and null is returned.
   * @param key    key with which the specified value is to be associated
   * @param value  value to be associated with the specified key
   * @return the previous value associated with the key (or null,
   *         if no such entry)
   */
  public void expandExternal(Node<K, V> h) throws IllegalArgumentException{

  	Node<K,V> leftchild = new Node(h,null,null,null,null);
    Node<K,V> rightchild = new Node(h,null,null,null,null);
    h.setleft(leftchild);
    h.setright(rightchild);

  }
  @Override
  public V put(K key, V value) throws IllegalArgumentException {
    // increase size
    // key exists
    checkKey(key);
    // get the node of the key
    Node<K,V> match = findNode(key);
    // check if there is entry, if there is replace and return it
    //int k = compare(key, match.getKey());//PROBLEM
    //so there is a corresponding entry value

    if(match.isExternal()){
     size++;
     expandExternal(match);
     match.setKey(key);
     match.setValue(value);
     return null;

      
    // check if there is entry if there isnt create 2 null node entries and make them L/R children and set their parents to the node
    } else{
    match.setKey(key);
    return match.setValue(value);
    }
}

  /**
   * Removes the entry with the specified key, if present, and returns
   * its associated value. Otherwise does nothing and returns null.
   * @param key  the key whose entry is to be removed from the map
   * @return the previous value associated with the removed key, or null if no such entry exists
   */
  @Override
  public V remove(K key) throws IllegalArgumentException {
  	checkKey(key);
  	Node<K,V> empty = new Node(null,null,null,null,null);
    // get the node of the key
    Node<K,V> match = findNode(key);
    if(match.isExternal() == true){
    	return null;
    }
    else{
    	V prev = match.getValue();
    	if(match.getleft().isExternal() == false && match.getright().isExternal() == false){
    		Node<K,V> change = treeMax(match.getleft());
    		match.setKey(change.getKey());
    		match.setValue(change.getValue());
    		match = change;
    	}
    	match.setleft(null);
    	match.setright(null);
    	match.setKey(null);
    	match.setValue(null);
    	size --;
    	return prev;
    	}
    	/*if(){
    		// case for internal with 2 internal children
    	}
    	if{
    		//case for internal with left internal child and right external
    	}
    	if{
    		//case for internal with right internal with left with child & left external
    	}
    	if{
    		// case for internal with right internal w/out left child & left external
    	}
    	if{
    		// case for internal w 2 external children
    	}*/
    	//return null;
    }

    public Node<K,V> treeMax(Node<K,V> b){
    	Node<K,V> scale = b;
    	while(scale.isExternal() == false){
    		scale = scale.getright();
    	}
    	return scale.getparent();
    }

   // return null;

  /*
  public Node<K,V> swap(K key) throws IllegalArgumentException{
  	Node<K,V> empty = new Node(null,null,null,null,null);
  	checkKey(key);
  	Node<K,V> og = findNode(key);
  	V value = og.getValue();
  	
    /*if(og.getright().isExternal() == true && og.getleft().isExternal() == true){
  	  	return og;
    }

  	if(og.getright().isExternal() == false){
  		Node<K,V> next = og.getright();
  		while(next.getleft().isExternal() == false){
  			next = next.getleft();
  		}
  		return next;
  	} else if(og.getright().isExternal() == true && og.getleft().isExternal() == false){
  		Node<K,V> next = og.getleft();
  		while(next.getright().isExternal() == false){
  			next = next.getright();
  		}
  		return next;

  	} else{
  		return empty;
  	}
  }
/*
  public Node<K,V> increment(Node<K,V> node) throws IllegalArgumentException{
  	Node<K,V> inc = node;
  	Node<K,V> next = new Node(null,null,null,null,null);

  		while(inc.getright().getright().isExternal() == false && inc.getleft().isExternal() == true){
  			next = inc.getright();
  			return next;
  		}
  		while(inc.getright().isExternal() == true && inc.getleft().getleft().isExternal() == false){
  			next = inc.getleft();
  			return next;
  		}
  	return next;
  }
*/
  // Support for iteration
  /**
   * Returns an iterable collection of all key-value entries of the map.
   *
   * @return iterable collection of the map's entries
   */

  public void adds(Node<K,V> n, ArrayList<Entry<K,V>> a){
    if(n.isExternal() == false){
      a.add(n);
      if(n.getleft().isExternal() == false || n.getright().isExternal() == false){
        adds(n.getleft(),a);
        adds(n.getright(),a);
      }
    }
  }
  @Override
  public Iterable<Entry<K,V>> entrySet() {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>();
    adds(root,buffer);
    return buffer;
  }
}
