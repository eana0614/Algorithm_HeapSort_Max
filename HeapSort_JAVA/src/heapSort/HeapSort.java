package heapSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class HeapSort {
	private static int arrayLength = 1000;
	public static void main(String[] args) {
	
		String filePath = "random.txt";
		String fileName = "java heap sort.txt";
		
		int[] readArray;
		
		try {
			
			readArray = readInputFile(filePath);
			int[] sortedArray = buildMaxHeap(readArray);
			writeSortedFile(fileName, sortedArray);
			
			System.out.println("DONE");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static int[] buildMaxHeap(int[] readArray) {
		int [] result = null;
		for(int i=readArray.length/2; i >=1 ; i--){
			result = maxHeapify(readArray, i);
		}
		return result;
	}

	private static int[] maxHeapify(int[] readArray, int i) {
		int left = leftChild(i);
		int right = rightCild(i);
		int large = 0;
		
		if( left < readArray.length && readArray[left] > readArray[i]){
			large = left;
		}else{
 			large = i;
		}
		
		if( right < readArray.length && readArray[right] > readArray[i]){
			large = right;
		}
		
		if(large != i){
			int temp = readArray[i];
			readArray[i] = readArray[large];
			readArray[large] = temp;
			readArray = maxHeapify(readArray, large);
		}
		
		return readArray;
	}
	
	private static int parent(int i){
		return i/2;
	}

	private static int leftChild(int i) {
		return 2*i;
	}

	private static int rightCild(int i) {
		return 2*i + 1;
	}

	private static void writeSortedFile(String fn, int[] array) throws IOException{
		File ouputFile = new File(fn);
		FileOutputStream fos = new FileOutputStream(ouputFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		
		for(int i=0; i<array.length; i++){
			bw.write(String.valueOf(array[i]));
			bw.newLine();
		}
		
		bw.close();
		osw.close();
		fos.close();
	}

	private static int[] readInputFile(String filePath) throws IOException   {
		File inputFile = new File(filePath);
		FileInputStream fis = new FileInputStream(inputFile);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		int[] tempArray = new int[arrayLength];
		
		String s;
		int i=0;
		
		while((s = br.readLine())!=null){
			tempArray[i]= Integer.parseInt(s);
			i++;
		}
		
		br.close();
		isr.close();
		fis.close();
		
		return tempArray;
	}

}
