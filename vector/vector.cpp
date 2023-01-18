using namespace std;

template <typename T> class vector {
  T *arr;
  long long capacity;
  long long currentSize;

public:
  vector();
  ~vector();
  void push_back(T element);
  void insert(T element, int index);
  void emplace_back(T &&element);
  void pop();
  long long size();
  T get(int index);
  long long getcapacity();
  T &operator[](int index);
  const T &operator[](int index) const;
};

template <typename T> vector<T>::vector() {
  arr = new T[1];
  currentSize = 0;
  capacity = 1;
}

template <typename T> vector<T>::~vector() { delete arr; }

template <typename T> void vector<T>::push_back(T element) {
  if (currentSize < capacity) {
    arr[currentSize] = element;
    currentSize++;
  } else {
    capacity *= 2;
    T *temp = new T[capacity];
    for (int i = 0; i < capacity; ++i)
      temp[i] = arr[i];
    delete arr;
    arr = temp;
    arr[currentSize] = element;
    currentSize++;
  }
}

template <typename T> void vector<T>::insert(T element, int index) {
  if (index == capacity) {
    push_back(element);
  } else {
    arr[index] = element;
  }
}

template <typename T> void vector<T>::pop() {
  if (currentSize > 0)
    currentSize--;
}

template <typename T> long long vector<T>::size() { return currentSize; }

template <typename T> T vector<T>::get(int index) {
  if (index < currentSize)
    return arr[index];
}

template <typename T> void vector<T>::emplace_back(T &&element) {
  if (currentSize < capacity) {
    new (arr + currentSize) T(forward<T>(element));
    currentSize++;
  } else {
    capacity *= 2;
    T *temp = new T[capacity];
    for (int i = 0; i < currentSize; ++i)
      temp[i] = move(arr[i]);
    delete arr;
    arr = temp;
    new (arr + currentSize) T(forward<T>(element));
    currentSize++;
  }
}

template <typename T> long long vector<T>::getcapacity() { return capacity; }

template <typename T> T &vector<T>::operator[](int index) {
  if (index >= currentSize) {
    throw out_of_range("Index out of range");
  }
  return arr[index];
}

template <typename T> const T &vector<T>::operator[](int index) const {
  if (index >= currentSize) {
    throw out_of_range("Index out of range");
  }
  return arr[index];
}
