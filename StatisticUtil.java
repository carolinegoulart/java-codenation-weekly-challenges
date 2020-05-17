package br.com.codenation;

import java.util.*;

public class StatisticUtil {

	public static int average(int[] elements) {
		int soma = 0;
		for(int element: elements) {
			soma += element;
		}
		return (soma/elements.length);
	}

	public static int mode(int[] elements) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(int element_i: elements){
			int count = 0;
			for(int element_j: elements){
				if(element_i == element_j) {
					count ++;
				}
			}
			map.put(count, element_i);
		}
		return map.descendingMap().firstEntry().getValue();
	}

	public static int median(int[] elements) {
		List<Integer> array = new ArrayList<>();
		for (int element: elements){
			array.add(element);
		}
		Collections.sort(array);
		if (array.size() % 2 == 0) {
			int first_element = array.get(array.size()/2 - 1);
			int second_element = array.get(array.size()/2 );
			return ((first_element + second_element)/2);
		} else {
			return array.get(array.size()/2);
		}
	}

}