import java.util.ArrayList;
import java.util.function.Supplier;
//Name: Ryan Namgung

/**
 * Class to unit test an arbitrary map implementation
 */
public class TestMap {

  /**
   * Test get method of arbitrary map
   * @param c Supplier to create a map of specific type
   * @param <M> Specific map structure
   * @return Result of unit test
   */
  public static <M extends Map<Integer, String>>
    boolean TestGet(Supplier<M> c) {
      Map<Integer, String> map = c.get();
      map.put(1,"Hello");
      map.put(2,"This");
      map.put(3,"Hi");

      //TODO write unit test for get
      //Setup
      String m = map.get(3);

      //Execute
      //Test
      return m == "Hi";
      //Empty teardown
    }

  /**
   * Test get method of arbitrary map
   * @param c Supplier to create a map of specific type
   * @param <M> Specific map structure
   * @return Result of unit test
   */
  public static <M extends Map<Integer, String>>
    boolean TestPut(Supplier<M> c) {
      //TODO write unit test for put
      //Setup
      Map<Integer, String> map = c.get();
      map.put(1,"Hello");
      map.put(2,"This");
      map.put(3,"Hi");
      //Execute
      map.put(4,"A");
      map.put(5,"B");
      //Test

      return map.size() == 5 && map.get(4) == "A";
      //Empty teardown
    }

  /**
   * Test get method of arbitrary map
   * @param c Supplier to create a map of specific type
   * @param <M> Specific map structure
   * @return Result of unit test
   */
  public static <M extends Map<Integer, String>>
    boolean TestRemove(Supplier<M> c) {

      //TODO write unit test for remove
      //Setup
      Map<Integer, String> map = c.get();
      map.put(1,"Hello");
      map.put(2,"This");
      map.put(3,"Hi");
      //Execute
      String m = map.remove(3);
      //Test
      return m == "Hi" && map.size() == 2;
      //Empty teardown
    }

  /**
   * Class to hold test entries for comparison
   */
  private static class TestEntry<K, V> implements Entry<K, V> {
    private K key;   ///Key
    private V value; ///Value

    /**
     * Constructor
     * @param k Key
     * @param v Value
     */
    public TestEntry(K k, V v) {
      key = k;
      value = v;
    }

    /**
     * Returns the key stored in this entry.
     * @return the entry's key
     */
    public K getKey() {
      return key;
    }

    /**
     * Returns the value stored in this entry.
     * @return the entry's value
     */
    public V getValue() {
      return value;
    }

    /**
     * Returns equality of two entries
     * @param e Entry
     * @return Equality comparison of two entries
     */
    public boolean equals(Entry<K, V> e) {
      return key.equals(e.getKey()) && value.equals(e.getValue());
    }
  }

  /**
   * Test entrySet method of arbitrary map
   * @param c Supplier to create a map of specific type
   * @param <M> Specific map structure
   * @return Result of unit test
   */
  public static <M extends Map<Integer, String>>
    boolean TestEntrySet(Supplier<M> c) {
      //TODO write unit test for EntrySet
      //Setup
      //create an arraylist of #s you manually enter
      //B is a map
      Map<Integer, String> map = c.get();
      ArrayList<TestEntry<Integer, String>> a = new ArrayList<>();

      boolean equal1 = false;
      boolean equal2 = false;


      a.add(new TestEntry<>(0,"Hello"));
      a.add(new TestEntry<>(1,"This"));
      a.add(new TestEntry<>(2,"Hi"));
      a.add(new TestEntry<>(3,"A"));
      a.add(new TestEntry<>(4,"B"));

      map.put(0,"Hello");
      map.put(1,"This");
      map.put(2,"Hi");
      map.put(3,"A");
      map.put(4,"B");

      Iterable<Entry<Integer, String>> it = map.entrySet();


      for(TestEntry<Integer, String> v1: a){
        equal1 = false; // is set a is = to set b
        for(Entry<Integer, String> v2: it){
          if(v1.equals(v2)){
            equal1 = true;        
          }
        }
        // check if its false
        if(equal1 != true)
          break;
      }
      for(Entry<Integer, String> v3: it){
        equal2 = false; // if set b is = to set a
        for(TestEntry<Integer, String> v4: a){
          if(!(v3.equals(v4))){
            equal2 = true;
          }
        }
      if(equal2 != true)
        break;
      }
    return equal1 && equal2;
}

  /**
   * Test methods of arbitrary map
   * @param s Name of map data structure
   * @param c Supplier to create a map of specific type
   * @param <M> Specific map structure
   */
  public static <M extends Map<Integer, String>>
    void TestMap(String s, Supplier<M> c) {
      boolean succ = true;
      boolean res = true;

      System.out.println("Testing " + s);

      System.out.printf("Test %20s passed? ", "get()");
      succ = succ && (res = TestGet(c));
      System.out.printf("%10s\n", res);

      System.out.printf("Test %20s passed? ", "put()");
      succ = succ && (res = TestPut(c));
      System.out.printf("%10s\n", res);

      System.out.printf("Test %20s passed? ", "remove()");
      succ = succ && (res = TestRemove(c));
      System.out.printf("%10s\n", res);

      System.out.printf("Test %20s passed? ", "entrySet()");
      succ = succ && (res = TestEntrySet(c));
      System.out.printf("%10s\n", res);

      System.out.println("passed? " + succ);
    }

  public static void main(String args[]) {
    TestMap("UnsortedTableMap", UnsortedTableMap<Integer, String>::new);
    System.out.printf("\n\n");
    TestMap("SortedTableMap", SortedTableMap<Integer, String>::new);
    System.out.printf("\n\n");
    TestMap("ChainHashMap", ChainHashMap<Integer, String>::new);
    System.out.printf("\n\n");
    TestMap("ProbeHashMap", ProbeHashMap<Integer, String>::new);
    System.out.printf("\n\n");
    TestMap("TreeMap", TreeMap<Integer, String>::new);
   }
}
