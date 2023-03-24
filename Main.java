import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        int[] test = new int[8];
        for(int i=0; i<test.length-1;i++){
            test[i] = i;
        }
        //mergeSort(test,0,test.length-1);
        int[] arr = new int[16];
        for(int i=0; i<arr.length-1; i++){
            arr[i] = (int) (Math.random()*100);
        }
        System.out.println(Arrays.toString(arr));
        merge(arr,0,arr.length/2,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    static long exp (long x, long m) {
        long tmp = 1;
        if(x >= 2147483647 || m >= 2147483647){
            tmp = -tmp;
        } else if(m > 0){
            tmp = x*exp(x,m-1);
        }
        return tmp;
    }
    public static void mergeSort(int[] arr, int left, int right){
        if(right - left > 0){
            int middle = (left+right)/2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle, right);
            merge(arr, left, middle, right);
        }
    }
    public static void merge(int[] arr, int left, int middle, int right){
        //separates arr into two arrays. easier to read
        int length1 = 1;
        if(left == middle){
            length1 = 1;
        } else {
            length1 = right-middle;
        }
        int length2 = right-length1;
        if(right % 2 == 0){ //correction of lengths
            length2++;
        } else {
            length1++;
        }
        int[] arr1 = new int[length1];
        int[] arr2 = new int[length2];

        //split arrays
        for(int i=0; i<arr1.length; i++){
            arr1[i] = arr[i];
        }
        for(int i=0; i<arr2.length; i++){
            arr2[i] = arr[i+arr1.length];
        }

        //sort both arrays
        arr1 = bubbleSort(arr1, false);
        arr2 = bubbleSort(arr2,false);

        //make new array to merge sort items into
        //arr will be modified directly but for now I want to compare changes
        int arr1pos = 0;
        int arr2pos = 0;
        for(int i=0; i<arr.length; i++) {
            //fixes the wonderful ArrayIndexOutOfBounds issue.
            if(arr2pos == arr2.length){
                arr2pos--;
            }
            if(arr1pos == arr1.length){
                arr1pos--;
            }
            //does the merge sorting
            if (arr1[arr1pos] > arr2[arr2pos]) {
                arr[i] = arr2[arr2pos];
                arr2pos++;
            } else {
                arr[i] = arr1[arr1pos];
                arr1pos++;
            }
        }
    }
    //mergeSort requires the arrays to be pre-sorted. this does that, temporarily.
    //with full mergeSort implementation the arrays will be broken down into single-element arrays which are by definition sorted
    //but mergeSort doesn't work right now
    public static int[] bubbleSort(int[] bubblePile, boolean verbose){
        int increment = 0;
        int pileLength = bubblePile.length;
        int pileLength2 = bubblePile.length;
        master:
        for(int i=0; i<pileLength2-1; i++) {
            for(int j=0; j<pileLength2-1; j++) {
                int getJ = (int)(bubblePile[j+1]);
                if(bubblePile[j] > bubblePile[j+1]) {
                    bubblePile[j+1] = bubblePile[j];
                    bubblePile[j] = getJ;
                }
                increment++;
                if(verbose) {
                    System.out.println(Arrays.toString(bubblePile));
                }
                if(increment >= pileLength*pileLength){
                    break master;
                }
            }
        }
        return bubblePile;
    }
}