//Name: Ryan Namgung
//TODO Use this file as a basis for your experiments
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/*
 */
public class Timing {
  /**
   * @param n Size of experiment
   * @param repeats Number of times to repeat experiment
   * @return Seconds taken for experiment
   */


  public static double TimeUnsortedTableMapAverage(int k, int v) {

  long start = System.nanoTime();

   for(int j = 0; j < v; ++j) {
     UnsortedTableMap<Integer, Integer> m = new UnsortedTableMap<>();
     for(int i = 0; i < k; ++i)
        m.put(i, 1);
  }

    long stop = System.nanoTime();
    double sec = (stop - start)/(double)1e9/v; //average elapsed time
    return sec;
  }

  public static double TimeSortedTableMapBest(int k, int v) {

    long start = System.nanoTime();

    for(int j = 0; j < v; ++j) {
      SortedTableMap<Integer, Integer> m = new SortedTableMap<>();
      for(int i = 0; i < k; ++i)
        m.put(0, 1);
    }

    long stop = System.nanoTime();
    double sec = (stop - start)/(double)1e9/v; //average elapsed time 
    return sec;
  }

  public static double TimeSortedTableMapWorst(int k, int v) {

    long start = System.nanoTime();

    for(int j = 0; j < v; ++j) {
      SortedTableMap<Integer, Integer> m = new SortedTableMap<>();
      for(int i = 0; i < k; ++i)
        m.put(0 - i, 1);// because the worst case is that we have to shift through the n values of the array then insert there
    }

    long stop = System.nanoTime();
    double sec = (stop - start)/(double)1e9/v; //average elapsed time
    return sec;
  }

  public static double TimeChainHashMap(int k, int v) {
    

    long start = System.nanoTime();

    for(int j = 0; j < v; ++j) {
      ChainHashMap<Integer, Integer> m = new ChainHashMap<>();
      for(int i = 0; i < k; ++i)
        m.put(i, 0);
    }
//not done for both 
    long stop = System.nanoTime();
    double sec = (stop - start)/(double)1e9/v; //average elapsed time
    return sec;
  }

    public static double TimeProbeHashMap(int k, int v) {
    long start = System.nanoTime();


    for(int j = 0; j < v; ++j) {
      ProbeHashMap<Integer, Integer> m = new ProbeHashMap<>();
      for(int i = 0; i < k; ++i)
        m.put(i, 0);

    }

    long stop = System.nanoTime();
    double sec = (stop - start)/(double)1e9/v; //average elapsed time
    return sec;
  }

  public static double TimeBSTBest(int k, int v) {
      double sec = 0.0;
    for(int i = 0; i < v; ++i) {
      TreeMap <Double, Double> l = new TreeMap<>();
      long start = 0;
      long stop = 0;
      double min = 0;
      double max = 0;
      double increment = 4;
      double current = 0;
      while(l.size() <= k){
        if(current > max){
          increment /= 2;
          min -= increment/2;
          max += increment/2;
          current = min;
        }
      start = System.nanoTime();
      l.put(current, current);
      stop = System.nanoTime();
      sec += (stop - start)/(double) 1e9/v;
      current += increment;
      }
    }
    return sec;
  }

  public static double TimeBSTAverage(int k, int v) {

    long start = System.nanoTime();
    Random rand = new Random();

    for(int j = 0; j < v; ++j) {
      TreeMap<Integer, Integer> m = new TreeMap<>();
      for(int i = 0; i < k; ++i)
        m.put(rand.nextInt(k), 1);
    }

    long stop = System.nanoTime();
    double sec = (stop - start)/(double)1e9/v; //average elapsed time 
    return sec;
  }

  public static double TimeBSTWorst(int k, int v) {

    long start = System.nanoTime();

    for(int j = 0; j < v; ++j) {
      TreeMap<Integer, Integer> m = new TreeMap<>();
      for(int i = 0; i < k; ++i)
        m.put(i, 1);
    }

    long stop = System.nanoTime();
    double sec = (stop - start)/(double)1e9/v; //average elapsed time 
    return sec;
  }


  /**
   * Run all timing functions and output to terminal and csv file
   * @param args Command-line arguments. Ignored here.
   * @exception FileNotFoundException If system cannot open filename, exception.
   */
  public static void main(String[] args) throws FileNotFoundException {
    PrintWriter pw = new PrintWriter("data_lists.csv");
    System.out.printf("%10s %15s\n", "N", "Time");
    pw.println("N,Time");

    int N = 66000;
    for(int n = 2; n < N; n*=2) {
      System.out.printf("%10s ", n);
      pw.print(n);
      int repeats = Math.max(10, N/n);
      /*double tBST = TimeBST(n, repeats);
      System.out.printf("%15e", tBST);
      pw.print("," + tBST);
      */


      double tstmb = TimeSortedTableMapBest(n, repeats);
      System.out.printf("%15e", tstmb);
      pw.print("," + tstmb);

      double tstmw = TimeSortedTableMapWorst(n, repeats);
      System.out.printf("%15e", tstmw);
      pw.print("," + tstmw);

      double tchm = TimeChainHashMap(n, repeats);
      System.out.printf("%15e", tchm);
      pw.print("," + tchm);
      
      double tphm = TimeProbeHashMap(n, repeats);
      System.out.printf("%15e", tphm);
      pw.print("," + tphm);

      double tbstb = TimeBSTBest(n, repeats);
      System.out.printf("%15e", tbstb);
      pw.print("," + tbstb);

      double tbsta = TimeBSTAverage(n, repeats);
      System.out.printf("%15e", tbsta);
      pw.print("," + tbsta);

      double tstma = TimeUnsortedTableMapAverage(n, repeats);
      System.out.printf("%15e", tstma);
      pw.print("," + tstma);


      double tbstw = TimeBSTWorst(n, repeats);
      System.out.printf("%15e", tbstw);
      pw.print("," + tbstw);
      
      System.out.println();
      pw.println();
    }
    pw.flush();
    pw.close();
  }


}
