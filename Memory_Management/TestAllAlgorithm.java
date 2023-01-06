import java.util.Scanner;

public class TestAllAlgorithm {
    
  public static void main(String[] args) {
    TestAllAlgorithm index = new TestAllAlgorithm();
    int choice;

    do {
      choice = index.menu();
      switch (choice) {
        case 1:
          System.out.println("*** By Best Fit Algorithm *** ");
          BestFitAlgorithm.main(args);
          break;
        case 2:
          System.out.println("*** By First Fit Algorithm *** ");
          FirstFitAlgorithm.main(args);
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
              \n\nPlease Select your calculation:
              \n1. Best Fit 
              \n2. First Fit
              \n3. Quit Program 
              Choose an option to simulate:  """);
    
          choice = Integer.parseInt(sc.nextLine());
          sc.close();
        } while (choice < 1 || choice > 3);
    
        return choice;
      }
}

