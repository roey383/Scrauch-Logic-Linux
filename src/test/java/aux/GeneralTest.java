package aux;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneralTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(RandomStringUtils.randomAlphanumeric(5).toUpperCase());
		
		Map<String, Double> priceMap = new HashMap<String, Double>();
		priceMap.put("Effective Java", 41.79);
		priceMap.put("Head First Java", 29.02);
		priceMap.put("Java Concurrency In Practice", 30.67);
		priceMap.put("Java SE 8 for Really Impatient", 31.99);
		priceMap.put("Head First Design Pattern", 39.05);
		Set<Entry<String, Double>> setOfEntries = priceMap.entrySet();
		Iterator<Entry<String, Double>> iterator = setOfEntries.iterator();
		while (iterator.hasNext()) {
			Entry<String, Double> entry = iterator.next();
			Double value = entry.getValue();
			if (value.compareTo(Double.valueOf(39.00)) > 0) {
				System.out.println("removeing : " + entry);
				//priceMap.remove(entry.getKey());
				//priceMap.remove(entry.getKey(), entry.getValue());
				iterator.remove(); // always use remove() method of iterator
			}
		}
		
	}

}
