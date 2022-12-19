package moviePackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {
	
	List<List<String>> list = new ArrayList<>();
	
	public ReadCSV(String fileName) {
		
		
		BufferedReader bufferedReader = null;
		
		try {
			bufferedReader = Files.newBufferedReader(Paths.get(fileName));
			String line = "";
			
			while((line = bufferedReader.readLine())!=null) {
				List<String> tempList = new ArrayList<String>();
				String arr[] = line.split(",");
				tempList = Arrays.asList(arr);
				list.add(tempList);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(bufferedReader != null) {
					bufferedReader.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<List<String>> getList() {
		return list;
	}

	public static void main(String[] args) {
		new ReadCSV(null);
	}

}
