#define _CRT_SECURE_NO_DEPRECATE

#include "rwchead.h"


int leftChild(int i) {
	return 2 * i;
}

int rightChild(int i) {
	return 2 * i + 1;
}

int* maxHeapify(int* inputArray, int i, int length) {
	int left = leftChild(i);
	int right = rightChild(i);

	int large = 0;

	if (left < length && inputArray[left] > inputArray[i]) {
		large = left;
	}
	else {
		large = i;
	}

	if (right < length && inputArray[right] > inputArray[i]) {
		large = right;
	}

	if (large != i) {
		int temp = inputArray[i];
		inputArray[i] = inputArray[large];
		inputArray[large] = temp;
		inputArray = maxHeapify(inputArray, large, length);
	}

	return inputArray;
}

int* buildMaxHeap(int * inputArray, int length) {
	int * result = NULL;
	int i;

	for (i = length / 2; i >= 1; i--) {
		result = maxHeapify(inputArray, i, length);
	}

	return result;
}


int main(void) {

	char* inputFileName = "random.txt";
	char* outpuFileName = "c heap sort.txt";

	int count = countElement(inputFileName);

	int* readfile = readTxtfile(inputFileName, count);

	int* sortedArray = buildMaxHeap(readfile, count);

	writeTxtFile(sortedArray, outpuFileName, count);

	printf("!] Sucess! \n");

}
