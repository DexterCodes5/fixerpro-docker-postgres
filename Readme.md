I integrated Spring Security, Spring Session with Redis,
OAuth2 Login, Google reCaptcha v3, Docker for PostgreSQL and
Redis...

To run the project you have to fill in the
application.properties where the values are named 
YOUR_CLIENT_ID, YOUR_SECRET_HERE..., in the MailService class
you have to fill in YOUR_EMAIL_HERE, in templates checkout.html
and login.html need YOUR_SITE_KEY for reCaptcha, in static/js
the files /login/login.js, /login.sign-up.js and checkout.js 
need YOUR_SITE_KEY for their calls to the reCaptcha API.