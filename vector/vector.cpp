#include <string>
#include <iostream>
#include <sstream>
using namespace std;

template <typename T> class vector {
  T *arr;
  long long capacity;
  long long currentSize;

public:
  vector();
  vector(vector<T> &other);
  vector(vector<T> && other);
  vector(initializer_list<T> list);
  ~vector();
  void push_back(T element);
  void insert(T element, int index);
  void emplace_back(T &&element);
  void pop();
  long long size();
  T get(int index);
  T* getarr();
  long long getcapacity();
  T &operator[](int index);
  const T &operator[](int index) const;
  vector<T> &operator=(vector<T> &other);
  vector<T> &operator=(vector<T> &&other);
  bool operator==(const vector<T>& other) const;
  bool operator!=(const vector<T>& other) const;
  bool operator<(const vector<T>& other) const;
  bool operator<=(const vector<T>& other) const;
  bool operator>(const vector<T>& other) const;
  bool operator>=(const vector<T>& other) const;
  string to_string() const;
};

template <typename T> vector<T>::vector() {
  arr = new T[1];
  currentSize = 0;
  capacity = 1;
}

template <typename T> vector<T>::vector(vector<T> &other) {
  arr = new T[other.capacity];
  currentSize = other.currentSize;
  capacity = other.capacity;
  for(int i = 0; i < currentSize; ++i)
    arr[i] = other[i];
}

template <typename T> vector<T>::vector(vector<T>&& other) {
  arr = other.arr;
  capacity = other.capacity;
  currentSize = other.currentSize;
  other.arr = nullptr;
  other.currentSize = 0;
  other.capacity = 0;
}

template <typename T> vector<T>::vector(initializer_list<T> list) {
  arr = new T[list.size()];
  currentSize = list.size();
  capacity = list.size();
  int i = 0;
  for (auto &elem : list) {
    arr[i] = elem;
    i++;
  }
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

template <typename T> T* vector<T>::getarr() {
  return arr;
}

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

template <typename T> vector<T> &vector<T>::operator=(vector<T> &other) {
  delete arr;
  capacity = other.capacity;
  currentSize = other.currentSize;
  arr = new T[capacity];
  for(int i = 0; i < currentSize; ++i)
    arr[i] = other[i];
  return *this;
}

template <typename T> vector<T> &vector<T>::operator=(vector<T> &&other) {
  delete arr;
  capacity = other.capacity;
  currentSize = other.currentSize;
  arr = new T[capacity];
  for(int i = 0; i < currentSize; ++i)
    arr[i] = other[i];
  other.arr = nullptr;
  other.currentSize = 0;
  other.capacity = 0;
  return *this;
}

template <typename T> bool vector<T>::operator==(const vector<T>& other) const {
  if(currentSize != other.currentSize) return false;
  for(int i = 0; i < currentSize; ++i)
    if(arr[i] != other[i])
      return false;
  return true;
}

template <typename T> bool vector<T>::operator!=(const vector<T>& other) const {
  return !(*this == other);
}

template <typename T> bool vector<T>::operator<(const vector<T>& other) const {
  if(currentSize != other.currentSize) return currentSize < other.currentSize;
  for(int i = 0; i < currentSize; ++i)
    if(arr[i] < other[i])
      return true;
  return false;
}
template <typename T> bool vector<T>::operator<=(const vector<T>& other) const {
  return !(other < *this);
}

template <typename T> bool vector<T>:: operator>(const vector<T>& other) const {
  return other < *this;
}

template <typename T> bool vector<T>::operator>=(const vector<T>& other) const {
  return !(other > *this);
}

template <typename T> string vector<T>::to_string() const {
  stringstream strstream;
  strstream << "[";
  for (int i = 0; i < currentSize; ++i) {
    strstream << arr[i];
    if (i < currentSize - 1) {
      strstream << ", ";
    }
  }
  strstream << "]";
  return strstream.str();
}
