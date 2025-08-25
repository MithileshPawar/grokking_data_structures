package java.arrays;

import java.util.Arrays;

//Chapter 3: Sorted Arrays
public class SortedArray {
    private final Number[] array;
    private final int maxSize;
    private int size = 0;
    private final Class<?> type;

    public SortedArray(int maxSize, Class<?> type) {
        this.maxSize = maxSize;
        this.array = new Number[maxSize];
        this.type = type;
    }

    public int size() {
        // Return the number of elements in the array.
        return size;
    }

    public int maxSize() {
        // Return the maximum capacity of the array.
        return maxSize;
    }

    public Number get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bound: " + index);
        }
        return array[index];
    }

    @Override
    public String toString() {
        return "SortedArray" + Arrays.toString(Arrays.copyOf(array, size));
    }

    /*
     * Functionality:
     * Inserts the given value into the sorted array while maintaining the sorted
     * order.
     * If the array is already full, raises a ValueError.
     * Otherwise, shifts elements to the right to make room for the new value and
     * inserts it in the correct position to keep the array sorted.
     */
    public void insert(Number value) {

        if (size >= maxSize) {
            throw new IllegalStateException("The array is already full, maximum size: " + maxSize);
        }
        int i = size;
        while (i > 0 && compare(array[i - 1], value) > 0) {
            array[i] = array[i - 1];
            i--;
        }
        array[i] = value;
        size++;
    }

    /*
     * Functionality:
     * Performs a linear search over the values in the sorted array.
     * Since the array is sorted, we can stop searching once we pass the point
     * where the target value would be located.
     * Returns the index of the target value if found, otherwise returns None.
     */
    public Integer linearSearch(Number target) {
        for (int i = 0; i < size; i++) {
            if (compare(array[i], target) == 0) {
                return i;
            } else if (compare(array[i], target) > 0) {
                return null;
            }
        }
        return null;
    }

    /*
     * Functionality:
     * Performs a binary search on the sorted array.
     * Keeps track of left and right indices, and calculates the midpoint index.
     * Checks if the midpoint value matches the target. If so, returns the midpoint
     * index.
     * Otherwise, recurses on either the left or right half of the array depending
     * on if the
     * midpoint value is greater than or less than the target.
     * Returns the index if found, otherwise returns None if the target is not
     * found.
     */
    public Integer binarySearch(Number target) {
        int left = 0, right = size - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = compare(array[mid], target);
            if (cmp == 0)
                return mid;
            else if (cmp > 0)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return null;
    }

    /*
     * Functionality:
     * Finds the index of the target value using the find method.
     * If the target is not found, raises a ValueError.
     * Otherwise, shifts all elements after the target to the left to fill in the
     * gap.
     * If it succeeds, it decrements the size of the array by 1.
     */
    public void delete(Number target) {
        Integer index = binarySearch(target);
        if (index == null) {
            throw new IllegalArgumentException(
                    "Unable to delete element " + target + ": the entry is not in the array");
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    // Helper to compare Numbers (int/double)
    private int compare(Number a, Number b) {
        if (type == Integer.class) {
            return Integer.compare(a.intValue(), b.intValue());
        } else if (type == Double.class) {
            return Double.compare(a.doubleValue(), b.doubleValue());
        } else if (type == Float.class) {
            return Float.compare(a.floatValue(), b.floatValue());
        } else if (type == Long.class) {
            return Long.compare(a.longValue(), b.longValue());
        } else if (type == Short.class) {
            return Short.compare(a.shortValue(), b.shortValue());
        } else if (type == Byte.class) {
            return Byte.compare(a.byteValue(), b.byteValue());
        } else {
            throw new UnsupportedOperationException("Unsupported type: " + type);
        }
    }
}