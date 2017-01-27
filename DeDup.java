import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ugender Malgireddy
 * DeDup is a class with methods to provide arrays with distinct primitive integers.
 */
public class DeDup {

	// randomIntegers is a provided variable. It being public, the instance values can be manipulated if required.
    public int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,19,1,18,4,9,3,
                                   20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,
                                   13,5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};   

    
    /**
     * Basic implementation using Collections and Iteration which would work with Java 5 and above.
     * Pros: Compatible with older versions of Java. Comparable or better performance to other methods if the array is small or deployed on single core box.
     * Cons: Would run slower if the array size is large. Can not take advantage of multiple core when available. 
     * 
     * @param inputArray - initial set of values 
     * @param retainOrder - specify if the original order needs to be retained
     * @return int[] with distinct values
     */
    public int[] removeDupBasic(int[] inputArray, boolean retainOrder) {
    	
    	// Making a Set out of input Array will result in distinct integers
    	Set<Integer> numberSet = retainOrder ? new LinkedHashSet<Integer>() : new HashSet<Integer>();
    	for(int i=0; inputArray!=null && i<inputArray.length; i++) {
    		numberSet.add(inputArray[i]);
    	}
    	
    	// convert the Set into an Array
    	int[] uniqueInt = new int[numberSet.size()];
    	int counter = 0;
    	
    	// Since we do not have any remove operation for-each iteration is preferred.
    	for(Integer member : numberSet) {
    		uniqueInt[counter] = member;
    		counter++;
    	}

    	return uniqueInt;
    }
    
    /**
     * Implementaion using Stream API in Java 8, without using Collections.
     * Pros: Legible and readable code. Can utilize a multi-core system to the best and have better performance on large arrays.
     * Cons: Would not run on JVM's below JAVA 8. Adding parallel might not be useful for small arrays or single core systems. Ordered version has large overhead.
     * 		 If necessary we can have a property or class path variable which would signify multi-core based on which we can add parallelism.
     * 
     * @param inputArray - initial set of values 
     * @param retainOrder - specify if the original order needs to be retained
     * @return int[] with distinct values
     */    
    public int[] removeDupIntStreamImpl(int[] inputArray, boolean retainOrder) {
    	
    	// a stream out of Array will be ordered by default 
    	if(retainOrder) {
    		return Arrays.stream(inputArray)
    				.distinct()
    				.toArray();
    	} else {
    		return Arrays.stream(inputArray)
    				.parallel()
    				.unordered()
    				.distinct()
    				.toArray();
    	}
    }
    
    /**
     * Implementaion using Stream API in Java 8, using Collections.
     * Pros: Legible and readable code. Can utilize a multi-core system to the best and have better performance on large arrays.
     * Cons: Would not run on JVM's below JAVA 8. Adding parallel might not be useful for small arrays or single core systems.
     * 		 If necessary we can have a property or class path variable which would signify multi-core based on which we can add parallelism.
     * 
     * @param inputArray - initial set of values 
     * @param retainOrder - specify if the original order needs to be retained
     * @return int[] with distinct values
     */ 
    public int[] removeDupCollectionStreamImpl(int[] inputArray, boolean retainOrder) {
    	
    	Set<Integer> arrSet;
    	if(retainOrder) {
    		arrSet = Arrays.stream(inputArray)
    				.parallel()
    				.boxed()
    				.collect(Collectors.toCollection(LinkedHashSet::new));
    	} else {
    		arrSet = Arrays.stream(inputArray)
    				.parallel()
    				.boxed()
    				.collect(Collectors.toSet());
    	}
    	
    	// Using method reference (with static method) should technically be faster than lambda expression
    	// To use lambda expression replace (Integer::intValue) with (i -> i) 
    	return arrSet.parallelStream().mapToInt(Integer::intValue).toArray();
    }
    
    public static void main(String [] args) {
    	
    	DeDup dd = new DeDup();
    	
    	// Additional support for integers from scanner.
    	// Note: This would throw NumberFormatExceptions if any non integer inputs are used.
    	if(args.length>0) {
    		dd.randomIntegers = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
    	}
    	
    	System.out.println("Original Array = " + Arrays.toString(dd.randomIntegers));
    	
    	long startTime, endTime;
    	
    	startTime = System.nanoTime();
    	System.out.println("\nMethod 1 (removeDupBasic with Order retained) = " + Arrays.toString(dd.removeDupBasic(dd.randomIntegers, true)));
    	endTime = System.nanoTime();
    	System.out.println("Time taken(MicroSec)="+(endTime-startTime)/1000);
    	
    	startTime = System.nanoTime();
    	System.out.println("Method 1 (removeDupBasic without Order retained) = " + Arrays.toString(dd.removeDupBasic(dd.randomIntegers, false)));
    	endTime = System.nanoTime();
    	System.out.println("Time taken(MicroSec)="+(endTime-startTime)/1000);
    	
    	startTime = System.nanoTime();
    	System.out.println("\nMethod 2 (removeDupIntStreamImpl with Order retained) = " + Arrays.toString(dd.removeDupIntStreamImpl(dd.randomIntegers, true)));
    	endTime = System.nanoTime();
    	System.out.println("Time taken(MicroSec)="+(endTime-startTime)/1000);
    	
    	startTime = System.nanoTime();
    	System.out.println("Method 2 (removeDupIntStreamImpl without Order retained) = " + Arrays.toString(dd.removeDupIntStreamImpl(dd.randomIntegers, false)));
    	endTime = System.nanoTime();
    	System.out.println("Time taken(MicroSec)="+(endTime-startTime)/1000);
    	
    	startTime = System.nanoTime();
    	System.out.println("\nMethod 3 (removeDupCollectionStreamImpl with Order retained) = " + Arrays.toString(dd.removeDupCollectionStreamImpl(dd.randomIntegers, true)));
    	endTime = System.nanoTime();
    	System.out.println("Time taken(MicroSec)="+(endTime-startTime)/1000);
    	
    	startTime = System.nanoTime();
    	System.out.println("Method 3 (removeDupCollectionStreamImpl without Order retained) = " + Arrays.toString(dd.removeDupCollectionStreamImpl(dd.randomIntegers, false)));
    	endTime = System.nanoTime();
    	System.out.println("Time taken(MicroSec)="+(endTime-startTime)/1000);
    }
}
