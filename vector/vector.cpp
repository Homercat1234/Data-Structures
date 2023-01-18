template <typename T> class vector {
  T *arr;
  long long capacity;
  long long currentSize;

  public:
    vector () {
      arr = new T[1];
      currentSize = 0;
      capacity = 1;
    }

    ~ vector () {
      delete arr;
    }

    void push_back(T element);
    void insert(T element, int index);
    void pop();
    long long size();
    T get(int index);
    long long getcapacity();
};

template <typename T> 
void vector<T>::push_back (T element) {
  if(currentSize < capacity) {
    arr[currentSize] = element;
    currentSize++;
  } else {
    capacity *= 2;
    T *temp = new T[capacity];
    for(int i = 0; i < capacity; ++i)
      temp[i] = arr[i];
    delete arr;
    arr = temp;
    arr[currentSize] = element;
    currentSize++;
  }
}

template <typename T> 
void vector<T>::insert(T element, int index) {
  if(index == capacity) {
    push_back(element);
  } else {
    arr[index] = element;
  }
}

template <typename T> 
void vector<T>::pop() {
  if(currentSize > 0) currentSize--;
}

template <typename T> 
long long vector<T>::size() {
  return currentSize;
}

template <typename T> 
T vector<T>::get(int index) {
  if(index < currentSize) return arr[index];
}

template <typename T> 
long long vector<T>::getcapacity() {
  return capacity;
}
