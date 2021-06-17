import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    private static class pcb implements Comparable<pcb>{
        int id;
        int arriveTime;
        int needTime;
        int startTime;
        int finishTime;
        int needRAM;
        char state;
        /*基于arriveTime的比较*/
        @Override
        public int compareTo(pcb otherPcb){
            if (this.arriveTime == otherPcb.arriveTime){
                return 0;
            }else if (this.arriveTime < otherPcb.arriveTime){
                return -1;
            }else{
                return 1;
            }
        }
        /*初始化*/
        public void getinfo(int id){
            this.id = id;
            this.arriveTime = (int)Math.random()*5;
            this.needTime = (int)Math.random()*10+1;
            this.needRAM = (int)Math.random()*400;
            this.state = 'R';
        }
    }
    public class RR{
        private int numOfPCB;
        private Queue<pcb> ReadyQueue;
        private Queue<pcb> UnreachQueue;
        private Queue<pcb> ExecutedQueue;
        private int TimeSlice;
        private int TotalWholeTime = 0;
        private int TotalWeightTime = 0;
        private RR(int numOfPCB, Queue<pcb> pcbQueue, int timeSlice){
            this.numOfPCB = numOfPCB;
            this.UnreachQueue = pcbQueue;
            this.TimeSlice = timeSlice;
            this.ReadyQueue = new LinkedBlockingQueue<>();
            this.ExecutedQueue = new LinkedList<>();
        }
        public void RRAlgorithm(){
            ReadyQueue.add(UnreachQueue.poll());
            pcb currPcb = ReadyQueue.poll();
            int currTime = ExecutedQueue(currPcb,0);
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入进程数量：");
        int pcbnum = scanner.nextInt();
        pcb[] arry = new pcb[pcbnum];
        for (int i = 0; i < pcbnum; i++) {
            arry[i].getinfo(i);
        }
        /*排序，先来先服务*/
        Arrays.sort(arry);
        int timeSlice = 2;


    }
}
