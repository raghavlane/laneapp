package War;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class MainClass {

	public static CallObject read(String inputFile) throws IOException {
		private static List<String> mlist = new ArrayList<String>();
		private static List<String> olist = new ArrayList<String>();
		private static List<String> ilist = new ArrayList<String>();
		CallObject callObject = new CallObject();
		File inputWorkbook = new File(inputFile);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);
			for (int j = 1; j < sheet.getRows(); j++) {
				Cell cell = sheet.getCell(0, j);
				Cell cell2 = sheet.getCell(1, j);
				Cell cell3 = sheet.getCell(5, j);
				String cellcont = cell.getContents();
				String cell2cont = cell2.getContents();
				String cell3cont = cell3.getContents();
				if (!(cell2cont.length() > 0)) {
					if (cell3cont.equalsIgnoreCase("incoming")) {
						ilist.add(cellcont);
					}
					if (cell3cont.equalsIgnoreCase("outgoing")) {
						olist.add(cellcont);
					}
					if (cell3cont.equalsIgnoreCase("missed")) {
						mlist.add(cellcont);
					}
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		}
		callObject.setIlist(ilist);
		callObject.setMlist(mlist);
		callObject.setOlist(olist);
		return callObject;
	}
	public static String getMessage(){
		
		return null;
	}

	public static Set<String> comparecalls(CallObject callObject1,
			CallObject callObject2) {
		LinkedHashMap<String, String> consolidated = new LinkedHashMap<String, String>();
		List<String> m1 = callObject1.getMlist();
		List<String> m2 = callObject2.getMlist();
		List<String> i1 = callObject1.getIlist();
		List<String> i2 = callObject2.getIlist();
		List<String> o1 = callObject1.getOlist();
		List<String> o2 = callObject2.getOlist();
		for (String number : m1) {
			if (!(i1.contains(number) || i2.contains(number)
					|| o1.contains(number) || o2.contains(number))) {
				consolidated.put(number, "");
			}
		}
		for (String number : m2) {
			if (!(i1.contains(number) || i2.contains(number)
					|| o1.contains(number) || o2.contains(number))) {
				consolidated.put(number, "");
			}
		}
		return consolidated.keySet();
	}
	
	public static Set<String> dialledAndRecieved(CallObject callObject1,
			CallObject callObject2) {
		LinkedHashMap<String, String> consolidated = new LinkedHashMap<String, String>();
		List<String> m1 = callObject1.getMlist();
		List<String> m2 = callObject2.getMlist();
		List<String> i1 = callObject1.getIlist();
		List<String> i2 = callObject2.getIlist();
		List<String> o1 = callObject1.getOlist();
		List<String> o2 = callObject2.getOlist();
		for (String number : i1) {
				consolidated.put(number, "");
		}
		for (String number : o1) {
			consolidated.put(number, "");
		}
		for (String number : i2) {
			consolidated.put(number, "");
		}
		for (String number : o2) {
			consolidated.put(number, "");
		}
		return consolidated.keySet();
	}
	
}
