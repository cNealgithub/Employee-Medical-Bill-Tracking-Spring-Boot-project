package com.CMPDI.CPRMSE.NE.poratl.util;
/*
 * import org.springframework.security.core.Authentication; import
 * org.springframework.security.web.authentication.AuthenticationSuccessHandler;
 * 
 * import jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse; import
 * jakarta.servlet.http.HttpSession;
 * 
 * import java.io.IOException;
 * 
 * public class CustomAuthenticationSuccessHandler implements
 * AuthenticationSuccessHandler {
 * 
 * @Override public void onAuthenticationSuccess(HttpServletRequest request,
 * HttpServletResponse response, Authentication authentication) throws
 * IOException { // Assuming employeeCode is the username String employeeCode =
 * authentication.getName();
 * 
 * // Store employee code in session HttpSession session = request.getSession();
 * session.setAttribute("employeeCode", employeeCode);
 * 
 * // Redirect to values page response.sendRedirect("/values"); } }
 */


/*
 * import org.springframework.security.core.Authentication; import
 * org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.web.authentication.AuthenticationSuccessHandler;
 * import jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse; import
 * jakarta.servlet.http.HttpSession; import java.io.IOException; import
 * java.util.Collection;
 * 
 * public class CustomAuthenticationSuccessHandler implements
 * AuthenticationSuccessHandler {
 * 
 * @Override public void onAuthenticationSuccess(HttpServletRequest request,
 * HttpServletResponse response, Authentication authentication) throws
 * IOException { // Assuming employeeCode is the username String employeeCode =
 * authentication.getName();
 * 
 * // Store employee code in session HttpSession session = request.getSession();
 * session.setAttribute("employeeCode", employeeCode);
 * 
 * // Check the role and redirect accordingly Collection<? extends
 * GrantedAuthority> authorities = authentication.getAuthorities(); for
 * (GrantedAuthority authority : authorities) { if
 * (authority.getAuthority().equals("ROLE_ADMIN")) {
 * response.sendRedirect("/admin"); return; } else if
 * (authority.getAuthority().equals("ROLE_USER")) {
 * response.sendRedirect("/values"); return; } }
 * 
 * // Default redirect response.sendRedirect("/"); } }
 */

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Assuming employeeCode is the username
        String employeeCode = authentication.getName();
        
        // Store employee code in session
        HttpSession session = request.getSession();
        session.setAttribute("employeeCode", employeeCode);
        
        // Check the role and redirect accordingly
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin");
                return;
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                response.sendRedirect("/values");
                return;
            }
        }
        
        // Default redirect
        response.sendRedirect("/");
    }
}
