import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    // This tells our app that if Heroku sets a port for us, we need to use that port.
    // Otherwise, if they do not, continue using port 4567.

    ProcessBuilder process = new ProcessBuilder();
     Integer port;
     if (process.environment().get("PORT") != null) {
         port = Integer.parseInt(process.environment().get("PORT"));
     } else {
         port = 4567;
     }

    setPort(port);

    // index route
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      // model.put("clients", request.session().attribute("clients"));
      //model.put("categ", Category.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    }
  }