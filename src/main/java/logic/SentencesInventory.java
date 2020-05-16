package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SentencesInventory {

	private List<String> sentences;

	public SentencesInventory(String path, boolean shuffle) throws IOException {
		// TODO Auto-generated constructor stubimport java.net.URL;
//		String absolutPath = this.getClass().getClassLoader().
//				getResource(path).getPath();
//		this.sentences = Files.lines(Paths.get(absolutPath)).collect(Collectors.toList());
		this.sentences = new ArrayList<String>();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line = reader.readLine();
		try {
		    while (line != null) {
		        this.sentences.add(line);
		        line = reader.readLine();
		    }  
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		if (shuffle) {
			Collections.shuffle(sentences);
		}
		sentences = Collections.unmodifiableList(sentences);

	}

	public int size() {
		return sentences.size();
	}

	public String get(int index) {
		// TODO Auto-generated method stub
		return sentences.get(index);
	}

}
