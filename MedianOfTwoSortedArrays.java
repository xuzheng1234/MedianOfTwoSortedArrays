/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package median.of.two.sorted.arrays;

/**
 *
 * @author zxa
 */
public class MedianOfTwoSortedArrays {

    /**
    Question: There are 2 sorted arrays A and B of size n each. 
     * Write an algorithm to find the median of the array obtained after merging 
     * the above 2 arrays(i.e. array of length 2n). The complexity should be O(log(n))
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] A = {1, 2, 3};
        int[] B = {4, 5, 6, 7,8};
        //System.out.println(merge(A, B));
        System.out.println(getMedian(A,B));
    }
    public static double getMedian(int[] A,int []B)
    {
        if((A.length +B.length)%2!=0 )
        {
            return kthElement(A,B,(A.length+B.length)/2+1);
        }
        else
        {
            int mid1= kthElement(A,B,(A.length+B.length)/2);
            int mid2=  kthElement(A,B,(A.length+B.length)/2+1);
      
            return (mid1+mid2)/2.0;
        }
    }
    
    
public static int kthElement(int[] A, int[] B, int k) {

        if (k < 1 || k > A.length + B.length) {
            return -1;
        }
        return kthElement2(A, B, k, 0, A.length - 1);
    }

    public static int kthElement2(int[] A, int[] B, int k, int start, int end) {
        if (start > end) {
            return kthElement2(B, A, k, 0, B.length - 1);
        }
        int i = (end - start) / 2 + start;
        int j = k - i - 1 - 1;
        //  System.out.println(i+" "+j);

        if ((j >= 0 && j < B.length && A[i] >= B[j])
                && (j + 1 < B.length && j + 1 >= 0 && A[i] <= B[j + 1])) {
            return A[i];
        } else if (j < 0 && j + 1 >= 0 && j + 1 < B.length && A[i] <= B[j + 1]) {
            return A[i];
        } else if (j + 1 >= B.length && j >= 0 && j < B.length && A[i] >= B[j]) {
            return A[i];
        } 
        else if (j + 1 >= 0 && j + 1 < B.length && A[i] > B[j + 1]) {

            return kthElement2(A, B, k, start, i - 1);

        } else if (j >= 0 && j < B.length && A[i] < B[j]) {
            return kthElement2(A, B, k, i + 1, end);
        } 
        else if (i + 1 == k) {
            return A[i];
        } 
        
        
        else if (i >= k) {

            return kthElement2(A, B, k, start, i - 1);
        } else {
            //i+B.length+1<k
            return kthElement2(A, B, k, i + 1, end);
        }
    }
    static double merge(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int count = 0;
        int total = (m + n) / 2 + (m + n) % 2;
        int i = 0;
        int j = 0;
        int median = 0;

        for (count = 0; count < total; count++) {
            if (i < m && j < n) {
                if (A[i] < B[j]) {

                    median = A[i++];
                } else {

                    median = B[j++];
                }
            } else {
                break;
            }

        }
        if (i < m) {

            if (count < total) {
                i += total - count - 1;
                median = A[i];
                i++;
            }
        } else {

            if (count < total) {
                j += total - count - 1;
                median = B[j];
                j++;
            }
        }
        if ((m + n) % 2 == 0) {

            if (i < m && j < n) {
                if (A[i] < B[j]) {
                    return (median + A[i]) / 2.0;
                } else {
                    return (median + B[j]) / 2.0;
                }
            } else if (i < m) {
                return (median + A[i]) / 2.0;
            } else {
                return (median + B[j]) / 2.0;
            }
        } else {
            return median;
        }
    }
}
