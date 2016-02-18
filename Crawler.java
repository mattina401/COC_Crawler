import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

public class Crawler {
	public static void main(String[] args)throws Exception{
		long startTime = System.currentTimeMillis();
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		//TreeMap<String, Integer> hm = new TreeMap<String, Integer>();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("What word you want to search? ");
		String inputst = scanner.next();
		System.out.print("How many page? ");
		int inputpg = scanner.nextInt();
		
		String word = inputst;
		int page = inputpg;
		
		for (int j = 0; j <= page; j++) {
			
			Document doc = Jsoup.connect("http://www.cc.gatech.edu/search/node/" + word + "?page=" + j).get();
			Elements titles = doc.select("div.no-sidebars ol li h3");
			
			
			StringTokenizer st = new StringTokenizer(titles.text());
			
			int count = 1;
			
			while(st.hasMoreTokens()) {
				
				String key = st.nextToken().replaceAll("[^A-Za-z]","");
				
				if(hm.containsKey(key)) {
					count = hm.get(key) + 1;
				}
				
				hm.put(key, count);
				count = 1;
			}
		}
		
		
		hm.remove("");
		
		ArrayList as = new ArrayList(hm.entrySet());
        
        Collections.sort(as, new Comparator() {
            public int compare(Object o1, Object o2)
            {
                Map.Entry e1 = (Map.Entry)o1;
                Map.Entry e2 = (Map.Entry)o2;
                Integer first = (Integer)e1.getValue();
                Integer second = (Integer)e2.getValue();
                return second.compareTo(first);
            }
        });
         
        Iterator i = as.iterator();
        System.out.println("");
        System.out.println("Result:\n");
        while (i.hasNext())
        {
            System.out.println((Map.Entry)i.next());
        }
		
		scanner.close();
		long endTime = System.currentTimeMillis();
		System.out.println("");
		System.out.println("Took "+(endTime - startTime) + " milliseconds"); 
		
	}
}





