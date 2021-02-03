package testcases;

import static java.util.stream.Collectors.toMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class MaxConsecutiveRepeatChar {

	@Test
	public void MaxConsecutiveRepeatChar() {

		String word = "aabbaabbbbbaaaabbbbecccaccccgggnggggngggnnnngggggggeegggggggggge";

		char[] arr = word.toCharArray();
		int count = 1;

		Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < word.length(); i++) {

			if (i + 1 < word.length()) {

				if (arr[i] == arr[i + 1]) {

					System.out.println(arr[i] + " " + arr[i + 1]);
					count++;
					System.out.println(arr[i] + " in if Count :" + (count));
					if (map.containsKey(arr[i])) {
						int charCount = map.get(arr[i]);
						System.out.println(charCount + "----" + count);
						if (count >= charCount)
							map.put(arr[i], count);
					} else {
						map.put(arr[i], count);
					}

				} else {

					System.out.println(arr[i] + " " + arr[i + 1]);
					System.out.println(arr[i] + " Count " + (count));

					count = 1;
					

					System.out.println(map);
					if (!map.containsKey(arr[i+1])) 
							map.put(arr[i+1], count);

				}

			}

		}

		System.out.println(map);
		
		// order
		map = map.entrySet().stream()
		.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) 
		.collect(toMap(Map.Entry::getKey, 
					   Map.Entry::getValue, 
					   (character, Count) -> Count, LinkedHashMap::new));

		
	System.out.println("Sorted map in Descending order "+map);	
		
		
		
		
		
		
		
		

	}

}
