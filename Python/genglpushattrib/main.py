import cgi


from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app

import glpushattrib


class MainPage(webapp.RequestHandler):
    def get(self):
        self.response.out.write("""
          <html>
            <body>
              <form action="/sign" method="post">
                <div><textarea name="content" rows="30" cols="80"></textarea></div>
                <div><input type="submit" value="Generate glPushAttrib"></div>
              </form>
            </body>
          </html>""")


class ResultPage(webapp.RequestHandler):
    def post(self):
        self.response.out.write('<html><body>You wrote:<pre>')
        self.response.out.write(cgi.escape(self.request.get('content')))
        dispString = glpushattrib.generateGLPushAttrib(cgi.escape(self.request.get('content')))
        self.response.out.write('</pre>Result:<pre>')
        self.response.out.write(dispString)
        self.response.out.write('</pre></body></html>')

application = webapp.WSGIApplication(
                                     [('/', MainPage),
                                      ('/sign', ResultPage)],
                                     debug=True)


def main():
    run_wsgi_app(application)

if __name__ == "__main__":
    main()
