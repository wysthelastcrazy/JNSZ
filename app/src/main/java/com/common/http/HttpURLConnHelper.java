package com.common.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.DCHZ.TYLINCN.util.MyLog;

public class HttpURLConnHelper {
	/**
	 * 作用：实现网络访问文件，将获取到数据储存在文件流中
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @return inputstream
	 */
	public static InputStream loadFileFromURL(String urlString) {
		BufferedInputStream bis = null;
		HttpURLConnection httpConn = null;
		try {
			// 创建url对象
			URL urlObj = new URL(urlString);
			try{
				trustAllHosts();
				HttpsURLConnection https = (HttpsURLConnection)urlObj.openConnection();
	            if (urlObj.getProtocol().toLowerCase().equals("https")) {
	                https.setHostnameVerifier(DO_NOT_VERIFY);
	                httpConn = https;
	            } else {
	            	httpConn = (HttpURLConnection)urlObj.openConnection();
	            }
	            httpConn.setDoInput(true);
				httpConn.setRequestMethod("GET");
				httpConn.setConnectTimeout(5000);
				httpConn.connect();

				// 判断跟服务器的连接状态。如果是200，则说明连接正常，服务器有响应
				if (httpConn.getResponseCode() == 200) {
					// 服务器有响应后，会将访问的url页面中的内容放进inputStream中，使用httpConn就可以获取到这个字节流
					bis = new BufferedInputStream(httpConn.getInputStream());
					return bis;
				}
			}catch(IOException e){
				 e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 对流对象进行关闭，对Http连接对象进行关闭。以便释放资源。
				bis.close();
				httpConn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
		 
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    private static void trustAllHosts() {
        final String TAG = "trustAllHosts";
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
     
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
     
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
     
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        } };
     
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
	
	
	/**
	 * 作用：实现网络访问文件，将获取到的数据存在字节数组中
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @return byte[]
	 */
	public static byte[] loadByteFromURL(String url) {
		HttpURLConnection httpConn = null;
		BufferedInputStream bis = null;
		
		try {
			// 创建url对象
			URL urlObj = new URL(url);
			try{
				trustAllHosts();
	            if (urlObj.getProtocol().toLowerCase().equals("https")) {
	            	HttpsURLConnection https = (HttpsURLConnection)urlObj.openConnection();
	                https.setHostnameVerifier(DO_NOT_VERIFY);
//	                https.setDefaultHostnameVerifier(DO_NOT_VERIFY);
	                httpConn = https;
	            } else {
	            	httpConn = (HttpURLConnection)urlObj.openConnection();
	            }
	            httpConn.setDoInput(true);
				httpConn.setRequestMethod("GET");
				httpConn.setConnectTimeout(5000);
				httpConn.connect();
			if (httpConn.getResponseCode() == 200) {
				bis = new BufferedInputStream(httpConn.getInputStream());
				return streamToByte(bis);
			}
			}catch(IOException e){
				 e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				httpConn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 作用：实现网络访问文件，将获取到的数据保存在指定目录中
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @return byte[]
	 */
	public static boolean saveFileFromURL(String url, File destFile) {
		HttpURLConnection httpConn = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(destFile));
			URL urlObj = new URL(url);
			httpConn = (HttpURLConnection) urlObj.openConnection();
			httpConn.setRequestMethod("GET");
			httpConn.setDoInput(true);
			httpConn.setConnectTimeout(5000);
			httpConn.connect();

			if (httpConn.getResponseCode() == 200) {
				bis = new BufferedInputStream(httpConn.getInputStream());
				int c = 0;
				byte[] buffer = new byte[8 * 1024];
				while ((c = bis.read(buffer)) != -1) {
					bos.write(buffer, 0, c);
					bos.flush();
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
				httpConn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 作用：实现网络访问文件，先给服务器通过“POST”方式提交数据，再返回相应的数据
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @param params
	 *            ：访问url时，需要传递给服务器的参数。格式为：username=wangxiangjun&password=abcde&
	 *            qq=32432432
	 *            为了防止传中文参数时出现编码问题。采用URLEncoder.encode()对含中文的字符串进行编码处理。
	 *            服务器端会自动对进行过编码的字符串进行decode()解码。
	 * @return byte[]
	 */
	public static byte[] doPostSubmit(String url, String params) {
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpConn = null;
		try {
			URL urlObj = new URL(url);
			httpConn = (HttpURLConnection) urlObj.openConnection();

			// 如果通过post方式给服务器传递数据，那么setDoOutput()必须设置为true。否则会异常。
			// 默认情况下setDoOutput()为false。
			// 其实也应该设置setDoInput()，但是因为setDoInput()默认为true。所以不一定要写。

			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setRequestMethod("POST");
			httpConn.setConnectTimeout(5 * 1000);
			// 设置请求方式。请求方式有两种：POST/GET。注意要全大写。
			// POST传递数据量大，数据更安全，地址栏中不会显示传输数据。
			// 而GET会将传输的数据暴露在地址栏中，传输的数据量大小有限制，相对POST不够安全。但是GET操作灵活简便。

			// 判断是否要往服务器传递参数。如果不传递参数，那么就没有必要使用输出流了。
			if (params != null) {
				byte[] data = params.getBytes();
				bos = new BufferedOutputStream(httpConn.getOutputStream());
				bos.write(data);
				bos.flush();
			}
			// 判断访问网络的连接状态
			MyLog.debug("000", "[doPostSubmit] code:"+httpConn.getResponseCode());
			if (httpConn.getResponseCode() == 200) {
				bis = new BufferedInputStream(httpConn.getInputStream());
				// 将获取到的输入流转成字节数组
				return streamToByte(bis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
				httpConn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	public static byte[] streamToByte(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int c = 0;
		byte[] buffer = new byte[8 * 1024];
		try {
			while ((c = is.read(buffer)) != -1) {
				baos.write(buffer, 0, c);
				baos.flush();
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
