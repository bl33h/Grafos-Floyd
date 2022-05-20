import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import org.junit.Test;

public class FloydTest{
		Waze waze = new Waze();
		@Test
		public void test() throws FileNotFoundException {
			waze.read();
			waze.Floyd();
			int [][]wm=waze.getWM();
			String [][]dm=waze.getdM();
			assertEquals(wm[0][0], 0);
			assertEquals(wm[0][1], 25);
			assertEquals(wm[0][2], 40);
			assertEquals(wm[0][3], 30);
			assertEquals(wm[0][4], 40);
			assertEquals(wm[1][0], 25);
			assertEquals(wm[1][1], 0);
			assertEquals(wm[1][2], 65);
			assertEquals(wm[1][3], 55);
			assertEquals(wm[1][4], 15);
			assertEquals(wm[2][0], 40);
			assertEquals(wm[2][1], 65);
			assertEquals(wm[2][2], 0);
			assertEquals(wm[2][3], 15);
			assertEquals(wm[2][4], 80);
			assertEquals(wm[3][0], 30);
			assertEquals(wm[3][1], 55);
			assertEquals(wm[3][2], 15);
			assertEquals(wm[3][3], 0);
			assertEquals(wm[3][4], 70);
			assertEquals(wm[4][0], 40);
			assertEquals(wm[4][1], 15);
			assertEquals(wm[4][2], 80);
			assertEquals(wm[4][3], 70);
			assertEquals(wm[4][4], 0);
			// --------------------------------------------------
			assertEquals(dm[0][0].charAt(0), '0');
			assertEquals(dm[0][1].charAt(0), 'E');
			assertEquals(dm[0][2].charAt(0), 'G');
			assertEquals(dm[0][3].charAt(0), 'M');
			assertEquals(dm[0][4].charAt(0), 'E');
			assertEquals(dm[1][0].charAt(0), 'A');
			assertEquals(dm[1][1].charAt(0), '0');
			assertEquals(dm[1][2].charAt(0), 'A');
			assertEquals(dm[1][3].charAt(0), 'A');
			assertEquals(dm[1][4].charAt(0), 'S');
			assertEquals(dm[2][0].charAt(0), 'A');
			assertEquals(dm[2][1].charAt(0), 'A');
			assertEquals(dm[2][2].charAt(0), '0');
			assertEquals(dm[2][3].charAt(0), 'M');
			assertEquals(dm[2][4].charAt(0), 'E');
			assertEquals(dm[3][0].charAt(0), 'A');
			assertEquals(dm[3][1].charAt(0), 'A');
			assertEquals(dm[3][2].charAt(0), 'G');
			assertEquals(dm[3][3].charAt(0), '0');
			assertEquals(dm[3][4].charAt(0), 'E');
			assertEquals(dm[4][0].charAt(0), 'E');
			assertEquals(dm[4][1].charAt(0), 'E');
			assertEquals(dm[4][2].charAt(0), 'E');
			assertEquals(dm[4][3].charAt(0), 'E');
			assertEquals(dm[4][4].charAt(0), '0');
		}
    }