import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dominos {

	static List<Integer> pizzaOrders = new ArrayList<Integer>();
	static Integer[] maxPizzaOrderNType;
	static Integer[] pizzaSlices;
	static List<Integer> selectedPizzaSlices = new ArrayList<Integer>();

	public static void ConvertFileToInt(String document) {

		try (BufferedReader br = new BufferedReader(new FileReader(document))) {
			String line;
			int counter = 0;

			while ((line = br.readLine()) != null) {
				String[] file = line.split(" ");
				counter++;

				if (counter <= 1) {
					maxPizzaOrderNType = new Integer[file.length];
					for (int i = 0; i < file.length; i++) {
						maxPizzaOrderNType[i] = Integer.parseInt(file[i]);
						System.out.println(maxPizzaOrderNType[i]);
					}
				} else {
					pizzaSlices = new Integer[file.length];
					for (int i = 0; i < file.length; i++) {
						pizzaSlices[i] = Integer.parseInt(file[i]);

					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public Integer[] OrderPizza(String document) {
		ConvertFileToInt(document);

		Integer sortedPizzaOrders[];
		int maxPizzaOrder = maxPizzaOrderNType[0];
		int maxPizzaType = maxPizzaOrderNType[1];

		int result = pizzaSlices[maxPizzaType - 1];
		int newResult = 0;
		int stopInd = 0;
		selectedPizzaSlices.add(result);

		// Storing pizza types starting from 0
		pizzaOrders.add(maxPizzaType - 1);

		for (int i = maxPizzaType - 2; i >= 0; i--) {
			result = result + pizzaSlices[i];
			pizzaOrders.add(i);
			int newInd = i - 1;

			if ((result > maxPizzaOrder) == true) {
				result = result - pizzaSlices[i];
				pizzaOrders.remove((Integer) i);

				if (newResult == 0) {
					newResult = result;
					stopInd = i;
				}
			}

			if (newResult != 0) {
				if (newInd <= 0) {
					newInd = 0;
					continue;
				}
				newResult = newResult + pizzaSlices[newInd];

				if (newResult > result && newResult <= maxPizzaOrder) {
					result = newResult;
					for (int k = stopInd; k <= pizzaOrders.size() - stopInd; k++) {
						pizzaOrders.remove(k);
					}
					pizzaOrders.add(newInd);
				}
			}
		}

		sortedPizzaOrders = new Integer[pizzaOrders.size()];
		sortedPizzaOrders = pizzaOrders.toArray(sortedPizzaOrders);

		CountingSort sortObj = new CountingSort();

		sortObj.CountSort(sortedPizzaOrders);

		return sortedPizzaOrders;
	}
}
