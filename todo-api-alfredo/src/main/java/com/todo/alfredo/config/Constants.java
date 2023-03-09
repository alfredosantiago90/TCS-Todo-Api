package com.todo.alfredo.config;

public class Constants {
	 // Constantes para Spring Security

   public static final String LOGIN_URL = "/login";
   public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
   public static final String TOKEN_BEARER_PREFIX = "Bearer ";
   public static final String SWAGGER_IU = "/swagger-ui/index.html";

   // Constantes para JWT

   public static final String ISSUER_INFO = "https://alfredo.io/";
   public static final String SUPER_SECRET_KEY = "security-api";
   public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
}

