package logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SentencesInventory {

	private List<String> sentences;

	public SentencesInventory(String path, boolean shuffle) throws IOException {
		// TODO Auto-generated constructor stubimport java.net.URL;
		String absolutPath = this.getClass().getClassLoader().
				getResource(path).getPath();
		this.sentences = Files.lines(Paths.get(absolutPath)).collect(Collectors.toList());
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
