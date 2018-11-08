package classes;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Classe permettant de convertir des tables html en CSV
 * @author mathi
 *
 */

public class Donnee_Html extends Donnee{
	/**
	 * Le HTML de la page wikip�dia
	 */
	private String html;

	public Donnee_Html(String html) {
		this.html = html;
	}
	
	public void extraire(String langue, String titre) throws IOException {
		String html ="";
		URL page = new URL("https://"+langue+".wikipedia.org/wiki/"+titre+"?action=render");
			html = recupContenu(page);
		
		Donnee_Html donneeHTML = new Donnee_Html(html);
		donneeHTML.htmlToCSV(html,"C:\\Users\\mathi\\Documents\\html.csv");
	}
	
	public static String recupContenu(URL url) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		String inputLine;

		while ((inputLine = in.readLine()) != null)
			result.append(inputLine);

		in.close();
		return result.toString();
	}
	
	/**
	 * M�thode qui parcoure les tables du HTML et les convertit en CSV
	 * @param html
	 * @throws IOException 
	 */
	public void htmlToCSV(String html, String path) {
		try {
			FileWriter writer = new FileWriter(path);
			Document page = Jsoup.parseBodyFragment(html);
			Elements lignes = page.getElementsByTag("tr");
			
			for (Element ligne : lignes) {
				Elements cellules = ligne.getElementsByTag("td");
				for (Element cellule : cellules) {
					writer.write(cellule.text().concat("; "));
				}
				writer.write("\n");
			}
			writer.close();
		}
		catch (IOException e) {
			e.getStackTrace();
		}
		
	}
}