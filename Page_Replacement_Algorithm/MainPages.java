import java.util.Scanner;

public class MainPages {

  public static void main(String[] args) {
    MainPages index = new MainPages();
    Scanner sc = new Scanner(System.in);
    int choice;

    do {
      choice = index.menu();
      switch (choice) {
        case 1:
          System.out.println("*** By FIFO Method *** ");
          index.FIFOMethod();
          break;
        case 2:
          System.out.println("*** By LRU Method *** ");
          index.LRUMethod();
          break;
        case 3:
        default:
          System.out.println("You exits program. Thank You!");
      }
    } while (choice != 3);

  }

  public int menu() {
    int choice;
    Scanner sc = new Scanner(System.in);
    do {
      System.out.println("\n========= Page Replacement Algorithm ==========\n");
      System.out.println("""
          \n\nPlease Select your calculation: \n
          \n1. FIFO(First In First Out) \n
          \n2. LRU (Least Recently Used) \n
          \n3. Quit 
            \nChoose an option to simulate:  """);

      choice = Integer.parseInt(sc.nextLine());
    } while (choice < 1 || choice > 3);

    return choice;
  }

  public void FIFOMethod(){
    Scanner sc = new Scanner(System.in);

    int frames; //get frames
    int lenIncome; //get length Income 
    System.out.printf("Input Number of frames: ");
    frames = sc.nextInt();
    System.out.printf("Input page table size: ");
    lenIncome = sc.nextInt();

    int incomingStream[] = new int[lenIncome];
    
    int i = 0;
    while(i < lenIncome){
      int numIncom = i+1;
      System.out.printf("Enter number of page "+numIncom+"(int): ");
      incomingStream[i] = sc.nextInt();
      i++;
    }

    FIFO fifo = new FIFO();    
    int pageFaults = fifo.pageFaults(incomingStream,lenIncome,frames);
    int hit =  lenIncome - pageFaults;

    System.out.println("\nFIFO RESULT: ");
    System.out.println("Page faults (*): " + pageFaults);
    System.out.println("Page fault Ratio (%): " + (double) pageFaults / lenIncome);
    System.out.println("Success: " + hit);
    System.out.println("Success Ratio (%) : " + (double) hit / lenIncome);
  }

  public void LRUMethod(){
    LRU a = new LRU();

    Scanner sc = new Scanner(System.in);

    int p[], n, fr[], m,fs[];

    System.out.printf("\nInput the Number of frames: ");
    m = sc.nextInt();

    System.out.printf("Input page table size: ");
    n = sc.nextInt();
    p = new int[n];
    System.out.printf("Input each page number\n");
    for (int i = 0; i < n; i++){
      int numIncom = i+1;
      System.out.printf("Enter number of page "+numIncom+"(int): ");
      p[i] = sc.nextInt();
    }
      

    fr = new int[m]; //number of frames
    fs = new int[m]; // number of success 


    int pageFaults = a.lru(n,m,p,fr,fs);
    int hit = n - pageFaults; //n is length of table

    System.out.println("Page faults (*): " + pageFaults);
    System.out.println("Page fault Ratio (%): " + (double) pageFaults / n);
    System.out.println("Success: " + hit);
    System.out.println("Success Ratio (%) : " + (double) hit / n);

  }

}