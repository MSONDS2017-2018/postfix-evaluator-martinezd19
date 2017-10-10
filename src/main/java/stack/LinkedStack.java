package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a {@link CustomArrayList}
 *
 * @param <T> the type of the elements stored in the stack
 */
public class LinkedStack<T>
    implements StackInterface<T> {

  private CustomArrayList<T> list;

  public LinkedStack() {
    list = new CustomArrayList<>();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public final T pop()
      throws StackUnderflowException {

    if (isEmpty()) {
      throw new StackUnderflowException();
    }

    return list.remove(size() - 1);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public final T top()
      throws StackUnderflowException {

    if (isEmpty()) {
      throw new StackUnderflowException();
    }

    return list.get(size() - 1);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public final boolean isEmpty() {
    return list.size() == 0;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public final int size() {
    return list.size();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public final void push(final T elem) {
    list.add(elem);
  }
}
