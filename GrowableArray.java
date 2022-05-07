public class GrowableArray {
    public int arr_size;
    Object arr_elements[];

    public GrowableArray() {
        arr_size = 0;
        arr_elements = new Object[0];
    }

    public boolean isEmpty(){
        return this.arr_size == 0;
    }

    public boolean isFull(){
        return this.arr_size == arr_elements.length;
    }

    private void doubleSize(){
        Object[] new_double_size_arr;
        if (this.arr_elements.length==0) new_double_size_arr = new Object[1];
        else new_double_size_arr = new Object[2*this.arr_elements.length];
        System.arraycopy(this.arr_elements, 0, new_double_size_arr, 0, this.arr_elements.length);
        this.arr_elements = new_double_size_arr;
    }

    public void insertKey(Object key){
        if (this.isFull()) {
            this.doubleSize();
        }
        arr_size++;
        arr_elements[arr_size-1] = key;
    }
}