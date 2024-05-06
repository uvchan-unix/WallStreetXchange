import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catalyst.advanced.CatalystAdvancedIOHandler;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main implements CatalystAdvancedIOHandler {
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	@Override
	public void runner(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String url = "https://5d86-2401-4900-230a-83f4-1955-c6b3-105e-a871.ngrok-free.app/WallStreetXchange/webhook/login";
			String method = request.getMethod();

			switch (request.getRequestURI()) {
				case "/": {
					String name = request.getParameter("name");
					LOGGER.log(Level.INFO, "Hello " + name);
					response.setStatus(200);
					response.getWriter().write("Hello from Main.java");
					break;
				}
				case "/login": {
					if (method.equals("POST")) { // This should probably be POST if you're expecting to receive data.
						BufferedReader reader = new BufferedReader(request.getReader());
						String line;
						StringBuilder payload = new StringBuilder();
						while ((line = reader.readLine()) != null) {
							payload.append(line);
						}

						OkHttpClient client = new OkHttpClient();
						RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
								payload.toString());
						Request requestObj = new Request.Builder()
								.url(url)
								.post(requestBody)
								.build();

						try (Response wallStreetRes = client.newCall(requestObj).execute()) {
							String res = wallStreetRes.body().string();
							PrintWriter writer = response.getWriter();
							writer.write(res);
						} catch (Exception e) {
							LOGGER.log(Level.SEVERE, "Error calling external service", e);
							response.setStatus(500);
							response.getWriter().write("Failed to process login");
						}
					}
					break;
				}

				default: {
					response.setStatus(404);
					response.getWriter().write("You might find the page you are looking for at \"/\" path");
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception in Main", e);
			response.setStatus(500);
			response.getWriter().write("Internal server error");
		}
	}

}