package seus.testes.de.aceitacao.aqui;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Test;

import classes.utilitarias.para.testes.de.aceitacao.JettyServer;

public class EfetuacaoDoPagamentoTest implements JettyServer.TesteAceitacao {

	private HttpClient client;
	
	@Before
	public void setUp() {
		client = new DefaultHttpClient();
	}

	@Test
	public void requisicaoParaSuaPropriaAPP() throws HttpException, IOException, InterruptedException {
		String uri = SERVER.urlFor("/alguma/path");
		HttpGet get = new HttpGet(uri);

		HttpResponse response = client.execute(get);
		
		assertEquals("1b: Get para URI que foi retornado", 200, response.getStatusLine().getStatusCode());
	}

}
