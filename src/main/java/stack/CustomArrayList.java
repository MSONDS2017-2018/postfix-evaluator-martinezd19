package stack;

/**
 * {@link CustomArrayList} acts identical to {@link java.util.ArrayList}, but with less methods.
 * This is to avoid using the Java API (project requirement)
 *
 * @param <T> the type of object for the array to hold
 */
public class CustomArrayList<T> {

  //Define constants for dynamic array operation
  private final int START_SIZE;
  private final int SIZE_MULTIPLIER;
  //Contains array of all objects
  private       T[] list;
  private       int currentIndex;

  @SuppressWarnings("unchecked")
  public CustomArrayList() {
    START_SIZE = 4;
    SIZE_MULTIPLIER = 2;
    currentIndex = -1;

    list = (T[]) new Object[START_SIZE];
  }

  /**
   * Returns the current size of the array.
   *
   * @return the current size of the array
   */
  public final int size() {
    return currentIndex + 1;
  }

  /**
   * Returns true if size() == 0, false otherwise.
   *
   * @return true if size() == 0, false otherwise
   */
  public final boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Adds given object to array.
   *
   * @param o - object to add to array
   */
  @SuppressWarnings("unchecked")
  public final void add(final T o) {
    if (currentIndex != list.length - 1) {
      list[++currentIndex] = o;
    } else {
      T[] tempList = list.clone();
      list = (T[]) new Object[list.length * SIZE_MULTIPLIER];
      for (int i = 0; i < tempList.length; i++) {
        list[i] = tempList[i];
      }

      list[++currentIndex] = o;
    }
  }

  /**
   * Removes an object from the array with a given index. All elements in front of the removed
   * element are shifted backwards.
   *
   * @param index - index of object to be removed
   *
   * @return the removed object
   *
   * @throws ArrayIndexOutOfBoundsException - if the given index is less than 0 or greater than
   *     size()-1
   */
  @SuppressWarnings("unchecked")
  public final T remove(final int index)
      throws ArrayIndexOutOfBoundsException {
    if (!checkIndex(index)) {
      throw new ArrayIndexOutOfBoundsException();
    }

    T[] tempList = list.clone();
    list = (T[]) new Object[Math.max(START_SIZE, list.length - 1)];
    for (int i = 0, j = 0; i < tempList.length; i++, j++) {
      if (i == index) {
        j--;
        continue;
      }
      list[j] = tempList[i];
    }

    currentIndex--;

    return (tempList[index]);
  }

  /**
   * Sets a given index in the array to a given object.
   *
   * @param index - index to set in the array
   * @param o - object to set given index in the array to
   *
   * @return object that was previously in given index in the array
   *
   * @throws ArrayIndexOutOfBoundsException - if the given index is less than 0 or greater than
   *     size()-1
   */
  public final T set(final int index, final T o)
      throws ArrayIndexOutOfBoundsException {
    if (!checkIndex(index)) {
      throw new ArrayIndexOutOfBoundsException();
    }

    T oldObject = list[index];
    list[index] = o;
    return oldObject;
  }

  /**
   * Gets the object at a given index.
   *
   * @param index - index to get object from
   *
   * @return the object at the given index
   *
   * @throws ArrayIndexOutOfBoundsException - if the given index is less than 0 or greater than
   *     size()-1
   */
  public final T get(final int index)
      throws ArrayIndexOutOfBoundsException {
    if (!checkIndex(index)) {
      throw new ArrayIndexOutOfBoundsException();
    }

    return list[index];
  }

  /**
   * Checks whether or not a given index is valid (false if invalid, true if valid).
   *
   * @param index index to check
   *
   * @return whether or not index is valid (false if invalid, true if valid)
   */
  private boolean checkIndex(final int index) {
    return 0 <= index && index <= size() - 1;
  }
}
