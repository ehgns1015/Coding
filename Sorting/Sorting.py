import random

class Sorting:

    def swap(self, arr, i, j):
        temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    
    def insertion_sort(self, arr):
        if arr is None:
            raise Exception("arr is null")
        for i in range(1, len(arr)):
            temp = arr[i]
            j = i - 1
            while j >= 0 and arr[j] > temp:
                arr[j + 1] = arr[j]
                j -= 1
            arr[j + 1] = temp

    def selection_sort(self, arr):
        if arr is None:
            raise Exception("arr is null")
        for i in range(len(arr)):
            min = i
            for j in range(i + 1, len(arr)):
                if arr[j] < arr[min]:
                    min = j
            self.swap(arr, i, min)

    def cocktail_sort(self, arr):
        if arr is None:
            raise Error("arr==null")
        swap = True
        i = 0
        j = len(arr) - 1
        while i < j and swap:
            swap = False
            for k in range(i, j):
                if arr[k] > arr[k + 1]:
                    self.swap(arr, k, k + 1)
                    swap = True
            i += 1
            if swap:
                for k in range(j, i, -1):
                    if arr[k] < arr[k - 1]:
                        self.swap(arr, k, k - 1)
                        swap = True
                j -= 1
    
    def merge_sort(self, arr):
        if arr is None:
            raise Exception("arr is null")
        if len(arr) > 1:
            mid = len(arr) // 2
            left = arr[:mid]
            right = arr[mid:]
            self.merge_sort(left)
            self.merge_sort(right)
            self.__merge_sort(arr, left, right)

    def __merge_sort(self, arr, left, right):
        i = 0
        j = 0
        for k in range(len(arr)):
            if j >= len(left) or (i < len(left) and left[i] < right[j]):
                arr[k] = left[i]
                i += 1
            else:
                arr[k] = right[j]
                j += 1 

    def quick_sort(self, arr, rand):
        if arr is None or rand >= len(arr) or rand < 0:
            raise Exception("unacceptable")
        self.__quick_sort(arr, 0, len(arr) - 1, rand)

    def __quick_sort(self, arr, first, last, rand):
        if first < last:
            i = first + 1
            j = last
            pivot = arr[rand]
            self.swap(arr, first, rand)
            while i <= j:
                while i <= j and arr[i] >= pivot:
                    i += 1
                while i <= j and arr[j] <= pivot:
                    j -= 1
                if i <= j:
                    self.swap(arr, i, j)
                    i += 1
                    j -= 1
            self.swap(arr, first, j)
            if first < j:
                self.__quick_sort(arr, first, j - 1, random.randint(first, j - 1))
            if last > i:
                self.__quick_sort(arr, j + 1, last, random.randint(j + 1, last))  
        

    def lsd_radix_sort(self, arr):
        buckets = [[] for i in range(19)]
        div = 1
        cont = True
        while cont:
            cont = False
            for i in arr:
                tmp = i // div
                cont = True if tmp // 10 != 0 else False
                digit = tmp % 10 if tmp > 0 else tmp % 10 * -1
                buckets[digit + 9].append(i)
            idx = 0
            for bucket in buckets:
                if bucket is not None:
                    for i in bucket:
                        arr[idx] = i
                        idx += 1
                    bucket.clear()
            div *= 10

