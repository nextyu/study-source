package java.util;

/**
 * Deque 是 double ended queue（双端队列）的简称
 * 是一个线性的集合，支持从两端添加或者移除元素
 */
public interface Deque<E> extends Queue<E> {
    /**
     * 在队列的头部插入元素
     * 如果失败，抛异常
     */
    void addFirst(E e);

    /**
     * 在队列的尾部插入元素
     * 如果失败，抛异常
     */
    void addLast(E e);

    /**
     * 在队列的头部插入元素
     * 如果失败，返回false
     */
    boolean offerFirst(E e);

    /**
     * 在队列的尾部插入元素
     * 如果失败，返回false
     */
    boolean offerLast(E e);

    /**
     * 返回并移除队列第一个元素，如果队列是空的，抛异常
     */
    E removeFirst();

    /**
     * 返回并移除队列最后一个元素，如果队列是空的，抛异常
     */
    E removeLast();

    /**
     * 返回并移除队列第一个元素，如果队列是空的，返回null
     */
    E pollFirst();

    /**
     * 返回并移除队列最后一个元素，如果队列是空的，返回null
     */
    E pollLast();

    /**
     * 返回队列第一个元素，如果队列是空的，抛异常
     */
    E getFirst();

    /**
     * 返回队列最后一个元素，如果队列是空的，抛异常
     */
    E getLast();

    /**
     * 返回队列第一个元素，如果队列是空的，返回null
     */
    E peekFirst();

    /**
     * 返回队列最后一个元素，如果队列是空的，返回null
     */
    E peekLast();

    /**
     * 移除队列里面和传入的参数相等的第一个出现的元素
     */
    boolean removeFirstOccurrence(Object o);

    /**
     * 移除队列里面和传入的参数相等的最后一个出现的元素
     */
    boolean removeLastOccurrence(Object o);

    // *** Queue methods ***

    /**
     * 和addLast方法相同 {@link #addLast}.
     */
    boolean add(E e);

    /**
     * 和offerLast方法相同 {@link #offerLast}.
     */
    boolean offer(E e);

    /**
     * 和removeFirst方法相同 {@link #removeFirst}.
     */
    E remove();

    /**
     * 和pollFirst方法相同 {@link #pollFirst}.
     */
    E poll();

    /**
     * 和getFirst方法相同 {@link #getFirst}.
     */
    E element();

    /**
     * 和peekFirst方法相同 {@link #peekFirst}.
     */
    E peek();


    // *** Stack methods ***

    /**
     * 和addFirst方法相同 {@link #addFirst}.
     */
    void push(E e);

    /**
     * 和removeFirst方法相同 {@link #removeFirst}.
     */
    E pop();


    // *** Collection methods ***

    /**
     * 和removeFirstOccurrence方法相同 {@link #removeFirstOccurrence}.
     */
    boolean remove(Object o);

    /**
     * 判断是否包含指定的元素
     */
    boolean contains(Object o);

    /**
     * 包含的元素数量
     */
    public int size();

    /**
     * 返回从第一个元素（头）到最后一个元素（尾）顺序的迭代器
     */
    Iterator<E> iterator();

    /**
     * 返回从最后一个元素（尾）到第一个元素（头）顺序的迭代器
     */
    Iterator<E> descendingIterator();

}
