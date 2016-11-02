class ArrayList
  attr_reader :size

  def initialize
    @max_size = 10
    @array = FixedArray.new(@max_size)
    @size = 0
  end

  def [](index)
    check_bounds(index)
    @array[index]
  end

  # Define a method "<<" which takes a single argument. This method should
  # append the argument to the end of this ArrayList and increase the size by
  # 1. The return value must be self.

  def <<(new_item)
    upsize

    @array[@size] = new_item

    @size += 1
  end

  # Define a method ">>" which takes a single argument. This method should
  # prepend the argument to the beginning of this ArrayList and increase the
  # size by 1. The return value must be self.

  def >>(new_item)
    @size += 1

    upsize

    @size.downto(1) do |i|
      @array[i] = @array[i - 1]
    end

    @array[0] = new_item
  end

  # Define a "delete" method which takes a single index argument. This method
  # should delete the value at the provided index and return it. The size should
  # be 1 less than it was before this method was called. The index must be
  # within the bounds of the ArrayList, or an IndexError should be raised.

  def delete(index)
    check_bounds index

    temp = 0

    deleted_value = @array[index]

    @size -= 1

    @size.times do |i|

      if temp == index
        temp += 1
      end

      @array[i] = @array[temp]

      temp += 1

    end

    @array[@size] = nil

    return deleted_value
  end

  # Define a method "[]=" which takes 2 arguments. This method should set the
  # value at the index defined in the first argument such that array_list[index]
  # will return the second argument.
  #
  # If the index is negative, an IndexError should be raised.
  #
  # If the index is bigger than the current size of the @array, the @array
  # should be replaced with a bigger FixedArray to fit the new index. All
  # indexes between the former last element and the new index should be
  # initialized with nil. An additional buffer should be included in the new
  # FixedArray (in case the array is grown more), though this is not required.
  #
  # The size after this method is called depends on the index provided. An
  # existing index would not affect the size, but an index greater than the last
  # index will add the difference to the size.

  def []=(index, new_value)
    check_lower_bound index

    while @size <= index
      @size += 1
      upsize
    end

    @array[index] = new_value
  end



  # PRIVATE METHODS

  private

  def upsize
    if @size == @max_size
      temp_array = @array

      @array = FixedArray.new(@max_size + 10)

      @max_size.times do |i|
        @array[i] = temp_array[i]
      end

      @max_size += 10
    end
  end

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
end
