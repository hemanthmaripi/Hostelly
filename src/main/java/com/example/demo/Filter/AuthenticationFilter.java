package com.example.demo.Filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Entity.User;
import com.example.demo.Service.JWTService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/api/*" })
public class AuthenticationFilter implements Filter {

	@Autowired
	private JWTService jwtService;

	private static final List<String> UNAUTHENTICATED_PATHS = List.of("/api/auth/signup", "/api/auth/login",
			"/api/auth/send-otp", "/api/auth/verify-otp", "/api/auth/forgot-password", "/api/auth/hosteller/login");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		System.out.println("Checking the Request");
		// Set CORS headers
		setCorsHeaders(httpResponse);

		// Handle preflight OPTIONS requests
		if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
			httpResponse.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		// Log the incoming request
		System.out.println("Incoming Request: " + httpRequest.getMethod() + " " + httpRequest.getRequestURI());

		// Skip authentication for unauthenticated paths
		String url = httpRequest.getRequestURI();

		if (UNAUTHENTICATED_PATHS.contains(url)) {
			chain.doFilter(request, response); // Pass the request without authentication
			return;
		}

		try {

			// Extract Token and Validate the User
			User authenticatedUser = executeFilterLogic(httpRequest, httpResponse, chain);

			if (authenticatedUser == null) {
				sendErrorResponse(httpResponse, HttpServletResponse.SC_UNAUTHORIZED,
						"Unauthorized: User not found Login again");
				return;
			}

			if (url.startsWith("api/admin/") && authenticatedUser.getRole() != "admin") {
				sendErrorResponse(httpResponse, HttpServletResponse.SC_FORBIDDEN, "Forbidden: Admin access required");
				return;
			}

			// Attach user details to request
			request.setAttribute("authenticatedUser", authenticatedUser);
			chain.doFilter(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			sendErrorResponse(httpResponse, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}

	// Extracts and validates the token and returns the User
	private User executeFilterLogic(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String token = getAuthTokenFromCookies(request);
		System.out.println("Extracted Token: " + token);

		if (token != null) {
			User authenticatedUser = (User) jwtService.validateToken(token);

			System.out.println("User Details { Name: " + authenticatedUser.getName() + "  Email: "
					+ authenticatedUser.getEmail() + "Role: " + authenticatedUser.getRole() + "Hostel: " + authenticatedUser.getHostel().getName());
			
			System.out.println(authenticatedUser.getName());
			return authenticatedUser;
		} else {
			return null;
		}

	}

	// Sets CORS Headers
	private void setCorsHeaders(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "3600");
	}

	// Gets Authentication token from the client cookies
	private String getAuthTokenFromCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			System.err.println("No Cookie found");
			return null;
		}

		return Arrays.stream(cookies)
				.peek(cookie -> System.out.println("Cookie: {}={}" + cookie.getName() + cookie.getValue()))
				.filter(cookie -> "authToken".equals(cookie.getName())).map(Cookie::getValue).findFirst().orElse(null);
	}

	// Sends error response to client
	private void sendErrorResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
		response.setStatus(statusCode);
		response.getWriter().write(message);
	}

}
