package br.com.ntk.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
 * Executa requisicao HTTP
 * 
 * @author Edurdo Wallace
 * @author NTK Solutions
 */
public class HttpUtil {
	
	/**
	 * Executa requisicao HTTP
	 * 
	 * @param httpType - tipo da requisicao
	 * @param url - a ser requisitada
	 * @param timeout
	 * @return response da URL requisitada
	 * @throws SocketTimeoutException em caso de timeout
	 * @throws Exception em caso de erro
	 */
	public static String execute(HttpType httpType,String url, int timeout) 
			throws SocketTimeoutException, Exception {
		return execute(httpType, url, new HashMap<String, String>(), 0);
	}
	
	/**
	 * Executa requisicao HTTP
	 * 
	 * @param httpType - tipo da requisicao
	 * @param url - a ser requisitada
	 * @return response da URL requisitada
	 * @throws SocketTimeoutException em caso de timeout
	 * @throws Exception em caso de erro
	 */
	public static String execute(HttpType httpType,String url) 
			throws SocketTimeoutException, Exception {
		return execute(httpType, url, new HashMap<String, String>(), 0);
	}
	
	/**
	 * Executa requisicao HTTP
	 * 
	 * @param httpType - tipo da requisicao
	 * @param url - a ser requisitada
	 * @param parametros
	 * @param timeout - duracao da requisicao
	 * @return response da URL requisitada
	 * @throws SocketTimeoutException em caso de timeout
	 * @throws Exception em caso de erro
	 */
	public static String execute(HttpType httpType,String url,  Map<String, String> parametros, 
				int timeout) throws SocketTimeoutException, Exception {
		
		Builder config = RequestConfig.custom();
		
		if (timeout > 0) {
			System.out.println("timeout: " + timeout);
			config.setSocketTimeout(timeout);
		}
		
		RequestConfig requestConfig = config.build();
		CloseableHttpClient cliente = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		StringBuffer retorno = null;
		HttpRequestBase requestBase = null;
		
		try {
			
		
			switch (httpType) {
				case GET :
					HttpGet httpGet = new HttpGet(url);
					requestBase = httpGet;
					break;
				
				case POST :
					HttpPost httpPost = new HttpPost(url);
					List <NameValuePair> nvps = new ArrayList <NameValuePair>();
					for (Map.Entry<String, String> e : parametros.entrySet()) {
						nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
					}
					httpPost.setEntity(new UrlEncodedFormEntity(nvps));
					requestBase = httpPost;
					break;
				case DELETE:
					break;
				case PUT:
					break;
			}

			System.out.println("Requisicao: " + requestBase.getRequestLine());
			
			//executa requisicao
			long start = System.currentTimeMillis();
			response = cliente.execute(requestBase); 
			long end = System.currentTimeMillis();
		
			System.out.println("Tempo: " + (end - start));
			
			entity = response.getEntity();
			
			//verifica se a requisicao foi efetuada com sucesso
	 		if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {	
	 			retorno = new StringBuffer();	 			
	 			String linha = null;
	 			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), "ISO-8859-1")); 	
	 			while((linha = br.readLine()) != null ) {
	 				retorno.append(linha);
	 			}
	 			br.close();
	 			
	 		} else { 
	 			System.out.println("Nao foi possivel realizar a comunicacao com o Adquirente " + response.getStatusLine().toString());
	 			throw new Exception("Nao foi possivel realizar a comunicacao com o Adquirente");
	 		}
	 		
	 		System.out.println(retorno.toString());
	 		
		} catch (SocketTimeoutException e) {
			System.out.println(e.getCause() +  " " + e.getMessage());
			throw e;
		}  finally {
			cliente.close();
		}
		response.close();
		entity.getContent().close();
		return retorno.toString(); 
	}
	
}