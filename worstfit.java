import java.util.Scanner;

public class worstfit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // input number of memory blocks and processes
        System.out.print("Enter the number of memory blocks: ");
        int numBlocks = scanner.nextInt();
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        // input memory blocks
        int[] memoryBlocks = new int[numBlocks];
        System.out.println("Enter the sizes of the memory blocks:");
        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            memoryBlocks[i] = scanner.nextInt();
        }

        // input processes
        int[] processSizes = new int[numProcesses];
        System.out.println("Enter the sizes of the processes:");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            processSizes[i] = scanner.nextInt();
        }

        // Allocation array to track which block is assigned to each process
        int[] allocation = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            allocation[i] = -1; // Initialize as not allocated
        }

        // perform worst fit allocation
        for (int i = 0; i < numProcesses; i++) {
            int worstIndex = -1; // index of the largest block 
            for (int j = 0; j < numBlocks; j++) {
                if (memoryBlocks[j] >= processSizes[i]) {
                    if (worstIndex == -1 || memoryBlocks[j] > memoryBlocks[worstIndex]) {
                        worstIndex = j;
                    }
                }
            }

            // If a suitable block is found, allocate it to the process
            if (worstIndex != -1) {
                allocation[i] = worstIndex; // Assign block index to the process
                memoryBlocks[worstIndex] -= processSizes[i]; // Update remaining block size
            }
        }

        // output of allocation results
        System.out.println("\nProcess allocation results:");
        for (int i = 0; i < numProcesses; i++) {
            if (allocation[i] != -1) {
                System.out.println("Process " + (i + 1) + " allocated to Block " + (allocation[i] + 1));
            } else {
                System.out.println("Process " + (i + 1) + " could not be allocated");
            }
        }

        // Output of remaining memory blocks
        System.out.println("\nRemaining memory in blocks:");
        for (int i = 0; i < numBlocks; i++) {
            System.out.println("Block " + (i + 1) + ": " + memoryBlocks[i] + " KB");
        }

        scanner.close();
    }
}
