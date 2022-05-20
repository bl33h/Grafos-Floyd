import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class StreetTest {
	Waze waze = new Waze();
	
	@Test
	public void test() {
		
		//****************************************************
		
		waze.newStreet("Mixco", "Antigua", 30);
		waze.newStreet("Antigua", "Mixco", 30);
		
		waze.newStreet("Antigua", "Escuintla", 25);
		waze.newStreet("Escuintla", "Antigua", 25);
		
		waze.newStreet("Escuintla", "Santa-Lucia", 15);
		waze.newStreet("Santa-Lucia", "Escuintla", 15);
		
		waze.newStreet("Santa-Lucia", "Guatemala", 90);
		waze.newStreet("Guatemala", "Santa-Lucia", 90);
		
		waze.newStreet("Guatemala", "Mixco", 15);
		waze.newStreet("Mixco", "Guatemala", 15);
		
		waze.newStreet("Guatemala", "Antigua", 40);
		waze.newStreet("Antigua", "Guatemala", 40);
		
		waze.newStreet("Escuintla", "Guatemala", 70);
		waze.newStreet("Guatemala", "Escuintla", 70);
		
		//****************************************************
		
		waze.checkCities();
		ArrayList<String> c = waze.getCities();
		ArrayList<Street> r = waze.getRoutes();
		
		assertEquals(c.get(0), "Antigua");
		assertEquals(c.get(1), "Escuintla");
		assertEquals(c.get(2), "Guatemala");
		assertEquals(c.get(3), "Mixco");
		assertEquals(c.get(4), "Santa-Lucia");
		
		//****************************************************
		
		assertEquals(r.get(0).getOrigin(), "Mixco");
		assertEquals(r.get(0).getDestination(), "Antigua");
		assertEquals(r.get(0).getDistance(), 30);
		
		assertEquals(r.get(2).getOrigin(), "Antigua");
		assertEquals(r.get(2).getDestination(), "Escuintla");
		assertEquals(r.get(2).getDistance(), 25);
		
		assertEquals(r.get(4).getOrigin(), "Escuintla");
		assertEquals(r.get(4).getDestination(), "Santa-Lucia");
		assertEquals(r.get(4).getDistance(), 15);
		
		assertEquals(r.get(6).getOrigin(), "Santa-Lucia");
		assertEquals(r.get(6).getDestination(), "Guatemala");
		assertEquals(r.get(6).getDistance(), 90);
		
		assertEquals(r.get(8).getOrigin(), "Guatemala");
		assertEquals(r.get(8).getDestination(), "Mixco");
		assertEquals(r.get(8).getDistance(), 15);
		
		assertEquals(r.get(10).getOrigin(), "Guatemala");
		assertEquals(r.get(10).getDestination(), "Antigua");
		assertEquals(r.get(10).getDistance(), 40);
		
		assertEquals(r.get(12).getOrigin(), "Escuintla");
		assertEquals(r.get(12).getDestination(), "Guatemala");
		assertEquals(r.get(12).getDistance(), 70);
		
		//****************************************************
		
		assertEquals(c.size(), 5);
		assertEquals(r.size(), 14);
		
		//****************************************************
		
		waze.pauseStreet("Mixco", "Antigua");
		waze.pauseStreet("Antigua", "Escuintla");
		waze.pauseStreet("Escuintla", "Santa-Lucia");
		waze.pauseStreet("Santa-Lucia", "Guatemala");
		waze.pauseStreet("Guatemala", "Mixco");
		waze.pauseStreet("Guatemala", "Antigua");
		waze.pauseStreet("Escuintla", "Guatemala");
		
		//****************************************************
		
		assertEquals(r.get(0).getDistance(), 31416);
		assertEquals(r.get(2).getDistance(), 31416);
		assertEquals(r.get(4).getDistance(), 31416);
		assertEquals(r.get(6).getDistance(), 31416);
		assertEquals(r.get(8).getDistance(), 31416);
		assertEquals(r.get(10).getDistance(), 31416);
		assertEquals(r.get(12).getDistance(), 31416);

	}
}
