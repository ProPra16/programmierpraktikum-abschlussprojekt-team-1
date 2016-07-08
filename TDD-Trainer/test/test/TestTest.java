package test;

	import static org.junit.Assert.*;
	import org.junit.*;
	
	public class TestTest {
		@Test
		public void testeTestklasse1(){
			assertEquals("Banane",io.Testklasse.test());
		}
	
	@Test
	public void testeTestklasse2(){
		assertEquals("foo", io.Testklasse.bar());
	}
	
	@Test
	public void testeTestklasse3(){
		assertEquals("bar", io.Testklasse.foo());
	}
	
	@Test
	public void failingTestTestklasse(){
		assertEquals("foo", io.Testklasse.bar());
	}
}
