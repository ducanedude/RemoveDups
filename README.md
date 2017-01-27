# RemoveDups
Java program to remove duplicates in an array of primitive integers.

#Assumption

 * In all practical uses the expected type out of a filtered/refined process is same as the output. So all methods return an array of primitive integers.
 * package structure has not been created for execution of the standalone class. If this is part of an application user must place the file in a package.
 * The purpose of main method is to quickly test the capabilities provided by DeDup class. In production consumer would instantiate DeDup and use appropriate method on the object.

# Getting Started

Clone from
[GIT](https://github.com/ducanedude/RemoveDups) and then
use Java 8:

    $ git clone https://github.com/ducanedude/RemoveDups.git
    $ javac DeDup.java
	
# Run this program

    $ java DeDup
    $ java DeDup 1 2 3 4 5

# Expected output (no argument execution)

    Original Array = [1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000, 11, 16, 19, 1, 18, 4, 9, 3, 20, 17, 8, 15, 6, 2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14, 20, 17, 16, 3, 6, 19, 13, 5, 11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7, 15, 17, 10, 6, 1, 8, 18, 4, 14, 13, 2, 11]

    Method 1 (removeDupBasic with Order retained) = [1, 2, 34, 25, 45, 3, 26, 85, 4, 86, 43, 10000, 11, 16, 19, 18, 9, 20, 17, 8, 15, 6, 5, 10, 14, 12, 13, 7]
    Time taken(MicroSec)=661
    Method 1 (removeDupBasic without Order retained) = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 10000, 16, 17, 18, 19, 20, 85, 86, 25, 26, 34, 43, 45]
    Time taken(MicroSec)=467

    Method 2 (removeDupIntStreamImpl with Order retained) = [1, 2, 34, 25, 45, 3, 26, 85, 4, 86, 43, 10000, 11, 16, 19, 18, 9, 20, 17, 8, 15, 6, 5, 10, 14, 12, 13, 7]
    Time taken(MicroSec)=51679
    Method 2 (removeDupIntStreamImpl without Order retained) = [45, 26, 85, 4, 34, 86, 25, 43, 2, 10000, 16, 19, 1, 18, 9, 3, 20, 17, 8, 15, 6, 5, 10, 14, 12, 13, 7, 11]
    Time taken(MicroSec)=8614

    Method 3 (removeDupCollectionStreamImpl with Order retained) = [1, 2, 34, 25, 45, 3, 26, 85, 4, 86, 43, 10000, 11, 16, 19, 18, 9, 20, 17, 8, 15, 6, 5, 10, 14, 12, 13, 7]
    Time taken(MicroSec)=4859
    Method 3 (removeDupCollectionStreamImpl without Order retained) = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 10000, 16, 17, 18, 19, 20, 85, 86, 25, 26, 34, 43, 45]
    Time taken(MicroSec)=1372
