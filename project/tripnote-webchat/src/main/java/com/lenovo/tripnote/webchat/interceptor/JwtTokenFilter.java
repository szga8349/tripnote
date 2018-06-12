package com.lenovo.tripnote.webchat.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import net.sf.json.JSONObject;

import org.springframework.web.filter.GenericFilterBean;

import com.lenovo.tripnote.webchat.vo.Result;
import com.lenovo.tripnote.webchat.vo.TokenVo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class JwtTokenFilter extends GenericFilterBean {

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        // Change the req and res to HttpServletRequest and HttpServletResponse
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        // Get authorization from Http request
        final String authHeader = request.getHeader("authorization");
        // If the Http request is OPTIONS then just return the status code 200
        // which is HttpServletResponse.SC_OK in this code
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(req, res);
        }
        // Except OPTIONS, other request should be checked by JWT
        else {

            // Check the authorization, check if the token is started by "Bearer "
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            	JSONObject result = new JSONObject();
      			response.setHeader("Content-type", "application/json;charset=UTF-8");
      			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      			OutputStream ps = response.getOutputStream();
      			 //throw new ServletException("Token Expire");
     			// 这句话的意思，使得放入流的数据是utf8格式
     			result.put("code", "-1");
            	result.put("message","Missing or invalid Authorization header");//throw new ServletException("");
     			ps.write(result.toString().getBytes("UTF-8"));
     			ps.close();
                return ;
            }

            // Then get the JWT token from authorization
            final String token = authHeader.substring(7);

            try {
                // Use JWT parser to check if the signature is valid with the Key "secretkey"
                final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
                
                TokenVo  vo = new TokenVo();
                vo.setUserId((Integer)claims.get(Result.USERID));
                vo.setUserLoginName((String)claims.get(Result.USER_LOGINNAME));
                vo.setLoginTime(claims.getIssuedAt());
                // Add the claim to request header
                request.setAttribute(Result.TOKEN, vo);
            } catch (final Exception e) {
            	JSONObject result = new JSONObject();
      			response.setHeader("Content-type", "application/json;charset=UTF-8");
      			OutputStream ps = response.getOutputStream();
      			 //throw new ServletException("Token Expire");
     			// 这句话的意思，使得放入流的数据是utf8格式
     			result.put("code", "-1");
     			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            	if(e instanceof ExpiredJwtException){
         			result.put("message","Token Expire");
            	}else if(e instanceof SignatureException){
            		result.put("message","Invalid token");//throw new ServletException("");
            	}
     			ps.write(result.toString().getBytes("UTF-8"));
     			ps.close();
     			return ;
            }
            chain.doFilter(req, res);
        }
    }
}