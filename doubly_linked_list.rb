class DoublyLinkedList
  attr_reader :size

  def initialize
    @head = nil
    @tail = nil
    @size = 0
  end

  # Use this nested class for storing the values of the DoublyLinkedList. Each
  # DoublyLinkedList::Node contains the value at its index, and a link to the
  # DoublyLinkedList::Node at the next index (called the "child" here), and at
  # the previous index (called "previous"). If the child is nil, that denotes
  # the last element of the DoublyLinkedList, while a nil previous denotes the
  # first.
  class Node
    attr_accessor :value, :previous, :child

    def initialize(value, previous = nil, child = nil)
      @value = value
      @previous = previous
      @child = child
    end
  end

  # Define a method ">>" which takes a single argument. This method should
  # prepend the argument to the beginning of this DoublyLinkedList and increase
  # the size by 1. The return value must be self.

  def >> (value)
    new_node = Node.new(value, nil, @head)

    if @size == 0
      @tail = new_node
    else
      @head.previous = new_node
    end

    @head = new_node

    @size += 1

    return self
  end

  # Define a method "<<" which takes a single argument. This method should
  # append the argument to the end of this DoublyLinkedList and increase the
  # size by 1. The return value must be self.

  def << (value)
    new_node = Node.new(value, @tail, nil)

    if @size == 0
      @head = new_node
    else
      @tail.child = new_node
    end

    @tail = new_node

    @size += 1

    return self
  end

  # Define a "first" method which takes no arguments. This method should return
  # the value of that item. An IndexError should be raised if the list is empty.

  def first
    check_empty_list
    return @head.value
  end

  # Define a "last" method which takes no arguments. This method should return
  # the value of that item. An IndexError should be raised if the list is empty.

  def last
    check_empty_list
    return @tail.value
  end

  # Define a "delete_first" method which takes no arguments. This method should
  # delete the first item in the list and return the value of that item. The
  # size must be reduced by 1. An IndexError should be raised if the list is
  # empty.

  def delete_first
    check_empty_list
    return_value = @head.value
    @size -= 1

    if @size == 0
      @head = nil
      @tail = nil
    else
      @head = @head.child
      @head.previous = nil
    end

    return return_value
  end

  # Define a "delete_last" method which takes no arguments. This method should
  # delete the last item in the list and return the value of that item. The size
  # must be reduced by 1. An IndexError should be raised if the list is empty.

  def delete_last
    check_empty_list
    return_value = @tail.value
    @size -= 1

    if @size == 0
      @tail = nil
      @head = nil
    else
      @tail = @tail.previous
      @tail.child = nil
    end

    return return_value
  end

  # Define an "each" method which takes no arguments but accepts a block. The
  # block is yielded to with each element in the list, in order. The return
  # value must be self.

  def each

    this_node = @head

    size.times do |i|
      yield this_node.value
      this_node = this_node.child
    end

    return self
  end

  # Define an "each_reversed" method which takes no arguments but accepts a
  # block. The block is yielded to with each element in the list, in reverse
  # order. The return value must be self.

  def each_reversed

    this_node = @tail

    size.times do |i|
      yield this_node.value
      this_node = this_node.previous
    end

    return self
  end

  #########

  private

  def check_bounds(index)
    check_lower_bound(index)
    check_upper_bound(index)
  end

  def check_lower_bound(index)
    raise IndexError, "Invalid index: #{index}" if index < 0
  end

  def check_upper_bound(index)
    raise IndexError, "Invalid index: #{index}" if index >= size
  end

  def check_empty_list
    raise IndexError, "Empty list" if size == 0
  end
end
