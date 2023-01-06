import java.util.ArrayList;
import java.util.Scanner;
// import java.awt.event.KeyEvent;

class MemoryBlock{
    int block_size;
    int memory_location;

    MemoryBlock(int block_size, int memory_loc) {
        this.block_size = block_size;
        this.memory_location = memory_loc;
    }
}

class Job{
    int job_size;
    String job_number;

    Job(int job_size, String job_num) {
        this.job_size = job_size;
        this.job_number = job_num;
    }

    
}

public class FirstFitAlgorithm{
    int MEMORY_SIZE;
    int JOB_SIZE;
    int MEMORY_LOCATION;
    String JOB_NUMBER;
    String status;
    
    static ArrayList<MemoryBlock> memo = new ArrayList<>();
    static ArrayList<Job> jobs = new ArrayList<>();
    static ArrayList<FirstFitAlgorithm> content = new ArrayList<>();

    FirstFitAlgorithm() {

    }

    FirstFitAlgorithm(MemoryBlock m, Job j) {
        this.MEMORY_SIZE = m.block_size;
        this.MEMORY_LOCATION = m.memory_location;
        this.JOB_SIZE = j.job_size;
        this.JOB_NUMBER = j.job_number;
        this.status = "Busy";
    }

    static FirstFitAlgorithm f = new FirstFitAlgorithm();

    void addMemory(MemoryBlock m) {
        memo.add(m);
    }

    void addJob(Job j) {
        jobs.add(j);
    }

    void content_of_block() {
        System.out.println("\n\t\t\t\t*** Display Content's Result ***\n\t\t\t\t");
        System.out.print("\n\n+-------------------------------------------------------------------------------------------------+\n");
        System.out.println("|\t\t\t\t\t  Content\t\t\t\t\t\t  |");
        for(int i=0; i<jobs.size(); i++) {
            for(int j=0; j<memo.size(); j++) {
                if(jobs.get(i).job_size <= memo.get(j).block_size) {
                FirstFitAlgorithm list = new FirstFitAlgorithm(memo.get(j), jobs.get(i));
                    content.add(list);
                    memo.remove(j);
                    break;
                }
            }
        }

        System.out.print("+------------------+-------------------+-------------+----------+--------+------------------------+\n");
        System.out.printf("| %s | %s | %s | %s | %s | %s |%n", " Memory location", "Memory block size", " Job number", "Job size", "Status", "Internal Fragmentation");
        System.out.print("+------------------+-------------------+-------------+----------+--------+------------------------+\n");
        for(int i=1; i<=content.size(); i++) {
            for(int j=0; j<content.size(); j++) {
                if(i == content.get(j).MEMORY_LOCATION) {
                    System.out.printf("| %16d | %16dKB | %11s | %7dKB | %6s | %21dK |%n",content.get(j).MEMORY_LOCATION, content.get(j).MEMORY_SIZE, content.get(j).JOB_NUMBER, content.get(j).JOB_SIZE, content.get(j).status, content.get(j).MEMORY_SIZE-content.get(j).JOB_SIZE);
                    break;
                }
            }
        }
        for(MemoryBlock i:memo) {
            System.out.printf("| %16d | %16dKB | \t     | \t\t| %6s | \t\t\t  |\n",i.memory_location, i.block_size, "Free");
        }
        int sumMem=0;
        int sumJob=0;
        for(FirstFitAlgorithm f:content) {
            sumMem+=f.MEMORY_SIZE;
            sumJob+=f.JOB_SIZE;
        }
        for(MemoryBlock i:memo) {
            sumMem+=i.block_size;
        }
        System.out.print("+------------------+-------------------+-------------+----------+--------+------------------------+\n");
        System.out.printf("| %s | %16dKB | %s | %7dKB |\t | \t\t\t  |\n", "Total Available:", sumMem, "Total Used:", sumJob);
        System.out.print("+------------------+-------------------+-------------+----------+--------+------------------------+\n\n\n");
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int mem;
        int j;
        
        System.out.println("\n\t\tFirst-fit Algorithm Program\n");

        System.out.print("Input number of memory block: ");
        mem = sc.nextInt();
        MemoryBlock[] memory = new MemoryBlock[mem];

        System.out.println("");
        for(int i=0; i<mem; i++) {
            System.out.printf("Input memory(KB) %d : ", i+1);
            memory[i] = new MemoryBlock(sc.nextInt(), i+1);
            f.addMemory(memory[i]);
        }

        System.out.println("");
        System.out.print("Input number of job/program: ");
        j = sc.nextInt();
        Job[] job = new Job[j];

        System.out.println("");
        for(int i=0; i<j; i++){
            System.out.printf("Input job/program %d : ", i+1);
            job[i] = new Job(sc.nextInt(), "J"+Integer.toString(i+1));
            f.addJob(job[i]);
        }
        sc.nextLine();

        f.content_of_block();
            
        sc.close();
    }

    public static void menu() {
        
    }
}