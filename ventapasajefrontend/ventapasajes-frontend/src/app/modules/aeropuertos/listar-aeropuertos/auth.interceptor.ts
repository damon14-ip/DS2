import { HttpInterceptorFn } from '@angular/common/http';

/**
 * Intercepts outgoing HTTP requests to add the Authorization header with the JWT token.
 * This avoids having to add the header manually to every single API call.
 */
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Retrieve the token from localStorage.
  const token = localStorage.getItem('token');

  // If a token exists, clone the request and add the Authorization header.
  if (token) {
    const cloned = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`),
    });
    return next(cloned);
  }

  // If there's no token, pass the original request along.
  return next(req);
};