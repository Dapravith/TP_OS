import java.util.ArrayList;
import java.util.Scanner;

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

public class BestFitAlgorithm{
    private int MEMORY_SIZE;
    private int MEMORY_LOCATION;
    private int JOB_SIZE;
    private String JOB_NUMBER;
    private String status;

    private static ArrayList<MemoryBlock> Mem = new ArrayList<>();
    private static ArrayList<Job> Job = new ArrayList<>();
    private static ArrayList<BestFitAlgorithm> best = new ArrayList<>();
    private static ArrayList<Integer> position = new ArrayList<>();

    BestFitAlgorithm() {

    }

    BestFitAlgorithm(MemoryBlock m, Job j) {
        this.MEMORY_SIZE = m.block_size;
        this.MEMORY_LOCATION = m.memory_location;
        this.JOB_SIZE = j.job_size;
        this.JOB_NUMBER = j.job_number;
        this.status = "Busy";
        // this.status = "Free";
    }

    private static BestFitAlgorithm b = new BestFitAlgorithm();

    void AddMemory(MemoryBlock m) {
        Mem.add(m);
    }

    void AddJob(Job j) {
        Job.add(j);
    }

    void content() {
        System.out.println("\n\t\t\t\t*** Display Content's Result ***\n\t\t\t\t");
        System.out.print("\n\n+-------------------------------------------------------------------------------------------------+\n");
        System.out.println("|\t\t\t\t\t  Content\t\t\t\t\t\t  |");
        
        for(int i=0; i<Job.size(); i++) {
            
            int min;
            int track;
            int state=0;
            
            int count = 0;
            int[] tmp_fragment = new int[Mem.size()];
            for(int j=0; j<Mem.size(); j++) {
                if(Mem.get(j).block_size >= Job.get(i).job_size) {
                    tmp_fragment[count] = Mem.get(j).block_size - Job.get(i).job_size;
                    state = 1;
                    position.add(j);
                    count++;
                }
            }
            if(state == 1) {
                min = tmp_fragment[0];
                track = 0;

                for(int j=0; j<count; j++) {
                    if(min > tmp_fragment[j]) {
                        min = tmp_fragment[j];
                        track = position.get(j);
                    }
                }
                BestFitAlgorithm list = new BestFitAlgorithm(Mem.get(track), Job.get(i));
                best.add(list);
                Mem.remove(track);
            }
            position.clear();
        }
        System.out.print("+------------------+-------------------+-------------+----------+--------+------------------------+\n");
        System.out.printf("| %s | %s | %s | %s | %s | %s |%n", " Memory location", "Memory block size", " Job number", "Job size", "Status", "Internal Fragmentation");
        System.out.print("+------------------+-------------------+-------------+----------+--------+------------------------+\n");
        for(BestFitAlgorithm i:best) {
            if(i.MEMORY_SIZE-i.JOB_SIZE == 0) {
                System.out.printf("| %16d | %16dKB | %11s | %7dKB | %6s | %22s |%n", i.MEMORY_LOCATION, i.MEMORY_SIZE, i.JOB_NUMBER, i.JOB_SIZE, i.status, "None");
            }
            else if(i.MEMORY_SIZE-i.JOB_SIZE > 0) {
                System.out.printf("| %16d | %16dKB | %11s | %7dKB | %6s | %21dK |%n", i.MEMORY_LOCATION, i.MEMORY_SIZE, i.JOB_NUMBER, i.JOB_SIZE, i.status, i.MEMORY_SIZE-i.JOB_SIZE);
            }
        }
        for(MemoryBlock i:Mem) {
            System.out.printf("| %16d | %16dKB | \t     | \t\t| %6s | \t\t\t  |\n", i.memory_location, i.block_size, "Free");
        }
        int sumMem=0;
        int sumJob=0;
        for(BestFitAlgorithm i:best) {
            sumMem += i.MEMORY_SIZE;
            sumJob += i.JOB_SIZE;
        }
        for(MemoryBlock i:Mem) {
            sumMem += i.block_size;
        }
        System.out.print("+------------------+-------------------+-------------+----------+--------+------------------------+\n");
        System.out.printf("| %s | %16dKB | %s | %7dKB |\t | \t\t\t  |\n", "Total Available:", sumMem, "Total Used:", sumJob);
        System.out.print("+------------------+-------------------+-------------+----------+--------+------------------------+\n\n\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int mem;
        int job;

        System.out.println("\n\t\tBest-fit Algorithm Program\n");
        System.out.print("Input number of memory block: ");
        mem = sc.nextInt();
        MemoryBlock[] memory = new MemoryBlock[mem];

        System.out.println("");
        for(int i=0; i<mem; i++) {
            System.out.printf("Input memory(KB) %d : ", i+1);
            memory[i] = new MemoryBlock(sc.nextInt(), i+1);
            b.AddMemory(memory[i]);
        }

        System.out.println("");
        System.out.print("Input number of job/program: ");
        job = sc.nextInt();
        Job[] jobs = new Job[job];

        System.out.println("");
        for(int i=0; i<job; i++){
            System.out.printf("Input job/program %d : ", i+1);
            jobs[i] = new Job(sc.nextInt(), "J"+Integer.toString(i+1));
            b.AddJob(jobs[i]);
        }
        sc.nextLine();

        b.content();

        sc.close();
    }

    public static void menu() {
        
    }

}