import math

class Heap:
    def __init__(self):
        self.list = [None]
    
    def __swap(self, i, j):
        temp = self.list[i]
        self.list[i] = self.list[j]
        self.list[j] = temp

    def __up_heap(self, i):
        j = math.floor(i / 2)
        if i > 1:
            if list[j] < list[i]:
                self.swap(i, j)
            self.__up_heap(j)

    def __down_heap(self, i):
        if i * 2 <= self.size():
            j = i * 2
            if list[j] > list[j + 1]:
                j += 1
            if list[i] > list[j]:
                self.swap(i, j)
            if j < self.size():
                self.__down_heap(j)
        
    def add(self, item):
        if item is None:
            raise Error("item===null")
        list.append(item)
        self.__up_heap(self.size())

    def remove(self):
        if (self.is_empty()):
            raise Error("list===null")
        removed = list[1]
        self.swap(1, self.size())
        list[self.size()] = None
        return removed

    def get_max(self):
        if self.is_empty():
            return None
        return self.list[1]

    def is_empty(self):
        return self.size() is 0

    def clear(self):
        self.list = [None]
    
    def size(self):
        return len(self.list) - 1
