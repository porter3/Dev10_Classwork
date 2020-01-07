package CommonEnd;

/**
 *
 * @author jake
 */
public class CommonEnd {
    // Given 2 arrays of ints, a and b, return true if they 
    // have the same first element or they have the same 
    // last element. Both arrays will be length 1 or more. 
    //
    // commonEnd({1, 2, 3}, {7, 3}) -> true
    // commonEnd({1, 2, 3}, {7, 3, 2}) -> false
    // commonEnd({1, 2, 3}, {1, 3}) -> true
    public boolean commonEnd(int[] a, int[] b) {
        int startA = a[0];
        int endA = a[a.length-1];
        int startB = b[0];
        int endB = b[b.length-1];
        return (startA == startB) || (endA == endB);
    }
}
